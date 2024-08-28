package SinkADotcom;
import java.util.*;
public class DotcomBust {
    //声明并初始化变量
    private GameHelper helper = new GameHelper();
    private ArrayList<DotCom> dotComList = new ArrayList<DotCom>();
    private int numOfGuesses = 0;
    private void setUpGame(){
        //先设置dotcoms并赋值
        DotCom one = new DotCom();
        one.setName("Pets.com");
        DotCom two = new DotCom();
        two.setName("eToys.com");
        DotCom three = new DotCom();
        three.setName("Go2.com");
        //将dotcoms放入Arraylist
        dotComList.add(one);
        dotComList.add(two);
        dotComList.add(three);
        //用户界面
        System.out.println("击沉所有的dotcoms");
        for(DotCom dotComToSet : dotComList){
            //要求DotCom的位置,调用这个DotComs的setter方法来指派刚取得的位置
            ArrayList<String> newLocation = helper.placeDotCom(3);
            dotComToSet.setLocationCells(newLocation);
        }
    }
    private void startPlaying(){
        while(!dotComList.isEmpty()){
            String userGuess = helper.getUserInput("请输入");
            checkUserGuess(userGuess);
        }
        finishGame();
    }
    private void checkUserGuess(String userGuesses){
        numOfGuesses++;
        String result = "miss";
        for(DotCom dotComToTest : dotComList){
            result = dotComToTest.checkYourself(userGuesses);
            if(result.equals("hit")){
                break;
            }
            if(result.equals("kill")){
                dotComList.remove(dotComToTest);
                break;
            }
        }
        System.out.println(result);
    }
    private void finishGame(){
        System.out.println("恭喜通关");
        System.out.println("你一共猜了" + numOfGuesses + "次");
    }
    public static void main(String[] args) {
        DotcomBust game = new DotcomBust();
        game.setUpGame();
        game.startPlaying();
    }
}
