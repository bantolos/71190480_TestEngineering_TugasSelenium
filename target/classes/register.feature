Feature: feature to test register functionality
  Scenario Outline: periksa register dengan data yang benar/valid
    Given browser terbukaa
    And pengguna berada di halaman register
    When pengguna memasukkan <firstname> <password>
    And tombol register ditekan
#   if firstname x password valid
    Then pengguna diarahkan ke halaman login
    Examples:
      |firstname         |password          |
      |                  |                  |
      |bantolostd        |abcd1234          |
      |bantolostd        |                  |
      |                  |abcd1234          |
      |bantolostd        |abcd123           |
      |bantolostd        |abcd1234567891011 |
      |bantolostd        |abcd1234!!        |
      |bantolostd        |abcd1234          |
