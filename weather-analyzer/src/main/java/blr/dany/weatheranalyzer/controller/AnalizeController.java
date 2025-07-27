package blr.dany.weatheranalyzer.controller;

import blr.dany.weatheranalyzer.service.ForecastAnalysisService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/analize")
public class AnalizeController {


    private final ForecastAnalysisService forecastAnalysisService;

    @GetMapping("/test")
    public void test(){
        System.out.println(forecastAnalysisService.analyzeForecast("Ussuriysk"));
    }
}
