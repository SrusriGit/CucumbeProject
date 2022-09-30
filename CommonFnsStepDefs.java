package com.sat.StepDefinitions;

import java.io.FileInputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sat.Pages.CommonFns;
import com.sat.Pages.CustomerPage;
import com.sat.testUtil.Assertions;
import com.sat.testUtil.Log;
import com.sat.testUtil.Testutil;
import com.sat.testUtil.Wait;
import com.sat.testbase.TestBase;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CommonFnsStepDefs {

	TestBase testbase = new TestBase();
	WebDriver driver = TestBase.getDriver();

	private CustomerPage accountpage = new CustomerPage(driver);
	private CommonFns common = new CommonFns(driver);
	Actions action = new Actions(driver);
	Assertions assertion = new Assertions(driver);

	Testutil testUtil = new Testutil(driver);

	@When("user click on settings")
	public void user_click_on_settings() {
		try {
			testUtil.clickOn(driver, driver.findElement(By.xpath("//*[@title='Dynamics 365']")), 40);
			Thread.sleep(3000);
			Wait.untilPageLoadComplete(driver);
			testUtil.clickOn(driver, common.settings(), 60);
			Wait.untilPageLoadComplete(driver);
		} catch (Exception e) {
			System.out.println("Exception :" + e + " has occurred");
		}
	}

	@Then("user selects Settings drop down")
	public void user_select_Settings_drop_down() {
		try {
			Log.info("User selects Settings drop down");
			Wait.untilPageLoadComplete(driver);
			testUtil.clickOn(driver, common.downButtonArrow(), 20);
		} catch (Exception e) {
			System.out.println("Exception :" + e + " has occurred");
		}
	}

	@And("user selects {string} under {string}")
	public void user_selects(String role, String label) {
		try {
			Wait.untilPageLoadComplete(driver);
			testUtil.clickOn(driver, common.selectGroupOfSettings(role, label), 20);
		} catch (Exception e) {
			System.out.println("Exception :" + e + " has occurred");
		}

	}

	@And("user searches with the username")
	public void Clickoncontactname() throws InterruptedException {
		try {
			Properties prop = new Properties();
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/java/com/sat/config/config.properties");
			String name = prop.getProperty("username");
			common.searchForRecords().sendKeys(name, Keys.ENTER);
			Wait.untilPageLoadComplete(driver);
			WebDriverWait wait = new WebDriverWait(driver, 40);
			wait.until(ExpectedConditions
					.elementToBeClickable(driver.findElement(By.xpath("//a[@title='" + name + "']"))));
			testUtil.jsclick(driver.findElement(By.xpath("//a[@title='" + name + "']")), driver);
			Thread.sleep(3000);
			Wait.untilPageLoadComplete(driver);
		} catch (Exception e) {
			System.out.println("Exception :" + e + " has occurred");
		}

	}

	@And("user select a security role {string}")
	public void user_select_a_security_role(String securityRole) throws InterruptedException {
		try {
			Wait.untilPageLoadComplete(driver);
			assertion.CheckAssertionTrue(common.manageUserRole().getText().contentEquals("Manage User Roles"), common.manageUserRole().getText()+"is visible instead of Manage User Roles");
			if(!common.selectRole(securityRole).isSelected())
			{
				testUtil.clickOn(driver, common.selectRole(securityRole), 60);
				assertion.CheckAssertionTrue(common.selectRole(securityRole).isSelected(), securityRole+" is not selected");
			}
		} catch (Exception e) {
			System.out.println("Exception :" + e + " has occurred");
		}

	}
}
