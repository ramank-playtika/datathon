package datathon.strategy;

import datathon.dto.InfoDto;
import datathon.dto.TreatmentDto;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class TimeStrategy implements Strategy {
  @Value("classpath:static/images/time/*")
  private Resource[] resources;

  @Override
  public void fill(InfoDto infoDto, TreatmentDto treatmentDto) {
    //not use timezone

    int timeOfDay = infoDto.getDate().getHour();
    String time;
    if (timeOfDay < 12) {
      time = "Morning";
    } else if (timeOfDay < 16) {
      time = "Afternoon";
    } else if (timeOfDay < 21) {
      time = "Evening";
    } else {
      time = "Night";
    }
    treatmentDto.setTime(time);
    String finalTime = time;
    treatmentDto.setTimeImg("/images/time/" + Arrays.stream(resources).filter(r -> FilenameUtils.removeExtension(r.getFilename()).equals(finalTime.toLowerCase()))
        .map(Resource::getFilename).findFirst().orElse(resources[0].getFilename()));
  }
}
