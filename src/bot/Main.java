package bot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write bot token here:");
        String token = scanner.nextLine();
        ApiContextInitializer.init();
        TelegramBotsApi bot = new TelegramBotsApi();
        try {
            bot.registerBot(new Bot(token));

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
