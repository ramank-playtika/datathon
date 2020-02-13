package datathon.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InfoDto {
    private String userId;
    private String cityName;
    private String countryName;
    private String eventName;
    private String weather;
    private String temperature;
    private Long timeInMs;
}
