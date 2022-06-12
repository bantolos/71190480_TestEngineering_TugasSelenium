import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

public class LoginTest {
    String email;
    String password;
    ChromeDriver chromeDriver;

    @Given("browser terbuka")
    public void browser_terbuka() {
        System.out.println("1. Browser terbuka");
        System.setProperty("webdriver.chrome.driver","C://chromedriver.exe"); // di laptop saya bisanya pakai ini Pak, drivernya diletakkan di C:/
        //di atas adalah solusi dari https://stackoverflow.com/questions/70690792/eclipse-error-when-trying-to-run-in-windows
        //yang di bawah ini tidak bisa Pak
        //System.setProperty("webdriver.chrome.driver",Objects.requireNonNull(getClass().getClassLoader().getResource("webdriver/chromedriver.exe")).getFile());

        chromeDriver = new ChromeDriver();
        chromeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40)); // menunggu (maksimal waktu) browser terbuka
        chromeDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));
    }
    @Given("pengguna berada di halaman login")
    public void pengguna_berada_di_halaman_login() {
        System.out.println("2. Pengguna berada di halaman login");
        chromeDriver.navigate().to("https://demo.guru99.com/insurance/v1/index.php");
    }
    @When("pengguna memasukkan ([^\"]*) dan ([^\"]*)$")
    public void pengguna_memasukkan_dan(String inpEmail, String inpPassword) {
        System.out.println("3. Pengguna memasukkan <email> dan <password>");
        email = inpEmail;
        password = inpPassword;
        chromeDriver.findElement(By.name("email")).sendKeys(inpEmail);
        chromeDriver.findElement(By.name("password")).sendKeys(inpPassword);
    }
    @When("tombol login ditekan")
    public void tombol_login_ditekan() {
        System.out.println("4. Tombol login ditekan");
        chromeDriver.findElement(By.name("submit")).click();
    }
    @Then("pengguna diarahkan ke halaman utama")
    public void pengguna_diarahkan_ke_halaman_utama() {
        System.out.println("5. Pengguna diarahkan ke halaman utama");
        Assertions.assertTrue(password.matches("[A-Za-z0-9]+") && password.length()!=0 && password.length() >= 8 && password.length() <= 13 && !email.equalsIgnoreCase(""),"Login gagal");
        List<WebElement> list = chromeDriver.findElements(By.xpath("/html/body/div[3]/form/input"));
        Assertions.assertTrue(list.size()>0,"Tombol logout tidak ditemukan");
        chromeDriver.close();
        chromeDriver.quit();
    }
}
