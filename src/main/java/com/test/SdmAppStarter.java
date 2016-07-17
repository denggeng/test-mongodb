package com.test;

import java.net.UnknownHostException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import com.mongodb.MongoClientURI;
import com.test.framework.repository.BaseRepositoryImpl;

@EnableAutoConfiguration
@SpringBootApplication
@EnableMongoRepositories(repositoryBaseClass = BaseRepositoryImpl.class)
@EnableSpringDataWebSupport
@ComponentScan
public class SdmAppStarter {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SdmAppStarter.class, args);
	}

	@Bean
	public MongoDbFactory createMongoDbFactory() throws UnknownHostException {
		MongoClientURI mcUri = new MongoClientURI("mongodb://127.0.0.1/test");
		SimpleMongoDbFactory smf = new SimpleMongoDbFactory(mcUri);
		return smf;

	}

}
