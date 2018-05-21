Feature: Get books by city
  Return list of books

  Scenario Outline: Enter Valid City
    Given The city is '<city>'
    When Enter the city as '<city>' and choose '<database>'
    Then I should get S1 result as '<result>'

Examples:
    | city     | database |  result      | 
    | Anderson | stub     | success      |
    | Anderson | mongodb  | success      |

