package LeetCode;

import java.util.*;


public class Programmercarl {
    //27 移除元素， 类似：283 移动零
    public int removeElement(int[] nums, int val) {
        //双指针，一个按序指向下一个元素，一个指向非val元素
        int n = nums.length;
        int index = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == val) continue;
            nums[index++] = nums[i];
        }
        return index;
    }

    //209. 长度最小的子数组
    //给定一个含有 n 个正整数的数组和一个正整数 target 。找出该数组中满足其总和大于等于 target 的长度最小的子数组
    // [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
    public int minSubArrayLen(int target, int[] nums) {
        //双指针，先移动右指针直到和大于等于target，（更新minLength），再移动左指针，直到和小于target
        int left = 0;
        int minLength = Integer.MAX_VALUE;
        int sum = 0;
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            while (sum >= target){
                minLength = Math.min(minLength, right-left+1);
                sum -= nums[left++];
            }
        }
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    //59 螺旋矩阵2
    //给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
    public int[][] generateMatrix(int n) {
        //设置填充边界top bottom left right， 依次填充
        int target = n*n;
        int num = 1;
        int top = 0, bottom = n-1, left = 0, right = n-1;
        int[][] result = new int[n][n];
        while (num <= target){
            //先从左到右
            for (int i = left; i <= right; i++) {
                result[top][i] = num++;
            }
            top++;
            for (int i = top; i <= bottom; i++) {
                result[i][right] = num++;
            }
            right--;
            for (int i = right; i >= left; i--) {
                result[bottom][i] = num++;
            }
            bottom--;
            for (int i = bottom; i >= top; i--) {
                result[i][left] = num++;
            }
            left++;
        }
        return result;
    }

    //206. 反转链表
    //给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
     }
    public ListNode reverseList(ListNode head) {
        //迭代法，迭代方法是将右指针移动到下一个节点并记录.next，然后将该节点指向上个节点pre
        /*ListNode pre = null;
        ListNode cur = head;
        ListNode temp = null;
        while (cur != null){
            temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;*/
        //递归法
        //终止条件
        if (head.next == null){
            return head;
        }else if (head == null){
            return null;
        }
        //调用递归
        ListNode last = reverseList(head.next);
        //将下个节点指向自己，并把自己的next设置为null
        head.next.next = head;
        head.next = null;
        return last;
    }

    //24. 两两交换链表中的节点
    //给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
    public ListNode swapPairs(ListNode head) {
        //递归：终止条件、递归、必要操作
        if (head == null || head.next == null )
            return head;
        ListNode temp = head.next;
        ListNode newHead = swapPairs(head.next.next);
        temp.next = head;
        head.next = newHead;
        return temp;
    }

    //19. 删除链表的倒数第 N 个结点
    //给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
    public ListNode removeNthFromEnd(ListNode head, int n) {
        //首先遍历链表获取长度，再将第n个节点删除
        //题解：设置dummyHead指向头部，设置两个指针，fast先移动n+1次，fast和头部的slow同时移动，直至fast到末尾
        ListNode p = head;
        int num = 0, index = 0;
        while (p != null){
            p = p.next;
            num++;
        }
        p = head;
        index = num - n;
        if (index == 0) return head.next;
        while (index > 1){
            p = p.next;
            index--;
        }
        p.next = p.next.next;
        return head;
    }

    //142. 环形链表 II
    //给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
    public ListNode detectCycle(ListNode head) {
        //设置快慢指针fast slow，快指针的速度是2，二者同时出发，（非环长度a，环长=b+c）在环中的b处相遇，得：a = (n-1)(b+c)+c，即从head出发的指针ptr会和slow在入环点相遇。
        ListNode slow = head, fast = head;
        while (fast != null){
            slow = slow.next;
            if (fast.next != null){
                fast = fast.next.next;
            }else return null;
            if (fast == slow) {
                ListNode ptr = head;
                while (ptr != slow){
                    ptr = ptr.next;
                    slow = slow.next;
                }
                return ptr;
            }
        }
        return null;
    }

    //242. 有效的字母异位词
    //给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
    public boolean isAnagram(String s, String t) {
        //创建map
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        for (int i = 0; i < t.length(); i++) {
            char temp = t.charAt(i);
            if (map.containsKey(temp)){
                map.put(temp, map.get(temp)-1);
            }else {
                return false;
            }
        }
        for (int i = 0; i < s.length(); i++) {
            if (map.get(s.charAt(i)) != 0) return false;
        }
        return true;
    }

    //349. 两个数组的交集
    public int[] intersection(int[] nums1, int[] nums2) {
        Map<Integer,Boolean> map = new HashMap();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums1.length; i++) {
            map.put(nums1[i],false);
        }
        for (int i = 0; i < nums2.length; i++) {
            if (map.containsKey(nums2[i]) && !map.get(nums2[i])){
                list.add(nums2[i]);
                map.put(nums2[i], true);
            }
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    //202. 快乐数
    //编写一个算法来判断一个数 n 是不是快乐数。
    //「快乐数」 定义为：
    //对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
    //然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
    //如果这个过程 结果为 1，那么这个数就是快乐数。
    //如果 n 是 快乐数 就返回 true ；不是，则返回 false 。
    public boolean isHappy(int n) {
        Set<Integer> seen = new HashSet<>();
        while (n != 1 && !seen.contains(n)){
            seen.add(n);
            n = getNext(n);
        }
        return n == 1;
    }
    private int getNext(int n) {
        int totalSum = 0;
        while (n > 0) {
            int d = n % 10;
            n = n / 10;
            totalSum += d * d;
        }
        return totalSum;
    }

    //454. 四数相加 II
    //给你四个整数数组 nums1、nums2、nums3 和 nums4 ，数组长度都是 n ，请你计算有多少个元组 (i, j, k, l) 能满足：
    //0 <= i, j, k, l < n
    //nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        //分为两组，分别计算每两个数相加的值，value为出现次数
        Map<Integer, Integer> map = new HashMap<>();
        int n = nums1.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map.put(nums1[i] + nums2[j], map.getOrDefault(nums1[i] + nums2[j], 0) + 1);
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map.containsKey(- nums3[i] - nums4[j])){
                    ans += map.get(- nums3[i] - nums4[j]);
                }
            }

        }
        return ans;
    }

    //18. 四数之和
    //给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。请你找出并返回满足下述全部条件且不重复的四元组 [nums[a], nums[b], nums[c], nums[d]] （若两个四元组元素一一对应，则认为两个四元组重复）：
    //0 <= a, b, c, d < n
    //a、b、c 和 d 互不相同
    //nums[a] + nums[b] + nums[c] + nums[d] == target
    //你可以按 任意顺序 返回答案 。
    public List<List<Integer>> fourSum(int[] nums, int target) {
        //与三数之和相同，排序后，前两个指针固定，设置双指针，从头和尾分别开始遍历。前两者之和的负数为target，left+right如果小于target，移动right，反之移动left。直到二者相遇，进入下一次循环。
        List<List<Integer>> list = new ArrayList<>();
        int temp = 0;
        int n = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < n; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue; // 去重
            for (int j = i+1; j < n; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue; // 去重
                int targetTemp = target - nums[i] - nums[j];
                int left = j+1, right = n-1;
                while (left < right){
                    if (nums[left] + nums[right] == targetTemp) {
                        List<Integer> listTemp = new ArrayList<>();
                        listTemp.add(nums[i]);
                        listTemp.add(nums[j]);
                        listTemp.add(nums[left]);
                        listTemp.add(nums[right]);
                        list.add(listTemp);
                        while (left < right && nums[left] == nums[left+1]) left++;
                        while (left < right && nums[right] == nums[right-1]) right--;
                        left++;
                        right--;
                    }else if (nums[left] + nums[right] > targetTemp){
                        right--;
                    }else {
                        left++;
                    }
                }

            }
        }
        return list;
    }

    //344. 反转字符串
    public void reverseString(char[] s) {
        int n = s.length;
        if (n < 2) return;
        for (int i = 0; i < n / 2; i++) {
            char temp = s[n-i-1];
            s[n-i-1] = s[i];
            s[i] = temp;
        }
    }

    //151. 反转字符串中的单词
    //给你一个字符串 s ，请你反转字符串中 单词 的顺序。
    //单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。
    //返回 单词 顺序颠倒且 单词 之间用单个空格连接的结果字符串。
    public String reverseWords(String s) {
        //遍历字符串，将非空格的单词放入list，最后将单词顺序反转放入StringBuilder
        s = s.trim();
        List<String> wordList = Arrays.asList(s.split("\\s+"));
        Collections.reverse(wordList);
        return String.join("", wordList);
    }

    //28. 找出字符串中第一个匹配项的下标
    //给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串的第一个匹配项的下标（下标从 0 开始）。如果 needle 不是 haystack 的一部分，则返回  -1 。
    /*public int strStr(String haystack, String needle) {
        int index = -1;
        if (haystack.length() < needle.length()) return -1;
        for (int i = 0; i <= haystack.length()-needle.length(); i++) {
            index = i;
            for (int j = 0; j < needle.length(); j++) {
                if (haystack.charAt(i+j) != needle.charAt(j)) {
                    index = -1;
                    break;
                }
            }
            if (index != -1) break;
        }
        return index;
    }*/

    //KMS
    public int strStr(String haystack, String needle) {
        if (needle.length() == 0) return 0;
        int[] next = new int[needle.length()];
        getNext(next, needle);

        int j = 0;
        for (int i = 0; i < haystack.length(); i++) {
            while (j > 0 && needle.charAt(j) != haystack.charAt(i))
                j = next[j - 1];
            if (needle.charAt(j) == haystack.charAt(i))
                j++;
            if (j == needle.length())
                return i - needle.length() + 1;
        }
        return -1;

    }

    private void getNext(int[] next, String s) {
        int j = 0;
        next[0] = 0;
        for (int i = 1; i < s.length(); i++) {
            while (j > 0 && s.charAt(j) != s.charAt(i))
                j = next[j - 1];
            if (s.charAt(j) == s.charAt(i))
                j++;
            next[i] = j;
        }
    }
    private int[] getNext2(String s) {
        int[] next = new int[s.length()];
        next[0] = 0;
        int j = 0;
        for (int i = 1; i < s.length(); i++) {
            while (j > 0 && s.charAt(i) != s.charAt(j)){
                j = next[j-1];
            }
            if (s.charAt(j) == s.charAt(i))
                j++;
            next[i] = j;
            //匹配字符串：p是要匹配的字符串，返回p在s第一次出现的位置。
            /*if (j == p.length())
                return i - p.length() + 1;*/
        }
        return next;
    }

    //459. 重复的子字符串
    //给定一个非空的字符串 s ，检查是否可以通过由它的一个子串重复多次构成。
    public boolean repeatedSubstringPattern(String s) {
        int[] next = new int[s.length()];
        int j = 0;
        next[0] = 0;
        for (int i = 1; i < s.length(); i++) {
            while (j > 0 && s.charAt(i) != s.charAt(j)){
                j = next[j-1];
            }
            if (s.charAt(i) == s.charAt(j)){
                j++;
            }
            next[i] = j;
        }
        int lpsLength = next[s.length()-1];
        return lpsLength > 0 && s.length() % (s.length() - lpsLength) == 0;
    }

    //232. 用栈实现队列
    //请你仅使用两个栈实现先入先出队列。队列应当支持一般队列支持的所有操作（push、pop、peek、empty）：
    //实现 MyQueue 类：
    //void push(int x) 将元素 x 推到队列的末尾
    //int pop() 从队列的开头移除并返回元素
    //int peek() 返回队列开头的元素
    //boolean empty() 如果队列为空，返回 true ；否则，返回 false
    //说明：
    //你 只能 使用标准的栈操作 —— 也就是只有 push to top, peek/pop from top, size, 和 is empty 操作是合法的。
    //你所使用的语言也许不支持栈。你可以使用 list 或者 deque（双端队列）来模拟一个栈，只要是标准的栈操作即可。
    class MyQueue {
        //将一个栈当作输入栈，用于压入 push 传入的数据；另一个栈当作输出栈，用于 pop 和 peek 操作。
        Deque<Integer> inStack;
        Deque<Integer> outStack;
        public MyQueue() {
            inStack = new ArrayDeque<>();
            outStack = new ArrayDeque<>();
        }

        public void push(int x) {
            inStack.push(x);
        }

        public int pop() {
            if (outStack.isEmpty()){
                in2out();
            }
            return outStack.pop();
        }

        public int peek() {
            if (outStack.isEmpty()){
                in2out();
            }
            return outStack.peek();
        }

        public boolean empty() {
            return inStack.isEmpty() && outStack.isEmpty();
        }

        public void in2out(){
            while (!inStack.isEmpty()){
                outStack.push(inStack.pop());
            }
        }
    }

    //225. 用队列实现栈
    //请你仅使用两个队列实现一个后入先出（LIFO）的栈，并支持普通栈的全部四种操作（push、top、pop 和 empty）。
    //实现 MyStack 类：
    //void push(int x) 将元素 x 压入栈顶。
    //int pop() 移除并返回栈顶元素。
    //int top() 返回栈顶元素。
    //boolean empty() 如果栈是空的，返回 true ；否则，返回 false 。
    class MyStack {
        //用两个队列模拟栈，压入时将要压入的元素放到q2，再将q1已存在的元素逐个压入，最后q1 q2互换
        Queue<Integer> q1;
        Queue<Integer> q2;

        public MyStack() {
            q1 = new LinkedList<>();
            q2 = new LinkedList<>();
        }

        public void push(int x) {
            q2.offer(x);
            while (!q1.isEmpty()){
                q2.offer(q1.poll());
            }
            Queue<Integer> temp = q1;
            q1 = q2;
            q2 = temp;
        }

        public int pop() {
            return q1.poll();
        }

        public int top() {
            return q1.peek();
        }

        public boolean empty() {
            return q1.isEmpty();
        }
    }

    //20. 有效的括号
    //给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
    //有效字符串需满足：
    //左括号必须用相同类型的右括号闭合。
    //左括号必须以正确的顺序闭合。
    //每个右括号都有一个对应的相同类型的左括号。
    public boolean isValid(String s) {
        //先将左括号压入栈，直到出现第一个右括号；将右括号对应的左括号出栈，如果出栈失败则返回false，如果全部出栈则返回true；
        //题解：将三组括号放入map集合中
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            if (c == '(' || c == '{' || c == '['){
                stack.add(c);
            }else if (stack.isEmpty() ){
                return false;
            }else if (c == ')'){
                Character peek = stack.peek();
                if ( peek != '(') return false;
                else stack.pop();
            }else if (c == '}'){
                Character peek = stack.peek();
                if (peek != '{') return false;
                else stack.pop();
            }else if (c == ']'){
                Character peek = stack.peek();
                if (peek != '[') return false;
                else stack.pop();
            }
        }
        if (stack.isEmpty()) return true;
        return false;

    }

    //1047. 删除字符串中的所有相邻重复项
    //给出由小写字母组成的字符串 s，重复项删除操作会选择两个相邻且相同的字母，并删除它们。
    //在 s 上反复执行重复项删除操作，直到无法继续删除。
    //在完成所有重复项删除操作后返回最终的字符串。答案保证唯一。
    public String removeDuplicates(String s) {
        //与上一题相同，将字符入栈，如果栈顶元素与入栈元素相同，则出栈
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!stack.isEmpty() && c == stack.peek()){
                stack.pop();
                continue;
            }
            stack.push(c);
        }
        //正则表达式为将字符串只留下字母
        return stack.toString().replaceAll("[^a-zA-Z]", "");
    }

    //150. 逆波兰表达式求值
    //给你一个字符串数组 tokens ，表示一个根据 逆波兰表示法 表示的算术表达式。
    //请你计算该表达式。返回一个表示表达式值的整数。
    //注意：
    //有效的算符为 '+'、'-'、'*' 和 '/' 。
    //每个操作数（运算对象）都可以是一个整数或者另一个表达式。
    //两个整数之间的除法总是 向零截断 。
    //表达式中不含除零运算。
    //输入是一个根据逆波兰表示法表示的算术表达式。
    //答案及所有中间计算结果可以用 32 位 整数表示。
    public int evalRPN(String[] tokens) {
        //将元素依次入栈，如果遇到运算符，则将前面的两个数与运算符计算，得到的值再压入栈。。。
        Stack<String> stack = new Stack<>();
        String s;
        for (int i = 0; i < tokens.length; i++) {
            s = tokens[i];
            if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")){
                String temp2 = stack.pop();
                String temp1 = stack.pop();
                switch (s){
                    case "+":
                        stack.push(Integer.toString( Integer.parseInt(temp1) + Integer.parseInt(temp2)));
                        break;
                    case "-":
                        stack.push(Integer.toString( Integer.parseInt(temp1) - Integer.parseInt(temp2)));
                        break;
                    case "*":
                        stack.push(Integer.toString( Integer.parseInt(temp1) * Integer.parseInt(temp2)));
                        break;
                    case "/":
                        stack.push(Integer.toString( Integer.parseInt(temp1) / Integer.parseInt(temp2)));
                        break;
                }
            }else {
                stack.push(s);
            }
        }
        return Integer.parseInt(stack.peek());
    }

    //239. 滑动窗口最大值
    //给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
    //返回 滑动窗口中的最大值 。
    /*public int[] maxSlidingWindow(int[] nums, int k) {
        //超时
        //设置map集合，key为值，value为出现次数；首先将前k个数字加入map，再移动滑动窗口，每次移动时，减去左侧第一个元素，判断是否为max
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < k; i++) {
            map.put(nums[i], map.getOrDefault(nums[i],0) + 1);
            max = Math.max(max, nums[i]);
        }
        list.add(max);
        //移动滑动窗口，每次移动时，减去左侧第一个元素，判断是否为max
        for (int i = k; i < nums.length; i++) {
            int leftElement = nums[i-k];
            map.put(leftElement, map.get(leftElement) - 1);
            // 如果该元素计数为0，则从map中移除
            if (map.get(leftElement) == 0) {
                map.remove(leftElement);
            }
            // 加入新元素
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            // 如果移除的元素是当前的最大值，需要重新计算最大值
            if (leftElement == max) {
                max = Integer.MIN_VALUE;
                for (Integer key : map.keySet()) {
                    max = Math.max(max, key);
                }
            } else {
                // 如果不是最大值，直接比较新加入的元素和当前最大值
                max = Math.max(max, nums[i]);
            }
            list.add(max);
        }
        int[] array = new int[nums.length - k + 1];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }*/
    public int[] maxSlidingWindow(int[] nums, int k){
        //单调队列：
        Deque<Integer> deque = new ArrayDeque<>();
        int[] result = new int[nums.length - k + 1];
        for (int i = 0; i < k; i++) {
            while (!deque.isEmpty() && nums[i] > deque.peekFirst()){
                deque.pollFirst();
            }
            deque.offerFirst(nums[i]);
        }
        result[0] = deque.peekLast();
        //移动滑动窗口，如果被移除的元素等于last，则出队列；如果新加入的元素大于等于first的元素，则first的出队列直到新元素是最小值
        for (int i = k; i < nums.length; i++) {
            if (!deque.isEmpty() && nums[i-k] == deque.peekLast()){
                deque.pollLast();
            }
            while (!deque.isEmpty() && nums[i] > deque.peekFirst()){
                deque.pollFirst();
            }
            deque.offerFirst(nums[i]);
            result[i-k+1] = deque.peekLast();
        }
        return result;
    }

    //347. 前 K 个高频元素
    //给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
    public int[] topKFrequent(int[] nums, int k) {
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap =
                new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));

        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        }));
        int[] n = new int[k];
        for (int i = 0; i < k; i++) {
            n[i] = list.get(i).getKey();
        }
        return n;
    }
    //堆
    /*public int[] topKFrequent(int[] nums, int k) {
        // 1. 计算每个数字的频率
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // 2. 使用优先队列（小顶堆）来存储频率最高的 K 个元素
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap =
            new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));

        // 3. 遍历频率映射，将元素添加到小顶堆中
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            minHeap.offer(entry);
            if (minHeap.size() > k) {
                minHeap.poll(); // 移除频率最低的元素
            }
        }

        // 4. 从小顶堆中提取前 K 个高频元素
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = minHeap.poll().getKey();
        }

        return result;
    }*/

    //二叉树节点
    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val){ this.val = val;}
        TreeNode(int val, TreeNode left, TreeNode right){
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    //二叉树的前序遍历
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        //1. 首先确定参数和返回值
        preorder(root, list);
        return list;

    }
    //2. 终止条件
    //3. 确定单层递归的逻辑
    public void preorder(TreeNode root, List<Integer> list){
        if (root == null){
            return;
        }
        list.add(root.val);
        preorder(root.left, list);
        preorder(root.right, list);
    }
    //后序遍历
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        postorder(root, list);
        return list;
    }
    public void postorder(TreeNode root, List<Integer> list){
        if (root != null) {
            postorder(root.left, list);
            postorder(root.right, list);
            list.add(root.val);
        }
        return;
    }

    //二叉树的层序遍历BFS

    //102. 二叉树的层序遍历--迭代
    public List<List<Integer>> levelOrder(TreeNode root) {
        //设置一个队列存储每层的元素，同时记录每层的元素数量，以便遍历下一层；遍历下一层时，将元素出队列，然后把其左右子节点送入队列直到本层元素被遍历完成，记录长度开启下一轮
        List<List<Integer>> list = new ArrayList<>();
        if (root == null){
            return list;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int currentSize = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < currentSize; i++) {
                TreeNode temp = queue.poll();
                level.add(temp.val);
                if (temp.left != null){
                    queue.offer(temp.left);
                }
                if (temp.right != null){
                    queue.offer(temp.right);
                }
            }
            list.add(level);
        }
        return list;
    }

    //107. 二叉树的层序遍历 II 自低而上
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        //跟从上到下相同，只是将每次遍历的一层加入到结果集合的第一个就可以了
        List<List<Integer>> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null){
            return list;
        }
        queue.offer(root);
        while (!queue.isEmpty()) {
            int currentSize = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < currentSize; i++) {
                TreeNode temp = queue.poll();
                level.add(temp.val);
                if (temp.left != null) queue.add(temp.left);
                if (temp.right != null) queue.add(temp.right);
            }
            list.add(0, level);
        }
        return list;
    }

    //199. 二叉树的右视图
    //给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
    public List<Integer> rightSideView(TreeNode root) {
        //与层序遍历相似，创建一个队列从根节点开始迭代，首先将根节点入队，并记录队列长度，将队列中的节点依次poll并将右、左子节点入队，再将每层的第一个值放入list。
        List<Integer> result = new ArrayList<>();
        if (root == null){
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int currentSize = queue.size();
            for (int i = 0; i < currentSize; i++) {
                TreeNode temp = queue.poll();
                if (i == 0){
                    result.add(temp.val);
                }
                if (temp.right != null) queue.add(temp.right);
                if (temp.left != null) queue.add(temp.left);
            }
        }
        return result;
    }
    //637. 二叉树的层平均值
    //给定一个非空二叉树的根节点 root , 以数组的形式返回每一层节点的平均值。与实际答案相差 10-5 以内的答案可以被接受。
    public List<Double> averageOfLevels(TreeNode root) {
        //同上
        List<Double> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null) return list;
        queue.offer(root);
        double sum = root.val;
        while (!queue.isEmpty()) {
            int currentSize = queue.size();
            list.add(sum/currentSize);
            sum = 0;
            for (int i = 0; i < currentSize; i++) {
                TreeNode temp = queue.poll();
                if (temp.left != null) {
                    queue.add(temp.left);
                    sum += temp.left.val;
                }
                if (temp.right != null) {
                    queue.add(temp.right);
                    sum += temp.right.val;
                }
            }
        }
        return list;
    }

    //n叉树：
    /*public class Node{
        public int val;
        public List<Node> children;
        public Node(){}
        public Node(int val){
            this.val = val;
        }
        public Node(int val, List<Node> children){
            this.val = val;
            this.children  = children;
        }
    }*/

    //429. N 叉树的层序遍历
    //给定一个 N 叉树，返回其节点值的层序遍历。（即从左到右，逐层遍历）。
    //树的序列化输入是用层序遍历，每组子节点都由 null 值分隔（参见示例）。
    /*public List<List<Integer>> levelOrder(Node root) {
        //与二叉树类似，只是将每个for循环中的左右子节点换为遍历list集合
        List<List<Integer>> list = new ArrayList<>();
        Queue<Node> queue =new LinkedList<>();
        if (root == null) {
            return list;
        }
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int currentSize = queue.size();
            for (int i = 0; i < currentSize; i++) {
                Node temp = queue.poll();
                level.add(temp.val);
                int childrenNum = temp.children.size();
                for (int j = 0; j < childrenNum; j++) {
                    queue.offer(temp.children.get(j));
                }
            }
            list.add(level);
        }
        return list;
    }*/

    //515. 在每个树行中找最大值
    //给定一棵二叉树的根节点 root ，请找出该二叉树中每一层的最大值。
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null){
            return new ArrayList<>();
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int currentSize = queue.size();
            int maxVal = Integer.MIN_VALUE;
            for (int i = 0; i < currentSize; i++) {
                TreeNode temp = queue.poll();
                maxVal = Math.max(maxVal, temp.val);
                if (temp.left != null){
                    queue.offer(temp.left);
                }
                if (temp.right != null){
                    queue.offer(temp.right);
                }
            }
            list.add(maxVal);
        }
        return list;
    }


    //完美二叉树：
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;
        public Node() {}
        public Node(int _val) {
            val = _val;
        }
        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
    //116. 填充每个节点的下一个右侧节点指针
    //给定一个 完美二叉树 ，其所有叶子节点都在同一层，每个父节点都有两个子节点。
    // 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
    //初始状态下，所有 next 指针都被设置为 NULL。
    public Node connect(Node root) {
        //层序遍历，在每层取出节点时将其next指向下一个，直到循环到最后一个本层元素
        Queue<Node> queue = new LinkedList<>();
        if (root == null) return root;
        queue.offer(root);
        while (!queue.isEmpty()) {
            int currentSize = queue.size();
            Node tempFore = null;
            for (int i = 0; i < currentSize; i++) {
                Node temp = queue.poll();
                if (tempFore != null){
                    tempFore.next = temp;
                }
                tempFore = temp;
                if (temp.left != null) queue.offer(temp.left);
                if (temp.right != null) queue.offer(temp.right);
            }
            tempFore.next = null;
        }
        return root;
    }

    //117. 填充每个节点的下一个右侧节点指针 II
    //填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL 。
    //初始状态下，所有 next 指针都被设置为 NULL 。

    //104. 二叉树的最大深度
    //给定一个二叉树 root ，返回其最大深度。
    //二叉树的 最大深度 是指从根节点到最远叶子节点的最长路径上的节点数。
    public int maxDepth(TreeNode root) {
        //层序遍历
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null) return 0;
        queue.offer(root);
        int depth = 0;
        while (!queue.isEmpty()) {
            int currentSize = queue.size();
            for (int i = 0; i < currentSize; i++) {
                TreeNode temp = queue.poll();
                if (temp.left != null) queue.offer(temp.left);
                if (temp.right != null) queue.offer(temp.right);
            }
            depth++;
        }
        return depth;
    }
    //111. 二叉树的最小深度
    //最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
    //说明：叶子节点是指没有子节点的节点。
    public int minDepth(TreeNode root) {
        //层序遍历
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null) return 0;
        queue.offer(root);
        int depth = 0;
        while (!queue.isEmpty()) {
            depth++;
            int currentSize = queue.size();
            for (int i = 0; i < currentSize; i++) {
                TreeNode temp = queue.poll();
                if (temp.left == null && temp.right == null){
                    return depth;
                }
                if (temp.left != null) queue.offer(temp.left);
                if (temp.right != null) queue.offer(temp.right);
            }
        }
        return depth;
    }

    //226. 翻转二叉树
    //给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。
    public TreeNode invertTree(TreeNode root) {
        //层序遍历，每个元素互换其左右子节点
        if (root == null) return root;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            TreeNode temp = queue.poll();
            if (temp.left != null || temp.right != null){
                if (temp.left != null) queue.offer(temp.left);
                if (temp.right != null) queue.offer(temp.right);
                TreeNode tn = temp.left;
                temp.left = temp.right;
                temp.right = tn;
            }
        }
        return root;
    }
    /*//递归
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return root;
        TreeNode left = invertTree1(root.left);
        TreeNode right = invertTree1(root.right);
        root.left = right;
        root.right = left;
        return root;
    }*/

    //101. 对称二叉树
    //给你一个二叉树的根节点 root ， 检查它是否轴对称。
    public boolean isSymmetric(TreeNode root) {
        //层序遍历，将每层的结果放入双向队列，
        //迭代法：使用两个指针qp，每次移动时走向相反方向，此时每次迭代比较的值都是对称位置的值
        return checkMirrorTree(root, root);


    }
    public boolean checkMirrorTree(TreeNode p, TreeNode q) {
        if (q == null && p == null) return true;
        if (q == null || p == null) return false;
        return p.val == q.val && checkMirrorTree(p.left, q.right) && checkMirrorTree(p.right, q.left);
    }

    //222. 完全二叉树的节点个数
    //给你一棵 完全二叉树 的根节点 root ，求出该树的节点个数。
    //完全二叉树 的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。
    //若最底层为第 h 层，则该层包含 1~ 2h 个节点。
    public int countNodes(TreeNode root) {
        //层序遍历
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int count = 0;
        while (!queue.isEmpty()) {
            int currentSize = queue.size();
            for (int i = 0; i < currentSize; i++) {
                TreeNode temp = queue.poll();
                count++;
                if (temp.left != null) queue.offer(temp.left);
                if (temp.right != null) queue.offer(temp.right);
            }
        }
        return count;
    }
    //迭代：
    /*public int countNodes(TreeNode root) {
        if(root == null){
            return 0;
        }
        return 1 + countNodes(root.left) + countNodes(root.right);
    }*/


    //110. 平衡二叉树
    //给定一个二叉树，判断它是否是 平衡二叉树
    //平衡二叉树 是指该树所有节点的左右子树的深度相差不超过 1。
    public boolean isBalanced(TreeNode root) {
        //自上而下递归，检查左右节点的深度差是否为1
        return height(root) >= 0;
    }
    public int height(TreeNode root) {
        //返回值、终止条件
        if (root == null) return 0;
        int leftHeight = height(root.left);
        if (leftHeight == -1) return -1;
        int rightHeight = height(root.right);
        if (rightHeight == -1) return -1;

        int result;
        if (Math.abs(leftHeight - rightHeight) > 1) return -1;
        else return Math.max(leftHeight, rightHeight) + 1;
    }
    //110. 平衡二叉树
    //给定一个二叉树，判断它是否是 平衡二叉树
    //平衡二叉树 是指该树所有节点的左右子树的深度相差不超过 1。
    /*public boolean isBalanced1(TreeNode root) {
        //递归调用方法判断其是否是平衡二叉树，
        //1. 返回值和参数：返回左右子树的高度差，如果相差大于1则返回-1，如果相差小于等于1则返回二者最大值+1
        return height1(root) > -1;
    }
    public int height1 (TreeNode root) {
        if (root == null) return 0;
        int leftHeight = height1(root.left);
        if (leftHeight == -1 ) return -1;
        int rightHeight = height1(root.right);
        if (rightHeight == -1 ) return -1;

        int result = 0;
        if (Math.abs(leftHeight - rightHeight) > 1) return -1;
        else return Math.max(leftHeight, rightHeight) + 1;
    }*/


    //257. 二叉树的所有路径
    //给你一个二叉树的根节点 root ，按 任意顺序 ，返回所有从根节点到叶子节点的路径。
    //叶子节点 是指没有子节点的节点。
    public List<String> binaryTreePaths(TreeNode root) {
        //深度优先搜索迭代
        List<String> paths = new ArrayList<>();
        traversalOfTreeRood(root, "", paths);
        return paths;
    }
    public void traversalOfTreeRood(TreeNode root,String path, List<String> result) {
        if (root != null) {
            //path是已走过的路径，且path的尾部是->
            StringBuilder pathSB = new StringBuilder(path);
            pathSB.append(Integer.toString(root.val));
            if (root.left == null && root.right == null){
                result.add(pathSB.toString());
            }else {
                pathSB.append("->");
                traversalOfTreeRood(root.left, pathSB.toString(), result);
                traversalOfTreeRood(root.right, pathSB.toString(), result);
            }
        }
    }
    //257. 二叉树的所有路径
    //给你一个二叉树的根节点 root ，按 任意顺序 ，返回所有从根节点到叶子节点的路径。
    //叶子节点 是指没有子节点的节点。
    /*public List<String> binaryTreePaths1(TreeNode root) {
        List<String> paths = new ArrayList<>();
        //递归，判断下一个节点是否是叶子节点，如果是则直接加到sb后；如果不是则将其加入sb，添加->，并继续下一次递归

    }
    public void traversalOfTreeRood1(TreeNode root, String path, List<String> paths){
        if (root == null) return;
        StringBuilder pathSB = new StringBuilder(path);
        pathSB.append(Integer.toString(root.val));
        if (root.left == null && root.right == null) {
            paths.add(pathSB.toString());
        }else {
            pathSB.append("->");
            traversalOfTreeRood1(root.left, pathSB.toString(), paths);
            traversalOfTreeRood1(root.right, pathSB.toString(), paths);
        }
    }*/


    //404. 左叶子之和
    //给定二叉树的根节点 root ，返回所有左叶子之和。
    public int sumOfLeftLeaves(TreeNode root) {
        //迭代返回所有做叶子的和
        if (root == null) return 0;
        return dfs(root);
    }
    public int dfs (TreeNode root) {
        if (root == null) return 0;
        int sum = 0;
        if (root.left != null ) {
            sum += isLeafNode(root.left) ? root.left.val : dfs(root.left);
        }
        if (root.right != null && !isLeafNode(root.right)) {
            sum += dfs(root.right);
        }
        return sum;
    }
    public boolean isLeafNode(TreeNode root) {
        return root.left == null && root.right == null;
    }

    /*//404. 左叶子之和
    //给定二叉树的根节点 root ，返回所有左叶子之和。
    public int sumOfLeftLeaves1(TreeNode root) {
        //1. 返回值int，参数root
        if (root == null) return 0;
        return dfs(root);
    }
    public int dfs1(TreeNode root){
        if (root == null) return 0;
        int sum = 0;
        if (root.left != null ) {
            sum += isLeafNode(root.left)? root.left.val : dfs(root.left);
        }
        if (root.right != null && !isLeafNode(root.right)){
            sum += dfs(root.right);
        }
        return sum;
    }*/

    //513.找树左下角的值
    //给定一个二叉树的 根节点 root，请找出该二叉树的 最底层 最左边 节点的值。
    //假设二叉树中至少有一个节点。
    /*int curheight = 0;
    int curval = 0;
    public int findBottomLeftValue(TreeNode root) {
        //深度遍历找到深度最深的叶子节点
        dfsLeftLeaf(root,0);
        return curval;
    }
    private void dfsLeftLeaf(TreeNode root, Integer height) {
        if (root == null) return;
        height++;
        dfsLeftLeaf(root.left, height);
        dfsLeftLeaf(root.right, height);
        if (height > curheight) {
            curheight = height;
            curval = root.val;
        }
    }*/
    //bfs:从右往左遍历
    public int findBottomLeftValue(TreeNode root) {
        //bfs
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int val = root.val;
        while (!queue.isEmpty()){
            int curSize = queue.size();
            for (int i = 0; i < curSize; i++) {
                TreeNode temp = queue.poll();
                if (temp.right != null) queue.offer(temp.right);
                if (temp.left != null) queue.offer(temp.left);
                if (i == curSize-1) val = temp.val;
            }
        }
        return val;
    }

    //112. 路径总和
    //给你二叉树的根节点 root 和一个表示目标和的整数 targetSum 。判断该树中是否存在 根节点到叶子节点 的路径，
    // 这条路径上所有节点值相加等于目标和 targetSum 。如果存在，返回 true ；否则，返回 false 。
    //叶子节点 是指没有子节点的节点。
    public boolean hasPathSum(TreeNode root, int targetSum) {
        //dfs遍历时计算路径和，直到达到叶子节点时返回值，如果与targetsum相等则返回true
        if (root == null) return false;
        return dfsSum(root, targetSum);
    }
    private boolean dfsSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        targetSum -= root.val;
        if (root.left == null & root.right == null) return targetSum == 0;
        return dfsSum(root.left, targetSum)  || dfsSum(root.right, targetSum);
    }

    //654. 最大二叉树
    //给定一个不重复的整数数组 nums 。 最大二叉树 可以用下面的算法从 nums 递归地构建:
    //创建一个根节点，其值为 nums 中的最大值。
    //递归地在最大值 左边 的 子数组前缀上 构建左子树。
    //递归地在最大值 右边 的 子数组后缀上 构建右子树。
    //返回 nums 构建的 最大二叉树 。
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        //1. 返回值TreeNode, 参数：root，子数组
        //2. 终止条件：nums长度为0
        //3. 执行操作：递归调用左数组和右数组，将其中最大值放入放入节点
        if (nums == null || nums.length == 0) return null;
        TreeNode root = new TreeNode(nums[findMaxNum(nums)]);
        maxTree(root, nums);
        return root;
    }
    public void maxTree(TreeNode root, int[] nums){
        if (nums == null || nums.length == 0) return;
        int idx = findMaxNum(nums);
        if (idx == -1 || idx == nums.length) return;
        int[] numsL = Arrays.copyOfRange(nums, 0, idx);
        int[] numsR = Arrays.copyOfRange(nums, idx+1, nums.length);
        int maxIL = findMaxNum(numsL);
        int maxIR = findMaxNum(numsR);
        root.left = maxIL == -1 ? null : new TreeNode(numsL[maxIL]);
        root.right = maxIR == -1 ? null : new TreeNode(numsR[maxIR]);
        maxTree(root.left, numsL);
        maxTree(root.right, numsR);
    }
    //找出最大值的索引
    public int findMaxNum(int[] nums){
        if (nums.length == 0) return -1;
        int max = nums[0];
        int index = 0;
        for (int i = 1; i < nums.length; i++) {
            if (max < nums[i]){
                max = nums[i];
                index = i;
            }
        }
        return index;
    }
    //官解：简洁版
