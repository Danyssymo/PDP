package blr.dany.weatheranalyzer.service.impl;

import blr.dany.weatheranalyzer.dto.response.HourResponse;
import blr.dany.weatheranalyzer.service.DangerAnalysisStrategy;
import blr.dany.weatheranalyzer.service.DangerLevel;
import org.springframework.stereotype.Component;

@Component
public class ExtremeHeatStrategy implements DangerAnalysisStrategy {

    @Override
    public boolean matches(HourResponse hour) {
        return hour.getTempC() > 38;
    }

    @Override
    public DangerLevel getLevel() {
        return DangerLevel.RED;
    }

    @Override
    public String getReason() {
        return MessageConstants.EXTREME_HEAT_STRATEGY_MSG ;
    }
}
