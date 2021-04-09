package com.betvictor.processing.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;

@SuppressWarnings("deprecation")
@Configuration
public class MongoConfig {
	
	
	@Value("${db.collection.host}")
	private String host;
	
	@Value("${db.collection.port}")
	private int port;
	
	@Value("${db.collection.name}")
	private String db;
	
	@Bean
	public MongoOperations mongoTemplate() throws Exception {
		
		MappingMongoConverter converter =
			new MappingMongoConverter(mongoDbFactory(), mongoMappingContext);
		converter.setTypeMapper(new DefaultMongoTypeMapper(null));

		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory(), converter);

		return mongoTemplate;
	}
	
	@Autowired
	MongoMappingContext mongoMappingContext;
	
	@Bean
	public MongoDbFactory mongoDbFactory() throws Exception {
		ServerAddress address = new ServerAddress(host, port);
		MongoClientOptions.Builder opt = MongoClientOptions.builder();
		return new SimpleMongoDbFactory(new MongoClient(address, opt.build()), db);
	}
	
	@Bean
    public GridFsTemplate gridFsTemplate() throws Exception {
        return new GridFsTemplate(mongoDbFactory(), mappingMongoConverter());
    }
	
	@Bean
	public MappingMongoConverter mappingMongoConverter() throws Exception{

	  DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDbFactory());
	  MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, mongoMappingContext);
	  converter.setTypeMapper(new DefaultMongoTypeMapper(null));

	  return converter;
	 }
}
