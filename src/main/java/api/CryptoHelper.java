package api;

import java.util.HashMap;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class CryptoHelper implements Connection{

    static double getAvgOfpair(String pair, String typeOfOperation) {
        Matcher m = Pattern.compile("type:"+typeOfOperation+"[a-z0-9:,.]+price:[0-9.]+")
                .matcher(Objects.requireNonNull(CONNECTION
                        .Request("trades", new HashMap<String, String>() {
                            {
                                put("pair", pair);
                            }
                        }))
                        .replaceAll(("[\\[\\]\"{}]"), "")
                        .replaceAll((pair + ":"), ""));
        int counter = 0;
        double sumOfAll = 0;
        while (m.find()) {
            counter++;
            sumOfAll += Double.parseDouble(m.group()
                    .replaceAll(("[a-z0-9:.,]+:"), ""));
        }
        return sumOfAll / counter;
    }
}
