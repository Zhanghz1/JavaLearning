package UI;

import com.sun.jdi.event.StepEvent;
import com.sun.source.tree.ContinueTree;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Objects;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener, ActionListener {
    //游戏界面相关代码
    //初始化窗口

    //加载数据的索引数组
    int[][] data = new int[4][4];
    //二维数组中0的索引
    int x, y = 0;
    //展示图的路径
    String path = "PuzzleGame\\image\\animal\\animal3\\";
    //正确的数据
    int[][] win = new int[][]{
        {1,2,3,4},
        {5,6,7,8},
        {9,10,11,12},
        {13,14,15,0}};

    //条目对象
    JMenuItem replayItem = new JMenuItem("replay game");
    JMenuItem reLoginItem = new JMenuItem("reLogin game");
    JMenuItem closeItem = new JMenuItem("close game");
    JMenu changePictureJMenu = new JMenu("change picture");

    JMenuItem accountItem = new JMenuItem("account");

    //更换图片子菜单
    JMenuItem girls = new JMenuItem("girls");
    JMenuItem animals = new JMenuItem("animals");
    JMenuItem sports = new JMenuItem("sports");

    //统计步数
    int step = 0;

    public GameJFrame() throws HeadlessException {


        initialJFrame();

        initialJMenuBar();

        initialData();

        initialImage();

        this.setVisible(true);
    }

    //初始化图片索引
    private void initialData() {
        //创建初始数字并随机打乱
        int[] tempArr = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        Random r = new Random();
        for (int i = 0; i < tempArr.length; i++) {
            int index = r.nextInt(tempArr.length);
            int temp = tempArr[i];
            tempArr[i] = tempArr[index];
            tempArr[index] = temp;
        }
        //将数组数据添加到二维数组

        for (int i = 0; i < tempArr.length; i++) {
            if (tempArr[i] == 0){
                x = i/4;
                y = i%4;
            }
            data[i/4][i%4] = tempArr[i];
        }
    }

    private void initialImage() {
        this.getContentPane().removeAll();

        if (victory()){
            JLabel winJLabel = new JLabel(new ImageIcon("PuzzleGame\\image\\win.png"));
            winJLabel.setBounds(203,283,197,73);
            this.getContentPane().add(winJLabel);
        }

        JLabel stepCount = new JLabel("steps:" + step);
        stepCount.setBounds(50,30,100,20);
        this.getContentPane().add(stepCount);

        for (int i = 0; i < 4; i++) {

            for (int j = 0; j < 4; j++) {
                //创建图片对象
                //创建JLabel对象（管理容器）
                int num = data[i][j];
                JLabel jLabel = new JLabel(new ImageIcon(path + num  + ".jpg"));
                this.add(jLabel);
                jLabel.setBounds(105*j+83, 105*i+134, 105, 105);
                //给图片添加边框
                jLabel.setBorder(new BevelBorder(0));
                this.getContentPane().add(jLabel);
            }
        }
        //背景图片
        JLabel background = new JLabel(new ImageIcon("PuzzleGame\\image\\background.png"));
        background.setBounds(40,40,508,560);
        this.getContentPane().add(background);

        this.getContentPane().repaint();
    }

    private void initialJMenuBar() {
        //初始化菜单
        //菜单对象
        JMenuBar jMenuBar = new JMenuBar();
        //选项对象
        JMenu functionJMenu = new JMenu("Function Menu");
        JMenu aboutJMenu = new JMenu("About Us");

        //各项添加到子菜单中
        changePictureJMenu.add(girls);
        changePictureJMenu.add(animals);
        changePictureJMenu.add(sports);
        //添加监听
        girls.addActionListener(this);
        animals.addActionListener(this);
        sports.addActionListener(this);


        //放置各对象
        functionJMenu.add(changePictureJMenu);
        functionJMenu.add(replayItem);
        functionJMenu.add(reLoginItem);
        functionJMenu.add(closeItem);
        aboutJMenu.add(accountItem);

        replayItem.addActionListener(this);
        reLoginItem.addActionListener(this);
        closeItem.addActionListener(this);
        changePictureJMenu.addActionListener(this);
        accountItem.addActionListener(this);


        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutJMenu);

        this.setJMenuBar(jMenuBar);
    }

    private void initialJFrame() {
        this.setSize(603, 680);
        this.setTitle("PuzzleGame");
        //this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //默认居中防止，设置值为null取消默认
        //添加键盘监听事件
        this.addKeyListener(this);


        this.setLayout(null);
    }

    public boolean victory(){
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length; j++) {
                if(data[i][j] != win[i][j]){
                    return  false;
                }
            }
        }
        return true;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //按a显示原图
        int code = e.getKeyCode();
        if (code == 65){
            this.getContentPane().removeAll();
            JLabel all = new JLabel(new ImageIcon(path + "all.jpg"));
            all.setBounds(83, 134, 420, 420);
            this.getContentPane().add(all);
            //背景图片
            JLabel background = new JLabel(new ImageIcon("PuzzleGame\\image\\background.png"));
            background.setBounds(40,40,508,560);
            this.getContentPane().add(background);
            this.getContentPane().repaint();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (victory()) return;
        //对上下左右进行监听
        int code = e.getKeyCode();
        if (code == 37){
            if (y == 3) return;
            data[x][y] = data[x][y+1];
            data[x][y+1] = 0;
            y++;
            step++;
            initialImage();
        }else if(code == 38){
            if (x == 3) return;
            data[x][y] = data[x+1][y];
            data[x+1][y] = 0;
            x++;
            step++;
            initialImage();
        }else if(code == 39){
            if (y == 0) return;
            data[x][y] = data[x][y-1];
            data[x][y-1] = 0;
            y--;
            step++;
            initialImage();
        }else if(code == 40){
            if (x == 0) return;
            data[x][y] = data[x-1][y];
            data[x-1][y] = 0;
            x--;
            step++;
            initialImage();
        }else if (code == 65) {
            initialImage();
        }else if(code == 87){
            data = new int[][]{
                    {1,2,3,4},
                    {5,6,7,8},
                    {9,10,11,12},
                    {13,14,15,0}
            };
            initialImage();
        }



    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object object = e.getSource();
        if (object == replayItem){
            step = 0;
            initialData();
            initialImage();
        }else if(object == reLoginItem){
            this.setVisible(false);
            new LoginJFrame();
        }else if(object == closeItem){
            System.exit(0);
        }else if(object == accountItem){
            JDialog jDialog = new JDialog();
            JLabel jLabel = new JLabel(new ImageIcon("PuzzleGame\\image\\about.png"));
            jLabel.setBounds(0,0,258,258);
            jDialog.getContentPane().add(jLabel);
            jDialog.setSize(344,344);
            jDialog.setLocationRelativeTo(null);
            jDialog.setModal(true);
            jDialog.setVisible(true);
        }else if(object == girls){
            Random r = new Random();
            int randomIndex = r.nextInt(13) + 1;
            path = "PuzzleGame\\image\\girl\\girl" + randomIndex + "\\";
            step = 0;
            initialData();
            initialImage();

        }else if(object == animals){
            Random r = new Random();
            int randomIndex = r.nextInt(8) + 1;
            path = "PuzzleGame\\image\\animal\\animal"+ randomIndex + "\\";
            step = 0;
            initialData();
            initialImage();

        }else if(object == sports){
            Random r = new Random();
            int randomIndex = r.nextInt(10) + 1;
            path = "PuzzleGame\\image\\sport\\sport"+ randomIndex + "\\";
            step = 0;
            initialData();
            initialImage();
        }
    }
}
