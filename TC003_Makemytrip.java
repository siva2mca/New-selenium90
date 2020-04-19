package Base;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC003_Makemytrip {

	public static void main(String[] args) throws InterruptedException {
		// Load the URL
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--disable notifications");
		ChromeDriver driver = new ChromeDriver(option);
		driver.get("https://www.makemytrip.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// Click Hotels
		driver.findElementByXPath("//a[@class='makeFlex hrtlCenter column']").click();

		// Enter city as Goa, and choose Goa, India
		driver.findElementByXPath("(//div[@class='hsw_inner']//div)[1]").click();
		Thread.sleep(2000);
		driver.findElementByXPath("//input[@placeholder='Enter city/ Hotel/ Area/ Building']").sendKeys("goa");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//p[contains(text(),'Goa, India')]")).click();

		// Enter Check in date as Next month 15th (May 15) and Check out as start date+5
		driver.findElementById("checkin").click();
		driver.findElementByXPath("(//div[text()='15'])[2]").click();

		String checkin = driver.findElementByXPath("(//div[@class='DayPicker-Month'])[2]").getText();
		// System.out.println(checkin);
		// int startDate = Integer.parseInt(checkin);
		// WebElement currentMonth =
		// driver.findElementByXPath("(//div[@class='DayPicker-Month'])[2]");
		// currentMonth.findElement(By.xpath("(//div[text()='"+(checkin+5)+"'])[2]")).click();
		driver.findElementByXPath("(//div[text()='20'])[2]").click();
//		Date date = new Date(startDate); // Get the current date
//		DateFormat sdf = new SimpleDateFormat("dd"); //Get only the date (and not month, year, time etc)
//		String today = sdf.format(date); // Get today's date		

		// Click on ROOMS & GUESTS and click 2 Adults and one Children(age 12). Click
		// Apply Button.
		driver.findElementById("guest").click();

		// driver.findElementByXPath("//label[contains(text(),'ROOMS &
		// GUESTS')]").click();
		driver.findElementByXPath("(//li[text()='2'])[1]").click();
		driver.findElementByXPath("(//li[text()='1'])[2]").click();
		WebElement e = driver.findElementByXPath("//select[@class='ageSelectBox']");
		Select age = new Select(e);
		age.selectByVisibleText("12");
		driver.findElementByXPath("//button[text()='APPLY']").click();
		// driver.findElementById("hsw_apply_changes_btn").click();
		Thread.sleep(3000);

		// Click Search button
		driver.findElementByXPath("//button[@id='hsw_search_button']").click();
		Thread.sleep(3000);

		// Select locality as Baga
		driver.findElementByXPath("//label[text()='Baga']").click();
		Thread.sleep(3000);
		// Select 5 start in Star Category under Select Filters
		driver.findElementByXPath("//label[text()='5 Star']").click();
//			WebDriverWait wait = new WebDriverWait(driver, 30);
//		wait.until(ExpectedConditions.visibilityOf(	driver.findElementByXPath("//div[text()='Star Category']/following::ul//label[text()='5 Star']")));
//		Thread.sleep(2000);
//		driver.findElementByXPath("//div[text()='Star Category']/following::ul//label[text()='5 Star']").click();
//		
		// Click on the first resulting hotel and go to the new window
		// driver.findElementByXPath("//div[@class='makeFlex
		// spaceBetween']/div)[1]").click();
		driver.findElementByXPath("(//div[@class='listingRow  bdr    '])[1]").click();
		Thread.sleep(2000);

		// Print the Hotel Name
		Set<String> windowhandles = driver.getWindowHandles();
		List<String> Listofwindows = new ArrayList<String>();
		Listofwindows.addAll(windowhandles);
		driver.switchTo().window(Listofwindows.get(2));
		Thread.sleep(2000);
		String hotelname = driver.findElementById("detpg_hotel_name").getText();
		System.out.println("The hotel name is:" + hotelname);

		// Click MORE OPTIONS link and Select 3Months plan and close
		driver.findElementByXPath("//span[text()='MORE OPTIONS']").click();
		Thread.sleep(3000);
		driver.findElementByXPath("(//span[text()='SELECT'])[1]").click();
		driver.findElementByXPath("//span[@class='close']").click();
		// Click Book this now
		
		// driver.findElementById("detpg_headerright_book_now").click();
		driver.findElement(By.linkText("BOOK THIS NOW")).click();

		// Print the Total Payable amount
		String amount = driver.findElement(By.id("revpg_total_payable_amt")).getText();
		String totalpayableamount = amount.replaceAll("[^0-9]", "");
		System.out.println("Total amont: Rs." + totalpayableamount);
		// Close the browsers
		driver.quit();

	}

}
