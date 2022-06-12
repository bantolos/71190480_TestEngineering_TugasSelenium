Feature: feature to test register functionality
  Scenario Outline: periksa register dengan data yang benar/valid
    Given browser terbukaa
    And pengguna berada di halaman register
    When pengguna memasukkan <firstname> <email> <password>
    And tombol register ditekan
#    if email password firstname valid
    Then pengguna diarahkan ke halaman login
    Examples:
      |firstname         |email                  |password          |
      |                  |                       |                  |
      |bantolostd        |                       |abcd1234          |
      |bantolostd        |bantolostd@gmail.com   |                  |
      |                  |bantolostd@gmail.com   |abcd1234          |
      |bantolostd        |bantolostd@gmail.com   |abcd123           |
      |bantolostd        |bantolostd@gmail.com   |abcd1234567891011 |
      |bantolostd        |bantolostd@gmail.com   |abcd1234!!        |
      |bantolostd        |bantolostd@gmail.com   |abcd1234          |
