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

  Scenario: Verify user is able to send mail with attachments
    Given I navigate to "https://gmail.com" url
    When I login to Gmail using username & password
      | username | lokeshkumarfortestproject@gmail.com |
      | password | Feb@2021                            |
    And I compose email to "klokeshkumar@test123.com" with subject "Mail with Attachments"
      | content | Hi Team,\n\nThis is a test mail with Attachments.\n\nThanks & Regards,\nLokesh Kumar K |
    #add files in resource/files directory and give file name with extension in below parameter
    And I attach file "Test Doc.pdf" to composed mail
    Then I send mail
    And I verify mail is sent
    And I Logout from gmail

  Scenario: Verify user is able to send mail with multiple attachments
    Given I navigate to "https://gmail.com" url
    When I login to Gmail using username & password
      | username | lokeshkumarfortestproject@gmail.com |
      | password | Feb@2021                            |
    And I compose email to "klokeshkumar@teset123.com" with subject "Mail with Attachments"
      | content | Hi Team,\n\nThis is a test mail with Attachments.\n\nThanks & Regards,\nLokesh Kumar K |
    #add files in resource/files directory and give file name with extension in below parameter
    And I attach files to composed mail
      |Test Doc.docx|Test Doc.pdf|
    Then I send mail
    And I verify mail is sent
    And I Logout from gmail

  Scenario: Verify user is able to send mail with attachments more than 25mb file with Google drive link
    When I login to Gmail using username & password
      | username | lokeshkumarfortestproject@gmail.com |
      | password | Feb@2021                            |
    And I compose email to "klokeshkumar@outlook.com" with subject "Mail with Attachments"
      | content | Hi Team,\n\nThis is a test mail with Attachments.\n\nThanks & Regards,\nLokesh Kumar K |
    #add files in resource/files directory and give file name with extension in below parameter
    And I attach file "Test File Size.pdf" to composed mail
    Then I send mail
    And I verify mail is sent
    And I Logout from gmail



