import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class DataFile {
    private static HashMap<Long,Double> map_balance= new HashMap<>();

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
            map_balance.put(Long.parseLong(id_bal[0]), Double.parseDouble(id_bal[1]));
        }
    }
    private static void updateConfig() {
        try(FileWriter writer = new FileWriter(new File("DataFile.txt"), false))
        {
            // запись всей строки
            for (Map.Entry<Long, Double> longDoubleEntry : map_balance.entrySet()) {
                writer.write(((longDoubleEntry)) + " ");
            }
            writer.flush();
        }
        catch(IOException ex) {

            System.out.println(ex.getMessage());
        }

}

    static double getBalance(long id) throws FileNotFoundException {
        updateMap();
        if(map_balance.get(id)==null){
            createBalance(id);
        }
        updateConfig();
        return map_balance.get(id);

    }
    private static double balance(long id) {
        return map_balance.get(id);

    }

    private static void createBalance(long id) {
        map_balance.put(id, 0.0);
        updateConfig();
    }

    static void changeBalance(long id) {
        if(map_balance.get(id)==null){
            createBalance(id);
        }
        if(map_balance.get(id)<=0.0){
            return;
        }
        double res = balance(id)- (double) 5;
        map_balance.put(id, res);
        updateConfig();
    }


}
