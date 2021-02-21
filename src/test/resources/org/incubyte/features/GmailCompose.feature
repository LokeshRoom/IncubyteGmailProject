Feature: Composing Gmail

  Background:
    Given I launch "chrome" browser

  Scenario: Verify Compose button is available in Gmail
    Given I navigate to "https://gmail.com" url
    When I login to Gmail using username & password
      | username | lokeshkumarfortestproject@gmail.com |
      | password | Feb@2021                            |
    Then I verify Compose button is displayed in Gmail homepage
    And I Logout from gmail

  Scenario Outline: Verify user is able to send mail with attachments
    Given I navigate to "https://gmail.com" url
    When I login to Gmail using username & password
      | username | lokeshkumarfortestproject@gmail.com |
      | password | Feb@2021                            |
    And I compose email to "<emailid>" with subject "<subject>" and email content as "<content>"
    #add files in resource/files directory and give file name with extension in below parameter
    And I attach file "<files>" to composed mail
    Then I send mail
    And I verify mail is sent with email id "<emailid>" subject "<subject>" and email content as "<content>"
    And I verify attachments "<files>" are attached
    And I Logout from gmail
    Examples:
      | emailid                  | subject               | files        | content                                                                                |
      | klokeshkumar@outlook.com | Mail with Attachments | Test Doc.pdf | Hi Team,\n\nThis is a test mail with Attachments.\n\nThanks & Regards,\nLokesh Kumar K |

  Scenario Outline: Verify user is able to send mail with multiple attachments
    Given I navigate to "https://gmail.com" url
    When I login to Gmail using username & password
      | username | lokeshkumarfortestproject@gmail.com |
      | password | Feb@2021                            |
    And I compose email to "<emailid>" with subject "<subject>" and email content as "<content>"
    #add files in resource/files directory and give file name with extension in below parameter
    And I attach file "<files>" to composed mail
    Then I send mail
    And I verify mail is sent with email id "<emailid>" subject "<subject>" and email content as "<content>"
    And I verify attachments "<files>" are attached
    And I Logout from gmail
    Examples:
      | emailid                  | subject               | files                      | content                                                                                |
      | klokeshkumar@outlook.com | Mail with Attachments | Test Doc.docx:Test Doc.pdf | Hi Team,\n\nThis is a test mail with Attachments.\n\nThanks & Regards,\nLokesh Kumar K |


  Scenario Outline: Verify user is able to send mail with attachments more than 25mb file with Google drive link
    Given I navigate to "https://gmail.com" url
    When I login to Gmail using username & password
      | username | lokeshkumarfortestproject@gmail.com |
      | password | Feb@2021                            |
    And I compose email to "<emailid>" with subject "<subject>" and email content as "<content>"
    #add files in resource/files directory and give file name with extension in below parameter
    And I attach file "<files>" to composed mail
    Then I send mail
    And I verify mail is sent with email id "<emailid>" subject "<subject>" and email content as "<content>"
    And I verify attachments "<files>" are attached
    And I Logout from gmail
    Examples:
      | emailid                  | subject               | files              | content                                                                                |
      | klokeshkumar@outlook.com | Mail with Attachments | Test File Size.pdf | Hi Team,\n\nThis is a test mail with Attachments.\n\nThanks & Regards,\nLokesh Kumar K |


