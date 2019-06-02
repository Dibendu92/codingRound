package main.java;

import com.sun.javafx.PlatformUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignInTest {

    WebDriver driver = new ChromeDriver();

    @Test
    public void shouldThrowAnErrorIfSignInDetailsAreMissing() {

        setDriverPath();

        // open clear trip
        driver.get("https://www.cleartrip.com/");
        
        // wait for open the cleartrip.com
        waitFor(driver,2000,driver.findElement(By.linkText("Your trips")));
        
        // click on your trip option
        driver.findElement(By.linkText("Your trips")).click();
        
        // click on sign in button
        driver.findElement(By.id("SignIn")).click();
        
        // switch to frame
        driver.switchTo().frame("modal_window");
        
        // wait for sign in window to open
        waitFor(driver,4000,driver.findElement(By.id("signInButton")));
        
        //click on the sign in button
        driver.findElement(By.id("signInButton")).click();
        
        //wait for sign in error
        waitFor(driver,2000,driver.findElement(By.id("errors1")));
        String errors1 = driver.findElement(By.id("errors1")).getText();
        
        // verify the error after clicking the sign in button
        Assert.assertTrue(errors1.contains("There were errors in your submission"));
        driver.quit();
    }

    //Explicit Wait Applied
    private void waitFor(WebDriver driver, int durationInMilliSeconds, WebElement element) {

		WebDriverWait wait = new WebDriverWait(driver, durationInMilliSeconds);
		wait.until(ExpectedConditions.visibilityOf(element));

	}

    private void setDriverPath() {
        if (PlatformUtil.isMac()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver");
        }
        if (PlatformUtil.isWindows()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        }
        if (PlatformUtil.isLinux()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
        }
    }


}
