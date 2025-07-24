package blr.dany.weatheranalyzer.dto.response;

import lombok.Data;

@Data
public class Condition {

    private String text;
    private String icon;
    private int code;

}
