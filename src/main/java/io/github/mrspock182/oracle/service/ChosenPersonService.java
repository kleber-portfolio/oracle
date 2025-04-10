package io.github.mrspock182.oracle.service;

import io.github.mrspock182.oracle.entity.ChosenPerson;
import io.github.mrspock182.oracle.repository.PersonRepository;
import io.github.mrspock182.oracle.repository.dto.PersonOrmDynamoDB;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class ChosenPersonService {
    private final PersonRepository repository;

    public ChosenPersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public void evaluateChosenStatus(final ChosenPerson chosenPerson) {
        PersonOrmDynamoDB orm = new PersonOrmDynamoDB();
        orm.setId(chosenPerson.id());
        orm.setName(chosenPerson.name());
        orm.setPill(chosenPerson.pill());
        orm.setCreateIn(chosenPerson.createIn());

        boolean isTheOne = ThreadLocalRandom.current().nextBoolean();
        orm.setChosen(isTheOne);
        orm.setMessage(getOracleMessage(isTheOne));
        repository.save(orm);
    }


    private String getOracleMessage(boolean isChosen) {
        if (isChosen) {
            return "Being the One is just like being in love. " +
                    "No one can tell you you're in love, you just know it. " +
                    "Through and through. Balls to bones.";
        } else {
            return "Sorry, kid. You got the gift, but it looks like you're waiting for something. " +
                    "Your next life, maybe. Who knows? That's the way these things go.";
        }
    }

}
