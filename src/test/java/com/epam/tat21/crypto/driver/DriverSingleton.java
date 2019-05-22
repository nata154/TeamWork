package com.epam.tat21.crypto.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverSingleton {
	private static WebDriver driver;
	public static final String BROWSER = "browser";

	private DriverSingleton() {
	}

	public static WebDriver getDriver() {
		if (driver == null) {
			switch (System.getProperty("browser")) {

			case "firefox": {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
			}
			case "chrome": {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
			}
			case "edge": {
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
			}
			default: {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
			}
			}
			driver.manage().window().maximize();
		}
		return driver;
	}

	public static void closeDriver() {
		driver.quit();
		driver = null;
	}
}
