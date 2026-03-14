package Packages;

import org.hamcrest.MatcherAssert;
import org.hamcrest.core.StringContains;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPageScooter {
    private final WebDriver driver;

    public OrderPageScooter(WebDriver driver) {
        this.driver = driver;
    }

    //Поле: Имя
    private final By nameField = By.xpath(".//input[@placeholder='* Имя']");
    //Поле: Фамилия
    private final By surnameField = By.xpath(".//input[@placeholder='* Фамилия']");
    //Поле: Адрес: куда привезти
    private final By addressFromField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    //Поле: Станция метро
    private final By stationSubwayField = By.xpath(".//input[@placeholder='* Станция метро']");
    //Поле: Телефон: на него позвонит курьер
    private final By phoneNumberField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    //Кнопка "Далее" страницы "Для кого самокат"
    private final By orderNextButton = By.xpath("//button[text()='Далее']");
    //Поле: Когда привезти заказ
    private final By dateDeliveryField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    //Поле: Срок аренды
    private final By rentalPeriodField = By.className("Dropdown-arrow");
    //Поле: Цвет самоката
    private final By comForCourier = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    //Верхняя кнопка "Заказать"
    private final By buttonTopOrder = By.className("Button_Button__ra12g");
    //Нижняя кнопка "Заказать"
    private final By buttonBotOrder = By.className("Button_Button__ra12gButton_UltraBig__UU3Lp");
    //Кнопка "Заказать" финальная(в конце оформления заказа)
    private final By buttonFinishOrder = By.xpath("//div[contains(@class,'Order_Buttons')]/button[text()='Заказать']");
    //Кнопка "Да" на модальном окне "Хотите оформить заказ?"
    private final By orderConfirmButton = By.xpath("//div[@class='Order_Modal__YZ-d3']//button[contains(@class, 'Button_Button__ra12g') and contains(text(), 'Да')]");
    //Кнопка: Посмотреть статус
    private final By confirmHeader = By.xpath(".//button[text()='Посмотреть статус']");
    //Кнопка: "да все привыкли" подтверждениия куки
    private final By acceptCoocieButton = By.xpath(".//button[text()='да все привыкли']");
    //Надпись учебный тренажер
    private final By trainingSimulator = By.className("Header_Disclaimer__3VEni");
    //Окно "Заказ оформлен"
    private final By orderModal = By.className("Order_ModalHeader__3FDaJ");

    //Метод подтверждения куки
    public void setAcceptCookieButton() {
        driver.findElement(acceptCoocieButton).click();
    }
    //Метод заполнениия поля имя
    public void setName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }
    //Метод заполнения поля фамилия
    public void setSurname(String surname) {
        driver.findElement(surnameField).sendKeys(surname);
    }
    //Метод заполнения поля адрес
    public void setAddressFrom(String addressFrom) {
        driver.findElement(addressFromField).sendKeys(addressFrom);
    }
    //Метод выбора станции метро
    public void selectSubwayStation(String subway)  {
        driver.findElement(stationSubwayField).sendKeys(subway);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String xpath = String.format(".//div[contains(text(), '%s')]", subway);
        WebElement stationElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        stationElement.click();
    }
    //Метод метод заполнения поля номера телефона
    public void enterPhoneNumber(String phoneNumber) {
        driver.findElement(phoneNumberField).sendKeys(phoneNumber);
    }
    //Метод метод нажатия кнопки далее на страниице "Для кого самокат"
    public void clickNextButton() {
        driver.findElement(orderNextButton).click();
    }
    //Метод заполнения поля "когда привезти заказ"
    public void setDateDelivery(String dateDelivery) {
        driver.findElement(dateDeliveryField).sendKeys(dateDelivery);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(trainingSimulator).click();
    }
    //Метод выбора срока аренды
    public void setRentalPeriod(String rentalPeriod) {
        driver.findElement(rentalPeriodField).click();
        driver.findElement(By.xpath(".//div[text()='"+rentalPeriod+"']")).click();
    }
    //Метод выбора цвета самоката
    public void setColorScooter(String colorScooter) {
        driver.findElement(By.xpath(".//label[text()='"+colorScooter+"']")).click();
    }
    //Метод заполнения поля коментария для курьера
    public void enterComment(String comment) {
        driver.findElement(comForCourier).sendKeys(comment);
    }
    //Метод нажатия кнопки "Закать" сверху страницы
    public void clickButtonOrderTop() {
        driver.findElement(buttonTopOrder).click();
    }
    //Метод нажатия кнопки "Заказать" в конце оформления заказа
    public void clickButtonOrderFinish() {
        driver.findElement(buttonFinishOrder).click();
    }
    //Метод нажатия кнопки "Посмотреть статус" на модальном окне "Заказ оформлен"
    public void checkConfirmHeader() {
        driver.findElement(confirmHeader).click();
    }
    //Метод нажатия кнопки "Да" на модальном окне "Хотите оформить заказ?"
    public void clickOrderConfirmButton() {
        driver.findElement(orderConfirmButton).click();
    }
    //Метод проверки появления модального окна "Заказ оформлен"
    public void displayOrderModal() {
        String actual = driver.findElement(orderModal).getText().trim();
        MatcherAssert.assertThat(actual, StringContains.containsString("Заказ оформлен"));
    }

}
