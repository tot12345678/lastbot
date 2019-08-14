import java.io.*;
import java.util.Scanner;

class Configuration {
    private static Double tradeValue;
    private static void updateValue() throws FileNotFoundException {
        BufferedReader buf = new BufferedReader(new InputStreamReader(new FileInputStream(new File("DataFile.txt"))));
        Scanner sc = new Scanner(buf);
        while(sc.hasNextLine()){
            tradeValue = Double.parseDouble(sc.nextLine().replaceAll(".*/", ""));
        }
    }
    static void changeValue(long id, double value){
        tradeValue = value;
        updateConfig(id);
    }
    private static void updateConfig(long id) {
        try(FileWriter writer = new FileWriter(new File("DataFile.txt"), false))
        {
            writer.write((id + "/" + tradeValue) + "\n");
            writer.flush();
        }
        catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    static double getValue() {
        try{
            updateValue();
        }catch (FileNotFoundException e){
            System.out.println("getValue");
        }
        return tradeValue;
    }


//    static void changeBalance(long id) {
//        if(tradeValue.get(id)==null){
//            createBalance(id);
//        }
//        if(tradeValue.get(id)<=0.0){
//            return;
//        }
//        double res = balance(id)- (double) 5;
//        tradeValue.put(id, res);
//        updateConfig();
//    }


}
