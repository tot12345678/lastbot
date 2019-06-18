import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;


public class bot extends TelegramLongPollingBot {
    public static void main(String[] args) {
        System.err.close();
        System.setErr(System.out);
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new bot());

        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }
    public String getBotUsername() {
        //return System.getenv("username");
        return "Ofwgkta_bot";

    }public String getBotToken() {
        //return System.getenv("token");
        return "831206675:AAEhe93MAkyW3xw2TBKIE08zKIhb4wDeJII";
    }

    public void onUpdateReceived(Update update) {
        Menu menu = new Menu();
        menu.message_in(update.getMessage());
    }
}
