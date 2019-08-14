import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class AccessController {
    private static ArrayList<Long> userList = new ArrayList<>();
    static int authCode;
    private static void updateList() throws FileNotFoundException {
        BufferedReader buf = new BufferedReader(new InputStreamReader(new FileInputStream(new File("users.txt"))));
        Scanner sc = new Scanner(buf);
        while(sc.hasNextLine()){
            userList.add(Long.parseLong(sc.nextLine()));
        }


    }
    static void addUserInList(long id){
        userList.add(id);
        updateConfig(id);
    }
    private static void updateConfig(long id) {
        try(FileWriter writer = new FileWriter(new File("users.txt"), true))
        {
            writer.write((id) + "\n");
            writer.flush();

        }
        catch(IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
    static User checkUser(long id) throws FileNotFoundException {
        updateList();
        User user;
        if (userList.indexOf(id) == -1){
            user = new Guest();
        }else {
            user = new Owner();
        }
        return user;
    }


//    static void changeBalance(long id) {
//        if(userList.get(id)==null){
//            createBalance(id);
//        }
//        if(userList.get(id)<=0.0){
//            return;
//        }
//        double res = balance(id)- (double) 5;
//        userList.put(id, res);
//        updateConfig();
//    }


}
