import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Admin extends User {

    private long  guest_chat_id;
    private String text,id, name, surname, level;

    Admin(Update update){
        Message msg = update.getMessage();
        text = msg.getText();
        guest_chat_id = msg.getChatId();
        id = msg.getChat().getUserName();
        name = msg.getChat().getFirstName();
        surname = msg.getChat().getLastName();
        level = "Admin";
    }
    @Override
    void message_in() {
        logger();
        switch (text) {
            case("Content"):
                this.sendMsg(guest_chat_id, text, Buttons.content());
                break;
            case("Balance"):
                this.sendMsg(guest_chat_id, "Пиздец ты нищий", Buttons.balance());
                break;
            case("History"):
                this.sendMsg(guest_chat_id, text, Buttons.history());
                break;
            case("Settings"):
                this.sendMsg(guest_chat_id, text, Buttons.settings());
                break;
            default:
                this.sendMsg(guest_chat_id, "Главное меню", Buttons.start_chat());
                break;
        }
    }

    @Override
    void logger() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.printf("|Была совершена отправка сообщения %s \n|Пользователь %s имя %s %s\n|Уровень доступа %s\n",dateFormat.format(date),
                id, name,surname,level);

    }
}
