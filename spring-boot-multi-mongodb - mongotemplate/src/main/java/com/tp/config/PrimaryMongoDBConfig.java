package com.tp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * @author tanpei
 * @create 2019-05-30 16:14
 **/
@Configuration
public class PrimaryMongoDBConfig extends BasicMongoConfig {

    @Value("${mongodb.host}")
    private String host;
    @Value("${mongodb.port}")
    private int port;
    @Value("${mongodb.userName}")
    private String userName;
    @Value("${mongodb.pwd}")
    private String pwd;
    @Value("${mongodb.first.database}")
    private String firstMongoDB;

    public MongoProperties firstMongoProperties() {
        MongoProperties properties = new MongoProperties();
        properties.setHost(host);
        properties.setPort(port);
        properties.setPassword(pwd.toCharArray());
        properties.setUsername(userName);
        properties.setDatabase(firstMongoDB);
        return properties;
    }

    @Bean(name="mongoTemplate")
    @Primary
    @Override
    public MongoTemplate getMongoTemplate(){
        return new MongoTemplate(createMongoFactory(firstMongoProperties()));
    }
}
