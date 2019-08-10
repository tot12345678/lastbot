package api;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Wallet implements Connection {


    private static String getUserInfoBalance(){
        String myAllBalance = "";
        Matcher m = Pattern.compile("balances.*},")
                            .matcher(Objects.requireNonNull
                                    (CONNECTION
                                            .Request("user_info", null)));
        while (m.find()) {
            myAllBalance = m.group().replaceAll((":\\{|[^A-Z:0-9,]"), "");
        }

        return myAllBalance;
    }
    public static String getValueOfBalance(String crypto){

        for (String s : getUserInfoBalance().split(",")) {
            if ( s.contains(crypto)){
                crypto = (s.split(":")[1]);
            }
        }
        return crypto;
    }
}
