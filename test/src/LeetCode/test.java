package LeetCode;

import com.sun.jdi.Value;

import java.util.*;

public class test {

    /*给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
    字母异位词 是由重新排列源单词的所有字母得到的一个新单词。*/
    public List<List<String>> groupAnagrams (String[] strs) {
        //哈希表的键值对分别是排序后的字符串,存储分组的字母异位词的List<String>
        HashMap<String, List<String>> hm = new HashMap<>();
        for (String str : strs) {
            // 将字符串的字符排序
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            String sortedStr = new String(charArray);

            // 如果哈希表已存在字符串，则直接在List中添加
            if (hm.containsKey(sortedStr)) {
                hm.get(sortedStr).add(str);
            } else {
                // 不存在
                List<String> list = new ArrayList<>();
                list.add(str);
                hm.put(sortedStr, list);
            }
        }
        // 将哈希表中的值添加到结果中
        return new ArrayList<>(hm.values());
    }


    /*给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
    请你设计并实现时间复杂度为 O(n) 的算法解决此问题。*/
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        //先将数组放入set集合去重
        for (int num : nums) {
            set.add(num);
        }
        //记录最长的长度
        int longestStreak = 0;
        //遍历每个数字，分别计算以之为开头的连续序列长度
        //判断num-1的值是否存在，如果存在，说明num不是连续序列的开头；如果不存在，则while循环计算序列长度
        for (int num : set) {
            int currentStreak = 0;
            if (!set.contains(num-1)){
                //如果存在，说明num不是连续序列的开头,不需要操作
                //如果不存在，则while循环计算序列长度
                int val = num;
                currentStreak++;
                while (set.contains(val+1)){
                    val++;
                    currentStreak++;
                }
                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }
        return longestStreak;
    }


