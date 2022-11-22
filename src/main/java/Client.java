import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (true) {

            System.out.println("PRESS [1] LIST GOODS");
            System.out.println("PRESS [2] BUY GOOD");
            System.out.println("PRESS [3] LIST BUY HISTORY ");
            System.out.println("PRESS [0] TO EXIT");

            int choice = in.nextInt();

            if (choice == 1) {
                ArrayList<GoodItem> goodItems = getGoodItemList();
                for (int i = 0; i < goodItems.size(); i++) {
                    System.out.println(goodItems.get(i));
                }
            } else if (choice == 2) {

                System.out.println("list goods");

                ArrayList<GoodItem> goodItems = getGoodItemList();
                for (int i = 0; i < goodItems.size(); i++) {
                    System.out.println(goodItems.get(i));
                }

                System.out.println("insert goods");
                String goodName = in.next();

                for (GoodItem gd : goodItems) {
                    if (gd.getName().equals(goodName)){
                        BuyHistory buyHistory = new BuyHistory(gd.getName(),gd.getPrice());
                        ArrayList<BuyHistory> buyHistories = getBuyHistory();
                        buyHistories.add(buyHistory);
                        saveBuyHistiry(buyHistories);
                    } else {
                        System.out.println("error");
                    }
                }
            } else if (choice == 3) {
                ArrayList<BuyHistory> buyHistories = getBuyHistory();
                for (int i = 0; i < buyHistories.size(); i++) {
                    System.out.println(buyHistories.get(i).toString());
                }
            } else if (choice == 0) {
                System.exit(0);
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

    public static ArrayList<BuyHistory> getBuyHistory(){
        ArrayList<BuyHistory> buyHistories = new ArrayList<BuyHistory>();

        try {
            BufferedReader br =
                    new BufferedReader(new FileReader("buyHistory.txt"));
            String line = "";
            while ((line = br.readLine()) != null) {
                int goodPrice = Integer.parseInt(br.readLine());
                buyHistories.add(new BuyHistory(line,goodPrice));
            }
        } catch (Exception e) {

        }
        return buyHistories;
    }

    public static void saveBuyHistiry(ArrayList<BuyHistory> buyHistory){

        try {
            BufferedWriter bw =
                    new BufferedWriter(new FileWriter("buyHistory.txt"));
            for (BuyHistory bh : buyHistory) {
                bw.write(bh.getGoodName() + "\n");
                bw.write(bh.getGoodPrice() + "\n");
                bw.write(bh.getBuyTime() + "\n");
            }
            bw.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
