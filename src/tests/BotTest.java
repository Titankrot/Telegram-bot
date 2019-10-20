package tests;

import bot.Bot;
import org.junit.Test;

import static org.junit.Assert.*;

public class BotTest {

    @Test
    public void getBotUsername_0() {
        Bot bot = new Bot("token");
        assertEquals("test", bot.getBotUsername());
    }

    @Test
    public void getBotToken_0() {
        Bot bot = new Bot("token");
        assertEquals("token", bot.getBotToken());
    }
}