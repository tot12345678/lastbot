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
    }

    public String getBotToken() {
        return "831206675:AAEhe93MAkyW3xw2TBKIE08zKIhb4wDeJII";
    }

    //основная часть кода бота

    public void onUpdateReceived(Update update) {
        MessageHandMade msg = new MessageHandMade(update.getMessage());


    }


//    private void sendMsg(Message msg, String txt) {
//        SendMessage s = new SendMessage();// Боту может писать не один человек, и поэтому чтобы отправить сообщение, грубо говоря нужно узнать куда его отправлять
//        s.setText(txt).setChatId(msg.getChatId());
//        try { //Чтобы не крашнулась программа при вылете Exception
//            execute(s);
//        } catch (TelegramApiException update) {
//            update.printStackTrace();
//        }
//    }

    //ОТПРАВКА ФОТО

}
