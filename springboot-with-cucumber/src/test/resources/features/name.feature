Feature: Database Name Test

  Scenario: validate database table
    Given upload the names text file
    When load the NAMES table from database
    Then validate names with below data grid
      | FIRST_NAME | LAST_NAME | AGE |
      | SUBODH     | KUMAR     | 30  |
      | JULI       | KUMARI    | 24  |
      | BINDIYA    | KUMARI    | 20  |


