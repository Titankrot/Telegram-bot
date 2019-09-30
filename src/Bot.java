import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.HashMap;

public class Bot extends TelegramLongPollingBot {
    private HashMap<Integer, Student> students = new HashMap<>();
    private String botToken;

    public Bot(String token) {
        botToken = token;
    }

    private void sendText(Message message, String str) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(str);
        try{
            sendMessage(sendMessage);
        } catch (TelegramApiException e){
            e.printStackTrace();
        }
    }

    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        int id = message.getFrom().getId();
        if (!students.containsKey(id)) {
            Student student = new Student(id);
            students.put(id, student);
            sendText(message, student.askAuthorizationQues(0));
            return;
        }
        Student student = students.get(id);
        if (student.isNotAuthorized() &&
                message.getText() != null &&
                message.getReplyToMessage() != null &&
                Student.questionsToAttr.containsKey(message.getReplyToMessage().getText())) {
            String question = message.getReplyToMessage().getText();
            student.setAttr(Student.questionsToAttr.get(question), message.getText());
            String text = student.askAuthorizationQues(Student.questionNumber(question) + 1);
            if (text != null)
                sendText(message, text);
            return;
        }
        if (message.hasText()){
            switch (message.getText()){
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
                    sendText(message, "hz poka");
                    break;
            }
        }
    }

    public String getBotUsername() {
        return "test";
    }

    public String getBotToken() {
        return botToken;
    }
}
