Feature: Web Test
  some description
#please update #AppURL=https://www.calculator.net/ and FrameworkMode=Web in resources/config.properties to run with command "mvn test -Dcucumber.filter.tags=@ui" from terminal
  #test comment
  @demo @ui
  Scenario Outline: Verify Basic Operations
    Given I am on Calculator homepage
    When  I enter '<number1>' in calculator
    And   I press '<operator>'
    And   I enter '<number2>' in calculator
    Then  I see the result is '<result>'
    Examples:
      | number1 | operator | number2 | result |
      | 5       | +        | 7       | 12     |
#
  #@demo @ui
  #Scenario Outline: Verify Basic Operations of calculator
    #Given I am on Calculator homepage
    #When  I enter '<number1>' in calculator
    #And   I press '<operator>'
    #And   I enter '<number2>' in calculator
    #Then  I see the result is '<result>'
    #Examples:
      #| number1 | operator | number2 | result |
      #| 5       | *        | 7       | 35     |
