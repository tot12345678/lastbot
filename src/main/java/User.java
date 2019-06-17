import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

class User extends bot {

    User(Update update){

    }
    void message_in() throws IOException {
        System.out.println("Макет юзера");
        logger();

    }
    void logger(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.printf("Был совершен вход %s \n",dateFormat.format(date));
    }

    void sendMsg(long chat_id, String txt) {
        SendMessage s = new SendMessage();// Боту может писать не один человек, и поэтому чтобы отправить сообщение, грубо говоря нужно узнать куда его отправлять
        s.setText(txt).setChatId(chat_id);
        try { //Чтобы не крашнулась программа при вылете Exception
            execute(s);
        } catch (TelegramApiException update) {
            update.printStackTrace();
        }
    }
    <T> void sendMsg(long chat_id, T parameter, ReplyKeyboardMarkup butt) {
        String text = parameter.toString();
        SendMessage s = new SendMessage();// Боту может писать не один человек, и поэтому чтобы отправить сообщение, грубо говоря нужно узнать куда его отправлять
        s.setChatId(chat_id).setText(text).setReplyMarkup(butt);
        try { //Чтобы не крашнулась программа при вылете Exception
            execute(s);
        } catch (TelegramApiException update) {
            update.printStackTrace();
        }
    }
}
