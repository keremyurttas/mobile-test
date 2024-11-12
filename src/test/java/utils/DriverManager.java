package utils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class DriverManager {

    public static AndroidDriver driver;

    public static AndroidDriver setDriver() {
        // Set capabilities using DesiredCapabilities

        UiAutomator2Options options = new UiAutomator2Options().setApp("/home/kerem/Downloads/trendyol-com-811-68261104-f5911ed07ac49b6ebebf95f73bcb8254.apk").setPlatformName("Android").setPlatformVersion("15").setDeviceName("Pixel 9 Pro API 35").setAutomationName("UiAutomator2");

        try {
            // Log before creating the driver

            driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

            System.out.println("Driver initialized successfully");


            // Perform actions after driver setup
        } catch (MalformedURLException e) {
            System.err.println("MalformedURLException: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        }

        return driver;  // Return the driver instance
    }

    public static void main(String[] args) {
        setDriver();  // Call the setDriver method to start the session
    }
}
