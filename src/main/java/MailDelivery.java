import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MailDelivery extends Bot {


    void message_in(Message message) {
        logger();
        MainControll mainControll = new MainControll();
        mainControll.message_in(message);

    }

    private static void logger(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.printf("Был совершен вход %s\n",dateFormat.format(date));
    }

    <T,E> void sendMsg(long chat_id, T parameter, E butt) {
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
