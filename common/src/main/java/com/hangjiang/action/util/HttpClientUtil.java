package com.hangjiang.action.util;

import com.google.common.collect.Lists;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.util.PublicSuffixMatcher;
import org.apache.http.conn.util.PublicSuffixMatcherLoader;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HttpProcessor;
import org.apache.http.protocol.HttpProcessorBuilder;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;

/**
 * Created by jianghang on 2017/6/5.
 */
public class HttpClientUtil {
    private RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(15000).setConnectTimeout(15000).setConnectionRequestTimeout(15000).build();

    private static SSLConnectionSocketFactory sslConnectionSocketFactory = null;
    private static PoolingHttpClientConnectionManager cm = null;
    private static SSLContextBuilder builder = null;

    static {
        try {
            builder = new SSLContextBuilder();
            builder.loadTrustMaterial(null, new TrustStrategy() {
                @Override
                public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                    return true;
                }
            });

            sslConnectionSocketFactory = new SSLConnectionSocketFactory(builder.build(),new String[]{"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.2"},null, NoopHostnameVerifier.INSTANCE);
            Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create().register("http",new PlainConnectionSocketFactory()).register("https",sslConnectionSocketFactory).build();
            cm = new PoolingHttpClientConnectionManager(registry);
            cm.setMaxTotal(200);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
    }

    private static HttpClientUtil instance = null;

    public static HttpClientUtil getInstance(){
        if(instance == null){
            instance = new HttpClientUtil();
        }
        return instance;
    }

    public String sendHttpGet(String httpUrl){
        HttpGet httpGet = new HttpGet(httpUrl);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse httpResponse = null;
        HttpEntity entity;
        String responseContent;

        httpGet.setConfig(requestConfig);
        try {
            httpResponse = httpClient.execute(httpGet);
            Integer statusCode = httpResponse.getStatusLine().getStatusCode();
            System.out.println("status: " + statusCode);

            Header[] headers = httpResponse.getHeaders("Set-Cookie");

            for (Header header : headers) {
                System.out.println(header.getName() + " : " + header.getValue());
            }

            entity = httpResponse.getEntity();
            responseContent = EntityUtils.toString(entity,"UTF-8");
//            System.out.println("responseContent: " + responseContent);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(httpResponse != null){
                    httpResponse.close();
                }
                if(httpClient != null){
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public String sendHttpsGet(String httpUrl){
        HttpGet httpGet = new HttpGet(httpUrl);
        CloseableHttpClient httpClient;
        CloseableHttpResponse httpResponse;
        HttpEntity entity;
        String responseContent;

        try {
            httpClient = getHttpsClent();
            httpGet.setConfig(requestConfig);
            httpResponse = httpClient.execute(httpGet);

            Integer statusCode = httpResponse.getStatusLine().getStatusCode();
            System.out.println("statusCode: " + statusCode);

            Header[] headers = httpResponse.getHeaders("Set-Cookie");
            List<Header> headerList = Lists.newArrayList();
            StringBuilder stringBuilder = new StringBuilder();
            for (Header header : headers) {
                HeaderElement[] headerElements = header.getElements();
                String temp = headerElements[0].getName() + "=" + headerElements[0].getValue();
                System.out.println(temp);
                stringBuilder.append(temp).append(";");
            }
            String cookies = stringBuilder.substring(0,stringBuilder.length() - 1);
            System.out.println("cookies: " + cookies);
            Header header = new BasicHeader("Cookie",cookies);
            Header header1 = new BasicHeader("Accept","image/webp,image/*,*/*;q=0.8");
            headerList.add(header);
            headerList.add(header1);

            Header[] headersRequest = headerList.toArray(new Header[headerList.size()]);

            httpGet = new HttpGet("https://www.zhihu.com/captcha.gif?r=&type=login&lang=cn");
            httpGet.setHeaders(headersRequest);
            httpGet.setConfig(requestConfig);
            httpResponse = httpClient.execute(httpGet);
            System.out.println("Stream: " + httpResponse.getEntity().isStreaming());
            boolean isStream = httpResponse.getEntity().isStreaming();
            if(isStream){
                OutputStream outputStream = new FileOutputStream("/Users/jianghang/Desktop/QRC" + System.currentTimeMillis() + ".png");
                httpResponse.getEntity().writeTo(outputStream);
                outputStream.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }

        return null;
    }

    public CloseableHttpClient getHttpsClent(){
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslConnectionSocketFactory).setConnectionManager(cm).setConnectionManagerShared(true).build();

        return httpClient;
    }

    public static void main(String[] args){
//        HttpClientUtil.getInstance().sendHttpGet("http://www.zhihu.com");
        HttpClientUtil.getInstance().sendHttpsGet("https://www.zhihu.com");
    }
}
