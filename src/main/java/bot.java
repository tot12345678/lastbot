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
        return System.getenv("username");
    }public String getBotToken() {
        return System.getenv("token");
    }


    User user, admin;

    public void onUpdateReceived(Update update) {

        if(update.getMessage().getChat().getId() == 301289177) {
            (admin = new Admin(update)).message_in();
        } else{
            (user= new Guest(update)).message_in();
        }

    }
}
