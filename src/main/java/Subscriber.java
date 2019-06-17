import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

class Subscriber extends User {
    private long  chat_id;
    private String text, id,  name, surname, level;

    Subscriber(Update update) {
        super(update);
        Message msg = update.getMessage();
        text = msg.getText();
        chat_id = msg.getChatId();
        id = msg.getChat().getUserName();
        name = msg.getChat().getFirstName();
        surname = msg.getChat().getLastName();
        level = "subscriber";
    }

    //    Guest(Update update){
//        Message msg = update.getMessage();
//        text = msg.getText();
//        guest_chat_id = msg.getChatId();
//        id = msg.getChat().getUserName();
//        name = msg.getChat().getFirstName();
//        surname = msg.getChat().getLastName();
//        level = "user";
//    }
    @Override
    void message_in() throws IOException {
        logger();

        switch (text) {
            case("Content"):
                this.sendMsg(chat_id, text, Buttons.content());
                break;
            case("Balance"):
                this.sendMsg(chat_id, "1", Buttons.balance(chat_id));
                break;
            case("History"):
                this.sendMsg(chat_id, text, Buttons.history());
                break;
            case("Settings"):
                this.sendMsg(chat_id, text, Buttons.settings());
                break;
            case("Main menu"):
                this.sendMsg(chat_id, text, Buttons.start_chat());
                break;
            case("Withdraw money"):
                DataFile.changeBalance(chat_id, 5);
            default:
                break;
        }
    }

    @Override
    void logger() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.printf("Была совершена отправка сообщения %s \nПользователь %s имя %s %s\nУровень доступа %s",dateFormat.format(date),id,
        name,surname,level);

    }
}

