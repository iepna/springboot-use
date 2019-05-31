package com.tp.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import java.util.Arrays;

/**
 * @author tanpei
 * @create 2019-05-30 16:10
 **/
public abstract class BasicMongoConfig {
    /**
     * 创建mongodbFactory
     *
     * @return
     * @throws Exception
     */
    public MongoDbFactory createMongoFactory(MongoProperties mongoProperties) {
        return new SimpleMongoDbFactory(createMongoClient(mongoProperties),
                mongoProperties.getDatabase());
    }

    private MongoClient createMongoClient(MongoProperties mongoProperties) {
        MongoClientOptions.Builder build = new MongoClientOptions.Builder();
        MongoClientOptions myOptions = build.build();
        ServerAddress serverAddress = new ServerAddress(mongoProperties.getHost(), mongoProperties.getPort());
        MongoCredential credential = MongoCredential.createCredential(mongoProperties.getUsername(),
                mongoProperties.getDatabase(), mongoProperties.getPassword());
        return new MongoClient(serverAddress, Arrays.asList(credential), myOptions);
    }

    public abstract MongoTemplate getMongoTemplate();
}