/*    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return construct(nums, 0, nums.length - 1);
    }
    private TreeNode construct(int[] nums, int left, int right) {
        if (left > right) return null;
        int best = left;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > nums[best])
                best = i;
        }
        TreeNode node = new TreeNode(nums[best]);
        node.left = construct(nums, left, best-1);
        node.right = construct(nums, best, right);
    }*/

    //617. 合并二叉树
    //给你两棵二叉树： root1 和 root2 。
    //想象一下，当你将其中一棵覆盖到另一棵之上时，两棵树上的一些节点将会重叠（而另一些不会）。你需要将这两棵树合并成一棵新二叉树。
    // 合并的规则是：如果两个节点重叠，那么将这两个节点的值相加作为合并后节点的新值；否则，不为 null 的节点将直接作为新二叉树的节点。
    //返回合并后的二叉树。注意: 合并过程必须从两个树的根节点开始。
/*    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        //DFS中序遍历
        //1. 返回值TreeNode，参数两个TreeNode
        //2. 终止条件：两个参数都为null
        //3. 函数体
        return mergeTree(root1,root2);
    }

    private TreeNode mergeTree(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) return null;
        int val = (root1 == null ? 0 : root1.val) +  (root2 == null ? 0 : root2.val);
        TreeNode newRoot = new TreeNode(val);
        newRoot.left = mergeTree((root1 == null || root1.left == null) ? null : root1.left, (root2 == null || root2.left == null) ? null : root2.left);
        newRoot.right = mergeTree((root1 == null || root1.right == null) ? null : root1.right, (root2 == null || root2.right == null) ? null : root2.right);
        return newRoot;
    }*/
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }
        TreeNode merged = new TreeNode(t1.val + t2.val);
        merged.left = mergeTrees(t1.left, t2.left);
        merged.right = mergeTrees(t1.right, t2.right);
        return merged;
    }

    //700. 二叉搜索树中的搜索
    //给定二叉搜索树（BST）的根节点 root 和一个整数值 val。
    //你需要在 BST 中找到节点值等于 val 的节点。 返回以该节点为根的子树。 如果节点不存在，则返回 null 。
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) return null;
        TreeNode temp = null;
        if (root.val == val) temp = root;
        if (root.val < val) temp = searchBST(root.right, val);
        if (root.val > val) temp = searchBST(root.left, val);
        return temp;
    }

    //98. 验证二叉搜索树
    //给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
    //有效 二叉搜索树定义如下：
    //节点的左子树只包含 小于 当前节点的数。
    //节点的右子树只包含 大于 当前节点的数。
    //所有左子树和右子树自身必须也是二叉搜索树。
    public boolean isValidBST(TreeNode root) {
        if (root == null) return false;
        //中序遍历，得到的数组如果不是递增则false
        List<Integer> list = new ArrayList<>();
        dfsForBST(root, list);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) <= list.get(i-1)) return false;
        }
        return true;
    }
    private void dfsForBST(TreeNode root, List<Integer> list) {
        if (root == null) return;
        dfsForBST(root.left, list);
        list.add(root.val);
        dfsForBST(root.right, list);
    }

    //530. 二叉搜索树的最小绝对差
    //给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值 。
    //差值是一个正数，其数值等于两值之差的绝对值。
    public int getMinimumDifference(TreeNode root) {
        //迭代返回左子树的最大值，右子树的最小值。
        List<Integer> list = new ArrayList<>();
        dfsForBST(root, list);
        int dif = list.get(1) - list.get(0);
        for (int i = 1; i < list.size(); i++) {
            int t = list.get(i) - list.get(i-1);
            dif =  Math.min(dif,t);
        }
        return dif;
    }
