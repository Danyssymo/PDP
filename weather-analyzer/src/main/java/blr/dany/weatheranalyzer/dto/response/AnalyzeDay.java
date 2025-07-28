package blr.dany.weatheranalyzer.dto.response;

import blr.dany.weatheranalyzer.service.DangerLevel;
import lombok.Data;

@Data
public class AnalyzeDay {

    private DangerLevel level;
    private String message;

}
