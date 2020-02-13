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
public class WeatherStrategy implements Strategy {
    @Value("classpath:static/images/weather/*")
    private Resource[] resources;

    @Override
    public void fill(InfoDto infoDto, TreatmentDto treatmentDto) {
        treatmentDto.setWeather(StringUtils.isEmpty(infoDto.getWeather()) ? "Unknown" : infoDto.getWeather());
        treatmentDto.setWeatherImg("/images/weather/" + Arrays.stream(resources).filter(r -> FilenameUtils.removeExtension(r.getFilename()).equalsIgnoreCase(infoDto.getWeather())).map(Resource::getFilename).findFirst().orElse(resources[0].getFilename()));

    }
}
