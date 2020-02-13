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
        treatmentDto.setLocation(infoDto.getCityName());
        treatmentDto.setLocationImg("images/location/" +
                Arrays.stream(resources).filter(r -> FilenameUtils.removeExtension(r.getFilename()).equalsIgnoreCase(infoDto.getCityName())).map(Resource::getFilename).findFirst().orElse(resources[0].getFilename()));

    }
}
