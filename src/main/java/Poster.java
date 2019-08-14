import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Poster extends Bot{

    <T, E> void sendMsg(long chat_id, T parameter, E butt) {
        String text = parameter.toString();
        SendMessage s = new SendMessage();// Боту может писать не один человек, и поэтому чтобы отправить сообщение, грубо говоря нужно узнать куда его отправлять
        s.setChatId(chat_id).setText(text).setReplyMarkup((ReplyKeyboard)(butt));
        try { //Чтобы не крашнулась программа при вылете Exception
            execute(s);
        } catch (TelegramApiException update) {
            update.printStackTrace();
        }
    }
}
