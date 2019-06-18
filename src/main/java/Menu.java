import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Menu extends bot {


    void message_in(Message message) {
        logger();
        if (message.getText().equals("/menu")||message.getText().equals("Main menu")){
            appear(message);
        } else {
            MainControll mainControll = new MainControll();
            mainControll.message_in(message);
        }
    }

    void appear(Message message){
        sendMsg(message.getChatId(),message.getText(), Buttons.start_chat());
    }


    private static void logger(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.printf("Был совершен вход %s\n",dateFormat.format(date));
    }

//    void sendMsg(long chat_id, String txt) {
//        SendMessage s = new SendMessage();// Боту может писать не один человек, и поэтому чтобы отправить сообщение, грубо говоря нужно узнать куда его отправлять
//        s.setText(txt).setChatId(chat_id);
//        try { //Чтобы не крашнулась программа при вылете Exception
//            execute(s);
//        } catch (TelegramApiException update) {
//            update.printStackTrace();
//        }
//    }
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
