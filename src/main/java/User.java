import org.telegram.telegrambots.meta.api.objects.Message;

abstract class User {

    abstract <T,E> void sendMsg(long chat_id, T parameter, E butt);

    abstract void answer(Message message);
    abstract void logger(Message message);
}
