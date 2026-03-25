package packages;

import org.hamcrest.MatcherAssert;
import org.hamcrest.core.StringContains;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;



import java.time.Duration;

public class DescriptionQuestionsPage {
    private WebDriver driver;
    private final By questionItemLocator = By.cssSelector("[id^='accordion__heading-']");
    private final By answerItemLocator = By.cssSelector("[id^='accordion__panel-']");


    public DescriptionQuestionsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickToQuestion(int questionIndex) {
        List<WebElement> questionElementList = driver.findElements(questionItemLocator);
        WebElement questionElement = questionElementList.get(questionIndex);
        questionElement.click();
        String questionId = questionElement.getAttribute("id");
        String panelId = questionId.replace("heading", "panel");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement answerElement;
        answerElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(panelId)));
        WebElement currentAnswerElement = answerElement;
    }

    private void waitForAnswerToLoad() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
        wait.until(ExpectedConditions.visibilityOfElementLocated(answerItemLocator));
    }

    public void scrollToQuestion() {
        WebElement element = driver.findElement(questionItemLocator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }


    public void verifyAnswerText (int questionIndex, String expectedText) {
        List<WebElement> answerElementList = driver.findElements(answerItemLocator);
        WebElement answerElement = answerElementList.get(questionIndex);
        String actualText = answerElement.getText();
        MatcherAssert.assertThat("Текст ответа не соответствует содержимому", actualText, StringContains.containsString(expectedText));
    }

}
