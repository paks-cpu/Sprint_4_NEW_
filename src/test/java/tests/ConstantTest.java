package tests;

import packages.DescriptionQuestionsPage;
import baseTest.BaseTest;
import constantForQuestion.Constant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;

@RunWith(Parameterized.class)
public class ConstantTest extends BaseTest {
    private final By questions;
    private final By answer;
    private final By labelResult;
    private final String expected;

    public ConstantTest(By questions, By answer, By labelResult, String expected) {
        this.questions = questions;
        this.answer = answer;
        this.labelResult = labelResult;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Object[][] getParameters() {
        return new Object[][]{
                {DescriptionQuestionsPage.dropDownQuestionPayments, DescriptionQuestionsPage.dropDownAnswerPayments, DescriptionQuestionsPage.dropDownItemAnswerPayments, Constant.dropDownListAnswerPayments},
                {DescriptionQuestionsPage.dropDownQuestionHowManyScooters, DescriptionQuestionsPage.dropDownAnswerHowManyScooters, DescriptionQuestionsPage.dropDownItemHowManyScooters, Constant.dropDownListAnswerHowManyScooters},
                {DescriptionQuestionsPage.dropDownQuestionRentalTime, DescriptionQuestionsPage.dropDownAnswerRentalTime, DescriptionQuestionsPage.dropDownItemRentalTime, Constant.dropDownListAnswerRentalTime},
                {DescriptionQuestionsPage.dropDownQuestionOrderForToday, DescriptionQuestionsPage.dropDownAnswerOrderForToday, DescriptionQuestionsPage.dropDownItemOrderForToday, Constant.dropDownListAnswerOrderForToday},
                {DescriptionQuestionsPage.dropDownQuestionOrderExtension, DescriptionQuestionsPage.dropDownAnswerOrderExtension, DescriptionQuestionsPage.dropDownItemOrderExtension, Constant.dropDownListAnswerOrderExtension},
                {DescriptionQuestionsPage.dropDownQuestionChargingLevel, DescriptionQuestionsPage.dropDownAnswerChargingLevel, DescriptionQuestionsPage.dropDownItemChargingLevel, Constant.dropDownListAnswerChargingLevel},
                {DescriptionQuestionsPage.dropDownQuestionOrderCancellation, DescriptionQuestionsPage.dropDownAnswerOrderCancellation, DescriptionQuestionsPage.dropDownItemOrderCancellation, Constant.dropDownListAnswerOrderCancellation},
                {DescriptionQuestionsPage.dropDownQuestionBeyondTheMkad, DescriptionQuestionsPage.dropDownAnswerBeyondTheMkad, DescriptionQuestionsPage.dropDownItemBeyondTheMkad, Constant.dropDownListAnswerBeyondTheMkad},
        };
    }

    @Test
    public void checkQuestions() {
    DescriptionQuestionsPage page = new DescriptionQuestionsPage(driver, questions, answer, labelResult, expected);
    page.findQuestion();
    page.scrollToQuestion();
    page.waitForClickAndClick();
    page.waitForAnswerToBeVisibleAndLoaded();
    page.searchAndGetResponse();
    }

    }