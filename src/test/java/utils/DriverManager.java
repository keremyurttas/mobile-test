package utils;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverManager {

    public static AndroidDriver driver;

    public static AndroidDriver setDriver() {
        // Set capabilities using DesiredCapabilities
        DesiredCapabilities caps = new DesiredCapabilities();

        // General capabilities
        caps.setCapability("platformName", "Android");  // Make sure it's case-sensitive (Android)
        caps.setCapability("platformVersion", "15");
        caps.setCapability("deviceName", "Pixel 9 Pro API 35");
        caps.setCapability("automationName", "UiAutomator2");

        // App-specific capabilities
        caps.setCapability("app", "/home/kerem/Downloads/trendyol-com-811-68261104-f5911ed07ac49b6ebebf95f73bcb8254.apk");  // Path to the app

        try {
            // Log before creating the driver

            driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), caps);

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
