package blr.dany.weatheranalyzer.service;

import blr.dany.weatheranalyzer.dto.response.HourResponse;

public interface DangerAnalysisStrategy {

    boolean matches(HourResponse hour);
    DangerLevel getLevel();
    String getReason();

}
