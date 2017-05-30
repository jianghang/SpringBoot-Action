package com.hangjiang.action.util;

import com.google.common.collect.Lists;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jianghang on 2017/5/7.
 */
public class HttpRequestUtil {

    private static Logger logger = LoggerFactory.getLogger(HttpRequestUtil.class);

    public static String get(String url){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).get().build();

        String responseStr = "";
        try {
            Response response = client.newCall(request).execute();

            responseStr = handleResponse(response);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return responseStr;
    }

    public static String get(String url, Map<String,List<String>> headers){
        OkHttpClient client = new OkHttpClient();
        Request.Builder builder = new Request.Builder().url(url).get();

        Request request = handleHeaders(builder,headers);

        String responseStr = "";

        try {
            Response response = client.newCall(request).execute();
            responseStr = handleResponse(response);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return responseStr;
    }

    public static String get(String url,Map<String,List<String>> headers,Map<String,String> queryParameters){
        OkHttpClient client = new OkHttpClient();
        Request.Builder reqBuilder = handleQueryParameters(url,queryParameters).get();
        Request request = handleHeaders(reqBuilder,headers);
        String responseStr = "";
        try {
            Response response = client.newCall(request).execute();
            responseStr = handleResponse(response);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return responseStr;
    }

    public static void asyncGet(String url, Map<String,List<String>> headers,Map<String,String> queryParameters, Callback callback){
        OkHttpClient client = new OkHttpClient();
        Request.Builder builder = handleQueryParameters(url,queryParameters).get();
        Request request = handleHeaders(builder,headers);
        client.newCall(request).enqueue(callback);
    }

    public static String post(String url,String content){
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json;charset=utf-8");
        RequestBody requestBody = RequestBody.create(mediaType,content);
        Request request = new Request.Builder().url(url).post(requestBody).build();
        String responseStr = "";
        try {
            Response response = client.newCall(request).execute();
            responseStr = handleResponse(response);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return responseStr;
    }

    private static Request.Builder handleQueryParameters(String url,Map<String,String> queryParameters){
        if (CollectionUtils.isEmpty(queryParameters)){
            logger.info("url: " + url);
            return new Request.Builder().url(url);
        }
        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();

        for(Map.Entry<String,String> entry : queryParameters.entrySet()){
            urlBuilder.addQueryParameter(entry.getKey(),entry.getValue());
        }
        logger.info("url: " + urlBuilder.build().url());

        Request.Builder reqBuilder =  new Request.Builder().url(urlBuilder.build());

        return reqBuilder;
    }

    private static String handleResponse(Response response){
        String responseStr = "";
        try {
            if (!response.isSuccessful()) {
                throw new IOException("Server is error:" + response);
            }
            Headers responseHeader = response.headers();
            for (int i = 0; i < responseHeader.size(); i++) {
                logger.info(responseHeader.name(i) + " : " + responseHeader.value(i));
            }

            responseStr = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("Body: " + responseStr);

        return responseStr;
    }

    private static Request handleHeaders(Request.Builder builder,Map<String,List<String>> headers){
        if(CollectionUtils.isEmpty(headers)){
            return builder.build();
        }

        for(Map.Entry<String,List<String>> entry : headers.entrySet()){
            if(entry.getValue().size() > 1){
                for(String str : entry.getValue()){
                    builder.addHeader(entry.getKey(),str);
                }
            }else{
                builder.header(entry.getKey(),entry.getValue().get(0));
            }
        }

        return builder.build();
    }

    public static void main(String[] args){
        String url = "http://www.baidu.com";
        get(url);

        Map<String,List<String>> headers = new HashMap<String, List<String>>();
        headers.put("User-Agent", Lists.newArrayList("My super agent"));
        headers.put("Accept",Lists.newArrayList("text/html","application/xml"));
        get(url,headers);

        Map<String,String> queryParameters = new HashMap<String, String>();
        queryParameters.put("test","1111");
        queryParameters.put("test2","2222");
        get(url,headers,queryParameters);

        asyncGet(url, headers, queryParameters, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(!response.isSuccessful()){
                    logger.error("Server is error!");
                    return;
                }
                logger.info(response.body().string());
            }
        });
    }
}
