import api.Order;
import api.ProfitController;

public class ExmoThread extends Thread{
    @SuppressWarnings("InfiniteLoopStatement")
    @Override
    public void run() {
        try {
            while (true) {
                if (Bot.tumbler) {
                    if(Order.getOpenOrders()){
                    ProfitController.calculationOfCreationOrders("BTC_USD");
                    }else{
                        Thread.sleep(1000);
                    }
                } else {
                    Thread.sleep(1000);
                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}