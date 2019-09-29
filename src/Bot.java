import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.HashMap;

public class Bot extends TelegramLongPollingBot {
    private HashMap<Long, Student> Students = new HashMap<>();

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
        Long id = message.getChatId();
        if (!Students.containsKey(id))
            Students.put(id, new Student(id));
        if (message != null && message.hasText()){
            switch (message.getText()){
                case "/help":
                    sendText(message, "hz poka");
                    break;
                case "/settings":
                    sendText(message, "hz poka");
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
