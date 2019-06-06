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
        return "Ofwgkta_bot";
    }public String getBotToken() {
        return "";
    }


    public void onUpdateReceived(Update update) {
        MessageHandMade msg = new MessageHandMade(update.getMessage());
        Send s = new Send();
        switch (msg.getText()) {
            case("Content"):
                s.sendMsg(msg.getChatId(), msg.getText(), Buttons.content());
                break;
            case("Balance"):
                s.sendMsg(msg.getChatId(), String.format("\uD83D\uDE0D Your balance: \uD83D\uDE0D\uD83C\uDF89 %1000d$",145), Buttons.balance());
                break;
            case("History"):
                s.sendMsg(msg.getChatId(), msg.getText(), Buttons.history());
                break;
            case("Settings"):
                s.sendMsg(msg.getChatId(), msg.getText(), Buttons.settings());
                break;
            default:
                s.sendMsg(msg.getChatId(), "Главное меню", Buttons.start_chat());
                break;
        }
    }
}
