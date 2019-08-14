import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.io.FileNotFoundException;


public class Bot extends TelegramLongPollingBot {
    static boolean tumbler = false;

    public synchronized static void main(String[] args) {
        System.err.close();
        System.setErr(System.out);
        ApiContextInitializer.init();
        ExmoThread secondThread = new ExmoThread();
        secondThread.start();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new Bot());

        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }

    public String getBotUsername() {
        return System.getenv("username");
    }

    public String getBotToken() {
        return System.getenv("token");
    }


    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        try {
            (AccessController.checkUser(message.getChatId())).answer(message);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}

