package tests;

import bot.Attr;
import bot.Questionnaire;
import org.junit.Test;

import static org.junit.Assert.*;

public class QuestionnaireTest {
    private static String[] authorizeQuestions = new String[] {
            "Как твое имя?",
            "Какая у тебя группа?",
            "Какой номер твоей подгруппы?"
    };

    @Test
    public void getQuestionNumber_0() {
        int i = Questionnaire.getQuestionNumber(authorizeQuestions[0]);
        assertEquals(0, i);
    }

    @Test
    public void getQuestionNumber_1() {
        int i = Questionnaire.getQuestionNumber(authorizeQuestions[1]);
        assertEquals(1, i);
    }

    @Test
    public void getQuestionNumber_2() {
        int i = Questionnaire.getQuestionNumber(authorizeQuestions[2]);
        assertEquals(2, i);
    }

    @Test
    public void getQuestionNumber_minus1() {
        int i = Questionnaire.getQuestionNumber("Как так то?");
        assertEquals(-1, i);
    }

    @Test
    public void askAuthorizationQuestion_STEP0() {
        String str = Questionnaire.askAuthorizationQuestion(0);
        assertEquals(authorizeQuestions[0], str);
    }

    @Test
    public void askAuthorizationQuestion_STEP1() {
        String str = Questionnaire.askAuthorizationQuestion(1);
        assertEquals(authorizeQuestions[1], str);
    }

    @Test
    public void askAuthorizationQuestion_STEP2() {
        String str = Questionnaire.askAuthorizationQuestion(2);
        assertEquals(authorizeQuestions[2], str);
    }

    @Test
    public void askAuthorizationQuestion_STEP3() {
        String str = Questionnaire.askAuthorizationQuestion(3);
        assertEquals(null, str);
    }

    @Test
    public void getAttrFromQuestion_0() {
        Attr attr = Questionnaire.getAttrFromQuestion(authorizeQuestions[0]);
        assertEquals(Attr.Name, attr);
    }

    @Test
    public void getAttrFromQuestion_1() {
        Attr attr = Questionnaire.getAttrFromQuestion(authorizeQuestions[1]);
        assertEquals(Attr.Group, attr);
    }

    @Test
    public void getAttrFromQuestion_2() {
        Attr attr = Questionnaire.getAttrFromQuestion(authorizeQuestions[2]);
        assertEquals(Attr.Subgroup, attr);
    }
}