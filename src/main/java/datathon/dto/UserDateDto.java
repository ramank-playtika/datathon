package datathon.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDateDto {

  private String userId = "";
  private String date = LocalDate.now().toString();

}
