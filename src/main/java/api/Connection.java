package api;

public interface Connection {
    Exmo CONNECTION = new Exmo(System.getenv("keyA"),
            System.getenv("keyB"));
    }
