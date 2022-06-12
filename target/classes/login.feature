Feature: feature to test login functionality
  Scenario Outline: periksa login dengan akun yang benar
    Given browser terbuka
    And pengguna berada di halaman login
    When pengguna memasukkan <email> dan <password>
    And tombol login ditekan
    Then pengguna diarahkan ke halaman utama
    Examples:
      |email                  |password          |
      |                       |                  |
      |bantolostd@gmail.com   |                  |
      |                       |abcd1234          |
      |bantolostd@gmail.com   |abcd123           |
      |bantolostd@gmail.com   |abcd1234567891011 |
      |bantolostd@gmail.com   |abcd1234!!        |
      |bantolostd@gmail.com   |abcd1234          |
