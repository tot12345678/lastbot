import api.Wallet;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

class Buttons {
    static ReplyKeyboardMarkup start_chat(){
        ReplyKeyboardMarkup replyKeyboardMarkup =new ReplyKeyboardMarkup();
        ArrayList<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow row1 = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();
        KeyboardRow row3 = new KeyboardRow();
        replyKeyboardMarkup.setSelective(true).setResizeKeyboard(true).setOneTimeKeyboard(false);
        if (Bot.tumbler){
            row1.add("✅ Auto");
        }else{
            row1.add("❌ Auto");
        }
        row1.add("Balance");
        row2.add("Settings");
        row2.add("History");
        row3.add("Support");
        keyboard.add(row1);
        keyboard.add(row2);
        keyboard.add(row3);
        replyKeyboardMarkup.setKeyboard(keyboard);
        return (replyKeyboardMarkup);
    }

    static InlineKeyboardMarkup balance(){

        InlineKeyboardMarkup inlineKeyboardMarkup =new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList= new ArrayList<>();

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(new InlineKeyboardButton().setText(format("BTC %10s", Wallet.getValueOfBalance("BTC")))
                .setCallbackData("BTC"));
        row1.add(new InlineKeyboardButton().setText(format("USD %10s", Wallet.getValueOfBalance("USD")))
                .setCallbackData("USD"));
        row1.add(new InlineKeyboardButton().setText(format("ETH %10s", Wallet.getValueOfBalance("ETH")))
                .setCallbackData("ETH"));

        List<InlineKeyboardButton> row2 = new ArrayList<>();
        row2.add(new InlineKeyboardButton().setText(format("EUR %10s", Wallet.getValueOfBalance("EUR")))
                .setCallbackData("EUR"));
        row2.add(new InlineKeyboardButton().setText(format("RUB %10s", Wallet.getValueOfBalance("RUB")))
                .setCallbackData("RUB"));
        row2.add(new InlineKeyboardButton().setText(format("UAH %10s", Wallet.getValueOfBalance("UAH")))
                .setCallbackData("UAH"));

        List<InlineKeyboardButton> row3 = new ArrayList<>();
        row3.add(new InlineKeyboardButton().setText(format("PLN %10s", Wallet.getValueOfBalance("PLN")))
                .setCallbackData("PLN"));
        row3.add(new InlineKeyboardButton().setText(format("TRY %10s", Wallet.getValueOfBalance("TRY")))
                .setCallbackData("TRN"));
        row3.add(new InlineKeyboardButton().setText(format("LTC %10s", Wallet.getValueOfBalance("LTC")))
                .setCallbackData("LTC"));

        rowList.add(row1);
        rowList.add(row2);
        rowList.add(row3);
        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }
//    public static ReplyKeyboardMarkup content() {
//
//        return null;
//    }
    static  ReplyKeyboardMarkup settings(long chatId) {
        ReplyKeyboardMarkup balance =new ReplyKeyboardMarkup();
        ArrayList<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow row0 = new KeyboardRow();
        KeyboardRow row1 = new KeyboardRow();
        try {
            double value = Configuration.getBalance(chatId);
            if (value < 0.01 ){
                value = 0.0;
            }
            row0.add(format("Value of the step %5.2f$",value));
        } catch (Exception e) {
            Configuration.createBalance(chatId);
            row0.add("Value of the step " + Configuration.getBalance(chatId));
        }
        row1.add("Main menu");
        keyboard.add(row0);
        keyboard.add(row1);
        balance.setKeyboard(keyboard);
        balance.setSelective(true).setResizeKeyboard(true).setOneTimeKeyboard(false);
        return balance;
    }
//    public static ReplyKeyboardMarkup history() {
//
//        return null;
//    }
//    public static ReplyKeyboardMarkup settings(){
//        return null;
//    }
}
