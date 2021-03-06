
import org.telegram.telegrambots.meta.api.objects.Message;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

class Guest extends User {
    @Override
    <T, E> void sendMsg(long chat_id, T parameter, E butt) {
            Poster poster = new Poster();
            poster.sendMsg(chat_id, parameter.toString(), butt);

    }
    @Override
    void answer(Message message) {
        logger(message);
        long chatId = message.getChatId();
        String code = String.valueOf(AccessController.authCode);
        if ("Registration".equals(message.getText())) {
            AccessController.authCode = (int) (1000 + Math.random() * 9999);
            sendMsg(301289177, AccessController.authCode, null);
            System.out.println("fly");
        } else {
            if (code.equals(message.getText())) {
                AccessController.addUserInList(message.getChatId());
                sendMsg(message.getChatId(), "\uD83D\uDC7ESuccess\uD83D\uDC7E", Buttons.start_chat());
                sendMsg(301289177, "\uD83E\uDD18 New user added for our spider id: " +
                        message.getChatId(), null);
            } else {
                sendMsg(chatId, "Press Registration and enter code of registration",
                        Buttons.registration());
            }
        }

    }

    @Override
    void logger(Message message) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.printf("Был совершен вход %s %s\n",dateFormat.format(date), "чужак");
    }
}
