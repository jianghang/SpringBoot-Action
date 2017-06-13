package com.hangjiang.action.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;

/**
 * Created by jianghang on 2017/6/13.
 */
@Configuration
@Profile("prod")
@EnableJms
public class JmsConfig {

    @Value("${jms.broker-url}")
    private String brokerUrl;

    @Value("${jms.user}")
    private String user;

    @Value("${jms.user}")
    private String password;

    @Bean
    public ConnectionFactory connectionFactory(){
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL(brokerUrl);
        activeMQConnectionFactory.setUserName(user);
        activeMQConnectionFactory.setPassword(password);

        return activeMQConnectionFactory;
    }

    @Bean
    public JmsTemplate jmsTemplate(){
        return new JmsTemplate(connectionFactory());
    }

    @Bean(name = "jmsQueueListenerCF")
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(){
        DefaultJmsListenerContainerFactory jmsListenerContainerFactory = new DefaultJmsListenerContainerFactory();
        jmsListenerContainerFactory.setConnectionFactory(connectionFactory());
        jmsListenerContainerFactory.setConcurrency("3-10");
        jmsListenerContainerFactory.setRecoveryInterval(3000L);

        return jmsListenerContainerFactory;
    }
}
