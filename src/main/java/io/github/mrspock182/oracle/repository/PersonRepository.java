package io.github.mrspock182.oracle.repository;

import io.github.mrspock182.oracle.repository.dto.PersonOrmDynamoDB;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;

@Repository
public class PersonRepository {
    private final DynamoDbTable<PersonOrmDynamoDB> personTable;

    public PersonRepository(DynamoDbTable<PersonOrmDynamoDB> personTable) {
        this.personTable = personTable;
    }

    public void save(final PersonOrmDynamoDB orm) {
        try {
            personTable.putItem(orm);
        } catch (Exception ex) {
            throw new RuntimeException("Error to save person", ex);
        }
    }
}