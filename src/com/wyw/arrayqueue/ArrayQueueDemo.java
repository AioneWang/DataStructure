package com.wyw.arrayqueue;

import java.util.Scanner;

public class ArrayQueueDemo {
    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(3);
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("=======================");
            System.out.println("a(add):元素入队");
            System.out.println("g(get):元素出队");
            System.out.println("l(list):获取所有元素");
            System.out.println("h(head):获取第一个二元素");
            System.out.println("e(exit):退出程序");
            key = scanner.next().charAt(0);//接收一个字符
            switch (key) {
                case 'l':
                    queue.listQueue();
                    break;
                case 'a':
                    int val = scanner.nextInt();
                    queue.addQueue(val);
                    break;
                case 'g':
                    try {
                        int getVal = queue.getQueue();
                        System.out.println(getVal);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int headVal = queue.headQueue();
                        System.out.println(headVal);
                    } catch (Exception e) {
                        e.getMessage();
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    System.out.println("程序退出~~");
            }
        }


    }


}

class ArrayQueue {
    private int maxSize;
    private int front;//指向队首的前一个位置
    private int rear;//指向队尾
    private int[] arr;//存储队列数据

    //构造函数,初始化队列
    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        this.front = -1;
        this.rear = -1;
        this.arr = new int[maxSize];
    }

    //队列是否为空
    public boolean isEmpty() {
        return rear == front;
    }

    //队列是否为满
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    //元素入队
    public void addQueue(int n) {
        if (isFull()) {
            System.out.println("队列满，无法入队");
        }
        rear++;
        arr[rear] = n;
    }

    //元素出队
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列空，无法出队");
        }
        front++;
        return arr[front];
    }

    //显示所有元素
    public void listQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列空，没有元素");

        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]:%d\n", i, arr[i]);
        }
    }

    //获取队首数据
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队空，无法获得队首数据");//注意是获得数据并不是出队
        }
        return arr[front + 1];//front并没有改变
    }

}