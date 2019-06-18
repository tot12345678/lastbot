import org.telegram.telegrambots.meta.api.objects.Message;


class MainControll extends Menu {


    @Override
    void message_in(Message message) {
        String text = message.getText();
        switch (text) {
//            case "Content":
//                new Content(message);
//                return;
            case        "Balance":
            case        "Withdraw money":
                new Balance().appear(message);
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
                break;
        }
    }
}
