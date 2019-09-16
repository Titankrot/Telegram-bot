import java.lang.reflect.Array;

public class Bot {
    public String welcomeMessage = "Hello";
    private String[] startQuestion = { "What is ur name?", "What is ur group?", "What is ur subgroup?" };
    private Integer numberOfQuestion = 0;

    public void typeHelp() {
        System.out.printf("help");
    }

    public String askQuestion() {
        return startQuestion[numberOfQuestions++];
    }
}
