package com.wyw.sparsearray;

import java.io.*;
import java.util.ArrayList;

public class SparseArray {
    public static void main(String[] args) throws IOException {
        //1.原始二维数组
        int chessOri[][] = new int[11][11];
        chessOri[1][2] = 1;
        chessOri[2][3] = 2;
        chessOri[4][8] = 1;
        System.out.println("原始的二维数组：");
        for (int row[] : chessOri) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println("\n");
        }

        //2.创建稀疏数组[sum+1][3]
        //算一下非0的个数
        int sum = 0;
        for (int row[] : chessOri) {
            for (int data : row) {
                if (data != 0) {
                    sum++;
                }
            }
        }
//        System.out.println("sum:" + sum);

        //创建稀疏数组
        int count = 0;
        int sparseArray[][] = new int[sum + 1][3];
        sparseArray[0][0] = chessOri.length;
        sparseArray[0][1] = chessOri.length;
        sparseArray[0][2] = sum;
        for (int i = 0; i < chessOri.length; i++) {
            for (int j = 0; j < chessOri.length; j++) {
                if (chessOri[i][j] != 0) {
                    count++;
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = chessOri[i][j];
                }
            }
        }
        System.out.println("稀疏矩阵：");
        for (int[] ints : sparseArray) {
            for (int anInt : ints) {
                System.out.printf("%d\t", anInt);
            }
            System.out.println();

        }

        //3.将稀疏数组存到磁盘
        File file = new File("E:\\自己写的代码\\DS&A\\shangguiguDataStructure\\data.txt");
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            for (int[] ints : sparseArray) {
                for (int anInt : ints) {
                    fileWriter.write(anInt + ",");
                }
                fileWriter.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();//关闭文件
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //4.从磁盘读取稀疏矩阵
        int cols = 3;
        int rows = 0;//存放读入矩阵的行数
        int array[][] = new int[rows][cols];//存放读入的矩阵
        ArrayList arrayList = new ArrayList<>();
        FileReader fileReader = null;//文件流
        BufferedReader bufferedReader = null;//缓冲流
        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            String str;
            //遍历读取到行数，将每一行作为一个字符串放到str中
            while ((str = bufferedReader.readLine()) != null) {
                rows++;
                arrayList.add(str);
            }
            array = new int[rows][3];//确定稀疏矩阵规模
            //从arrayList中读取
            int curLine = 0;
            for (Object o : arrayList) {//遍历每一行
                String s = (String) o;
                String sArray[] = s.split(",");//每一行用，分割成数组
                //对每一行操作
                for (int i = 0; i < sArray.length; i++) {
                    array[curLine][i] = Integer.parseInt(sArray[i]);
                }
                curLine++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            bufferedReader.close();//关闭文件
            fileReader.close();
        }

        /*for (int[] ints : array) {
            for (int anInt : ints) {
                System.out.printf("%d\t", anInt);
            }
            System.out.println();
        }*/

        //5.稀疏数组转二维数组
        int chessOriAfter[][] = new int[array[0][0]][array[0][1]];//默认值0
        for (int i = 1; i < sparseArray.length; i++) {
            int row = array[i][0];
            int col = array[i][1];
            int val = array[i][2];
            chessOriAfter[row][col] = val;
        }
        System.out.println("chessOriAfter:");
        for (int[] ints : chessOriAfter) {
            for (int anInt : ints) {
                System.out.printf("%d\t", anInt);
            }
            System.out.println(11);
        }
    }
}
