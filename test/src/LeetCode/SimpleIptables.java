package LeetCode;

import java.util.*;
import java.net.*;

public class SimpleIptables {
    // 规则链的存储结构，每个链名对应一组规则列表
    static Map<String, List<Rule>> chains = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 读取总的规则和查询数量
        sc.nextLine(); // 读取换行符

        // 初始化默认的c0链
        chains.put("c0", new LinkedList<>());

        // 处理每条规则或查询
        for (int i = 0; i < n; i++) {
            String input = sc.nextLine();
            String[] parts = input.split(" ");

            if (parts[0].equals("M")) {
                // 查询命令 M ip
                String ip = parts[1];
                System.out.println(matchIp(ip, "c0"));
            } else {
                // 规则配置命令
                String op = parts[0];
                String chainName = parts[1];
                String ipOrCidr = parts[2];

                // 插入或追加规则
                if (op.equals("I") || op.equals("A")) {
                    String action = parts[3];
                    if (!chains.containsKey(chainName)) {
                        chains.put(chainName, new LinkedList<>());
                    }
                    Rule rule = new Rule(ipOrCidr, action);

                    // 插入在链头
                    if (op.equals("I")) {
                        chains.get(chainName).add(0, rule);
                    } else {
                        // 追加在链尾
                        chains.get(chainName).add(rule);
                    }
                }
                // 删除规则
                else if (op.equals("D")) {
                    String action = parts[3];
                    Rule ruleToRemove = new Rule(ipOrCidr, action);
                    chains.get(chainName).removeIf(rule -> rule.equals(ruleToRemove));
                }
                // 跳转规则 G
                else if (op.equals("G")) {
                    String targetChain = parts[4];
                    Rule rule = new Rule(ipOrCidr, "G", targetChain);
                    chains.get(chainName).add(rule);
                }
            }
        }
    }

    // 根据IP从指定的链开始匹配规则
    public static String matchIp(String ip, String chainName) {
        String currentChain = chainName;
        while (currentChain != null) {
            List<Rule> chain = chains.get(currentChain);
            if (chain == null) {
                return "U"; // 链不存在时返回Unknown
            }

            for (Rule rule : chain) {
                if (rule.matches(ip)) {
                    if (rule.action.equals("G")) {
                        currentChain = rule.targetChain; // 跳转到其他链继续匹配
                        break;
                    } else {
                        return rule.action; // 匹配到规则，返回对应的动作
                    }
                }
            }

            currentChain = null; // 如果没有匹配到任何规则，退出循环
        }
        return "U"; // 没有匹配到规则时返回Unknown
    }
}

// 定义规则类
class Rule {
    String cidr; // IP或CIDR
    String action; // 动作 (A=Accept, R=Reject, G=Goto)
    String targetChain; // 如果是Goto操作，表示跳转的链名

    public Rule(String cidr, String action) {
        this.cidr = cidr;
        this.action = action;
    }

    public Rule(String cidr, String action, String targetChain) {
        this.cidr = cidr;
        this.action = action;
        this.targetChain = targetChain;
    }

    // 匹配给定IP是否在规则中
    public boolean matches(String ip) {
        return ipInCidr(ip, cidr);
    }

    // 判断IP是否在CIDR范围内
    private boolean ipInCidr(String ip, String cidr) {
        try {
            // 如果 cidr 不包含 '/'，说明它是单个 IP 地址
            if (!cidr.contains("/")) {
                return ip.equals(cidr); // 直接比较 IP 是否相等
            }

            // CIDR 格式: a.b.c.d/n
            String[] parts = cidr.split("/");
            InetAddress ipAddress = InetAddress.getByName(parts[0]);
            int prefixLength = Integer.parseInt(parts[1]);

            byte[] ipBytes = InetAddress.getByName(ip).getAddress();
            byte[] cidrBytes = ipAddress.getAddress();

            int fullBytes = prefixLength / 8;
            int remainingBits = prefixLength % 8;

            // 比较前面完整的字节
            for (int i = 0; i < fullBytes; i++) {
                if (ipBytes[i] != cidrBytes[i]) {
                    return false;
                }
            }

            // 处理剩下的部分位数
            if (remainingBits > 0) {
                int mask = (1 << (8 - remainingBits)) - 1;
                if ((ipBytes[fullBytes] & ~mask) != (cidrBytes[fullBytes] & ~mask)) {
                    return false;
                }
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rule rule = (Rule) o;
        return Objects.equals(cidr, rule.cidr) && Objects.equals(action, rule.action);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cidr, action);
    }
}
