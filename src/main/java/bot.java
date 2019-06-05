import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
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
        return "Ofwgkta_bot";
    }public String getBotToken() {
        return "/";
    }


    public void onUpdateReceived(Update update) {
        MessageHandMade msg = new MessageHandMade(update.getMessage());
        Send s = new Send();
        switch (msg.getText()) {
            case "/get_balance":
                s.sendMsg(msg.getChatId(), Send_Buttons.balance());
            case "/get_settings":
                s.sendMsg(msg.getChatId(), Send_Buttons.settings());
            default:
                s.sendMsg(msg.getChatId(), "Главное меню", Send_Buttons.start_chat());
                break;

        }

    }
}
