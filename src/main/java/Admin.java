import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Admin {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.println("PRESS [1] ADD GOOD");
            System.out.println("PRESS [2] LIST GOODS");
            System.out.println("PRESS [3] DELETE GOOD");
            System.out.println("PRESS [0] TO EXIT");
            int choice = in.nextInt();

            if (choice == 1) {
                System.out.println("Insert name");
                String name = in.next();
                System.out.println("Insert price");
                int price = in.nextInt();
                GoodItem gd = new GoodItem(name,price);
                ArrayList<GoodItem> goodItems = getGoodItemList();
                goodItems.add(gd);
                saveGoodItems(goodItems);

            } else if (choice == 2) {

                ArrayList<GoodItem> goodItems = getGoodItemList();

                for (int i = 0; i < goodItems.size(); i++) {

                    System.out.println(goodItems.get(i).toString());

                }

            } else if (choice == 3) {

                ArrayList<GoodItem> goodItems = getGoodItemList();

                System.out.println("Insert index");

                int index = in.nextInt();

                if (index >=0 && index<goodItems.size()) {

                    goodItems.remove(index);
                    saveGoodItems(goodItems);
                }

            } else if (choice == 0){

                System.exit(0);

            } else {

                System.out.println("ERROR");

            }
        }

    }



   public static ArrayList<GoodItem> getGoodItemList(){
        ArrayList<GoodItem> goodItems = new ArrayList<GoodItem>();

        try{
            BufferedReader br =
                    new BufferedReader(new FileReader("goodItems.txt"));
            String line = "";
            while ((line = br.readLine()) != null) {
                int price = Integer.parseInt(br.readLine());
                goodItems.add(new GoodItem(line,price));
            }

        }catch (Exception e) {

        }
        return goodItems;
    }

    public static void saveGoodItems(ArrayList<GoodItem> goodItems){

        try{
            BufferedWriter bw =
                    new BufferedWriter(new FileWriter("goodItems.txt"));
            for (GoodItem gd : goodItems){
                bw.write(gd.getName() + "\n");
                bw.write(gd.getPrice() + "\n");
            }
            bw.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
