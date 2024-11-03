package tests;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import utils.DriverManager;


public class MobileApp {

    AndroidDriver driver;

    public void testSelectGender() {
        driver = DriverManager.setDriver();  // Initialize the driver inside a method

        if (driver != null) {
//            By maleButton = By.xpath("//android.widget.Button[@resource-id='trendyol.com:id/buttonSelectGenderMan']");
//            driver.findElement(maleButton).click();
        }

        // Close the driver session after the test
        if (driver != null) {
            driver.quit();
        }
    }

    public static void main(String[] args) {
        MobileApp app = new MobileApp();
        app.testSelectGender();  // Run the test
    }
}