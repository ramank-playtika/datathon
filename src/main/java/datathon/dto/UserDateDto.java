package datathon.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class UserDateDto {

  private String userId = "";
  private String date = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

}
