package datathon.strategy;

import datathon.dto.InfoDto;
import datathon.dto.TreatmentDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.apache.commons.io.FilenameUtils;

import java.util.Arrays;
import java.util.Date;

@Service
public class TimeStrategy implements Strategy {
    @Value("classpath:static/images/time/*")
    private Resource[] resources;

    @Override
    public void fill(InfoDto infoDto, TreatmentDto treatmentDto) {
        //not use timezone

        int timeOfDay = infoDto.getTimeInMs() != null ?
                new Date(infoDto.getTimeInMs()).getHours() : new Date(System.currentTimeMillis()).getHours();
        String time = "Morning";
        if (timeOfDay >= 0 && timeOfDay < 12) {
            time = "Morning";
        } else if (timeOfDay >= 12 && timeOfDay < 16) {
            time = "Afternoon";
        } else if (timeOfDay >= 16 && timeOfDay < 21) {
            time = "Evening";
        } else if (timeOfDay >= 21 && timeOfDay < 24) {
            time = "Night";
        }
        treatmentDto.setTime(time);
        String finalTime = time;
        treatmentDto.setTimeImg("/images/time/"+ Arrays.stream(resources).filter(r -> FilenameUtils.removeExtension(r.getFilename()).equals(finalTime.toLowerCase()))
                .map(Resource::getFilename).findFirst().orElse(resources[0].getFilename()));
    }
}
