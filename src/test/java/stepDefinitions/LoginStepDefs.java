package stepDefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginStepDefs {

	WebDriver driver = null;

	@Given("User navigates to login page")
	public void user_navigates_to_login_page() {
		System.setProperty("webdriver.chrome.driver",
				"C:/Users/ADMIN/workspaceNew/MyProject01/src/test/resources/Drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.browserstack.com/users/sign_in");
	}

	@When("user enters valid credentials")
	public void user_enters_valid_credentials() {
		// Send email address
		driver.findElement(By.xpath("//input[@id='identifierId']")).sendKeys("voonasruthi2@gmail.com");
		driver.findElement(By.xpath("//div[@id='identifierNext']")).click();

		// send password
		WebElement passwordButton = driver.findElement(By.xpath("//input[@name='password']"));
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(passwordButton));
		passwordButton.sendKeys("2691@lrsk");
		
	}

	@And("clicks on login button")
	public void clicks_on_login_button() {
		driver.findElement(By.xpath("//div[@id='passwordNext']")).click();
	}

	@Then("user is navigated to Gmail page")
	public void user_is_navigated_to_Gmail_page() {
	}

}
