package LeetCode;

import java.time.chrono.HijrahEra;
import java.util.*;

public class LeetCode_hot100_2 {




    //283 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
    //
    //请注意 ，必须在不复制数组的情况下原地对数组进行操作。
    public void moveZeroes(int[] nums) {
        //从头遍历，将所有非零元素前移，最后填充末尾为0
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0){
                nums[index++] = nums[i];
            }
        }
        for (int i = index; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    //11 盛最多水的容器
    // 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
    //
    //找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
    //
    //返回容器可以储存的最大水量。
    public int maxArea(int[] height) {
        //双指针，存水量=较短边*索引之差，记录一个最大值并保持更新，移动较短边，直到大于长边。。。
        int indexL = 0, indexR = height.length-1;
        int maxArea = 0;
        while (indexL <= indexR){
            if (height[indexL] <= height[indexR]){
                maxArea = Math.max(maxArea, height[indexL]*(indexR-indexL));
                indexL++;
            }else {
                maxArea = Math.max(maxArea, height[indexR]*(indexR-indexL));
                indexR--;
            }
        }
        return maxArea;
    }

    //15 三数之和
    //给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，
    // 同时还满足 nums[i] + nums[j] + nums[k] == 0 。请你返回所有和为 0 且不重复的三元组。
    public List<List<Integer>> threeSum(int[] nums) {
        //先排序，设置第一个指针i，放在最小值处，然后双指针left，right，分别从左右两边逼近，如果二者之和大于-nums[i]，则right左移直到小于-nums[i]
        //然后移动left直到二者相遇或满足-nums[i]
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> list = new ArrayList<>();
        if (n < 3) return null;
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i-1]) continue;
            int right = nums.length-1;
            int target = -nums[i];
            for (int left = i+1; left < n; left++) {
                if (left > i+1 && nums[left] == nums[left-1]) continue;
                while (left < right && nums[left] + nums[right] > target){
                    right--;
                }
                if (left == right) break;
                if (target == nums[left] + nums[right]){
                    List<Integer> list1 = new ArrayList<>();
                    list1.add(nums[i]);
                    list1.add(nums[left]);
                    list1.add(nums[right]);
                    list.add(list1);
                }
            }
        }
        return list;
    }

    //42 接雨水
    //给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
    public int trap(int[] height) {
        //解法1：动态规划。分别遍历出每个下标对应的，左右两边的最大值；最后计算出该下标的村水量=min(左右最大值)-下标对应值
        //解法2：双指针。以leftMax和RightMax作为左右最大值，从两边向中间遍历，优先移动较小侧的指针，如果遇到小于Max的值，则取Max-height[i]加入结果值。
        int n = height.length;
        int left = 0, right = n-1;
        int leftMax = 0, rightMax = 0;
        int ans = 0;
        while (left < right){
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            if (height[left] < height[right]) {
                ans += leftMax-height[left];
                left++;
            }else{
                ans += rightMax-height[right];
                right--;
            }
        }
        return ans;
    }

    //3 无重复字符的最长子串
    //给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串的长度。
    public int lengthOfLongestSubstring(String s) {
        //设置滑动窗口，从左侧开始，移动右指针，将值放入set，直到遇到重复字符；此时移动左指针
        Set<Character> set = new HashSet<>();
        int right = 0;
        int subLength = 0;
        for (int left = 0; left < s.length(); left++) {
            if (left != 0){
                set.remove(s.charAt(left-1));
            }
            while (right < s.length() && !set.contains(s.charAt(right))){
                set.add(s.charAt(right));
                right++;
            }
            subLength = Math.max(right-left, subLength);
        }
        return subLength;
    }

    //438 找到字符串中所有字母异位词
    // 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
    public List<Integer> findAnagrams(String s, String p) {
        //创建两个数组存放各字母出现的次数，先把p和s的前p.length()个放入，作为滑动窗口，依次迭代比较数组值
        int[] pc = new int[26];
        int[] sc = new int[26];
        List<Integer> list = new ArrayList<>();
        if (p.length() > s.length()) return list;
        for (int i = 0; i < p.length(); i++) {
            ++pc[p.charAt(i) - 'a'];
            ++sc[s.charAt(i) - 'a'];
        }
        if (Arrays.equals(pc, sc)) list.add(0);
        for (int i = p.length(); i < s.length(); i++) {
            ++sc[s.charAt(i) - 'a'];
            --sc[s.charAt(i-p.length()) - 'a'];
            if (Arrays.equals(pc, sc)) list.add(i-p.length()+1);
        }
        return list;
    }

    //560 和为K的子数组
    //给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。
    //子数组是数组中元素的连续非空序列。
    public int subarraySum(int[] nums, int k) {
        //前缀和
        Map<Integer, Integer> map = new HashMap<>();
        int pre = 0;
        int n = 0;
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            pre += nums[i];
            if (map.containsKey(pre - k)){
                n += map.get(pre - k);
            }
            map.put(pre, map.getOrDefault(pre, 0) + 1);
        }
        return n;
    }

    //76. 最小覆盖子串
    //给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
    public String minWindow(String s, String t) {
        //map集合，存放t的字符及其数量；创建滑动窗口，右指针的指向的字符在map集合中的value-1，matcher方法是判断是否有小于0的值，这样不需要考虑其他t不包含的其他字符；
        //当matcher为true时，记录左指针位置和长度，再移动左指针，直到matcher为false，
        int left = 0, right = 0;
        int minL = Integer.MAX_VALUE, index = 0;
        HashMap<Character, Integer> t_hm = new HashMap<>();
        for (char c : t.toCharArray()) {
            t_hm.put(c, t_hm.getOrDefault(c, 0) + 1);
        }
        while (right < s.length()) {
            if (t_hm.containsKey(s.charAt(right))) {
                t_hm.put(s.charAt(right), t_hm.get(s.charAt(right)) - 1);
            }
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
        return minL == Integer.MAX_VALUE ? "" : s.substring(index, index + minL);
    }
    private static boolean matcher(HashMap<Character, Integer> t) {
        for (int value : t.values()) {
            if (value > 0) {
                return false;
            }
        }
        return true;
    }

    //53 最大子数组和
    //给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。子数组是数组中的一个连续部分。
    public int maxSubArray(int[] nums) {
       //对每一个元素i来说，f(i)是以i为结尾的最大值。判断f(i)时只需要看num[i]和f(i-1)的值即可
        int temp = 0, sum = nums[0];
        for (int i = 0; i < nums.length; i++) {
            temp = Math.max(nums[i], nums[i]+temp);
            sum = Math.max(sum, temp);
        }
        return sum;
    }

    //56. 合并区间
    //以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
    // 请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
    public int[][] merge(int[][] intervals) {
        //重写sort方法将数组排序，再将有重叠的数组合并并放入list，最后返回数组
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            int start = intervals[i][0];
            int end = intervals[i][1];
            if (list.size() == 0 || list.get(list.size()-1)[1] < start){
                list.add(new int[]{start, end});
            }else {
                list.get(list.size()-1)[1] = Math.max(list.get(list.size()-1)[1], end);
            }
        }
        return list.toArray(new int[list.size()][]);
    }

    //189. 轮转数组
    //给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数
    public void rotate(int[] nums, int k) {
        //1. while循环k次，每次右移一位
        //2. 创建新数组，再赋值
        //3. 环形替换，
        //赋值：
        int n = nums.length;
        k = k % n;
        int[] r = new int[n];
        for (int i = 0; i < n; i++) {
            r[i] = nums[(n-k+i) % n];
        }
        System.arraycopy(r, 0, nums,0, n);
    }

    //238. 除自身以外数组的乘积
    //给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积 。
    //题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内。
    //请 不要使用除法，且在 O(n) 时间复杂度内完成此题。
    public int[] productExceptSelf(int[] nums) {
        //创建左乘积和右乘积数组，answer[i]=left[i]*right[i]
        int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];
        int L = 1;
        int R = 1;
        int[] answer = new int[n];
        for (int i = 0; i < n; i++) {
            left[i] = L;
            L *= nums[i];
        }
        for (int i = n-1; i >= 0; i--) {
            right[i] = R;
            R *= nums[i];
        }
        for (int i = 0; i < n; i++) {
            answer[i] = left[i] * right[i];
        }
        return answer;
    }

    //41. 缺失的第一个正数
    //给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
    //请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
    public int firstMissingPositive(int[] nums) {
        //对于一个长度为 N 的数组，其中没有出现的最小正整数只能在 [1,N+1] 中。
        //将数组的值本身当作索引将其在数组中对应的值取负，作为标记
        //最后遍历数组，第一个正值的索引+1就是题目所求
        int n = nums.length;
        //首先将非正数修改为n+1
        for (int i = 0; i < n; i++) {
            if (nums[i] <= 0) nums[i] = n+1;
        }
        //将对应索引变为负值
        for (int i = 0; i < n; i++) {
            int temp = Math.abs(nums[i]);
            if (temp < n) {
                nums[temp-1] = -Math.abs(nums[temp-1]);
            }
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) return i+1;
        }
        return n+1;
    }













}
