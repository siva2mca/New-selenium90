package Base;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class TC0002_Nykraa {

	@Test
	public void nykraa() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--disable notifications");
		ChromeDriver driver = new ChromeDriver(option);
		// 1.Load the URL
		driver.get("https://www.nykaa.com/");
		Thread.sleep(3000);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// 2. Mouseover on Brands and Mouseover on Popular
		WebElement b = driver.findElementByXPath("//a[text()='brands']");
		Actions a1 = new Actions(driver);
		a1.moveToElement(b).pause(3000).perform();
		a1.moveToElement(driver.findElementByXPath("//a[text()='Popular']")).pause(3000).perform();
		// 3.Click L'Oreal Paris
		driver.findElementByXPath("(//li[@class='brand-logo menu-links'])[5]//a/img").click();
		Thread.sleep(3000);
		// 4.newly opened window and check the title contains L'Oreal Paris
		Set<String> windowhandles = driver.getWindowHandles();
		System.out.println(windowhandles.size());
		List<String> listofwindows = new ArrayList<String>(windowhandles);
		// listofwindows.addAll(windowhandles);

		driver.switchTo().window(listofwindows.get(2));
		driver.manage().window().maximize();
		Thread.sleep(3000);
		String title = driver.getTitle();
		System.out.println("The page title is:" + title);
		if (title.contains("L'Oreal Paris")) {
			System.out.println("title is matched");
		} else {
			System.err.println("page launching failed");
		}
		Thread.sleep(3000);
		// 5.Click sort By and select customer top rated

		driver.findElementByXPath("//i[@class='fa fa-angle-down']").click();
		driver.findElementByXPath("//input[@id='3']/following-sibling::div[1]").click();
		// driver.findElementByXPath("//span[@class='pull-left']").click();
		// driver.findElementByXPath("//span[@class='pull-right']//i").click();
		// driver.findElementByXPath("(//span[text()='customer top
		// rated'])[2]").click();
		Thread.sleep(3000);
		// 6.Click Category and click Shampoo
		driver.findElementByXPath("(//div[@class='pull-right filter-options-toggle'])[1]//i").click();
		driver.findElementByXPath("//span[text()='Shampoo (21)']").click();
		Thread.sleep(3000);
		// 7.check whether the Filter is applied with Shampoo

		String filter = driver.findElementByXPath("//li[text()='Shampoo']").getText();
		if (filter.contains("Shampoo")) {
			System.out.println("filter is applied:" + filter);
		}

		// 8.Click on L'Oreal Paris Colour Protect Shampoo
		// driver.findElementByXPath("(//div[@class='card-img'])[1]").click();
		driver.findElementByXPath("//span[contains(text(),'Colour Protect Shampoo')]").click();

		// 9.new window and select size as 175ml
		Set<String> windowshandles1 = driver.getWindowHandles();
		List<String> listofwindows1 = new ArrayList<String>(windowshandles1);

		driver.switchTo().window(listofwindows1.get(3));
		Thread.sleep(3000);
		// System.out.println(driver.getTitle());
		driver.findElementByXPath("//span[text()='175ml']").click();

		// 10.Print the MRP of the product
		String MRP = driver.findElementByXPath("//span[@class='post-card__content-price-offer']").getText();
		System.out.println(MRP);
		Thread.sleep(2000);
		// 11.Click on ADD to BAG
		driver.findElementByXPath("(//div[@class='pull-left'])[1]").click();

		// 12.Go to Shopping Bag
		driver.findElementByXPath("//div[@class='AddBagIcon']").click();

		// 13.Click Proceed
		driver.findElementByXPath("//span[text()='Proceed']").click();
		// 14.Print the Grand Total amount
		String grandtotal = driver.findElementByXPath("(//div[@class='value'])[2]").getText();

		System.out.println(grandtotal);

		// 15.Click on Continue as Guest
		driver.findElementByXPath("(//button[@type='button'])[2]").click();

		// 16.Print the warning message (delay in shipment)
		String warningmsg = driver.findElementByXPath("//i[@class='warning-icon mr10']/following-sibling::div")
				.getText();
		System.out.println(warningmsg);
		// 17. Close all opened browsers
		driver.quit();

	}

}
