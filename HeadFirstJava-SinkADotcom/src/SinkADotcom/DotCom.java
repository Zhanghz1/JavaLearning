package SinkADotcom;
import java.util.*;
public class DotCom
{
    //DotCom的实例变量：保存位置的arraylist和名称
    private ArrayList<String> locationCells = new ArrayList<>();
    private String name;
    //更新DotCom位置的方法
    public void setLocationCells(ArrayList<String> loc){
        locationCells = loc;
    }
    public  void setName(String n){
        name = n;
    }
    //验证玩家猜测是否正确
    public String checkYourself(String userInput){
        String result = "miss";
        //indexOf()函数会返回参数的位置或是-1
        int index = locationCells.indexOf(userInput);
        if(index >= 0){
            locationCells.remove(index);
            if(locationCells.isEmpty()){
                result = "kill";
                System.out.println("你击沉了" + name);
            }
            else {
                result = "hit";
            }
        }
        return result;
    }
}
