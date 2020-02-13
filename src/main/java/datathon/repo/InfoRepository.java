package datathon.repo;

import datathon.dto.InfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class InfoRepository {
  private static String QUERY =
      "select u.user_id, u.user_first_name, u.user_last_name, u.user_balance_coins, u.city_name, u.country_name, h.event_name, c.main, c.feels_like, datediff(h.start_date, ?) as event_days " +
          "from user_profile u\n" +
          "left join holidays h on u.country_name=h.location\n" +
          "left join cities_weather_final c on u.city_name=c.city\n" +
          "where u.user_id=?\n" +
          "having event_days >= 0\n" +
          "order by abs(event_days)\n" +
          "limit 1";

  @Autowired
  JdbcTemplate jdbcTemplate;

  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

  public InfoDto getInfo(String userId, final LocalDateTime date) {
    List<InfoDto> result = jdbcTemplate.query(
        QUERY, new Object[]{date.format(formatter), userId},
        (rs, rowNum) -> InfoDto.builder().userId(rs.getString("user_id"))
            .firstName(rs.getString("user_first_name"))
            .lastName(rs.getString("user_last_name"))
            .balanceCoins(rs.getString("user_balance_coins"))
            .cityName(rs.getString("city_name"))
            .countryName(rs.getString("country_name"))
            .eventName(rs.getString("event_name"))
            .eventDays(rs.getString("event_days"))
            .temperature(rs.getString("feels_like"))
            .date(date)
            .weather(rs.getString("main")).build());
    return result.isEmpty() ? InfoDto.builder().date(date).build() : result.get(0);
  }
}
