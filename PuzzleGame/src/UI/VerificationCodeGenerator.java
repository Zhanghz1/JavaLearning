package UI;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class VerificationCodeGenerator {

    private static final int WIDTH = 100; // 验证码图片宽度
    private static final int HEIGHT = 30; // 验证码图片高度
    private static final String CODES = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int CODE_LENGTH = 5; // 验证码长度

    public static BufferedImage generateVerificationCode() {
        // 创建图片对象
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();

        // 设置背景色
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // 生成随机验证码
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < CODE_LENGTH; i++) {
            int index = random.nextInt(CODES.length());
            char code = CODES.charAt(index);
            sb.append(code);
        }
        String verificationCode = sb.toString();

        // 将验证码绘制到图片上
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString(verificationCode, 10, 20);

        // 添加干扰线
        for (int i = 0; i < 5; i++) {
            int x1 = random.nextInt(WIDTH);
            int y1 = random.nextInt(HEIGHT);
            int x2 = random.nextInt(WIDTH);
            int y2 = random.nextInt(HEIGHT);
            g.drawLine(x1, y1, x2, y2);
        }

        // 销毁绘图对象
        g.dispose();

        return image;
    }
}
