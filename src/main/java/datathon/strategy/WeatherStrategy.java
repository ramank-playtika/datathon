package datathon.strategy;

import datathon.dto.InfoDto;
import datathon.dto.TreatmentDto;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class WeatherStrategy implements Strategy {
  @Value("classpath:static/images/weather/*")
  private Resource[] resources;

  @Override
  public void fill(InfoDto infoDto, TreatmentDto treatmentDto) {
    String weather = infoDto.getWeather();
    if (weather != null) {
      treatmentDto.setWeather(weather);
      Arrays.stream(resources)
          .filter(r -> FilenameUtils.removeExtension(r.getFilename()).equalsIgnoreCase(weather)).findFirst()
          .ifPresent(resource -> treatmentDto.setWeatherImg("images/weather/" + resource.getFilename()));
    }
  }
}
