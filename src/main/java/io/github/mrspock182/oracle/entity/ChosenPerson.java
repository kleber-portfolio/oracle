package io.github.mrspock182.oracle.entity;

import io.github.mrspock182.oracle.entity.enumerable.PillEnum;

import java.time.LocalDate;

public record ChosenPerson(
        String id,
        String name,
        PillEnum pill,
        String message,
        Boolean isChosen,
        LocalDate createIn
) {
}