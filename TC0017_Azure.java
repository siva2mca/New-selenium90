package evaluation;

import java.io.File;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC0017_Azure {

	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		System.setProperty("webdriver.chrome.silentOutput", "true");
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		// chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("plugins.always_open_pdf_externally", true);
		chromePrefs.put("download.default_directory", "D:\\downloads");
		// chromePrefs.put("safebrowsing.enabled", "false");
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", chromePrefs);
		ChromeDriver driver = new ChromeDriver(options);

		driver.get("https://azure.microsoft.com/en-in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Click on Pricing
		driver.findElementById("navigation-pricing").click();
		Thread.sleep(3000);
		// 3) Click on Pricing Calculator
		driver.findElementByXPath("//a[contains(text(),'Pricing calculator')]").click();
		Thread.sleep(2000);
		// 4) Click on Containers
		driver.findElementByXPath("//button[@data-event-property='containers']").click();
		Thread.sleep(2000);
		// 5) Select Container Instances
		driver.findElementByXPath("(//span[text()='Container Instances'])[3]").click();

		// 6) Click on Container Instance Added View

//Actions  a1=new Actions(driver);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath
//		driver.findElementById("new-module-loc").click();
		Actions a1 = new Actions(driver);
		a1.sendKeys(Keys.PAGE_DOWN);
		a1.perform();
		// 7) Select Region as "South India"

		WebElement region = driver.findElementByXPath("(//select[@name='region'])[1]");
		Select dropdown = new Select(region);
		dropdown.selectByValue("asia-pacific-southeast");

		// 8) Set the Duration as 180000 seconds
		driver.findElementByXPath("(//div[@class='wa-textNumber']//input[@name='seconds'])[1]")
				.sendKeys(Keys.BACK_SPACE);
		driver.findElementByXPath("(//div[@class='wa-textNumber']//input[@name='seconds'])[1]")
				.sendKeys(Keys.BACK_SPACE);
		driver.findElementByXPath("(//div[@class='wa-textNumber']//input[@name='seconds'])[1]").sendKeys("180000");
		System.out.println("Duration 180000 entered");

		// 9) Select the Memory as 4GB
		WebElement memory = driver.findElementByXPath("(//select[@name='memory'])[1]");
		Select drop_mem = new Select(memory);
		drop_mem.selectByValue("4");

		// 10) Enable SHOW DEV/TEST PRICING
		a1.sendKeys(Keys.PAGE_DOWN);
		a1.perform();
		driver.findElementByXPath("//span[text()='Show Dev/Test Pricing']").click();
		Thread.sleep(1000);

		// 11) Select Indian Rupee as currency
		WebElement currency = driver.findElementByXPath("//select[@class='select currency-dropdown']");
		Select cur_drop = new Select(currency);
		cur_drop.selectByValue("INR");

		// 12) Print the Estimated monthly price
		System.out.println("Estimated Monthly Price is: " + driver
				.findElementByXPath("(//span[text()='Monthly cost']/parent::div/span[@class='numeric']/span)[1]")
				.getText());

		// 13) Click on Export to download the estimate as excel
		driver.findElementByXPath("//button[text()='Export']").click();

		// 14) Verify the downloded file in the local folder
		// Way1
//		File currentFile=new File("D:\\downloads");
//		if(currentFile.exists())
//		{
//			System.out.println("file path is correct");
//		}
//		else
//		{
//			System.out.println("File path is wrong");
//		}
		//// Way2
		String downloadpath = "D:\\downloads";
		String filename = "ExportedEstimate.xlsx";
		File dir = new File(downloadpath);
		File[] dir_contents = dir.listFiles();
		boolean flag = false;
		for (int i = 0; i < dir_contents.length; i++) {
			if (dir_contents[i].getName().equals(filename))
				flag = true;
		}

		if (flag == true) {
			System.out.println("File path is verified and located correct folder");
		} else {
			System.out.println("File path is located wrong location or file name is wrong");
		}
		Thread.sleep(2000);
		// 15) Navigate to Example Scenarios and Select CI/CD for Containers

		a1.moveToElement(driver.findElementByXPath("//a[text()='Example Scenarios']")).click().build().perform();

		Thread.sleep(2000);
		driver.findElementByXPath("//button[@title='CI/CD for Containers']").click();

		// 16) Click Add to Estimate
		a1.sendKeys(Keys.PAGE_DOWN);
		a1.perform();
		driver.findElementByXPath("//button[text()='Add to estimate']").click();
		// 17) Change the Currency as Indian Rupee
		driver.findElementByXPath("//select[@class='select currency-dropdown']");
		Select cur_drop1 = new Select(currency);
		cur_drop1.selectByValue("INR");

		// 18) Enable SHOW DEV/TEST PRICING
		driver.findElementByXPath("//span[text()='Show Dev/Test Pricing']").click();
		// 19) Export the Estimate
		driver.findElementByXPath("//button[text()='Export']").click();
		// 20) Verify the downloded file in the local folder

		String filename2 = "ExportedEstimate (1).xlsx";
		flag = false;
		for (int i = 0; i < dir_contents.length; i++) {
			if (dir_contents[i].getName().equals(filename2))
				flag = true;
		}

		if (flag == true) {
			System.out.println("File path is verified and located correct folder");
		} else {
			System.out.println("File path is located wrong location or file name is wrong");
		}

	}

}

//07/05/2020
//==========
//1) Go to https://azure.microsoft.com/en-in/
//2) Click on Pricing
//3) Click on Pricing Calculator
//4) Click on Containers
//5) Select Container Instances
//6) Click on Container Instance Added View
//7) Select Region as "South India"
//8) Set the Duration as 180000 seconds
//9) Select the Memory as 4GB
//10) Enable SHOW DEV/TEST PRICING
//11) Select Indian Rupee  as currency
//12) Print the Estimated monthly price
//13) Click on Export to download the estimate as excel
//14) Verify the downloded file in the local folder
//15) Navigate to Example Scenarios and Select CI/CD for Containers
//16) Click Add to Estimate
//17) Change the Currency as Indian Rupee
//18) Enable SHOW DEV/TEST PRICING
//19) Export the Estimate
//20) Verify the downloded file in the local folder