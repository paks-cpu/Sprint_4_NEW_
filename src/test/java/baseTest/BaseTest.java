package baseTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    final String site = "https://qa-scooter.praktikum-services.ru/";

    @Before
    public void setUpFireFox() {
        //Создание драйвера для firefox
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        implicitlyWait();
        getSiteScooter();
        implicitlyWait();
    }

    /* public void setUpGoogleChrome() {
        //Создание драйвера для googleChrome
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        getSiteScooter();
        implicitlyWait();
    } */



    public void getSiteScooter() {
        //Переход на сайт qa-scooter.praktikum-services.ru
        driver.get(site);
    }

    public void implicitlyWait() {
        //Ожидание загрузки страницы
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
