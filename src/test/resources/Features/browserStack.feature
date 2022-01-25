@Browserstack
Feature: Testing  BrowserStack Functionality


 @login
  Scenario: Test BrowserStack Login with valid credentials
    Given User navigates to BrowserStacklogin page
    When user enters valid username and password
    And clicks on login
    Then user is navigated to Browserstack page


	@navigationWebsites
	Scenario Outline: Test BrowserStack Websites navigation
    Given User navigates to BrowserStack home page
    When user clicks on the icon "<website>" to navigate
    Then user is navigated to selected "<website>" page

Examples:
	|website|
	|Live|
	|Automate|
	|Percy|
	

  #@browserStack
  #Scenario Outline: <RowNumber> Test BrowserStack Login with valid credentials
    #Given User navigates to BrowserStacklogin page
    #When user enters credentials from excel "<Sheetname>" with row <RowNumber>
    #And clicks on login
    #Then user is navigated to Browserstack page
#
#Examples:
#|Sheetname|RowNumber|
#|Credentials|0|
#|Credentials|1|



 #@gmail
  #Scenario: Test BrowserStack Login with valid credentialslogin page
    #When user enters valid credentials
    #And clicks on login button
    #Then user is navigated to Gmail page