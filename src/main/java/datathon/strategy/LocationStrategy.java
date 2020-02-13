package datathon.strategy;

import datathon.dto.InfoDto;
import datathon.dto.TreatmentDto;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class LocationStrategy implements Strategy {
  @Value("classpath:static/images/location/*")
  private Resource[] resources;

  @Override
  public void fill(InfoDto infoDto, TreatmentDto treatmentDto) {
    String countryName = infoDto.getCountryName();
    String cityName = infoDto.getCityName();
    if (cityName != null) {
      treatmentDto.setCity(cityName);
    }
    if (countryName != null) {
      treatmentDto.setCountry(countryName);
      Arrays.stream(resources)
          .filter(r -> FilenameUtils.removeExtension(r.getFilename()).equalsIgnoreCase(countryName)).findFirst()
          .ifPresent(resource -> treatmentDto.setLocationImg("/images/location/" + resource.getFilename()));
    }
  }
}
