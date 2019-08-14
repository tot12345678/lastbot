import api.Order;
import api.ProfitController;
import api.Wallet;

public class ExmoThread extends Thread {
    @SuppressWarnings("InfiniteLoopStatement")
    @Override
    public void run() {
        try {
            while (true) {
                if (Bot.tumbler & Order.getOpenOrders()) {
                    if(Double.valueOf(Wallet.getValueOfBalance("USD")) >= 0){
                        ProfitController.calculationOfCreationOrders("BTC_USD", Configuration.getValue());
                    }else {
                        Order.createOrder("BTC_USD", Double.valueOf(Wallet.getValueOfBalance("BTC")),
                                ProfitController.oldpPrice * 1.005, "sell");
                    }
                    Thread.sleep(10000);
                } else {
                    Thread.sleep(1000);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}