package datathon.strategy;

import datathon.dto.InfoDto;
import datathon.dto.TreatmentDto;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;

@Service
public class HolidayStrategy implements Strategy {
    @Value("classpath:static/images/holiday/*")
    private Resource[] resources;

    @Override
    public void fill(InfoDto infoDto, TreatmentDto treatmentDto) {
        treatmentDto.setHoliday(StringUtils.isEmpty(infoDto.getEventName()) ? "Unknown" : infoDto.getEventName());
        treatmentDto.setHolidayImg("images/holiday/" +
                Arrays.stream(resources).filter(r -> FilenameUtils.removeExtension(r.getFilename()).equalsIgnoreCase(infoDto.getEventName())).map(Resource::getFilename).findFirst().orElse(resources[0].getFilename()));

    }
}
