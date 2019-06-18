import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.IOException;

public class Balance extends Menu{

//    @Override
//    void message_in(Message message) {
//        return; //stub
//    }

    @Override
    void appear(Message message) {
        String text = message.getText();
        long chat_id = message.getChatId();
        switch (text) {
            case "Balance":
                try {
                    sendMsg(chat_id, text, Buttons.balance(chat_id));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "Withdraw money":
                sendMsg(chat_id, text, Buttons.with_asist());
                DataFile.changeBalance(chat_id);
                try {
                    sendMsg(chat_id, text, Buttons.balance(chat_id));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "Deposit money":
                break;//stub
            default:
                break;
        }
    }
}
