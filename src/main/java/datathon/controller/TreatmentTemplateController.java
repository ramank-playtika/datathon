package datathon.controller;

import datathon.dto.InfoDto;
import datathon.dto.TreatmentDto;
import datathon.dto.UserDateDto;
import datathon.repo.InfoRepository;
import datathon.strategy.GreetingStrategy;
import datathon.strategy.HolidayStrategy;
import datathon.strategy.LocationStrategy;
import datathon.strategy.Strategy;
import datathon.strategy.TimeStrategy;
import datathon.strategy.WeatherStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class TreatmentTemplateController {
  @Autowired
  private InfoRepository infoRepository;
  @Autowired
  private TimeStrategy timeStrategy;
  @Autowired
  private HolidayStrategy holidayStrategy;
  @Autowired
  private GreetingStrategy greetingStrategy;
  @Autowired
  private LocationStrategy locationStrategy;
  @Autowired
  private WeatherStrategy weatherStrategy;

  @RequestMapping("/")
  public String index() {
    return "redirect:/treatment";
  }

  @RequestMapping(value = "/treatment", method = RequestMethod.POST)
  public String treatment(UserDateDto userDateDto, Model model) {
    InfoDto info = infoRepository.getInfo(userDateDto.getUserId(), userDateDto.getDate());
    TreatmentDto treatmentDto = new TreatmentDto();
    Set<Strategy> strategies = Stream.of(weatherStrategy, holidayStrategy, greetingStrategy, timeStrategy, locationStrategy)
        .collect(Collectors.toSet());
    strategies.forEach(s -> s.fill(info, treatmentDto));
    model.addAttribute("treatment", treatmentDto);
    model.addAttribute("ud", userDateDto);
    return "template";
  }

  @RequestMapping(value = "/treatment", method = RequestMethod.GET)
  public String treatment(Model model) {
    model.addAttribute("treatment", new TreatmentDto());
    model.addAttribute("ud", new UserDateDto());
    return "template";
  }
}
