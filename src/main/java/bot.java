import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class bot extends TelegramLongPollingBot {
    Photo snap = new Photo();
    public static void main(String[] args) {
        System.err.close();
        System.setErr(System.out);
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new bot());

        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
        System.out.println("Бота поднял, но не Рассею((");
    }

    public String getBotUsername() {
        return System.getenv("username");
    }

    public String getBotToken() {
        return System.getenv("token");
    }

    //основная часть кода бота

      public void onUpdateReceived(Update update) {
        Message msg = update.getMessage(); // Это нам понадобится
        if(msg.hasText()) {
            String txt = msg.getText();
            System.out.println(txt + " Текстовое сообщение принято");
            if (txt.equals("/start")) {
                sendMsg(msg, "Ну шо ты пездюх?");
            }else if(txt.equals("/pic")){
                if(snap.f_id == "пусто, отправьте мне изображение"){
                    sendMsg(msg, snap.f_id);
                }else{
                    sendPh(msg, snap.f_id);
                }
            }
            else {
                sendMsg(msg, "Я не знаю таких команд");
            }
        }
        else if(msg.hasPhoto()){
            System.out.println(" Фото принято");
            snap.f_id = msg.getPhoto().stream()
                    .sorted(Comparator.comparing(PhotoSize::getFileSize).reversed())
                    .findFirst()
                    .orElse(null).getFileId();
            sendMsg(msg, snap.f_id);
            System.out.println(snap.f_id);
        }
    }


    @SuppressWarnings("deprecation") // Означает то, что в новых версиях метод уберут или заменят
    //ОТПРАВКА ТЕКСТА
    private void sendMsg(Message msg, String txt) {
        log(msg, txt);
        SendMessage s = new SendMessage();// Боту может писать не один человек, и поэтому чтобы отправить сообщение, грубо говоря нужно узнать куда его отправлять
        s.setText(txt).setChatId(msg.getChatId());
        try { //Чтобы не крашнулась программа при вылете Exception
            execute(s);
        } catch (TelegramApiException update) {
            update.printStackTrace();
        }
    }

    //ОТПРАВКА ФОТО
    private void sendPh(Message msg,String f_id) {
        log(msg, f_id);
        SendPhoto s = new SendPhoto().setPhoto(f_id).setChatId(msg.getChatId());
        try { //Чтобы не крашнулась программа при вылете Exception
            execute(s);
        } catch (TelegramApiException update) {
            update.printStackTrace();
        }
    }

    private void log(Message msg, String bot_answer) {
        String user_first_name = msg.getChat().getFirstName();
        String user_last_name = msg.getChat().getLastName();
        long user_id = msg.getChat().getId();
        String txt = "был отправлен не текстовый документ";
        if (msg.hasText())
            txt = msg.getText();
        System.out.println("\n ----------------------------");
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        System.out.println("Message from " + user_first_name + " " + user_last_name + ". (id = " + user_id + ") \n Text - " + txt);
        System.out.println("Bot answer: \n Text - " + bot_answer);
    }
}
//СОХРАНЕНИЕ ФОТО
class Photo{
    String f_id = "пусто, отправьте мне изображение";
}
