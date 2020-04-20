package Base;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC004_HPStore {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.silentOutput", "true");
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--disable notifications");

		ChromeDriver driver = new ChromeDriver();
		driver.get("https://store.hp.com/in-en/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//	try
//	 {
//		driver.findElementByXPath("//button[text()='Accept Cookies']").click();
//	}
//	catch (NoSuchElementException exception) {
//		System.out.println("Exception2 is catched");
//	}
//	
//	try {
//		driver.findElementByXPath("//span[@class='optly-modal-close close-icon']").click();
//	}
//	catch (NoSuchElementException exception) {
//		System.out.println("Exception1 is catched");
//	}

		// Mouse over on Laptops menu and click on Pavilion
		driver.findElementByXPath("//button[text()='Accept Cookies']").click();
		Thread.sleep(3000);
		WebElement laptops = driver.findElementByXPath("//span[text()='Laptops']");
		Actions a1 = new Actions(driver);
		a1.moveToElement(laptops).pause(3000).click(driver.findElementByXPath("//span[text()='Pavilion']")).perform();

//        try
//   	 {
//   		driver.findElementByXPath("//button[text()='Accept Cookies']").click();
//   	}
//   	catch (NoSuchElementException exception) {
//   		System.out.println("Exception2 is catched");
//   	}
//        
//   	try {
//   		driver.findElementByXPath("//span[@class='optly-modal-close close-icon']").click();
//   	}
//   	catch (NoSuchElementException exception) {
//   		System.out.println("Exception1 is catched");
//   	}
//        
//        
		// 3) Under SHOPPING OPTIONS -->Processor -->Select Intel Core i7

		// driver.findElementByXPath("//button[text()='Accept Cookies']").click();

		// Under SHOPPING OPTIONS --Processor --Select Intel Core i7
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,250)");
		Thread.sleep(3000);

		driver.findElementByXPath("(//span[text()='Processor'])[2]").click();
		driver.findElementByXPath("//span[text()='Intel Core i7']").click();
//      WebDriverWait wait=new WebDriverWait(driver,140);
//        
//        wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("(//span[text()='Processor'])[2]")));
//        
//        driver.findElementByXPath("(//span[text()='Processor'])[2]").click();
//        Thread.sleep(2000);

		Thread.sleep(5000);

		// Hard Drive Capacity --More than 1TB
		WebDriverWait wait = new WebDriverWait(driver, 140);
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("(//li[@class='item']//span[text()='More than 1 TB'])[1]")));
		driver.findElement(By.xpath("(//li[@class='item']//span[text()='More than 1 TB'])[1]")).click();
		System.out.println("Hard Disk capacity More than 1 TB selected");
		// driver.findElementByXPath("//span[text()='More than 1 TB']").click();
		driver.findElementByXPath("//div[@class=\"inside_closeButton fonticon icon-hclose\"]").click();

		// Select Sort By Price Low to High
		js.executeScript("window.scrollBy(0,600)");
		driver.findElementByXPath("//div[@class='product details product-item-details']").click();
		WebElement Sorter = driver.findElementById("sorter");

		Select select = new Select(Sorter);

		select.selectByValue("price_asc");
		Thread.sleep(3000);
// 		if(driver.findElementByXPath("//div[@class='inside_closeButton fonticon icon-hclose']").isDisplayed())
// 		{
// 			driver.findElementByXPath("//div[@class='inside_closeButton fonticon icon-hclose']").click();
// 		}
// 		else
// 		{
// 			System.out.println("There is no popup is displayed");
// 		}
//         WebElement sortBy =driver.findElementById("sorter");
// 		//WebDriverWait wait = new WebDriverWait(driver,30);
// 		wait.until(ExpectedConditions.elementToBeClickable(sortBy)).click();
// 		Select sortBySelect = new Select(sortBy);
// 		sortBySelect.selectByVisibleText("Price : Low to High ");

		// Print the First resulting Product Name and Price
		String product_name = driver
				.findElementByXPath(
						"(//div[@class='product details product-item-details'])[1]//a[@class='product-item-link']")
				.getText();
		String p = driver.findElementByXPath("(//span[@class='price-wrapper '])[1]").getText();
		String product_price = p.replaceAll("\\D", "");
		System.out.println("The product name is:" + product_name + " and Price is:" + product_price);
		
		// Click on Add to Cart
		Thread.sleep(2000);
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElementByXPath("(//button[@title='Add To Cart'])[1]")));
		driver.findElementByXPath("(//button[@title='Add To Cart'])[1]").click();
		Thread.sleep(2000);
		// Click on Shopping Cart icon -- Click on View and Edit Cart
		driver.findElementByXPath("//a[@class='action showcart']").click();
		driver.findElementByXPath("//span[text()='View and edit cart']").click();
		Thread.sleep(2000);

		// Check the Shipping Option -- Check availability at Pincode
		driver.findElementByXPath("//input[@name='pincode']").sendKeys("600032");

		driver.findElementByXPath("(//button[@type='button'])[4]").click();
		Thread.sleep(2000);
		String del_status = driver.findElementByXPath("//span[@class='available']").getText();
		System.out.println(del_status);

		// Verify the order Total against the product price
		String Gt = driver.findElementByXPath("//tr[@class='grand totals']").getText();
		String G_total = Gt.replaceAll("\\D", "");
		// System.out.println(G_total);

		// Proceed to Checkout if Order Total and Product Price matches
		if (G_total.equals(product_price)) {
			driver.findElementByXPath("//span[text()='Proceed to Checkout']").click();
		} else {
			System.out.println("Product price and total is mismathed");

		}
		// Assert.assertEquals(Price, OrderTotal);
		// System.out.println("Order total and Price matches");

		// Click on Place Order
		driver.findElementByXPath("(//span[text()='Place Order'])[4]").click();

		// Capture the Error message and Print
		String error_msg = driver.findElementByXPath("//div[@id='customer-email-error']").getText();
		System.out.println("the error message is :"+error_msg);
		// Close Browser
		driver.close();

	}
}
