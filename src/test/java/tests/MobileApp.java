package tests;

import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;

import utils.DriverManager;
import utils.Login;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import java.nio.file.Paths;


import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MobileApp {
    private AndroidDriver driver;
    private By myAccount = By.id("trendyol.com:id/tab_account");
    private By loginButton = By.id("trendyol.com:id/buttonLogin");
    private By emailInput = By.id("trendyol.com:id/editTextEmail");
    private By passwordInput = By.id("trendyol.com:id/editTextPassword");
    private By emailError = By.id("trendyol.com:id/textinput_error");
    private By dismissButton = By.id("trendyol.com:id/buttonDismiss");
    private By secondDismissButton = By.id("trendyol.com:id/imageButtonClose");
    private By snackbarError = By.id("trendyol.com:id/snackbar_text");
    private By userOrders = By.xpath("//android.widget.GridView[@resource-id='trendyol.com:id/recyclerViewGroups']/android.view.ViewGroup[1]");

    @BeforeEach
    public void init() {
        driver = DriverManager.setDriver();  // Initialize the driver

        if (driver != null) {
            dismissInitialPopups();  // Handle initial popups if displayed
            driver.findElement(myAccount).click();  // Navigate to "My Account"
        }
    }

    private void dismissInitialPopups() {
        try {
            if (driver.findElement(dismissButton).isDisplayed()) {
                driver.findElement(dismissButton).click();
            }
            if (driver.findElement(secondDismissButton).isDisplayed()) {
                driver.findElement(secondDismissButton).click();
            }
        } catch (Exception e) {
            // Ignore if elements are not found or not displayed
        }
    }

    @Test
    public void testWithEmptyText() {
        driver.findElement(loginButton).click();
        WebElement errorText = driver.findElement(emailError);
        assertNotNull(errorText);  // Check if error message is displayed
    }

    @Test
    public void testWithDummyText() {
        String dummyText = "loremipsum";
        driver.findElement(emailInput).sendKeys(dummyText);  // Enter dummy text
        driver.findElement(loginButton).click();
        WebElement errorText = driver.findElement(emailError);
        assertNotNull(errorText);  // Check if error message is displayed
    }

    @Test
    public void testWithFalsePassword() {
        String email = "loremipsum@gmail.com";
        String password = "123456";
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(loginButton).click();
        assertNotNull(driver.findElement(snackbarError));
    }

    @Test
    public void correctLoginTest() throws IOException {
        Login.performLogin(driver);

        assertNotNull(driver.findElement(userOrders));
        takeScreenshot("login_test_screenshot.png");

    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();  // Close the driver
        }
    }

    public void takeScreenshot(String filename) throws IOException {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        File destination = new File(System.getProperty("user.dir") + "/screenshots/" + filename);

        Files.createDirectories(Paths.get(System.getProperty("user.dir") + "/screenshots/"));
        if (destination.exists()) {
            destination.delete();
            System.out.println("Existing screenshot deleted.");
        }
        Files.copy(screenshot.toPath(), destination.toPath());
        System.out.println("Screenshot saved: " + destination.getAbsolutePath());
    }

    public static void main(String[] args) {
        MobileApp app = new MobileApp();
        app.init();
//        app.testSelectGender();  // Run the first test
//        app.testWithDummyText();  // Run the second test
        app.tearDown();
    }
}
