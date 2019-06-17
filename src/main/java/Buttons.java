import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.io.IOException;
import java.util.ArrayList;
class Buttons {
     static ReplyKeyboardMarkup start_chat(){
        ReplyKeyboardMarkup replyKeyboardMarkup =new ReplyKeyboardMarkup();
        ArrayList<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow row1 = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();
        KeyboardRow row3 = new KeyboardRow();
        replyKeyboardMarkup.setSelective(true).setResizeKeyboard(true).setOneTimeKeyboard(false);
        row1.add("Content");
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
//        public static InlineKeyboardMarkup line_but(){
//        InlineKeyboardMarkup inlineKeyboardMarkup =new InlineKeyboardMarkup();
//        List<List<InlineKeyboardButton>> rowList= new ArrayList<>();
//
//        List<InlineKeyboardButton> row1 = new ArrayList<>();
//        row1.add(new InlineKeyboardButton().setText("Баланс")  //создает строку кнопок
//                .setCallbackData("/get_balance"));
//
//        List<InlineKeyboardButton> row2 = new ArrayList<>();
//        row2.add(new InlineKeyboardButton().setText("Магазин")  //создает строку кнопок
//                .setCallbackData("magazine()"));
//
//        List<InlineKeyboardButton> row3 = new ArrayList<>();
//        row3.add(new InlineKeyboardButton().setText("Настройки")  //создает строку кнопок
//                .setCallbackData("settings()"));
//
//        rowList.add(row1);
//        rowList.add(row2);
//        rowList.add(row3);
//
//        inlineKeyboardMarkup.setKeyboard(rowList);
//        inlineKeyboardMarkup.se
//        return inlineKeyboardMarkup;
//    }
    static ReplyKeyboardMarkup content() {

        return null;
    }
    static  ReplyKeyboardMarkup balance(long chat_id) throws IOException {
        ReplyKeyboardMarkup balance =new ReplyKeyboardMarkup();
        ArrayList<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow row0 = new KeyboardRow();
        KeyboardRow row1 = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();
        row0.add(Double.toString(DataFile.getBalance(chat_id)));
        row1.add("Withdraw money");
        row1.add("Deposit money");
        row2.add("Main menu");
        keyboard.add(row0);
        keyboard.add(row1);
        keyboard.add(row2);
        balance.setKeyboard(keyboard);
        balance.setSelective(true).setResizeKeyboard(true).setOneTimeKeyboard(false);
        return balance;
    }
    static ReplyKeyboardMarkup history() {

        return null;
    }
    static ReplyKeyboardMarkup settings(){
        return null;
    }
}