    /*给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
    请注意 ，必须在不复制数组的情况下原地对数组进行操作。*/
    //[0,1,0,3,12]
    public void moveZeroes(int[] nums) {
        //将所有非0数字移动到前面
        int nonZeroIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[nonZeroIndex++] = nums[i];
            }
        }
        //填充剩余位置
        for (int i = nonZeroIndex; i < nums.length; i++) {
            nums[i] = 0;
        }
    }


    /*给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
    找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
    返回容器可以储存的最大水量。*/
    public int maxArea(int[] height) {
        //双指针分别指向头尾，计算此时面积，然后移动左右指针中最小的那个
        int right = height.length, left = 0;
        int max = 0;
        while (left < right){
            max = Math.max(max, (right-left) * Math.min(height[left],height[right]));
            if (height[right] >= height[left]){
                left++;
            }else right--;
        }
        return max;
    }


    /*给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]]
    满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。
    请你返回所有和为 0 且不重复的三元组。
    注意：答案中不可以包含重复的三元组。*/
    public List<List<Integer>> threeSum1(int[] nums) {
        //将输入放入list中， 排序，得到升序的数列；
        //再将排序后的list与自己相加，放入map<Integer[2], value>，Integer[2]存放索引
        //最后将map的value一一对应
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }
        //list+list
        Map<Integer[], Integer> map = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                map.put(new Integer[]{i, j}, list.get(i) + list.get(j));
            }
        }
        //得到的value在list遍历得到满足三元组的索引
        List<List<Integer>> indexList = new ArrayList<>();
        for (Map.Entry<Integer[], Integer> entry : map.entrySet()) {
            for (int i = 0; i < list.size(); i++) {
                List<Integer> index = new ArrayList<>();
                if (entry.getKey()[0] == i || entry.getKey()[1] == i) break;
                if (entry.getValue() + list.get(i) == 0){
                    Collections.addAll(index,list.get(entry.getKey()[0]),
                            list.get(entry.getKey()[1]), list.get(i));
                    Collections.sort(index);
                    if (!indexList.contains(index))
                        indexList.add(index);
                }
            }
        }
        return indexList;
    }
    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        // 枚举 a
        for (int first = 0; first < n; ++first) {
            // 需要和上一次枚举的数不相同
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            // c 对应的指针初始指向数组的最右端
            int third = n - 1;
            int target = -nums[first];
            // 枚举 b
            for (int second = first + 1; second < n; ++second) {
                // 需要和上一次枚举的数不相同
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                // 需要保证 b 的指针在 c 的指针的左侧
                while (second < third && nums[second] + nums[third] > target) {
                    --third;
                }
                // 如果指针重合，随着 b 后续的增加
                // 就不会有满足 a+b+c=0 并且 b<c 的 c 了，可以退出循环
                if (second == third) {
                    break;
                }
                if (nums[second] + nums[third] == target) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[third]);
                    ans.add(list);
                }
            }
        }
        return ans;
    }

    /*给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。*/
    public int trap(int[] height) {
        //设置双指针，左指针从0开始，右指针从length-1开始，遍历过程中，较短的先移动，移动到比
        //移动到比原值小的数时，差值即为存水量；移动到大值时，移动另外一个指针

        int left = 0, right = height.length-1;
        int leftMax = 0, rightMax = 0;
        int sum = 0;
        while (left < right){
            leftMax = Math.max(height[left], leftMax);
            rightMax = Math.max(height[right], rightMax);
            //先移动左指针
            if (height[left] <= height[right]){
                sum += leftMax -height[left];
                left++;
            }else{
                sum += rightMax -height[right];
                right--;
            }
        }
        return sum;
    }

    /*给定一个字符串 s ，请你找出其中不含有重复字符的最长子串的长度。*/
    public int lengthOfLongestSubstring(String s) {
        //设置滑动窗口，从字符串左侧开始，右指针递增放入hashset,如果不重复，重复则记录最大长度移动左指针，
        char[] chars = s.toCharArray();
        int right = 0;
        int max = 0;
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            if (i != 0) set.remove(s.charAt(i-1));
            while (right < s.length() && !set.contains(s.charAt(right))){
                set.add(s.charAt(right));
                ++right;
            }
            max = Math.max(max, right-i);
        }
        return max;
    }

    /*给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
    异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。*/
    public List<Integer> findAnagrams(String s, String p) {
        //创建两个整型数组分别放置s子字符串和p的字母数量
        int[] sCount = new int[26];
        int[] pCount = new int[26];
        List<Integer> list = new ArrayList<>();
        if (s.length() < p.length()) return list;
        for (int i = 0; i < p.length(); i++) {
            ++sCount[s.charAt(i)-'a'];
            ++pCount[p.charAt(i)-'a'];
        }
        if (Arrays.equals(sCount, pCount)) list.add(0);
        for (int i = 0; i < s.length() - p.length(); i++) {
            --sCount[s.charAt(i)-'a'];
            ++sCount[s.charAt(i + p.length())-'a'];
            if (Arrays.equals(sCount, pCount))  list.add(i+1);
        }
        return list;
    }
    /*给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。
    子数组是数组中元素的连续非空序列。*/
    public int subarraySum(int[] nums, int k) {
        //前缀和 + 哈希表
        //从左往右计算pre[i]的值并存入哈希表，如果下标为[i，j]的数组满足要求，pre[i]-k = pre[j-1]
        //则在计算前缀和的同时，判断哈希表中有无pre[i]-k的键
        int pre = 0;
        int count = 0;
        HashMap<Integer, Integer> hm = new HashMap<>();
        hm.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            pre = pre + nums[i];
            if (hm.containsKey(pre - k)){
                count += hm.get(pre - k);
            }
            hm.put(pre, hm.getOrDefault(pre, 0) + 1);

        }
        return count;

    }

    /*给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。
    你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
    返回 滑动窗口中的最大值 。*/
    public int[] maxSlidingWindow(int[] nums, int k) {
        //哈希表
        //先将前k个值放入哈希表，再移动窗口，每移动一
        return null;
    }


    /*给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
    注意：
    对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
    如果 s 中存在这样的子串，我们保证它是唯一的答案。*/
    public String minWindow(String s, String t) {
        int left = 0, right = 0;
        int minL = Integer.MAX_VALUE, index = 0;
        HashMap<Character, Integer> t_hm = new HashMap<>();

        for (char c : t.toCharArray()) {
            t_hm.put(c, t_hm.getOrDefault(c, 0) + 1);
        }

        while (right < s.length()) {
            //如果包含这个字符，对应value--
            if (t_hm.containsKey(s.charAt(right))) {
                t_hm.put(s.charAt(right), t_hm.get(s.charAt(right)) - 1);
            }
            //完全匹配后，开始移动left，直至不匹配
            while (matcher(t_hm) && left <= right) {
                if (right - left + 1 < minL) {
                    minL = right - left + 1;
                    index = left;
                }
                if (t_hm.containsKey(s.charAt(left))) {
                    t_hm.put(s.charAt(left), t_hm.get(s.charAt(left)) + 1);
                }
                left++;
            }
            right++;
        }
        //在找到最小子串后，判断 minL 是否为 Integer.MAX_VALUE，若是则返回空字符串。
        return minL == Integer.MAX_VALUE ? "" : s.substring(index, index + minL);
    }

    //如果哈希表中的值都小于等于0，返回true，表示全部匹配上
    private static boolean matcher(HashMap<Character, Integer> t) {
        for (int value : t.values()) {
            if (value > 0) {
                return false;
            }
        }
        return true;
    }

    /*给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。子数组
    是数组中的一个连续部分。*/
    //贪心算法
    public int maxSubArray(int[] nums) {

        int maxSum = nums[0];
        int currentSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            //currentSum确认了当前num[i]和当前的子数组和的最大值
            //如果前者更大，子数组和小于当前数值，要重新开始计算
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }

    /*以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
    请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。*/
    public int[][] merge(int[][] intervals) {
       Arrays.sort(intervals, new Comparator<int[]>() {
           @Override
           public int compare(int[] o1, int[] o2) {
               return o1[0] - o2[0];
           }
       });
       List<int[]> merged = new ArrayList<>();
       for (int i = 0; i < intervals.length; i++) {
           int L = intervals[i][0], R = intervals[i][1];
           if (merged.size() == 0 || merged.get(merged.size() - 1)[1] < L){
               merged.add(new int[]{L, R});
           } else {
               merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], R);
           }
       }
        return merged.toArray(new int[merged.size()][]);
    }

    /*给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。*/
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n; // 如果 k 大于数组长度，进行取模运算
        int[] r = new int[n];
        for (int i = 0; i < r.length; i++) {
            r[(i+k) % n] = nums[i];
        }
        System.arraycopy(r, 0, nums, 0, n);
    }

    /*给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积 。
    题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内。
    请 不要使用除法，且在 O(n) 时间复杂度内完成此题。*/
    public int[] productExceptSelf(int[] nums) {
        //answer[i] = pre[i-1] *
        int length = nums.length;
        int[] left = new int[length];
        int[] right = new int[length];
        int preL = 1;
        int preR = 1;

        for (int i = 0; i < left.length; i++) {
            left[i] = preL;
            preL = preL *nums[i];
        }

        for (int i = right.length-1; i >= 0; i--) {
            right[i] = preR;
            preR = preR * nums[i];
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = left[i]*right[i];
        }
        return nums;
    }

    /*给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
    请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。*/
    public int firstMissingPositive(int[] nums) {
        //对于一个长度为 N 的数组，其中没有出现的最小正整数只能在 [1,N+1] 中。
        //将数组的值本身当作索引将其在数组中对应的值取负，作为标记
        //最后遍历数组，第一个正值的索引+1就是题目所求
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] <= 0) nums[i] = n+1;
        }
        for (int i = 0; i < n; i++) {
            int num = Math.abs(nums[i]);
            if (num <= n){
                nums[num-1] = -Math.abs(nums[num-1]) ;
            }
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) return i+1;
        }
        return n+1;
    }

    /*给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用原地算法。*/
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        Set<Integer> row = new HashSet<>();
        Set<Integer> column = new HashSet<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    row.add(i);
                    column.add(j);
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (row.contains(i) || column.contains(j)) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    /*给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。*/
    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        List<Integer> list = new ArrayList<>();
        int top= 0, left = 0, bottom = m-1, right = n-1;
        while (left <= right && top <= bottom){
            for (int column = left; column <= right; column++) {
                list.add(matrix[top][column]);
            }
            for (int row = top + 1; row <= bottom; row++) {
                list.add(matrix[row][right]);
            }
            if (left < right && top < bottom) {
                for (int column = right - 1; column > left; column--) {
                    list.add(matrix[bottom][column]);
                }
                for (int row = bottom; row > top; row--) {
                    list.add(matrix[row][left]);
                }
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        return list;
    }

    /*给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。 */
    public void rotate(int[][] matrix) {
        int n = matrix.length;

        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n-i-1][j];
                matrix[n-i-1][j]= temp;
            } 
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

    }

    /*编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
    每行的元素从左到右升序排列。
    每列的元素从上到下升序排列。*/
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int x = 0, y = n - 1;
        while (x < m && y >= 0){
            if (matrix[x][y] == target ) return true;
            if (matrix[x][y] > target) y--;
            else x++;
        }
        return false;
    }


    /*给你两个单链表的头节点headA和headB，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 null。*/
    /*public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        List<ListNode> list = new ArrayList<>();
        if ()
        while (headA != null){
            list.add(headA);
            headA = headA.next;
        }
        while (headB != null){
            if (list.contains(headB)){
                return headB;
            }
            headB = headB.next;
        }
        return null;
    }*/

    /*给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。*/
    /*public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) {
            this.val = val; this.next = next; }
    }
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next ==null)
            return head;
        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }*/

    /*给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。*/
    /*public boolean isPalindrome(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while (head != null){
            list.add(head.val);
            head = head.next;
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != list.get(list.size()-i-1)) return false;
        }
        return true;
    }*/

    /*给你一个链表的头节点 head ，判断链表中是否有环。*/
    /*public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }
    public boolean hasCycle(ListNode head) {
        HashSet<ListNode> hs = new HashSet<>();
        while (head != null){
            hs.add(head);
            if (hs.contains(head)){
                return true;
            }
            head = head.next;
        }
        return false;
    }*/

    /*给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。*/
    //使用快慢指针：节约空间和时间
    /*public ListNode detectCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null){
            slow = slow.next;
            if (fast.next != null){
                fast = fast.next.next;
            }else return null;
            if (fast == slow){
                ListNode ptr = head;
                while (ptr != slow){
                    ptr = ptr.next;
                    slow = slow.next;
                }
                return ptr;
            }
        }
        return null;
    }*/

    /*将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 */
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) {
            this.val = val; this.next = next; }
    }
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode prehead = new ListNode(-1);
        ListNode head = prehead;
        while (list1 != null && list2 != null){
            if (list1.val <= list2.val){
                head.next = list1;
                list1 = list1.next;
            }else {
                head.next = list2;
                list2 = list2.next;
            }
            head = head.next;
        }
        head.next = list1 == null ? list2 : list1;
        return prehead.next;
    }

    /*给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
    请你将两个数相加，并以相同形式返回一个表示和的链表。*/
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        int carry = 0;
        while (l1 != null || l2 != null){
            int x = (l1 != null) ? l1.val : 0;
            int y = (l2 != null) ? l2.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            current.next = new ListNode(sum % 10);
            current = current.next;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;

        }
        if (carry > 0){
            current.next = new ListNode(carry);
        }
        return dummy.next;
    }

    /*给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。*/
    public ListNode removeNthFromEnd(ListNode head, int n) {
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

    /*给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。
    你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）*/
    public ListNode swapPairs(ListNode head) {
        ListNode pre = new ListNode(-1, head);
        ListNode q = pre;
        while (head != null){
            if (head.next != null){
                pre.next = head.next;
                head.next = pre.next.next;
                pre.next.next = head;
                pre = head;
                head = head.next;
            }else {
                pre.next = head;
                break;
            }
        }

        return q.next;
    }
    //递归
    /*public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = head.next;
        head.next = swapPairs(newHead.next);
        newHead.next = head;
        return newHead;
    }
     */

    /*给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
    k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。*/
    public ListNode reverseKGroup(ListNode head, int k) {
        int n = k;
        while (true){
            //判断末尾是否有k个结点
            while (k != 0 && head != null){
                head = head.next;
                k--;
            }
            if (k == 0){
                ListNode temp = head;
                //head.next =
            }
        }

    }

}


