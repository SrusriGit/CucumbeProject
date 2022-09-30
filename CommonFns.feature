@userRole
Feature: Select a security Role for a user
  

  @selectSecurityRole
  Scenario: Select a security Role for a user
    Given User navigates to CRM login page
    When user click on settings
    And user click on "Advanced Settings"
    Then user selects Settings drop down
    And user selects "Security" under "rowLabel"
    And user click on "Users"
    And user searches with the username
    And user click on " Manage Roles "
    And user select a security role "App Profile User"
