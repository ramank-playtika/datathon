package datathon.dto;

import lombok.Data;

@Data
public class TreatmentDto {
  private String greeting = "X";

  private String firstName = "";
  private String lastName = "";

  private String country = "X";
  private String city = "";
  private String locationImg = "/images/default.png";

  private String weather = "X";
  private String weatherImg = "/images/default.png";

  private String holiday = "X";
  private String holidayDays = "X";
  private String holidayImg = "/images/default.png";

  private String time = "X";
  private String timeImg = "/images/default.png";
}
