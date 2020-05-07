package evaluation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Tc001_Ajio {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		System.setProperty("webdriver.chrome.silentOutput", "true");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		// options.addArguments("--incognito");
		// DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		// capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		ChromeDriver driver = new ChromeDriver(options);
		driver.get("https://www.ajio.com/shop/sale");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Enter Bags in the Search field and Select Bags in Women Handbags
		driver.findElementByXPath("//input[@name='searchVal']").sendKeys("Bags");
		Thread.sleep(2000);
		WebElement mo = driver.findElementByXPath("//span[text()='Women Handbags']");

		Actions a1 = new Actions(driver);
		a1.moveToElement(mo);
		a1.click();
		a1.perform();
		Thread.sleep(3000);

		// Click on five grid and Select SORT BY as "What's New"
		driver.findElementByXPath("//div[@class='five-grid']").click();
		Thread.sleep(2000);
		WebElement mo1 = driver.findElementByXPath("//div[@class='filter-dropdown']/select");
		Select dropdown = new Select(mo1);
		dropdown.selectByVisibleText("What's New");

		// Enter Price Range Min as 2000 and Max as 5000
		driver.findElementByXPath("//span[text()='price']").click();
		driver.findElementById("minPrice").sendKeys("2000", Keys.TAB);
		driver.findElementById("maxPrice").sendKeys("5000", Keys.ENTER);
		Thread.sleep(3000);
		// Click on the product "Puma Ferrari LS Shoulder Bag"
		// a1.sendKeys(Keys.PAGE_DOWN);
		// a1.perform();

		driver.findElementByXPath("//div[text()='Ferrari LS Shoulder Bag']").click();

		// Verify the Coupon code for the price above 2690 is applicable for your
		// product,
		// if applicable the get the Coupon Code and Calculate the discount price for
		// the coupon
		Set<String> Allwindows = driver.getWindowHandles();
		List<String> listofwindows = new ArrayList<String>();
		listofwindows.addAll(Allwindows);
		driver.switchTo().window(listofwindows.get(1));
		Thread.sleep(3000);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='prod-price-section']/div")));

		String p1 = driver.findElementByXPath("//div[@class='prod-price-section']/div").getText();
		// System.out.println("Product value:"+p1);
		// String p1 = driver.findElementByXPath("//div[@class='prod-sp']").getText();
		String prod_price = p1.replaceAll("\\D", "");
		int prodcut_price = Integer.parseInt(prod_price);
		System.out.println("The product actual price is:" + prodcut_price);

		String offer_message = driver.findElementByXPath("//div[@class='promo-desc']").getText();
		// System.out.println("offer message is :"+offer_message);
		String offerval = offer_message.substring(22, 26);

		// System.out.println(offerval);
         //String P=offer_message.substring(11, 13);
		// System.out.println("Offer Percentage value:"+P);
		// int percentage=Integer.parseInt(P.substring(0, 1));
        //String offerstring = offerval.replaceAll("\\D", "");

		int offerValue1 = Integer.parseInt(offerval);

		System.out.println("Offer Value:" + offerValue1);

		// To get promo code
		String promo_code = driver.findElementByXPath("//div[@class='promo-title']").getText();
		System.out.println("The promo code is:" + promo_code);

		if (prodcut_price >= offerValue1) {
			System.out.println("The product value is applicable for offer price:" + promo_code);

			// To get coupon code
			String pro_code = driver.findElementByXPath("//div[text()='Use Code']/br").getText();
		} else {
			System.out.println("The product is not applicable for dicount");
		}
		// to get discount for the coupon
		String dcp = driver.findElementByXPath("//div[@class='promo-discounted-price']").getText();
		String dis_price = dcp.replaceAll("\\D", "");
		int discnt_price = Integer.parseInt(dis_price);
		System.out.println("The discount amount is:" + discnt_price);
		int discount_price = prodcut_price - discnt_price;
		System.out.println("The discount value is saved after applied coupon is:" + discount_price);

		// Check the availability of the product for pincode 560043, print the expected
		// delivery date if it is available
		driver.findElementByXPath("//span[contains(text(),'Enter pin-code to know estimated')]").click();
		driver.findElementByXPath("//input[@name='pincode']").sendKeys("632301");
		driver.findElementByXPath("//button[text()='CONFIRM PINCODE']").click();

		// print the expected delivery date if it is available
		String Expected_delivery = driver.findElementByXPath("//span[@class='edd-message-success-details-highlighted']")
				.getText();
		System.out.println("The delivery expected is:" + Expected_delivery);

		// Click on Other Informations under Product Details and Print the Customer Care
		// address, phone and email
		driver.findElementByXPath("//div[text()='Other information']").click();

		// To get Address
		String Customer_Address = driver.findElementByXPath("(//span[@class='other-info'])[6]").getText();
		System.out.println("The cutomer Address:" + Customer_Address);

		// Click on ADD TO BAG and then GO TO BAG
		driver.findElementByXPath("//span[text()='ADD TO BAG']").click();
		Thread.sleep(2000);
