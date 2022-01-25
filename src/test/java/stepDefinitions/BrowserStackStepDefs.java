package stepDefinitions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.utils.ConfigFileReader;
import com.utils.ExcelReader;
import com.utils.XlsReader;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserStackStepDefs {

	WebDriver driver = null;
	

	@Given("User navigates to BrowserStacklogin page")
	public void user_navigates_to_BrowserStacklogin_page() {
//		System.setProperty("webdriver.chrome.driver",
//				"C:/Users/ADMIN/workspaceNew/MyProject01/src/test/resources/Drivers/chromedriver.exe");
//		WebDriverManager.chromedriver().clearDriverCache().forceDownload().setup();
		ChromeOptions options = new ChromeOptions();

        //Add chrome switch to disable notification - "**--disable-notifications**"
        options.addArguments("--disable-notifications");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.browserstack.com/users/sign_in");
	}

	@When("user enters valid username and password")
	public void user_enters_valid_username_pwd() throws IOException {
		File file = new File("C:/Users/ADMIN/CucumberWorkspace/MyProject01/src/main/java/com/utils/config.properties");
		FileInputStream fileInput = new FileInputStream(file);
		
		Properties prop = new Properties();
		prop.load(fileInput);
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");
		driver.findElement(By.id("user_email_login")).sendKeys(username);
		driver.findElement(By.id("user_password")).sendKeys(password);

		
	}
	
	@When("user enters credentials from excel {string} with row {int}")
	public void user_enters_credentials_from_excel_with_row(String SheetName, Integer RowNumber) throws InvalidFormatException, IOException {
	 
//		XlsReader reader = new XlsReader("C:/Users/ADMIN/CucumberWorkspace/MyProject01/src/test/java/TestData/Credentials.xlsx");
////		List<Map<String,String>> testData = reader.getData("C:/Users/ADMIN/CucumberWorkspace/MyProject01/src/test/java/TestData/Credentials.xlsx", SheetName);
////		String username = testData.get(RowNumber).get("Username");
////		String password = testData.get(RowNumber).get("Password");
//		String username = reader.getCellData(SheetName,0,RowNumber);
//		String password = reader.getCellData(SheetName,1,RowNumber);
//		driver.findElement(By.id("user_email_login")).sendKeys(username);
//		driver.findElement(By.id("user_password")).sendKeys(password);
		
		File file = new File("C:/Users/ADMIN/CucumberWorkspace/MyProject01/src/main/java/com/utils/config.properties/config.properties");
		FileInputStream fileInput = new FileInputStream(file);
		
		Properties prop = new Properties();
		prop.load(fileInput);
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");
		driver.findElement(By.id("user_email_login")).sendKeys(username);
		driver.findElement(By.id("user_password")).sendKeys(password);
	}


	@And("clicks on login")
	public void clicks_on_login() {
//		WebDriverWait wait = new WebDriverWait(driver, (50));
//		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='submit' and @value='Sign me in']")));
		
		driver.findElement(By.xpath("//div/input[@id='user_submit']")).submit();	
	}

	@Then("user is navigated to Browserstack page")
	public void user_is_navigated_to_Browserstack_page() throws InterruptedException {
		
		// If credentials are invalid
		List<WebElement> ele = driver.findElements(By.xpath("//div[contains(@class,'error-msg hide')]"));
		if(ele.size()!=0)
		{
			System.out.println("Invalid credentials used");
			driver.quit();
			
		}else
		{
			WebDriverWait wait = new WebDriverWait(driver, (50));
			wait.until(ExpectedConditions.urlToBe("https://automate.browserstack.com/dashboard/v2/getting-started"));
			driver.close();
			driver.quit();
		}
		
	}

	@Given("User navigates to BrowserStack home page")
	public void user_navigates_to_BrowserStack_home_page() {
		ChromeOptions options = new ChromeOptions();

        //Add chrome switch to disable notification - "**--disable-notifications**"
        options.addArguments("--disable-notifications");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.browserstack.com/");
	}

	@When("user clicks on the icon {string} to navigate")
	public void user_clicks_on_the_icon_to_navigate(String icon) throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		List<WebElement> ele = driver.findElements(By.id("accept-cookie-notification"));
		if(!ele.isEmpty())
			driver.findElement(By.id("accept-cookie-notification")).click();
		WebElement navigate = driver.findElement(By.xpath("//div[@class='row']//a[contains(@class,'product-cards')][@title='"+icon+"']"));
		System.out.println(navigate);
		Thread.sleep(30);
		navigate.click();
		
	}

	@Then("user is navigated to selected {string} page")
	public void user_is_navigated_to_selected_website_page(String icon) {
		WebDriverWait wait = new WebDriverWait(driver, (50));
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		wait.until(ExpectedConditions.urlContains(icon.toLowerCase()));
		if(driver.getCurrentUrl().contains(icon.toLowerCase()))
		{
			Assert.assertTrue(driver.getCurrentUrl().contains(icon.toLowerCase()));
		}
			driver.quit();
	}
}
