import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Configuration {
    private static HashMap<Long, Double> tradeValueMap = new HashMap<>();

    private static void updateMap() throws FileNotFoundException {
        BufferedReader buf = new BufferedReader(new InputStreamReader(new FileInputStream(new File("DataFile.txt"))));
        Scanner sc = new Scanner(buf);
        StringBuilder builder = new StringBuilder();
        while(sc.hasNextLine()){
            builder.append(sc.nextLine());
        }
        String[] acc = builder.toString().split(" ");
        for (String s : acc) {
            String[] id_bal = s.split("=");
            tradeValueMap.put(Long.parseLong(id_bal[0]), Double.parseDouble(id_bal[1]));
        }
    }
    static void putValueInMap(long id, double value){
        tradeValueMap.put(id, value);
        updateConfig();
    }
    private static void updateConfig() {
        try(FileWriter writer = new FileWriter(new File("DataFile.txt"), false))
        {
            // запись всей строки
            for (Map.Entry<Long, Double> longDoubleEntry : tradeValueMap.entrySet()) {
                writer.write(((longDoubleEntry)) + "\n");
            }
            writer.flush();
        }
        catch(IOException ex) {

            System.out.println(ex.getMessage());
        }

    }

    static double getBalance(long id) {
        try{
        updateMap();
        }catch (FileNotFoundException e){
            System.out.println("getBalance");
        }
        if(tradeValueMap.get(id)==null){
            createBalance(id);
        }
        return tradeValueMap.get(id);

    }

    static void createBalance(long id) {
        tradeValueMap.put(id, 0.0);
        updateConfig();
    }

//    static void changeBalance(long id) {
//        if(tradeValueMap.get(id)==null){
//            createBalance(id);
//        }
//        if(tradeValueMap.get(id)<=0.0){
//            return;
//        }
//        double res = balance(id)- (double) 5;
//        tradeValueMap.put(id, res);
//        updateConfig();
//    }


}
