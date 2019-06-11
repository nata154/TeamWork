package com.epam.tat21.crypto.driver;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

public class CapabilitiesProvider {

    private static DesiredCapabilities capabilities;

    public static DesiredCapabilities getCapabilities(String browser) {
        switch (browser) {
            case "firefox":
                capabilities = DesiredCapabilities.firefox();
                break;
            case "chrome":
                capabilities = DesiredCapabilities.chrome();
                break;
            case "edge":
                capabilities = DesiredCapabilities.edge();
                break;
            default: {
                capabilities = DesiredCapabilities.chrome();
            }
        }
        capabilities.setBrowserName(browser);
        capabilities.setPlatform(Platform.WINDOWS);
        return capabilities;
    }
}
