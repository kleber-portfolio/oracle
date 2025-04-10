package io.github.mrspock182.oracle.event;

import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.mrspock182.oracle.entity.ChosenPerson;
import io.github.mrspock182.oracle.service.ChosenPersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ChosenPersonEvent implements Function<SQSEvent, String> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChosenPersonEvent.class);

    private final ObjectMapper objectMapper;
    private final ChosenPersonService chosenPersonService;

    public ChosenPersonEvent(
            ObjectMapper objectMapper,
            ChosenPersonService chosenPersonService) {
        this.objectMapper = objectMapper;
        this.chosenPersonService = chosenPersonService;
    }

    @Override
    public String apply(final SQSEvent sqsEvent) {
        try {
            for (SQSEvent.SQSMessage message : sqsEvent.getRecords()) {
                final ChosenPerson chosenPerson = objectMapper
                        .readValue(message.getBody(), ChosenPerson.class);
                chosenPersonService.evaluateChosenStatus(chosenPerson);
            }
            return "SUCCESS";
        } catch (Exception ex) {
            LOGGER.error("Event ChosenPerson Error: {}", ex.getMessage(), ex);
            throw new RuntimeException("Message processing failed", ex);
        }
    }
}