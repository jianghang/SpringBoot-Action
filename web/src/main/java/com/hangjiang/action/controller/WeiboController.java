package com.hangjiang.action.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import weibo4j.Oauth;
import weibo4j.examples.oauth2.Log;
import weibo4j.http.AccessToken;
import weibo4j.model.WeiboException;
import weibo4j.util.BareBonesBrowserLaunch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by jianghang on 2017/10/24.
 */
@Controller
@RequestMapping("weibo")
public class WeiboController {

    private Logger logger = LoggerFactory.getLogger(WeiboController.class);

    @Autowired
    @Qualifier("oauth")
    private Oauth oauth;

    @RequestMapping("/")
    public String weibo(){
        return "helloWeibo";
    }

    @RequestMapping("/authorize")
    public void authorize(){
        try {
            BareBonesBrowserLaunch.openURL(oauth.authorize("code"));
        } catch (WeiboException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/authorizecallback")
    public String authorizeCallback(@RequestParam String code){
        logger.info("code: " + code);
        try {
            AccessToken accesstoken = oauth.getAccessTokenByCode(code);
            String token = accesstoken.getAccessToken();
            logger.info("token: " + token);

        } catch (WeiboException e) {
            e.printStackTrace();
        }
        return "searchPage";
    }

    public static void main(String[] args){
        Oauth oauth = new Oauth();
        try {
            BareBonesBrowserLaunch.openURL(oauth.authorize("code",null));
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String code = br.readLine();
            Log.logInfo("code: " + code);
            System.out.println(oauth.getAccessTokenByCode(code));
        } catch (WeiboException | IOException e) {
            e.printStackTrace();
        }
    }
}
