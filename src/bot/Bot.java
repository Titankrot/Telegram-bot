package bot;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.HashMap;

public class Bot extends TelegramLongPollingBot {
    private HashMap<Integer, Student> students = new HashMap<>();
    private String botToken;
    private Schedule schedule;

    public Bot(String token) {
        botToken = token;
        schedule = new Schedule();
    }

    private void sendText(Message message, String str) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(str);
        try{
            sendApiMethod(sendMessage);
        } catch (TelegramApiException e){
            e.printStackTrace();
        }
    }

    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        Student student = authorizeStudent(message);
        if (student == null) return;
        int id = message.getFrom().getId();
        if (message.hasText()){
            switch (message.getText()){
                case "/today":
                    sendText(message, schedule.getTodaySchedule(student));
                    break;
                case "/help":
                    sendText(message, "hz poka");
                    break;
                case "/settings":
                    sendText(message, "hz poka");
                    break;
                case "/info":
                    sendText(message, student.toString());
                    break;
                default:
                    sendText(message, "Чтобы ответить боту смахните его вопрос влево,\n" +
                            "Чтобы вызвать информацию о себе:\n /info");
                    break;
            }
        }
    }

    private Student authorizeStudent(Message message) {
        int id = message.getFrom().getId();
        if (!students.containsKey(id)) {
            Student student = new Student(id);
            students.put(id, student);
            sendText(message, Questionnaire.askAuthorizationQuestion(0));
            return null;
        }
        else {
            Student student = students.get(id);
            if (student.isNotAuthorized() &&
                    message.getText() != null &&
                    message.getReplyToMessage() != null &&
                    Questionnaire.containsInQuestions(message.getReplyToMessage().getText())) {
                String question = message.getReplyToMessage().getText();
                String error = student.setAttr(Questionnaire.getAttrFromQuestion(question), message.getText());
                if (error == null) {
                    String text = Questionnaire.askAuthorizationQuestion(
                            Questionnaire.getQuestionNumber(question)
                            + 1);
                    if (text != null)
                        sendText(message, text);
                }
                else sendText(message, error);
                return null;
            }
            return student;
        }
    }

    public String getBotUsername() {
        return "test";
    }

    public String getBotToken() {
        return botToken;
    }
}
