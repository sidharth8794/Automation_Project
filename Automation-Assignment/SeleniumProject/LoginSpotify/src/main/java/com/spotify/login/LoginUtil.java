package com.spotify.login;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginUtil {

    public static boolean logIn(String userName, String password) {
        boolean result = false;
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            driver.get("https://accounts.spotify.com/en/login");
            driver.manage().window().maximize();

            // Step 1: Enter Email or Username
            WebElement usernameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.id("login-username")));
            usernameInput.sendKeys(userName);

            // Step 2: Optional - Click "Continue" if it appears
            try {
                WebElement continueBtn = wait.until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//button[@id=\"login-button\"]//span[contains(text(), 'Continue')]")));

                if (continueBtn.isDisplayed()) {
                    continueBtn.click();
                }
            } catch (TimeoutException e) {
                System.out.println("Continue button not present, skipping...");
            }

            // Step 3: Optional - Click "Continue with password" if it appears
            try {
                WebElement continueWithPasswordBtn = wait.until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//button[contains(text(), 'Log in with a password')]")));

                if (continueWithPasswordBtn.isDisplayed()) {
                    continueWithPasswordBtn.click();
                }
            } catch (TimeoutException e) {
                System.out.println("Continue with password button not present, skipping...");
            }

            // Step 4: Enter Password
            WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.id("login-password")));
            passwordInput.sendKeys(password);

            // Step 5: Click "Log In"
            WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[@id='login-button']//span[contains(text(), 'Log In')]")));

            loginBtn.click();

            System.out.println("Login submitted.");

            //Step 6: Validate that log in is successful
            WebElement logOutBtn = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//button//span[contains(text(), 'Log Out')]")));
            if (logOutBtn.isDisplayed()) {
                System.out.println("Login successful.");
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
             driver.quit();
        }
        return result;
    }
}
