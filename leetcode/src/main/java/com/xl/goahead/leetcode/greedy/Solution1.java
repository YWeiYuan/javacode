/*
 * Copyright 2012-2022
 */
package com.xl.goahead.leetcode.greedy;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 455 分发饼干
 * https://leetcode-cn.com/problems/assign-cookies/
 * 假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。
 * 对每个孩子 i，都有一个胃口值 g[i]，这是能让孩子们满足胃口的饼干的最小尺寸；并且每块饼干 j，都有一个尺寸 s[j]。
 * 如果 s[j]>= g[i]，我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。
 *
 * @author longquan.huang
 * @version 1.0
 * @date 2021/5/25 5:23 下午
 */
public class Solution1 {
    public static void main(String[] args) {

    }

    /**
     *解题思路：
     * 贪心策略：剩余孩子里胃口最小的孩子分配最小的饼干
     * 1，由于题目未提及顺序，所以先将胃口和饼干大小排序（因为测试用例有没排序的，被坑了）
     * 2，孩子全部喂饱或者饼干用尽，循环结束
     * 3，因为已经排序，所以只有饼干大于等于孩子，此孩子吃饱，孩子++
     * 4，每次循环都会使用（判断）一个饼干，不论是否喂得饱，饼干++
     * @param g
     * @param s
     * @return
     */
    public int findContentChildren(int[] g, int[] s) {
        if (null == g || null == s) {
            return 0;
        }
        // 排序
        Arrays.sort(g);
        Arrays.sort(s);
        //
        for (int i : s) {

        }




        return 1;
    }

}
