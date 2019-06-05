import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class Build_Buttons {
    static InlineKeyboardMarkup builder(int col_rows, int col_coluns, String face){
        InlineKeyboardMarkup inlineKeyboardMarkup =new InlineKeyboardMarkup();
        List<InlineKeyboardButton> rows = new ArrayList<>();
        List<List<InlineKeyboardButton>> rowList= new ArrayList<>();
        for (int i = 0; i < col_coluns; i++) { //создает набор из строк кнопок
            for (int l = 0; l < col_rows; l++) {
                rows.add(new InlineKeyboardButton().setText(face)  //создает строку кнопок
                        .setCallbackData("ziga"));
            }
            rowList.add(rows);
        }



        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }
}
