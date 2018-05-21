Feature: Get Books By Title
  Return list of cities and plotted in the map

  Scenario Outline: Enter Valid Title
    Given The title is '<title>'
    When Enter the title as '<title>' and choose '<database>'
    Then I should get S2 result as '<result>'

Examples:
    | title         | database |  result      | 
    | George Borrow | stub     | success      |
    | George Borrow | mongodb  | success      |
