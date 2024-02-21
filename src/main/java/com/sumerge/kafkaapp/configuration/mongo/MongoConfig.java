package com.sumerge.kafkaapp.configuration.mongo;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.sumerge.kafkaapp")

public class MongoConfig extends AbstractMongoClientConfiguration {

    //no need to define MongoTemplate bean in the previous configuration since it’s already defined in AbstractMongoClientConfiguration.
    @Override
    protected String getDatabaseName() {
        return "test";
    }


    @Override
    public MongoClient mongoClient() {
        ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017/test");
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

        return MongoClients.create(mongoClientSettings);
    }

//    @Override
//    public Collection getMappingBasePackages() {
//        return Collections.singleton("com.baeldung");
//    }
}