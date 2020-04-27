package base;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC008_Pepperfry {

	public static void main(String[] args) throws InterruptedException, IOException {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		System.setProperty("webdriver.chrome.siletOutput", "true");
		ChromeOptions option=new ChromeOptions();
		option.addArguments("--disable-popup-blocking");
		ChromeDriver driver=new ChromeDriver(option);
		driver.get("https://www.pepperfry.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//Mouseover on Furniture and click Office Chairs under Chairs
		WebElement mo=driver.findElementByXPath("(//a[text()='Furniture'])[1]");
		Actions a1=new Actions(driver);
		a1.moveToElement(mo);
		a1.pause(2000);
		a1.click(driver.findElementByXPath("//a[text()='Office Chairs']"));
		a1.perform();
		
        //click Executive Chairs
		driver.findElementByXPath("//h5[text()='Executive Chairs']").click();
		Thread.sleep(3000);
		
	    
		//Popup closed
 WebDriverWait wait =new WebDriverWait(driver,30);
//		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("(//div[@id='regPopUp']/a)")));
//				//driver.findElement(By.xpath("//div[@id='regPopUp']//a[@class='popup-close']")).click();
//        driver.findElementByXPath("(//div[@id='regPopUp']/a").click();
         
       //Change the minimum Height as 50 in under Dimensions
        // JavascriptExecutor js=(JavascriptExecutor)driver;
 		//js.executeScript("window.scrollBy,(0,250)");
 		
         driver.findElementByXPath("(//input[@class='clipFilterDimensionHeightValue'])[1]").clear();
         driver.findElementByXPath("(//input[@class='clipFilterDimensionHeightValue'])[1]").sendKeys("50",Keys.ENTER);
         Thread.sleep(2000);
         //Add "Poise Executive Chair in Black Colour" chair to Wishlist
         driver.findElementByXPath("(//a[@id='clip_wishlist_'])[2]").click();
         
         //Mouseover on Homeware and Click Pressure Cookers under Cookware
         WebElement mo1=driver.findElementByXPath("(//a[text()='Homeware'])[1]");
         a1.moveToElement(mo1);
         a1.pause(2000);
         a1.click(driver.findElementByXPath("//a[text()='Pressure Cookers']"));
         a1.perform();
         
         //Select Prestige as Brand
         driver.findElementByXPath("//label[@for='brandsnamePrestige']").click();
         Thread.sleep(2000);
         //Select Capacity as 1-3 Ltr
         driver.findElementByXPath("//li[@data-search='1 Ltr - 3 Ltr']").click();
         
         //Add "Nakshatra Cute Metallic Red Aluminium Cooker 2 Ltr" to Wishlist
         Thread.sleep(2000);
     	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@data-productname='Nakshatra Cute Metallic Red Aluminium Cooker 2 Ltr']")));
     	driver.findElement(By.xpath("//a[@data-productname='Nakshatra Cute Metallic Red Aluminium Cooker 2 Ltr']")).click();
     	
     	//10 Verify the number of items in Wishlist
    	Thread.sleep(2000);
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(("//span[@class='count_alert'])[2]"))));
    	System.out.println("Number of items in wishlist: "+driver.findElement(By.xpath("(//span[@class='count_alert'])[2]")).getText());
    	
    	//Navigate to Wishlist
    	driver.findElementByXPath("//div[@class='wishlist_bar']").click();
    	
    	//Move Pressure Cooker only to Cart from Wishlist
    	driver.findElementByXPath("(//a[@data-tooltip='Add to Cart'])[2]").click();
    	
    	//Click Proceed to Pay Securely
    	driver.findElementByXPath("//a[contains(text(),'Proceed to pay securely')]").click();
    	Thread.sleep(2000);
    	
    	//Check for the availability for Pincode 600128
    	driver.findElementByXPath("//input[@id='pin_code']").sendKeys("600128");
    	
    	//Click Proceed to Pay
    	driver.findElementByXPath("(//a[text()='PLACE ORDER'])[1]").click();
    	driver.findElementByXPath("//div[@id='ordersummary_accordian_header']").click();
    	
    	//Capture the screenshot of the item under Order Item
        WebElement order_screenshot=driver.findElementByXPath("//li[contains(@id,'payment_cart')]");  
    	File srcFile=order_screenshot.getScreenshotAs(OutputType.FILE);
    	File destFile=new File("./snaps/order_summary.png");
    	FileUtils.copyFile(srcFile, destFile);
    	
       //Close the browser
		driver.close();
		
    	
	}

}

//23/04/2020
//============
//1) Go to https://www.pepperfry.com/
//2) Mouseover on Furniture and click Office Chairs under Chairs
//3) click Executive Chairs
//4) Change the minimum Height as 50 in under Dimensions
//5) Add "Poise Executive Chair in Black Colour" chair to Wishlist
//6) Mouseover on Homeware and Click Pressure Cookers under Cookware
//7) Select Prestige as Brand
//8) Select Capacity as 1-3 Ltr
//9) Add "Nakshatra Cute Metallic Red Aluminium Cooker 2 Ltr" to Wishlist
//10) Verify the number of items in Wishlist
//11) Navigate to Wishlist
//12) Move Pressure Cooker only to Cart from Wishlist
//13) Check for the availability for Pincode 600128
//14) Click Proceed to Pay Securely
//15 Click Proceed to Pay
//16) Capture the screenshot of the item under Order Item
//17) Close the browser