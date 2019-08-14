package api;

import java.util.HashMap;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProfitController implements Connection {
    public static double oldpPrice = 0;
    private static HashMap<String, Double> buy_price = new HashMap<>(), //0
//                            sell_price = new HashMap<>(),//1
//                            last_trade = new HashMap<>(),//2
//                            high = new HashMap<>(),      //3
//                            low = new HashMap<>(),       //4
                            avg = new HashMap<>();       //5
//                            vol = new HashMap<>(),       //6
//                            vol_curr = new HashMap<>(),  //7
//                            updated = new HashMap<>();   //8

    private static void getOrdersInfo(){
        for (String element :Objects.requireNonNull(CONNECTION
                                    .Request("ticker", null))
                                    .replaceAll(("^\\{|}$"), "")
                                    .replaceAll(("\""), "")
                                    .trim()
                                    .split("},")) {
            String[] nameAndInfo = element.split(":\\{");
            String pairCrypto = nameAndInfo[0];
            String[] onlyInfo = nameAndInfo[1]
                    .replaceAll("[^0-9,.]", "")
                    .trim()
                    .split(",");
            buy_price.put(pairCrypto,Double.parseDouble(onlyInfo[0]));   //цена_покупки
//            sell_price.put(pairCrypto,Double.parseDouble(onlyInfo[1]));  //цена_продажи
//            last_trade.put(pairCrypto,Double.parseDouble(onlyInfo[2]));
//            high.put(pairCrypto,Double.parseDouble(onlyInfo[3]));
//            low.put(pairCrypto,Double.parseDouble(onlyInfo[4]));
            avg.put(pairCrypto,Double.parseDouble(onlyInfo[5]));
//            vol.put(pairCrypto,Double.parseDouble(onlyInfo[6]));
//            vol_curr.put(pairCrypto,Double.parseDouble(onlyInfo[7]));
//            updated.put(pairCrypto,Double.parseDouble(onlyInfo[8]));
        }
    }

    public static void calculationOfCreationOrders(String pair, double value) {
        //getAvgOFsells(pair) if you need
        getOrdersInfo();
        Matcher m = Pattern.compile("BTC_USD$")
                .matcher(pair);
        while (m.find()){
            if (buy_price.get(pair) < avg.get(pair)
                    & buy_price.get(pair) < CryptoHelper.getAvgOfpair(pair, "buy")){
                Order.createOrder(pair, value / buy_price.get(pair)
                        , buy_price.get(pair), "buy");
                oldpPrice = buy_price.get(pair);
                System.out.println("order is created");
                break;
            }
        }
        System.out.println("order is't created");
    }

}
