package main;

import lombok.Getter;

@Getter
public enum Resources {

    ANIMALS("src/main/resources/animals.json"),
    CHANCE_TO_EAT("src/main/resources/chance_to_eat.json"),
    ISLAND_CONFIG("src/main/resources/island_config.json");

    private final String value;

    Resources(String value) {
        this.value = value;
    }
}
