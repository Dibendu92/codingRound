package main.java;

import com.sun.javafx.PlatformUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class FlightBookingTest {

	WebDriver driver = new ChromeDriver();

	@Test
	public void testThatResultsAppearForAOneWayJourney() {
		
		//Setting driver path
		setDriverPath();
		
		// Opening Clear Trip
		driver.get("https://www.cleartrip.com/");
		
		waitFor(driver, 2000, driver.findElement(By.id("OneWay")));
		
		//Clicking on the one way radio button
		driver.findElement(By.id("OneWay")).click();

		// Enter value in the from tag
		driver.findElement(By.xpath("//input[@id='FromTag']")).clear();
		driver.findElement(By.xpath("//input[@id='FromTag']")).sendKeys("Bangalore");

		// wait for the auto complete options to appear for the origin
		waitFor(driver, 4000, driver.findElement(By.id("ui-id-1")));
		
		// select the first item from the destination auto complete list
		List<WebElement> originOptions = driver.findElement(By.id("ui-id-1")).findElements(By.tagName("li"));
		originOptions.get(0).click();
		
		// Enter value in the to tag
		driver.findElement(By.xpath("//input[@id='ToTag']")).clear();
		driver.findElement(By.xpath("//input[@id='ToTag']")).sendKeys("Delhi");

		// wait for the auto complete options to appear for the destination

		waitFor(driver, 4000, driver.findElement(By.id("ui-id-2")));
		
		// select the first item from the destination auto complete list
		List<WebElement> destinationOptions = driver.findElement(By.id("ui-id-2")).findElements(By.tagName("li"));
		destinationOptions.get(0).click();
		
		// Select the date 
		driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[3]/td[7]/a")).click();

		// all fields filled in. Now click on search
		driver.findElement(By.id("SearchBtn")).click();
		
		// wait for the search result to appear
		waitFor(driver, 5000, driver.findElement(By.className("searchSummary")));
		
		// verify that result appears for the provided journey search
		Assert.assertTrue(isElementPresent(By.className("searchSummary")));

		// close the browser
		driver.quit();

	}
	//Explicit Wait Function
	private void waitFor(WebDriver driver, int durationInMilliSeconds, WebElement element) {

		WebDriverWait wait = new WebDriverWait(driver, durationInMilliSeconds);
		wait.until(ExpectedConditions.visibilityOf(element));

	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
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
