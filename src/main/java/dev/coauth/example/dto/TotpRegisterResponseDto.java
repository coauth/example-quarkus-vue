package dev.coauth.example.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TotpRegisterResponseDto {

    public static class ErrorDetails {
        @JsonProperty("code")
        private int errorCode;

        @JsonProperty("message")
        private String errMessage;

        public ErrorDetails(int errorCode, String errMessage) {
            this.errorCode = errorCode;
            this.errMessage = errMessage;
        }
    }
    @JsonProperty("error")
    private ErrorDetails error;
    @JsonProperty("data")
    private String data;

    public TotpRegisterResponseDto(ErrorDetails error, String data){
        this.data=data;
        this.error=error;
    }

    public static TotpRegisterResponseDto success(String data) {
        return new TotpRegisterResponseDto(null, data);
    }

    public static TotpRegisterResponseDto error(int errorCode, String errMessage) {
        return new TotpRegisterResponseDto(new ErrorDetails(errorCode, errMessage), null);
    }
}
