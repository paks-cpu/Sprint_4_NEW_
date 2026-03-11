import static resourses.questions.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.hamcrest.core.StringContains;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.JavascriptExecutor;
import java.awt.*;
import java.time.Duration;
import org.hamcrest.MatcherAssert;

@RunWith(Parameterized.class)

public class questionsTEST {

    private WebDriver driver;
    private final String site = "https://qa-scooter.praktikum-services.ru/";
    private final By questions;
    private final By answer;
    private final By labelResult;
    private final String expected;

    private void waitLoadAfterClickQuestion(By labelResult){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(60));
        wait.until(ExpectedConditions.visibilityOfElementLocated(labelResult));
    }

    public questionsTEST(By questions, By answer, By labelResult, String expected) {
        this.questions = questions;
        this.answer = answer;
        this.labelResult = labelResult;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Object[][] getParameters() {
        return new Object[][]{
                {QUESTION_0, ANSWER_0, ITEM_ANSWER_0, TEXT_ANSWER_0},
                {QUESTION_1, ANSWER_1, ITEM_ANSWER_1, TEXT_ANSWER_1},
                {QUESTION_2, ANSWER_2, ITEM_ANSWER_2, TEXT_ANSWER_2},
                {QUESTION_3, ANSWER_3, ITEM_ANSWER_3, TEXT_ANSWER_3},
                {QUESTION_4, ANSWER_4, ITEM_ANSWER_4, TEXT_ANSWER_4},
                {QUESTION_5, ANSWER_5, ITEM_ANSWER_5, TEXT_ANSWER_5},
                {QUESTION_6, ANSWER_6, ITEM_ANSWER_6, TEXT_ANSWER_6},
                {QUESTION_7, ANSWER_7, ITEM_ANSWER_7, TEXT_ANSWER_7},

        };
    }

    @Before
    public void setUp() {
        //Создание драйвера для firefox
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        //Создание драйвера для googleChrome
        /* WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(); */
        //Переход на сайт qa-scooter.praktikum-services.ru
        driver.get(site);
        //Ожидание загрузки страницы (максимум 60 сек)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
    }

    @Test
    public void checkQuestions() {
        //Поиск элемента вопросов
        WebElement element = driver.findElement(questions);
        //Скролл до элемента вопросов
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        //Ожидание кликабельности вопросов
        waitLoadAfterClickQuestion(labelResult);
        //Клик по вопросу
        element.click();
        //Поиск ответа и получение текста ответа
        String actual = driver.findElement(answer).getText().trim();
        //Сравнение текстов ответов
        MatcherAssert.assertThat(actual, StringContains.containsString(expected));

    }

    @After
    //закрытие браузера
    public void quit() {
        driver.quit();
    }

}