/*    class Solution {
        int pre;
        int ans;

        public int getMinimumDifference(TreeNode root) {
            ans = Integer.MAX_VALUE;
            pre = -1;
            dfs(root);
            return ans;
        }

        public void dfs(TreeNode root) {
            if (root == null) {
                return;
            }
            dfs(root.left);
            if (pre == -1) {
                pre = root.val;
            } else {
                ans = Math.min(ans, root.val - pre);
                pre = root.val;
            }
            dfs(root.right);
        }
    }*/

    //501. 二叉搜索树中的众数
    //给你一个含重复值的二叉搜索树（BST）的根节点 root ，找出并返回 BST 中的所有 众数（即，出现频率最高的元素）。
    //如果树中有不止一个众数，可以按 任意顺序 返回。
    //假定 BST 满足如下定义：
    //结点左子树中所含节点的值 小于等于 当前节点的值
    //结点右子树中所含节点的值 大于等于 当前节点的值
    //左子树和右子树都是二叉搜索树
    List<Integer> answerOfFindMode = new ArrayList<>();
    int base, count, maxCount;
    public int[] findMode(TreeNode root) {
        dfsFindMode(root);
        int[] mode = new int[answerOfFindMode.size()];
        for (int i = 0; i < answerOfFindMode.size(); i++) {
            mode[i] = answerOfFindMode.get(i);
        }
        return mode;
    }

    public void dfsFindMode(TreeNode o) {
        if (o == null) {
            return;
        }
        dfsFindMode(o.left);
        updateFindMode(o.val);
        dfsFindMode(o.right);
    }
    public void updateFindMode(int x){
        if (x == base){
            ++count;
        }else {
            count = 1;
            base = x;
        }
        if (count == maxCount) {
            answerOfFindMode.add(base);
        }
        if (count > maxCount) {
            maxCount = count;
            answerOfFindMode.clear();
            answerOfFindMode.add(base);
        }
    }

    //236. 二叉树的最近公共祖先
    //给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
    //百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //后序遍历，如果遇到q或p则返回q或p
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null && right == null) return null;
        else if (left != null && right == null) return left;
        else if (left == null && right != null) return right;
        else return root;
    }

    //701. 二叉搜索树中的插入操作
    //给定二叉搜索树（BST）的根节点 root 和要插入树中的值 value ，将值插入二叉搜索树。 返回插入后二叉搜索树的根节点。 输入数据 保证 ，新值和原始二叉搜索树中的任意节点值都不同。
    //注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。 你可以返回 任意有效的结果 。
    public TreeNode insertIntoBST(TreeNode root, int val) {
        //
        if (root == null) return new TreeNode(val);
        if (root.val > val) root.left = insertIntoBST(root.left, val);
        if (root.val < val) root.right = insertIntoBST(root.right, val);
        return root;

    }

    //450. 删除二叉搜索树中的节点
    //给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
    //一般来说，删除节点可分为两个步骤：
    //首先找到需要删除的节点；
    //如果找到了，删除它。
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return root;
        if (root.val > key) root.left = deleteNode(root.left, key);
        if (root.val < key) root.right = deleteNode(root.right, key);
        if (root.val == key) {
            TreeNode tempR = root.right;
            TreeNode tempL = root.left;
            TreeNode temp = tempR;
            if (temp != null) {
                while (temp.left != null) {
                    temp = temp.left;
                }
                temp.left = tempL;
            }
            if (tempR == null) return tempL;
            root = tempR;
        }
        return root;
    }

    //669. 修剪二叉搜索树
    //给你二叉搜索树的根节点 root ，同时给定最小边界low 和最大边界 high。通过修剪二叉搜索树，使得所有节点的值在[low, high]中。
    //修剪树 不应该 改变保留在树中的元素的相对结构 (即，如果没有被移除，原有的父代子代关系都应当保留)。 可以证明，存在 唯一的答案 。
    //所以结果应当返回修剪好的二叉搜索树的新的根节点。注意，根节点可能会根据给定的边界发生改变。
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) return root;
        int a = inTheRange(root, low, high);
        //小于low的左子树直接舍弃，右子树逐一判断
        if (a == 1) {
            root.left = null;
            return trimBST(root.right, low, high);
        }
        if (a == 2) {
            root.left = trimBST(root.left, low, high);
            root.right = trimBST(root.right, low, high);
            return root;
        }
        if (a == 3) {
            root.right = null;
            return trimBST(root.left, low, high);
        }
        return root;
    }
    // 1表示小于low，2表示在区间内，3表示大于high
    private int inTheRange(TreeNode root, int low, int high) {
        int i = root.val;
        if (i<low) return 1;
        if (i>high) return 3;
        return 2;
    }
    //官解
