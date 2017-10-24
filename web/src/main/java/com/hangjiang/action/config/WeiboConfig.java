package com.hangjiang.action.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import weibo4j.Oauth;

/**
 * Created by jianghang on 2017/10/24.
 */
@Configuration
public class WeiboConfig {

    @Bean(name = "oauth")
    @Qualifier("oauth")
    public Oauth getOauth(){
        Oauth oauth = new Oauth();

        return oauth;
    }
}
