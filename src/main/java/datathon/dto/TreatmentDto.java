package datathon.dto;

import lombok.Data;

@Data
public class TreatmentDto {
  private String greeting = "Unknown";

  private String country = "Unknown";
  private String city = "";
  private String locationImg = "/images/default.png";

  private String weather = "Unknown";
  private String weatherImg = "/images/default.png";

  private String holiday = "Unknown";
  private String holidayImg = "/images/default.png";

  private String time = "Unknown";
  private String timeImg = "/images/default.png";
}
