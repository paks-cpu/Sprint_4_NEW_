package tests;

import packages.DescriptionQuestionsPage;
import baseTest.BaseTest;
import constantForQuestion.Constant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class ConstantTest extends BaseTest {
    private final int questionIndex;
    private final String expectedAnswer;

    public ConstantTest(int questionIndex, String expectedAnswer) {
        this.questionIndex = questionIndex;
        this.expectedAnswer = expectedAnswer;
    }

    @Parameterized.Parameters
    public static Object[][] getParameters() {
        return new Object[][]{
                {0, Constant.DROP_DOWN_LIST_ANSWER_PAYMENTS},
                {1, Constant.DROP_DOWN_LIST_ANSWER_HOW_MANY_SCOOTER},
                {2, Constant.DROP_DOWN_LIST_ANSWER_RENTAL_TIME},
                {3, Constant.DROP_DOWN_LIST_ANSWER_ORDER_FOR_TODAY},
                {4, Constant.DROP_DOWN_LIST_ANSWER_ORDER_EXTENSION},
                {5, Constant.DROP_DOWN_LIST_ANSWER_CHARGING_LEVEL},
                {6, Constant.DROP_DOWN_LIST_ANSWER_ORDER_CANCELLATION},
                {7, Constant.DROP_DOWN_LIST_ANSWER_BEYOND_THE_MKAD}
        };
    }

    @Test
    public void checkQuestions() {
    DescriptionQuestionsPage page = new DescriptionQuestionsPage(driver);
    page.scrollToQuestion();
    page.clickToQuestion(questionIndex);
    page.verifyAnswerText(questionIndex, expectedAnswer);
    }

    }