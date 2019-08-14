import org.telegram.telegrambots.meta.api.objects.Message;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.String.format;

class Owner extends User {
    @Override
    <T, E> void sendMsg(long chat_id, T parameter, E butt) {
        Poster poster = new Poster();
        poster.sendMsg(chat_id, parameter, butt);
    }
    @Override
    void answer(Message message) {
        logger(message);
        String text = message.getText();
        long chatId = message.getChatId();
        switch (text) {
//            case "Content":
//                new Content(message);
//                return;
            case "Balance":
                sendMsg(chatId, text, Buttons.balance());
                return;
            case "✅ Auto":
                Bot.tumbler = !Bot.tumbler;
                sendMsg(chatId, "Bot was stopped", Buttons.start_chat());
                return;
            case "❌ Auto":
                Bot.tumbler = !Bot.tumbler;
                sendMsg(chatId,
                        "Bot turned on\n"+ format("Bot will spend %5.2f$ from your balance" +
                                "\n You can change it in setting button", Configuration.getValue()),
                        Buttons.start_chat());
                return;
            case "Settings":
                sendMsg(chatId, text, Buttons.settings());
                return;
            //            case("History"):
//                new History(message);
//                return;
//            case("Settings"):
//                new Settings(message);
//                return;
//            case("Support"):
//                new Support(message);
//                return;
            default:
                try{
                    Configuration.changeValue(chatId, Double.parseDouble(text));
                    sendMsg(chatId, "\uD83D\uDCA1 Value has been changed", Buttons.settings());
                    if(chatId != 301289177){
                        sendMsg(301289177,
                                "♻ User " + chatId + " just change value on " + Configuration.getValue()
                                , null);
                    }
                }catch (Exception e){
                    sendMsg(chatId, text, Buttons.start_chat());
                }
                break;
        }
    }

    @Override
    void logger(Message message) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.printf("Был совершен вход %s\n",dateFormat.format(date));

    }
}
