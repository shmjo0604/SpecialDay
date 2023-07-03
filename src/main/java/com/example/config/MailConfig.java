package com.example.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {

    @Bean
    public JavaMailSender javaMailSender() {

        Properties mailProperties = new Properties();

        mailProperties.put("mail.smtp.auth", "true");
		mailProperties.put("mail.transport.protocol", "smtp");
		mailProperties.put("mail.smtp.starttls.enable", "true");
		mailProperties.put("mail.smtp.starttls.required", "true");
		mailProperties.put("mail.debug", "true");
        mailProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        mailProperties.put("mail.smtp.ssl.protocols", "TLSv1.2");
        
        //mailProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFacotry");

        JavaMailSenderImpl mailSenderImpl = new JavaMailSenderImpl();

        mailSenderImpl.setJavaMailProperties(mailProperties);
        mailSenderImpl.setHost("smtp.gmail.com");
        mailSenderImpl.setPort(587);
        mailSenderImpl.setUsername("shmjo6417@gmail.com");
        mailSenderImpl.setPassword("mjqlbczkiqntkvhb");
        mailSenderImpl.setDefaultEncoding("utf-8");

        return mailSenderImpl;

    }
    
}
