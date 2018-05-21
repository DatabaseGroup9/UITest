Feature: Get Books By Author
  Return list of books and plotted the cities in the map

  Scenario Outline: Enter Valid Author
    Given The author is '<author>'
    When Enter the author as '<author>' and choose '<database>'
    Then I should get S3 result as '<result>'

Examples:
    | author          | database |  result      | 
    | Romano Lavo-Lil | stub     | success      |
    | Romano Lavo-Lil | mongodb  | success      |

