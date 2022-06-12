import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

public class RegisterTest {
    String password;
    String c_password;
    String firstname;
    ChromeDriver chromeDriver;

    @Given("browser terbukaa")
    public void browser_terbukaa() {
        System.out.println("1. Browser terbukaa");
        System.setProperty("webdriver.chrome.driver","C://chromedriver.exe"); // di laptop saya bisanya pakai ini Pak, drivernya diletakkan di C:/
        //di atas adalah solusi dari https://stackoverflow.com/questions/70690792/eclipse-error-when-trying-to-run-in-windows
        //yang di bawah ini tidak bisa Pak
        //System.setProperty("webdriver.chrome.driver",Objects.requireNonNull(getClass().getClassLoader().getResource("webdriver/chromedriver.exe")).getFile());

        chromeDriver = new ChromeDriver();
        chromeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40)); // menunggu (maksimal waktu) browser terbuka
        chromeDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));
    }
    @Given("pengguna berada di halaman register")
    public void pengguna_berada_di_halaman_register() {
        System.out.println("2. Pengguna berada di halaman register");
        chromeDriver.navigate().to("https://demo.guru99.com/insurance/v1/register.php");
        List<WebElement> btnReset = chromeDriver.findElements(By.id("resetform"));
        List<WebElement> btnCreate = chromeDriver.findElements(By.name("submit"));
        if (btnCreate.size() > 0){
            System.out.println("Button create ditemukan");
        }
        if (btnReset.size() > 0){
            System.out.println("Button reset ditemukan");
        }
        Assertions.assertTrue(btnReset.size()>0,"Button reset ditemukan");
        Assertions.assertTrue(btnCreate.size()>0,"Button create ditemukan");
    }
    @When("pengguna memasukkan ([^\"]*) ([^\"]*)$")
    public void pengguna_memasukkan(String inpFirstName, String inpPassword) {
        System.out.println("3. Pengguna memasukkan nama depan, password, dan konfirmasi password");
        firstname = inpFirstName;
        password = inpPassword;
        c_password = inpPassword;
        chromeDriver.findElement(By.name("firstname")).sendKeys(inpFirstName);
        chromeDriver.findElement(By.name("password")).sendKeys(inpPassword);
        chromeDriver.findElement(By.name("c_password")).sendKeys(inpPassword);
    }
    @When("tombol register ditekan")
    public void tombol_register_ditekan() {
        System.out.println("4. Tombol register ditekan");
        chromeDriver.findElement(By.name("submit")).click();
    }
    @Then("pengguna diarahkan ke halaman login")
    public void pengguna_diarahkan_ke_halaman_login() {
        List<WebElement> btnLogin = chromeDriver.findElements(By.xpath("/html/body/div[3]/form/div[3]/input"));
        System.out.println("5. Pengguna diarahkan ke halaman login");
        Assertions.assertTrue(!firstname.equalsIgnoreCase("") && password.matches("[A-Za-z0-9]+") && password.length()!=0 && password.length() >= 8 && password.length() <= 13,"Register gagal");
        Assertions.assertTrue(btnLogin.size() > 0, "Button login tidak ditemukan");
        chromeDriver.close();
        chromeDriver.quit();
    }
}
