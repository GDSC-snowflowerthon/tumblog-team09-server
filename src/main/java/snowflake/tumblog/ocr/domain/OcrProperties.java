package snowflake.tumblog.ocr.domain;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "clova")
public class OcrProperties {
    private String invokeUrl; // "invoke-url"에 대응
    private String xOcrSecret; // "x-ocr-secret"에 대응

    public String getInvokeUrl() {
        return invokeUrl;
    }

    public void setInvokeUrl(String invokeUrl) {
        this.invokeUrl = invokeUrl;
    }

    public String getxOcrSecret() {
        return xOcrSecret;
    }

    public void setxOcrSecret(String xOcrSecret) {
        this.xOcrSecret = xOcrSecret;
    }
}
