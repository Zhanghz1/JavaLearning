package LeetCode;

import java.util.*;

public class CardFlippingGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 读取输入
        int n = scanner.nextInt(); // n表示牌的数量
        int[] A = new int[n]; // 存储正面的数字
        int[] B = new int[n]; // 存储反面的数字
        for (int i = 0; i < n; i++) {
            A[i] = scanner.nextInt(); // 第i张牌的正面数字
            B[i] = scanner.nextInt(); // 第i张牌的反面数字
        }

        // 结果数组，保存翻k张牌时的最大总分
        int[] result = new int[n + 1];

        // 初始总分为所有正面数字之和，不翻牌的情况
        int totalScore = 0;
        for (int i = 0; i < n; i++) {
            totalScore += A[i]; // 全部使用正面的数字
        }
        result[0] = totalScore; // 不翻牌的最大总分

        // 用优先队列（最大堆）来选择最佳的翻牌方案
        PriorityQueue<Integer> bestFlipChoices = new PriorityQueue<>(Collections.reverseOrder());

        // 遍历每张牌，计算翻牌时的代价，并通过堆管理翻牌后的最大收益
        for (int i = 0; i < n; i++) {
            // 翻第i张牌的收益是B[i] - A[i]（翻了正面改为反面），代价是 A[i] ⊕ (i + 1)
            int flipGain = B[i] - A[i];
            int flipCost = A[i] ^ (i + 1); // 按位异或计算翻牌代价

            // 计算净收益（收益减去代价）
            int netGain = flipGain - flipCost;
            // 将净收益放入最大堆中
            bestFlipChoices.offer(netGain);
        }

        // k表示翻牌的张数
        for (int k = 1; k <= n; k++) {
            // 从最大堆中取出一个最大净收益来进行翻牌操作
            if (!bestFlipChoices.isEmpty()) {
                totalScore += bestFlipChoices.poll(); // 累加净收益到总分中
            }
            result[k] = totalScore; // 保存翻k张牌时的最大总分
        }

        // 输出结果
        for (int k = 0; k <= n; k++) {
            System.out.println(result[k]);
        }

        scanner.close();
    }
}
