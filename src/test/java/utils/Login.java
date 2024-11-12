package utils;

import io.appium.java_client.android.AndroidDriver;
import io.github.cdimascio.dotenv.Dotenv;
import org.openqa.selenium.By;
import java.util.logging.Logger;

import utils.DriverManager;

public class Login {
    private static final Logger logger = Logger.getLogger(Login.class.getName());

    private static final By emailInput = By.id("trendyol.com:id/editTextEmail");
    private static final By passwordInput = By.id("trendyol.com:id/editTextPassword");
    private static final By loginButton = By.id("trendyol.com:id/buttonLogin");

    private static Dotenv dotenv = Dotenv.load(); // Load .env file

    public static void performLogin(AndroidDriver d) {
        String email = dotenv.get("APP_EMAIL");
        String password = dotenv.get("APP_PASSWORD");

        if (email == null || password == null) {
            throw new IllegalStateException("Email or password environment variables are not set.");
        }

        try {
            logger.info("Entering email");
            d.findElement(emailInput).sendKeys(email);  // Enter email

            logger.info("Entering password");
            d.findElement(passwordInput).sendKeys(password);  // Enter password

            logger.info("Clicking login button");
            d.findElement(loginButton).click();  // Click login button
        } catch (Exception e) {
            logger.severe("Login failed: " + e.getMessage());
            throw e;
        }
    }


}
