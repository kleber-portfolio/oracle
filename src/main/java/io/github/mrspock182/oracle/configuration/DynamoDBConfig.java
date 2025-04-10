package io.github.mrspock182.oracle.configuration;

import io.github.mrspock182.oracle.repository.dto.PersonOrmDynamoDB;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Configuration
public class DynamoDBConfig {
    @Bean
    public DynamoDbClient dynamoDbClient(
            @Value("${spring.cloud.aws.region.static}") final String region) {
        return DynamoDbClient.builder()
                .region(Region.of(region))
                .build();
    }

    @Bean
    public DynamoDbEnhancedClient enhancedClient(DynamoDbClient client) {
        return DynamoDbEnhancedClient.builder()
                .dynamoDbClient(client)
                .build();
    }

    @Bean
    public DynamoDbTable<PersonOrmDynamoDB> DynamoDbTablePerson(
            final DynamoDbEnhancedClient enhancedClient,
            @Value("${spring.cloud.aws.dynamodb.person-table}") final String tableName) {
        return enhancedClient.table(tableName, TableSchema.fromBean(PersonOrmDynamoDB.class));
    }
}