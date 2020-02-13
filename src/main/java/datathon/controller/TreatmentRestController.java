package datathon.controller;

import datathon.dto.InfoDto;
import datathon.repo.InfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TreatmentRestController {

    @Autowired
    private InfoRepository infoRepository;

    @RequestMapping("/data/{userId}")
    public InfoDto index(@PathVariable String userId) {
        InfoDto info = infoRepository.getInfo(userId, "");
        info.setTimeInMs(System.currentTimeMillis());
        return info;
    }

}
