package main.java;

import com.sun.javafx.PlatformUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class HotelBookingTest {

	WebDriver driver = new ChromeDriver();

	// Constructor to initialize web elements
	public HotelBookingTest() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.XPATH, using = "//a[contains(@title,'Find hotels in destinations around the world')]")
	private WebElement hotelLink;

	@FindBy(id = "Tags")
	private WebElement localityTextBox;

	@FindBy(id = "SearchHotelsButton")
	private WebElement searchButton;

	@FindBy(id = "travellersOnhome")
	private WebElement travellerSelection;

	@Test
	public void shouldBeAbleToSearchForHotels() {

		setDriverPath();
		//open clear trip
		driver.get("https://www.cleartrip.com/");
		
		// open hotel link
		hotelLink.click();
		
		// enter value to the locality text box
		localityTextBox.sendKeys("Indiranagar, Bangalore");
		
		// select no. of rooms and no. of adults
		new Select(travellerSelection).selectByVisibleText("1 room, 2 adults");
		
		//Click on the search button
		searchButton.click();
		driver.quit();

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
