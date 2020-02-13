package datathon.controller;

import datathon.dto.InfoDto;
import datathon.dto.TreatmentDto;
import datathon.repo.InfoRepository;
import datathon.strategy.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.HashMap;
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

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public ModelAndView index(@PathVariable String userId, @RequestParam(name = "timeInMs", required = false) Long timeInMs, Model model) throws IOException {
        InfoDto info = infoRepository.getInfo(userId);
        info.setTimeInMs(timeInMs);
        TreatmentDto treatmentDto = new TreatmentDto();
        Set<Strategy> strategies = Stream.of(weatherStrategy, holidayStrategy, greetingStrategy, timeStrategy, locationStrategy)
                .collect(Collectors.toSet());
        strategies.stream().forEach(s -> s.fill(info, treatmentDto));
        HashMap<String, Object> params = new HashMap<>();
        params.put("treatment", treatmentDto);
        return new ModelAndView("template", params);
    }

}
