package com.tp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * @author tanpei
 * @create 2019-05-30 16:31
 **/
@Configuration
public class SecondMongoDBConfig extends BasicMongoConfig {

    @Value("${mongodb.host}")
    private String host;
    @Value("${mongodb.port}")
    private int port;
    @Value("${mongodb.userName}")
    private String userName;
    @Value("${mongodb.pwd}")
    private String pwd;
    @Value("${mongodb.second.database}")
    private String secondMongoDB;

    public MongoProperties secondMongoProperties() {
        MongoProperties properties = new MongoProperties();
        properties.setHost(host);
        properties.setPort(port);
        properties.setPassword(pwd.toCharArray());
        properties.setUsername(userName);
        properties.setDatabase(secondMongoDB);
        return properties;
    }

    @Bean(name="secondMongoTemplate")
    @Override
    public MongoTemplate getMongoTemplate(){
        return new MongoTemplate(createMongoFactory(secondMongoProperties()));
    }
}
