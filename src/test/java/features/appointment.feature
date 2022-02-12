Feature: Appointment

  Scenario: User Create Appointment
    Given The user accesses the home page
    When The home page appears
    And The user enters information into the form
      | petName | ownerName | date | time | symptoms |
      | tornado | alex   | 11/02/2022 | 09:30AM  | vomiting and fever |
    And The user clicks on add appointment
    Then The record is generated in the system


  Scenario: User Create and Delete Appointment
    Given The user accesses the home page
    When The home page appears
    And The user enters information into the form
      | petName | ownerName | date | time | symptoms |
      | tornado 123 | alex 123  | 11/02/2022 | 10:00AM  | vomiting and fever 123 |
    And The user clicks on add appointment
    And The record is generated in the system
    Then The user clicks on delete appointment


  Scenario: Required fields
    Given The user accesses the home page
    When The home page appears
    And The user clicks on add appointment
    Then The system generates an alert