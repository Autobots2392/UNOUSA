package com.fresco.tester.base;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.json.simple.JSONObject;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fresco.tester.Helpers;
import com.fresco.tester.ProjectConstants;

import net.sourceforge.htmlunit.corejs.javascript.ast.NewExpression;

public class FrescoSearchDownload extends TestBase {

	String userName, password;
	JSONObject configNumber;

	public static final int EVEN_CLICK = 1;
	public static final int EVEN_SLEEP = 2;
	public static final int EVEN_ENTER_TEXT = 3;
	public static final int EVEN_WINDOW_CLOSE = 4;
	private static final String Hello = null;
	
	public FrescoSearchDownload(String userName, String password, JSONObject jsonObject) {
		super();
		this.userName = userName;
		this.password = password;
		this.configNumber = jsonObject;
	}

	public void startTest() throws Exception {
		// HtmlUnitDriver driver = new HtmlUnitDriver();
		// System.out.println("Page title is: " + driver.getTitle());
		WebDriver driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(ProjectConstants.TEST_END_POINT);
		// driver.manage().window().maximize();
		Dimension d = new Dimension(1366, 768);
		driver.manage().window().setSize(d);

		Thread.sleep(5000);

		// enter email.
		driver.findElement(By.id("username")).sendKeys(userName);
		// enter password.
		driver.findElement(By.id("password")).sendKeys(password);
		// enter captcha.
		String test = driver.findElement(By.xpath("//label[@id='ebcaptchatext']")).getText();
		String ans = String.valueOf(Helpers.decodeCaptcha(test));
		System.out.println("Test =" + test + " Ans =" + ans);
		driver.findElement(By.id("ebcaptchainput")).sendKeys(ans);
		driver.findElement(By.className("checkmark-signup")).click();
		// click on sign in button.
		driver.findElement(By.id("submit")).click();
		Thread.sleep(10000);

		for (int i = 0; i < configNumber.keySet().size(); i++) {
			if (configNumber.containsKey("event" + i)) {
				JSONObject event = (JSONObject) configNumber.get("event" + i);
				int eventType = ((Long) event.get("eventtype")).intValue();

				switch (eventType) {
				case EVEN_CLICK:
					try {
						handleClick((String) event.get("id"), driver);
					} catch (Exception e) {
						e.printStackTrace();
					}
					continue;
					
				case EVEN_SLEEP:
					try {
						handleSleep(((Long) event.get("timeinmilli")).intValue());
					} catch (Exception e) {
						e.printStackTrace();
					}
					continue;
					
				case EVEN_ENTER_TEXT:
					try {
						sendText(((String) event.get("id")), ((String) event.get("eventvalue")), driver);
					} catch (Exception e) {
						e.printStackTrace();
					}
					continue;
				
				case EVEN_WINDOW_CLOSE:
					
						try {
						handleWindowclose(((String) event.get("eventvalue")), driver);
						
						}
						
						catch(Exception e ){
							//driver.close();
							 System.out.println("the browser is still open");
						}
					}
					continue;
					
				}
			}
			
		Thread.currentThread();
		Thread.sleep(5000);
		/*
		 * WebDriverWait wait = new WebDriverWait(driver, 3);
		 * 
		 * 
		 * //presence in DOM wait.until(ExpectedConditions.presenceOfElementLocated(By.
		 * xpath("//a[@class='dropbtn nav-link dropbtnlogin']")));
		 * 
		 * 
		 * //clickable wait.until(ExpectedConditions.elementToBeClickable(By.
		 * xpath("//a[@class='dropbtn nav-link dropbtnlogin']"))); //WebDriverWait wait
		 * = new WebDriverWait(driver, 20);
		 * 
		 * WebElement element1
		 * =driver.findElement(By.xpath("//a[@class='dropbtn nav-link dropbtnlogin']"));
		 * //wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(
		 * "//span[contains(text(),'Hello')]"))); element1.click();
		 * System.out.println("click "); // WebDriverWait wait1 = new
		 * WebDriverWait(driver, 20); JavascriptExecutor js = ((JavascriptExecutor)
		 * driver); WebElement element2
		 * =driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
		 * js.executeScript("arguments[0].scrollIntoView(true);", element2);
		 * //js.executeScript("arguments[0].scrollIntoView(true);", element2);
		 * //wait1.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(
		 * "//a[contains(text(),'Logout')]"))); element2.click();
		 */	
		driver.get("https://uno.frescodata.com/logout.php");
		Thread.sleep(2000);
		driver.close();
		
		//driver.get("https://uno.frescodata.com/dashboard.php");
		//driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);	
		//driver.findElement(By.xpath("//span[contains(text(),'Hello')]")).click();
		//System.out.println("Done Mouse hover on logout  Menu");
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	
		//driver.findElement(By.xpath(" //a[contains(text(),'Logout')]")).click();
		//driver.close();
		//driver.quit();
		}
	

