package com.sat.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sat.testUtil.Testutil;
import com.sat.testUtil.Wait;
import com.sat.testbase.TestBase;

public class CommonFns {

	WebDriver driver = TestBase.getDriver();
	TestBase testbase = new TestBase();
	Testutil testutil = new Testutil(driver);
	Actions actions = new Actions(driver);

	@FindBy(xpath = "//button[@aria-label='Settings']")
	WebElement settings;

	@FindBy(xpath = "//a[@class='navTabButtonLink']//span[contains(@class,'navTabButtonArrowDown')]")
	WebElement downButtonArrow;

	@FindBy(xpath = "//input[@title='Search for records']")
	WebElement searchForRecords;
	
	@FindBy(xpath = "//div[@title='Manage User Roles']")
	WebElement manageUserRoles;
	

	public CommonFns(WebDriver rdriver) {
		driver = rdriver;
		PageFactory.initElements(rdriver, this);
	}

	public WebElement settings() {
		Wait.waitUntilElementVisible(driver, settings);
		return settings;
	}

	public WebElement downButtonArrow() {
		Wait.waitUntilElementVisible(driver, downButtonArrow);
		return downButtonArrow;
	}

	public WebElement selectGroupOfSettings(String role, String label) {
		return driver.findElement(By.xpath("//span[contains(@class,'nav-" + label + "')][text()='" + role + "']"));
	}

	public WebElement searchForRecords() {
		Wait.waitUntilElementVisible(driver, searchForRecords);
		return searchForRecords;
	}

	public WebElement selectRole(String securityRole) {
		return driver
				.findElement(By.xpath("//*[text()='" + securityRole + "']/ancestor::tr/td/input[@class='checkbox']"));
	}
	
	public WebElement manageUserRole() {
		Wait.waitUntilElementVisible(driver, manageUserRoles);
		return manageUserRoles;
	}
}