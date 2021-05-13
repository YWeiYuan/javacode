/*
 * Copyright 2012-2022
 */
package com.xl.goahead.algs4;


import lombok.Data;

/**
 * 判断链表是否有环的方法
 *
 * @author longquan.huang
 * @version 1.0
 * @date 2021/5/12 10:40 下午
 */
public class LoopLink {

    @Data
    public static class Node{
        public Node(Integer value) {
            this.value = value;
            this.next = null;
        }
        private Node next;
        private Integer value;
    }

    /**
     * 声明两个指针，一个指针走一次经过两个节点(快指针quick)，另一个走一次经过一个节点(慢指针slow)
     * 方法说明：快指针走的比较快，若链表有环，则一定会追上慢指针，若无环，则会走到链表末端。
     * @param node 链表起点
     * @return 是否有环
     */
    public static boolean hasLoop(Node node){
        //声明两个节点从头开始遍历节点
        Node slow = node;
        Node quick = node;
        //当快指针能够走到头表示无环
        while (quick != null && quick.next != null) {
            quick = quick.next.next;
            slow = slow.next;
            // 快跟慢能相遇，就说明 形成了闭环
            if (slow == quick) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);
        node4.setNext(node5);
        node5.setNext(node4);
        System.out.println(hasLoop(node1));
    }

}
