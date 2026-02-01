package utils;

import base.DriverFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtil {
    public static WebDriverWait getWait() {
        long timeout = Long.parseLong(ConfigReader.get("timeout"));
        return new WebDriverWait(
                DriverFactory.getDriver(),
                Duration.ofSeconds(timeout)
        );
    }

}