		// select Geography
//		driver.findElement(By.xpath("//div[@id='StateCollapsed']//h4[@class='panel-title collapsed']")).click();
//		driver.findElement(By.xpath("//input[@id='1']")).click();
//		Thread.sleep(5000);
//		// select industry
//		driver.findElement(By.xpath("//a[@id='v-pills-profile-tab']")).click();
//		Thread.sleep(5000);
//		driver.findElement(By.xpath("//div[@id='IndustryCollapsed']//h4[@class='panel-title collapsed']")).click();
//		driver.findElement(By.xpath("//div[@id='IndustryCollapse']//input[@type='checkbox'][1]")).click();
//		// driver.findElement(By.xpath("//div[@id='IndustryCollapse']//input[@type='checkbox'][2]")).click();
//		// click on next
//		JavascriptExecutor js1 = (JavascriptExecutor) driver;
//		WebElement element = driver.findElement(By.xpath(
//				"//div[@class='col-12 col-md-4 col-sm-4 text-center text-md-right text-sm-right']//a[@class='btn btn-primary btn-md next'][contains(text(),'Next')]"));
//		js1.executeScript("arguments[0].scrollIntoView();", element);
//		driver.findElement(By.xpath(
//				"//div[@class='col-12 col-md-4 col-sm-4 text-center text-md-right text-sm-right']//a[@class='btn btn-primary btn-md next'][contains(text(),'Next')]"))
//				.click();
//		// click on job function.
//		/*
//		 * driver.findElement(By.
//		 * xpath("//div[@id='FunctionCollapsed']//h4[@class='panel-title collapsed']")).
//		 * click();
		// driver.findElement(By.xpath(
//		 * "//div[@id='FunctionCollapse']//li[3]//label[1]//input[1]")).click();
//		 * Thread.sleep(1000);
//		 */
//		// click on phones.
		// Thread.sleep(8000);
//		driver.findElement(By.xpath("//input[@id='22']")).click();
//		Thread.sleep(3000);
//		// click on run button.
//		driver.findElement(By.xpath("//button[@id='run']")).click();
//		Thread.sleep(8000);
//		// print count
//		System.out.println(
//				"fOUND " + driver.findElement(By.xpath("//h6[@class='py-2 px-2 mb-0 display_count']")).getText());
//		/*
//		 * JavascriptExecutor jse = (JavascriptExecutor) driver;
//		 * jse.executeScript("window.scrollBy(0,0);");
//		 */
//		// click on download
//		/*
//		 * EventFiringWebDriver eventfire = new EventFiringWebDriver(driver);
//		 * eventfire.executeScript(
//		 * "document.querySelector('body.serch-result:nth-child(4)').scrollTop=500");
//		 */
//		// To zoom out 5 times
//		// Zoom out with sendkeys
//		/*
//		 * for(int i=0; i<5; i++){
//		 * driver.findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.CONTROL,Keys.
//		 * SUBTRACT)); }
//		 */
//		// Zoom out with robot class.
//
//		// System.out.println("About to zoom out");
//		// Robot robot = new Robot();
//		// for (int i = 0; i < 4; i++) {
//		// robot.keyPress(KeyEvent.VK_CONTROL);
//		// robot.keyPress(KeyEvent.VK_SUBTRACT);
//		// robot.keyRelease(KeyEvent.VK_SUBTRACT);
//		// robot.keyRelease(KeyEvent.VK_CONTROL);
//		// }
//		// To set the browser to default zoom level ie., 100%
//		// driver.findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.CONTROL,
//		// "0"));
//
//		/*
//		 * WebElement elementT =driver.findElement(By.xpath("//a[@id='download_pop']"));
//		 * 
//		 * ((JavascriptExecutor)
//		 * driver).executeScript("arguments[0].scrollIntoView(true);", elementT);
//		 * 
//		 * Thread.sleep(500);
//		 */
//		Thread.sleep(5000);
//		// click on download

//		driver.findElement(By.id("download_pop")).click();
//		driver.findElement(By.xpath("//div[@class='modal-dialog']//div[@class='modal-body']")).click();
//		// enter desired records to download.
//		driver.findElement(By.xpath("//input[@id='records']")).sendKeys("10");
//		// select download file format.
//		driver.findElement(By.xpath("//select[@id='format']")).click();
//		driver.findElement(By.xpath("//option[contains(text(),'Excel')]")).click();
//		// click on download button.
//		driver.findElement(By.xpath("//input[@id='downloadrec']")).click();
//		Thread.sleep(5000);
//
//		Thread.sleep(5000);
//// creating instance of Robot class
//		Robot rb = new Robot();
//		// pressing keys with the help of keyPress and keyRelease events
//		rb.keyPress(KeyEvent.VK_ALT);
//		rb.keyPress(KeyEvent.VK_S);
//		Thread.sleep(1000);
//		rb.keyRelease(KeyEvent.VK_S);
//		rb.keyRelease(KeyEvent.VK_ALT);
//		// Thread.sleep(1000);
//		rb.keyPress(KeyEvent.VK_ENTER);
//		rb.keyRelease(KeyEvent.VK_ENTER);
//		Thread.sleep(2000);
//		/*
//		 * JavascriptExecutor jse = (JavascriptExecutor) driver;
//		 * jse.executeScript("window.documen.write(accessCookie('transactionID'))");
//		 * System.out.println( "Your transaction id is" + jse );
//		 * System.out.println(" test execution complete " ); Thread.sleep(10000);
//		 */
//		driver.switchTo().defaultContent();
//		// Runtime.getRuntime().exec("D:\\SoftwareTestingMaterial\\AutoIt\\DownloadFile.exe");
//		Actions actions = new Actions(driver);
//		WebElement menuOption = driver.findElement(By.xpath("//span[contains(text(),'Hello')]"));
//		actions.moveToElement(menuOption).perform();
//		System.out.println("Done Mouse hover on logout  Menu");
//		driver.findElement(By.xpath(" //a[contains(text(),'Logout')]")).click();
//		driver.close();

	

	private void sendText(String id, String valule, WebDriver driver) {
		
		driver.findElement(By.xpath(id)).sendKeys(valule);
	}

	private void handleSleep(int object) {
		try {
			Thread.currentThread();
			Thread.sleep(object);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void handleClick(String id, WebDriver driver) {
		
		    try {
		       WebDriverWait wait1 = new WebDriverWait(driver,10);
		       wait1.until(ExpectedConditions.elementToBeClickable(By.xpath(id)));
		       driver.findElement(By.xpath(id)).click();

		       
		       
		    } 
		    catch (Exception e) {
		        System.out.println("Oh");
		       
		    }
	}
	private void handleWindowclose(String id, WebDriver driver) {
		
		try {
			WebElement element =driver.findElement(By.xpath("//span[contains(text(),'Hello')]"));
			element.click();
		    WebElement element2 =driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
		    element2.click();
			Thread.sleep(5000);
			driver.close();
			driver.quit();
		
		}
		catch(Exception e ){
			
			
			driver.close();
			System.out.println("the browser is still open,trying to close");
			driver.close();
		}
		}
}

	
