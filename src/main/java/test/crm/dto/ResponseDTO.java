package test.crm.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ResponseDTO {
    @JsonProperty(value = "http_code")
    private Short httpCode;
    private String message;
}
