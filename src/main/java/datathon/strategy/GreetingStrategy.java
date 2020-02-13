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

  private Map<String, String> currency = new HashMap() {{
    put("Belarus", "BYN");
    put("Israel", "ILS");
    put("Ukraine", "UAH");
    put("United States", "USD");
  }};

  @Override
  public void fill(InfoDto infoDto, TreatmentDto treatmentDto) {
    treatmentDto.setGreeting(dictionary.getOrDefault(infoDto.getCountryName(), "Hello!"));
    treatmentDto.setCurrency(currency.getOrDefault(infoDto.getCountryName(), "$"));
    int price = String.valueOf(infoDto.getBalanceCoins()).length();
    treatmentDto.setPrice(price + ".99");
    if (infoDto.getFirstName() != null) {
      treatmentDto.setFirstName(infoDto.getFirstName());
    }
    if (infoDto.getLastName() != null) {
      treatmentDto.setLastName(infoDto.getLastName());
    }
    if (infoDto.getBalanceCoins() != null) {
      treatmentDto.setCoins(infoDto.getBalanceCoins());
    }
  }
}
