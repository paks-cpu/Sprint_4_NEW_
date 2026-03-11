import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


@RunWith(Parameterized.class)
public class OrderCreateTest {
    private WebDriver driver;
    private final String site = "https://qa-scooter.praktikum-services.ru/";
    private String name;
    private String surname;
    private String phoneNumber;
    private String addressFrom;
    private String dateDelivery;
    private String rentalPeriod;
    private String colorScooter;
    private String comment;
    private String subway;

    private void waitLoadAfterClickQuestion(By labelResult){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.visibilityOfElementLocated(labelResult));
    }

    public OrderCreateTest(String name, String surname, String addressFrom, String subway, String phoneNumber,String dateDelivery, String rentalPeriod, String colorScooter, String comment) {
        this.name = name;
        this.surname = surname;
        this.addressFrom = addressFrom;
        this.subway = subway;
        this.phoneNumber = phoneNumber;
        this.dateDelivery = dateDelivery;
        this.rentalPeriod = rentalPeriod;
        this.colorScooter = colorScooter;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getParameters(){
        return new Object[][] {
                { "Тутпервоеимя", "Тутперваяфамилия", "Тутпервый", "Красносельская", "89671111111", "10.10.2026", "сутки", "серая безысходность", "тут первый комментарий" },
                { "Тутвтороеимя", "Тутвтораяфамилия", "Тутвторойадрес", "Красносельская", "89672222222", "10.10.2026", "двое суток", "чёрный жемчуг", "тут второй  комментарий" }
        };
    }

    @Before
    public void setUp() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        /* WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(); */
        driver.get(site);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
    }

    @Test
    public void OrderPositiveTestTop () {
        OrderPageScooter objOrderPageScooter = new OrderPageScooter(driver);
        objOrderPageScooter.setAcceptCoocieButton();
        objOrderPageScooter.clickButtonOrderTop();
        objOrderPageScooter.setName(name);
        objOrderPageScooter.setSurname(surname);
        objOrderPageScooter.setAddressFrom(addressFrom);
        objOrderPageScooter.selectSubwayStation(subway);
        objOrderPageScooter.enterPhoneNumber(phoneNumber);
        objOrderPageScooter.clickNextButton();
        objOrderPageScooter.setDateDelivery(dateDelivery);
        objOrderPageScooter.setRentalPeriod(rentalPeriod);
        objOrderPageScooter.setColorScooter(colorScooter);
        objOrderPageScooter.enterComment(comment);
        objOrderPageScooter.clickButtonOrderFinish();
        objOrderPageScooter.clickOrderConfirmButton();
        objOrderPageScooter.displayOrderModal();
    }

   @After
   public void quit() {
        driver.quit();
   }
}