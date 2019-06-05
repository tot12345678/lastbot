import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MessageHandMade extends Message {
    private Message msg;
    MessageHandMade (Message message){
        this.msg = message;
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        System.out.println("Message from " + msg.getChat().getFirstName() + " " + msg.getChat().getLastName()+ ". (id = " + msg.getChat().getId()+")");
    }

    @Override
    public String getText() {
        return msg.getText();
    }
}
