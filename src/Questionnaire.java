import java.util.Map;

public class Questionnaire {
    private static String[] autorizeQuestions = new String[] {
            "Как твое имя?",
            "Какая у тебя группа?",
            "Какой номер твоей подгруппы?"
    };

    static int questionNumber(String quest){
        for (int i = 0; i < autorizeQuestions.length; i++) {
            String question = autorizeQuestions[i];
            if (question.equals(quest))
                return i;
        }
        return -1;
    }

    static Map<String, String> questionsToAttr = Map.of(
            autorizeQuestions[0], "Name",
            autorizeQuestions[1], "Group",
            autorizeQuestions[2], "Subgroup"
    );

    static String askAuthorizationQuestion(int authorizeStep){
        if (authorizeStep == autorizeQuestions.length)
            return null;
        return autorizeQuestions[authorizeStep];
    }
}
