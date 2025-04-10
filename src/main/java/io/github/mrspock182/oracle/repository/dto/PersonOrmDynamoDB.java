package io.github.mrspock182.oracle.repository.dto;

import io.github.mrspock182.oracle.entity.enumerable.PillEnum;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.time.LocalDate;

@DynamoDbBean
public class PersonOrmDynamoDB {
    private String id;
    private String name;
    private PillEnum pill;
    private String message;
    private Boolean isChosen;
    private LocalDate createIn;

    @DynamoDbPartitionKey
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PillEnum getPill() {
        return pill;
    }

    public void setPill(PillEnum pill) {
        this.pill = pill;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getChosen() {
        return isChosen;
    }

    public void setChosen(Boolean chosen) {
        isChosen = chosen;
    }

    public LocalDate getCreateIn() {
        return createIn;
    }

    public void setCreateIn(LocalDate createIn) {
        this.createIn = createIn;
    }
}
