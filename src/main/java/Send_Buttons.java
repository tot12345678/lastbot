import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Send_Buttons extends SendMessage {
    public static InlineKeyboardMarkup start_chat(){
        InlineKeyboardMarkup inlineKeyboardMarkup =new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList= new ArrayList<>();

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(new InlineKeyboardButton().setText("Баланс")  //создает строку кнопок
                .setCallbackData("/get_balance"));

        List<InlineKeyboardButton> row2 = new ArrayList<>();
        row2.add(new InlineKeyboardButton().setText("Магазин")  //создает строку кнопок
                .setCallbackData("magazine()"));

        List<InlineKeyboardButton> row3 = new ArrayList<>();
        row3.add(new InlineKeyboardButton().setText("Настройки")  //создает строку кнопок
                .setCallbackData("settings()"));

        rowList.add(row1);
        rowList.add(row2);
        rowList.add(row3);

        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }
    public static String balance() {

        return "Ваш баланс 0 рублев! Вы ебаный бомж!";
    }
    public static String settings(){
        return "Отведай говнв";
    }


}