/*    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) {
            return null;
        }
        if (root.val < low) {
            return trimBST(root.right, low, high);
        } else if (root.val > high) {
            return trimBST(root.left, low, high);
        } else {
            root.left = trimBST(root.left, low, high);
            root.right = trimBST(root.right, low, high);
            return root;
        }
    }*/

    //108. 将有序数组转换为二叉搜索树
    //给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 平衡 二叉搜索树。
    //平衡二叉树 是指该树所有节点的左右子树的深度相差不超过 1。
    public TreeNode sortedArrayToBST(int[] nums) {
        //依次取区间的中间值作为val
        if (nums.length == 0) return null;
        TreeNode root = null;
        return getTreeNode(root, nums);
    }

    private TreeNode getTreeNode(TreeNode root, int[] nums) {
        int n = nums.length;
        if (n == 0 ) return null;
        root =  new TreeNode(nums[n/2]);
        if (n == 1) return root;
        int[] left = Arrays.copyOfRange(nums, 0, n/2);
        int[] right = Arrays.copyOfRange(nums, n/2+1, n);
        root.left = getTreeNode(root.left, left);
        root.right = getTreeNode(root.right, right);
        return root;
    }
    //官解，这样可以避免数组的拷贝，节省空间
    /*public TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length == 0) return null;
        return getTreeNode(nums, 0, nums.length - 1);
    }

    private TreeNode getTreeNode(int[] nums, int left, int right) {
        if (left > right) return null;

        // 取中间值作为根节点
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);

        // 递归构建左子树和右子树
        root.left = getTreeNode(nums, left, mid - 1);
        root.right = getTreeNode(nums, mid + 1, right);

        return root;
    }*/

    //538. 把二叉搜索树转换为累加树
    //给出二叉 搜索 树的根节点，该树的节点值各不相同，请你将其转换为累加树（Greater Sum Tree），使每个节点 node 的新值等于原树中大于或等于 node.val 的值之和。
    //提醒一下，二叉搜索树满足下列约束条件：
    //节点的左子树仅包含键 小于 节点键的节点。
    //节点的右子树仅包含键 大于 节点键的节点。
    //左右子树也必须是二叉搜索树。
    int sumOfBST = 0;
    public TreeNode convertBST(TreeNode root) {
        if (root == null) return root;
        convertBST(root.right);
        sumOfBST += root.val;
        root.val = sumOfBST;
        convertBST(root.left);
        return root;
    }

    //回溯法模板
