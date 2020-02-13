package datathon.strategy;

import datathon.dto.InfoDto;
import datathon.dto.TreatmentDto;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class GreetingStrategy implements Strategy {
    private Map<String, String> dictionary = new HashMap() {{
        put("Belarus", "Привет!");
        put("Israel", "שלום");
        put("Ukraine", "Добридень!");
        put("United States", "Hello!");
    }};

    @Override
    public void fill(InfoDto infoDto, TreatmentDto treatmentDto) {
        treatmentDto.setGreeting(dictionary.getOrDefault(infoDto.getCountryName(), "Hello!"));
    }
}
