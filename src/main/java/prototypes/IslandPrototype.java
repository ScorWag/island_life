package prototypes;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import data.AnimalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class IslandPrototype {
    private int length;
    private int width;
    private Map<AnimalType, Integer> capacityLocation;
    private Map<AnimalType, Float> foodRequired;
    private Map<AnimalType, Map<AnimalType, Integer>> chancesToEatInfo;


    public void setChancesToEatInfo(String chanceToEatFile) throws IOException {
        chancesToEatInfo = new ObjectMapper().readValue(Path.of(chanceToEatFile).toFile(),
                new TypeReference<Map<AnimalType, Map<AnimalType, Integer>>>() {});
    }
}
