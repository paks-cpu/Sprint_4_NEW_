package Packages;

import org.hamcrest.MatcherAssert;
import org.hamcrest.core.StringContains;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DescriptionQuestionsPage {
    private WebDriver driver;
    private WebElement element;
    private final By questions;
    private final By answer;
    private final By labelResult;
    private final String expected;

    public DescriptionQuestionsPage(WebDriver driver, By questions, By answer, By labelResult, String expected) {
        this.driver = driver;
        this.questions = questions;
        this.answer = answer;
        this.labelResult = labelResult;
        this.expected = expected;
    }

    public static final By dropDownQuestionPayments = By.id("accordion__heading-0");
    public static final By dropDownAnswerPayments = By.id("accordion__panel-0");
    public static final By dropDownItemAnswerPayments = By.xpath(".//*[@aria-controls='accordion__panel-0']");
    public static final By dropDownQuestionHowManyScooters = By.id("accordion__heading-1");
    public static final By dropDownAnswerHowManyScooters = By.id("accordion__panel-1");
    public static final By dropDownItemHowManyScooters = By.xpath(".//*[@aria-controls='accordion__panel-1']");
    public static final By dropDownQuestionRentalTime = By.id("accordion__heading-2");
    public static final By dropDownAnswerRentalTime = By.id("accordion__panel-2");
    public static final By dropDownItemRentalTime = By.xpath(".//*[@aria-controls='accordion__panel-2']");
    public static final By dropDownQuestionOrderForToday = By.id("accordion__heading-3");
    public static final By dropDownAnswerOrderForToday = By.id("accordion__panel-3");
    public static final By dropDownItemOrderForToday = By.xpath(".//*[@aria-controls='accordion__panel-3']");
    public static final By dropDownQuestionOrderExtension = By.id("accordion__heading-4");
    public static final By dropDownAnswerOrderExtension = By.id("accordion__panel-4");
    public static final By dropDownItemOrderExtension = By.xpath(".//*[@aria-controls='accordion__panel-4']");
    public static final By dropDownQuestionChargingLevel = By.id("accordion__heading-5");
    public static final By dropDownAnswerChargingLevel = By.id("accordion__panel-5");
    public static final By dropDownItemChargingLevel = By.xpath(".//*[@aria-controls='accordion__panel-5']");
    public static final By dropDownQuestionOrderCancellation = By.id("accordion__heading-6");
    public static final By dropDownAnswerOrderCancellation = By.id("accordion__panel-6");
    public static final By dropDownItemOrderCancellation = By.xpath(".//*[@aria-controls='accordion__panel-6']");
    public static final By dropDownQuestionBeyondTheMkad = By.id("accordion__heading-7");
    public static final By dropDownAnswerBeyondTheMkad = By.id("accordion__panel-7");
    public static final By dropDownItemBeyondTheMkad = By.xpath(".//*[@aria-controls='accordion__panel-7']");

    private void waitLoadAfterClickQuestion(By labelResult){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.visibilityOfElementLocated(labelResult));
    }

    public void findQuestion() {
        //Поиск элемента вопросов
        element = driver.findElement(questions);
    }
    public void scrollToQuestion() {
        //Скролл до элемента вопросов
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
    public void waitForClickAndClick() {
        //Ожидание кликабельности вопросов
        waitLoadAfterClickQuestion(labelResult);
        //Клик по вопросу
        element.click();
    }

    public void waitForAnswerToBeVisibleAndLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(driver -> {
            WebElement element = driver.findElement(answer);
            return element.isDisplayed() && !element.getText().trim().isEmpty();
        });
    }
    public void searchAndGetResponse() {
        //Поиск ответа и получение текста ответа
        String actual = driver.findElement(answer).getText().trim();
        //Сравнение текстов ответов
        MatcherAssert.assertThat(actual, StringContains.containsString(expected));
    }

}
