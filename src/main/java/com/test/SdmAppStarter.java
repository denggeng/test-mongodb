package com.test;

import java.net.UnknownHostException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.test.framework.repository.BaseRepositoryImpl;

@EnableAutoConfiguration
@SpringBootApplication
// @EnableMongoRepositories(repositoryBaseClass = BaseRepositoryImpl.class)
@EnableSpringDataWebSupport
@ComponentScan
public class SdmAppStarter extends SpringBootServletInitializer {

	@Configuration
	@EnableMongoRepositories(basePackages = "com.test.account", mongoTemplateRef = "mongoTemplate", repositoryBaseClass = BaseRepositoryImpl.class)
	public static class MainMongoConfig extends AbstractMongoConfiguration {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.springframework.data.mongodb.config.AbstractMongoConfiguration#
		 * getDatabaseName()
		 */
		@Override
		protected String getDatabaseName() {
			return "test";
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.springframework.data.mongodb.config.AbstractMongoConfiguration#
		 * mongo()
		 */
		@Override
		public Mongo mongo() throws Exception {
			return new MongoClient("127.0.0.1", 27017).getDB("test").getMongo();
		}

		/*
		 * @Bean
		 * 
		 * @Override public MappingMongoConverter mappingMongoConverter() throws
		 * Exception { MappingMongoConverter mmc =
		 * super.mappingMongoConverter(); mmc.setTypeMapper(customTypeMapper());
		 * return mmc; }
		 */

	}

	@Configuration
	@EnableMongoRepositories(basePackages = "com.test.domain", mongoTemplateRef = "entertainmentMongoTemplate", repositoryBaseClass = BaseRepositoryImpl.class)
	public static class SecondaryMongoConfig extends AbstractMongoConfiguration {
		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.springframework.data.mongodb.config.AbstractMongoConfiguration#
		 * getDatabaseName()
		 */
		@Override
		protected String getDatabaseName() {
			return "entertainment";
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.springframework.data.mongodb.config.AbstractMongoConfiguration#
		 * mongo()
		 */
		@Override
		public Mongo mongo() throws Exception {
			return new MongoClient("127.0.0.1", 27017).getDB("entertainment").getMongo();
		}
	}

	// public static final String MONGO_URI = "summba-dev10:30000";// String uri
	// = "127.0.0.1";
	public static final String MONGO_URI = "127.0.0.1";

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SdmAppStarter.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SdmAppStarter.class);
	}

	@Bean(name = "entertainmentMongoDbFactory")
	public MongoDbFactory createEntertainmentMongoDbFactory() throws UnknownHostException {
		// String uri = "127.0.0.1";
		MongoClientURI mcUri = new MongoClientURI("mongodb://" + MONGO_URI + "/entertainment");
		SimpleMongoDbFactory smf = new SimpleMongoDbFactory(mcUri);
		return smf;

	}

	@Bean(name = "entertainmentMongoTemplate")
	public MongoTemplate createEntertainmentMongoTemplate() throws UnknownHostException {
		return new MongoTemplate(createEntertainmentMongoDbFactory());
	}

	@Bean(name = "adminMongoDbFactory")
	public MongoDbFactory createAdminMongoDbFactory() throws UnknownHostException {
		// String uri = "127.0.0.1";
		MongoClientURI mcUri = new MongoClientURI("mongodb://" + MONGO_URI + "/test");
		SimpleMongoDbFactory smf = new SimpleMongoDbFactory(mcUri);
		return smf;

	}

	@Bean(name = "mongoTemplate")
	public MongoTemplate createAdminMongoTemplate() throws UnknownHostException {
		return new MongoTemplate(createAdminMongoDbFactory());
	}

}
