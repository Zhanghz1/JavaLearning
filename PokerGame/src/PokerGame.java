import java.util.*;

public class PokerGame {
    //崭新有序牌盒:cardBox
    static HashMap<Integer, String> cardBox = new HashMap<>();

    //洗牌员：cardIndex
    static ArrayList<Integer> cardIndex = new ArrayList<>();

    //准备牌
    static {
        String[] color = {"♥","♦","♠","♣"};
        String[] number = {"3","4","5","6","7","8","9","10","J","Q","K","A","2"};

        //将花色和数组拼接到一起
        int index = 0;
        for (String n : number) {
            for (String c : color) {
                cardBox.put(index, c+n);
                cardIndex.add(index);
                index++;
            }
        }
        cardBox.put(index, "Little Joker");
        cardIndex.add(index);
        index++;
        cardBox.put(index, "Big Joker");
        cardIndex.add(index);

    }

    public PokerGame(){

        //洗牌
        Collections.shuffle(cardIndex);

        //发牌
        TreeSet<Integer> lordCard = new TreeSet<>();
        TreeSet<Integer> p1Card = new TreeSet<>();
        TreeSet<Integer> p2Card = new TreeSet<>();
        TreeSet<Integer> p3Card = new TreeSet<>();

        for (int i = 0; i < cardIndex.size(); i++) {
            int index = cardIndex.get(i);
            if (i < 3){
                lordCard.add(index);
                continue;
            }
            if (i % 3 == 0){
                p1Card.add(index);
            }else if (i % 3 == 1){
                p2Card.add(index);
            }else {
                p3Card.add(index);
            }
        }
        //整理牌
        System.out.println(lordCard);
        System.out.println(p1Card);
        System.out.println(p2Card);
        System.out.println(p3Card);


    }

    //name:玩家，treeSet：每位玩家的牌
    public void lookPoker(String name, TreeSet<Integer> treeSet){
        for (int i : treeSet) {
            String poker = cardBox.get(i);
             
        }


    }




}