/*    void backtracking(参数) {
        if (终止条件) {
            存放结果;
            return;
        }

        for (选择：本层集合中元素（树中节点孩子的数量就是集合的大小）) {
            处理节点;
            backtracking(路径，选择列表); // 递归
            回溯，撤销处理结果
        }
    }*/

    //77. 组合
    //给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
    //你可以按 任何顺序 返回答案。
    List<List<Integer>> ansOfCombine = new ArrayList<>();
    List<Integer> tempOfCombine = new ArrayList<>();
    public List<List<Integer>> combine(int n, int k) {
        //想象为n叉数问题，k为树的深度，在每一层递归进行for循环，for的次数等于k-已经有的数字个数;
            backTrackingOfCombine(1,n, k);
            return ansOfCombine;
    }
    public void backTrackingOfCombine(int startIdx, int n, int k) {
        if (tempOfCombine.size() == k){
            //存储的是地址，如果直接add temp，在后续会发生改变；因此add的是temp的副本
            ansOfCombine.add(new ArrayList<>(tempOfCombine));
            return;
        }
        for (int i = startIdx; i <= n - (k - tempOfCombine.size()) + 1; i++) {
            tempOfCombine.add(i);
            backTrackingOfCombine(i + 1, n, k);
            //移除最后一个数字，进项下次循环
            tempOfCombine.removeLast();
        }
    }

    //216. 组合总和 III
    //找出所有相加之和为 n 的 k 个数的组合，且满足下列条件：
    //只使用数字1到9
    //每个数字 最多使用一次
    //返回 所有可能的有效组合的列表 。该列表不能包含相同的组合两次，组合可以以任何顺序返回。

    public List<List<Integer>> combinationSum3(int k, int n) {
        backTrackingOfCombine3(1, n, k);
        return ansOfCombine;
    }
    private void backTrackingOfCombine3(int startIdx, int n, int k) {
        //终止条件，和为n或已经九个数
        int sum = sumOfList(tempOfCombine);
        if (sum > n) return;
        if (tempOfCombine.size() == k && sum == n) {
            ansOfCombine.add(new ArrayList<>(tempOfCombine));
            return;
        }
        for (int i = startIdx; i < 10 - (k - tempOfCombine.size()) + 1 ; i++) {
            tempOfCombine.add(i);
            backTrackingOfCombine3(i + 1, n, k);
            tempOfCombine.removeLast();
        }

    }
    private int sumOfList(List<Integer> list) {
        int sum = 0;
        for (Integer i : list) {
            sum += i;
        }
        return sum;
    }

    //17. 电话号码的字母组合
    //给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
    //给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
    List<String> ansOfLetterCom = new ArrayList<>();
    //digits == "" 字符串是引用类型，要这样比：digits.equals("")
    //isEmpty() 在字符串为 null 时会抛出 NullPointerException，而 equals("") 在字符串为 null 时会安全地返回 false。
    public List<String> letterCombinations(String digits) {
        if (digits.isEmpty() || digits == null) return ansOfLetterCom;
        StringBuilder sbOfLetterCom = new StringBuilder();
        backTrackingOfLetterCom(digits, 0, sbOfLetterCom);
        return ansOfLetterCom;
    }
    private void backTrackingOfLetterCom(String digits, int index, StringBuilder sbOfLetterCom) {
        int n = digits.length();
        if (sbOfLetterCom.length() == n ) {
            ansOfLetterCom.add(new String(sbOfLetterCom.toString()));
            return;
        }
        String s = getStringofIndex(Character.getNumericValue(digits.charAt(index)));
        for (int i = 0; i < s.length(); i++) {
            sbOfLetterCom.append(s.charAt(i));
            backTrackingOfLetterCom(digits, index + 1, sbOfLetterCom);
            sbOfLetterCom.deleteCharAt(sbOfLetterCom.length() - 1);
        }
    }

    private String getStringofIndex(int c) {
        switch (c){
            case 2: return "abc";
            case 3: return "def";
            case 4: return "ghi";
            case 5: return "jkl";
            case 6: return "mno";
            case 7: return "pqrs";
            case 8: return "tuv";
            case 9: return "wxyz";
            default: return "";
        }
    }

    //39. 组合总和
    //给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
    //candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
    //对于给定的输入，保证和为 target 的不同组合数少于 150 个。
