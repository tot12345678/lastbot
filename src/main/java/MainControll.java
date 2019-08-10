import org.telegram.telegrambots.meta.api.objects.Message;


class MainControll extends Menu {


    @Override
    void message_in(Message message) {
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
            case "❌ Auto":
                Bot.tumbler = !Bot.tumbler;
                sendMsg(chatId, text, Buttons.start_chat());
                return;
            case "Settings":
                sendMsg(chatId, text, Buttons.settings(chatId));
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
                    Configuration.putValueInMap(chatId, Double.parseDouble(text));
                    sendMsg(chatId, text, Buttons.settings(chatId));
                }catch (Exception e){
                    sendMsg(chatId, text, Buttons.start_chat());
                }
                break;
        }
    }
}
