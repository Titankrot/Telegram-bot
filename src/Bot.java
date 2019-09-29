import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.HashMap;

public class Bot extends TelegramLongPollingBot {
    private HashMap<Integer, Student> Students = new HashMap<>();

    private void sendText(Message message, String str){
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
        if (!Students.containsKey(id)) {
            Student student = new Student(id);
            Students.put(id, student);
            sendText(message, student.askAthorizationQues(0));
            return;
        }
        Student student = Students.get(id);
        if (student.isNotAuthorized() &&
                message.getText() != null &&
                message.getReplyToMessage() != null &&
                Student.QuestionsToAttr.containsKey(message.getReplyToMessage().getText())) {
            String question = message.getReplyToMessage().getText();
            student.setAttr(Student.QuestionsToAttr.get(question), message.getText());
            sendText(message, student.askAthorizationQues(Student.questionNumber(question) + 1));
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
                    sendText(message, student.ToString());
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
        return "967966815:AAFUMT98NnKDu4p7NG7dAuDS5Ug8EzNLav8";
    }
}
