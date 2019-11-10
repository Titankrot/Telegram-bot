package bot;

import java.util.Arrays;

public class Questionnaire {
    private static String[] authorizeQuestions = new String[] {
            "Как твое имя?",
            "Какая у тебя группа?",
            "Какой номер твоей подгруппы?"
    };

    static boolean containsInQuestions(String question) {
        return Arrays.asList(authorizeQuestions).contains(question);
    }

    public static Attr getAttrFromQuestion(String question) {
        if (question.equals(authorizeQuestions[0])) {
            return Attr.Name;
        }
        else if (question.equals(authorizeQuestions[1])) {
            return Attr.Group;
        }
        else {
            return Attr.Subgroup;
        }
    };

    public static int getQuestionNumber(String quest){
        for (int i = 0; i < authorizeQuestions.length; i++) {
            String question = authorizeQuestions[i];
            if (question.equals(quest)) {
                return i;
            }
        }
        return -1;
    }

    public static String askAuthorizationQuestion(int authorizeStep){
        if (authorizeStep == authorizeQuestions.length) {
            return null;
        }
        return authorizeQuestions[authorizeStep];
    }
}