//		WebDriverWait wait=new WebDriverWait(driver,30);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='GO TO BAG']")));
//		driver.findElementByXPath("//span[text()='GO TO BAG']").click();
		WebElement eleGoBag = driver.findElementByXPath("//span[text()='GO TO BAG']/parent::div/parent::a");
		js.executeScript("arguments[0].click()", eleGoBag);

		// Check the Order Total before apply coupon
		String order_total = driver.findElementByXPath("//span[@class='price-value bold-font']").getText();
		System.out.println("Order total is:" + order_total);

		// Enter Coupon Code and Click Apply
		driver.findElementById("couponCodeInput").sendKeys("EPIC");
		driver.findElementByXPath("//button[text()='Apply']").click();

		// Verify the Coupon Savings amount(round off if it in decimal) under Order
		// Summary and the matches the amount calculated in Product details
		String coupon_savings = driver.findElementByXPath("(//span[@class='price-value discount-price'])[2]").getText();

		System.out.println("Coupon Price:" + coupon_savings);
		String replaceCP = coupon_savings.replaceAll("[:,a-zA-Z ]", "");

		String subCP = replaceCP.substring(1);
		float floatCP = Float.parseFloat(subCP);

		int CouponSavingsAmount = (int) Math.round(floatCP);

		System.out.println("Coupon Savings amount:" + CouponSavingsAmount);
		if (CouponSavingsAmount == discount_price) {
			System.out.println("coupon Savings amount matches with caluculated discount amount");
		}

		else {
			System.out.println("The coupon savings amount is not matched");
		}

		// Click on Delete and Delete the item from Bag
		driver.findElementByXPath("//div[@class='delete-btn']").click();
		System.out.println("Deleted action done successfully");
		Thread.sleep(2000);

		// Close all the browsers
		driver.quit();
	}

}

//1) Go to www.ajio.com/shop/sale
//2) Enter Bags in the Search field and Select Bags in Women Handbags
//3) Click on five grid and Select SORT BY as "What's New"
//4) Enter Price Range Min as 2000 and Max as 5000
//5) Click on the product "Puma Ferrari LS Shoulder Bag"
//6) Verify the Coupon code for the price above 2690 is applicable for your product, 
//if applicable the get the Coupon Code and Calculate the discount price for the coupon
//7) Check the availability of the product for pincode 560043, print the expected delivery date if it is available
//8) Click on Other Informations under Product Details and Print the Customer Care address, phone and email
//9) Click on ADD TO BAG and then GO TO BAG
//10) Check the Order Total before apply coupon
//11) Enter Coupon Code and Click Apply
//12) Verify the Coupon Savings amount(round off if it in decimal) under Order Summary and the matches the amount calculated in Product details
//13) Click on Delete and Delete the item from Bag
//14) Close all the browsers