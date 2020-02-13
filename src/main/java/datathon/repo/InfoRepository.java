package datathon.repo;

import datathon.dto.InfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InfoRepository {
    private static String QUERY =
        "select u.user_id, u.user_first_name, u.user_last_name, u.city_name, u.country_name, h.event_name, c.main, c.feels_like " +
        "from user_profile u\n" +
            "left join holidays h on u.country_name=h.location\n" +
            "left join cities_weather c on u.city_name=c.city\n" +
            "where u.user_id=? and h.start_date>CURRENT_DATE\n" +
            "order by h.start_date\n" +
            "limit 1";

    @Autowired
    JdbcTemplate jdbcTemplate;

    public InfoDto getInfo(String userId) {
        List<InfoDto> result = jdbcTemplate.query(
                QUERY, new Object[]{userId},
                (rs, rowNum) -> InfoDto.builder().userId(rs.getString("user_id"))
                        .cityName(rs.getString("city_name"))
                        .countryName(rs.getString("country_name"))
                        .eventName(rs.getString("event_name"))
                        .temperature(rs.getString("feels_like"))
                        .weather(rs.getString("main")).build());
        return result.isEmpty() ? InfoDto.builder().build() : result.get(0);
    }
}
