package datathon.strategy;

import datathon.dto.InfoDto;
import datathon.dto.TreatmentDto;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class HolidayStrategy implements Strategy {
  @Value("classpath:static/images/holiday/*")
  private Resource[] resources;

  @Override
  public void fill(InfoDto infoDto, TreatmentDto treatmentDto) {
    String eventName = infoDto.getEventName();
    if (eventName != null) {
      treatmentDto.setHoliday(eventName);
      Arrays.stream(resources)
          .filter(r -> FilenameUtils.removeExtension(r.getFilename()).equalsIgnoreCase(eventName.replace("'", "_"))).findFirst()
          .ifPresent(resource -> treatmentDto.setHolidayImg("images/holiday/" + resource.getFilename()));
    }
  }
}
