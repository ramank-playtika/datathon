package datathon.strategy;

import datathon.dto.InfoDto;
import datathon.dto.TreatmentDto;
import org.springframework.stereotype.Service;

import java.io.File;
import java.net.URL;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
@Service
public interface Strategy {

    void fill(InfoDto infoDto, TreatmentDto treatmentDto);

    default Set<String> listFiles(String dir) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        URL url = loader.getResource("/static/images/"+dir);
        String path = url.getPath();
        return Stream.of(new File(path).listFiles())
                .filter(file -> !file.isDirectory())
                .map(f->f.getName().toLowerCase())
                .collect(Collectors.toSet());
    }
}
