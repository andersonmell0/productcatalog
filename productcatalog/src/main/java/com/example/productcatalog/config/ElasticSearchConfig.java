package com.example.productcatalog.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.core.ElasticsearchEntityMapper;
import org.springframework.data.elasticsearch.core.EntityMapper;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages="com.example.productcatalog.repository")
public class ElasticSearchConfig extends AbstractElasticsearchConfiguration {

	@Override
	public RestHighLevelClient elasticsearchClient() {
		ClientConfiguration clienteConfiguration = ClientConfiguration.builder().connectedTo("localhost:9200","localhost:9300").build();
		return RestClients.create(clienteConfiguration).rest();
	}
	
	@Bean
	@Override
	public EntityMapper entityMapper() {
		ElasticsearchEntityMapper entityMapper = new ElasticsearchEntityMapper(elasticsearchMappingContext(), new DefaultConversionService());
		entityMapper.setConversions(elasticsearchCustomConversions());
		
		return entityMapper;
	}

}