/*    List<List<Integer>> ansOfCombine = new ArrayList<>();
    List<Integer> tempOfCombine = new ArrayList<>();*/
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        backTracking39(candidates, target, 0, 0);
        return ansOfCombine;
    }

    private void backTracking39(int[] candidates, int target, int startIndex, int currentSum) {
        if (currentSum == target) {
            ansOfCombine.add(new ArrayList<>(tempOfCombine));
            return;
        }
        if (currentSum > target) {
            return;
        }

        for (int i = startIndex; i < candidates.length; i++) {
            // 选择当前元素
            tempOfCombine.add(candidates[i]);
            // 进入下一层决策树
            //i可以保证不往更小值回溯
            backTracking39(candidates, target, i, currentSum + candidates[i]);
            // 回溯，撤销选择
            tempOfCombine.remove(tempOfCombine.size() - 1);
        }
    }

    //40. 组合总和 II
    //给定一个候选人编号的集合 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
    //candidates 中的每个数字在每个组合中只能使用 一次 。
    //注意：解集不能包含重复的组合。
    /*    List<List<Integer>> ansOfCombine = new ArrayList<>();
    List<Integer> tempOfCombine = new ArrayList<>();*/
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        //不能重复的39题
        Arrays.sort(candidates);
        backTracking40(candidates, 0, target, 0);
        return ansOfCombine;
    }
    private void backTracking40(int[] candidates, int index, int target, int sum) {
        int n = candidates.length;
        if (sum == target) {
            ansOfCombine.add(new ArrayList<>(tempOfCombine));
            return;
        }

        for (int i = index; i < n && sum + candidates[i] <= target; i++) {
            if (i > index && candidates[i] == candidates[i-1]) continue;
            sum += candidates[i];
            tempOfCombine.add(candidates[i]);
            backTracking40(candidates, i + 1, target, sum);
            int temp = tempOfCombine.getLast();
            sum -= temp;
            tempOfCombine.removeLast();
        }
    }

    //131. 分割回文串
    //给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是回文串 。返回 s 所有可能的分割方案。
    List<String> tempOf131 = new ArrayList<>();
    List<List<String>> ansOf131 = new ArrayList<>();

    public List<List<String>> partition(String s) {
        backTracking131(s, 0, new StringBuilder());
        return ansOf131;
    }

    private void backTracking131(String s, int index, StringBuilder sb) {
        int n = s.length();
        if (index == n){
            ansOf131.add(new ArrayList<>(tempOf131));
            return;
        }
        for (int i = index; i < n; i++) {
            sb.append(s.charAt(i));
            if (isRevStr(sb.toString())){
                tempOf131.add(sb.toString());
                backTracking131(s, i + 1, new StringBuilder());
                tempOf131.removeLast();
            }
        }
    }

    private boolean isRevStr(String s) {
        for (int i = 0; i < s.toCharArray().length; i++) {
            if (s.charAt(i) != s.charAt(s.length() - i - 1)) return false;
        }
        return true;
    }


}

