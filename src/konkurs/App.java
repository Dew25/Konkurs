/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konkurs;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author jvm
 */
class App {
    private int numGamers;
    
    public void run(){
        System.out.print("Количество игроков: ");
        Scanner sc = new Scanner(System.in);
        double dNum = 0;
        int num = 0;
        Random r = new Random();
        this.numGamers = sc.nextInt();
        double[] sum = new double[numGamers];
        sc.nextLine();
        double[][] myArray = new double[numGamers][numGamers];
        for (int i = 0; i < numGamers; i++) {
            for (int j = 0; j < numGamers; j++) {
                myArray[i][j] = 2;
                if(i == j) myArray[i][j] = 3;
            }
        }
        for (int i = 0; i < numGamers; i++) {
            for (int j = 0; j < numGamers; j++) {
                num = r.nextInt(3);
                if(0 == num) dNum = 0;
                if(1 == num) dNum = 1;
                if(2 == num) dNum = 0.5;
                if(i == j){
                    continue;
                }else if(0.5 == dNum){
                    myArray[i][j] = dNum;
                    myArray[j][i] = dNum;
                }else if(myArray[i][j] == 2){
                    if(0 == dNum){
                        myArray[i][j] = dNum;
                        myArray[j][i] = dNum+1;
                    }else if(1 == dNum){
                        myArray[i][j] = dNum;
                        myArray[j][i] = dNum-1;
                    }
                }
                   
            }
            
        }
        
        for (int i = 0; i < myArray.length; i++) {
            for (int j = 0; j < myArray[i].length; j++) {
               sum[i] += myArray[i][j];
            }
            sum[i]=sum[i]-3;
        }
        System.out.println("Несортированный список участников:");
        for (int i = 0; i < myArray.length; i++) {
            this.printNumber(i,true);
            for (int j = 0; j < myArray[i].length; j++) {
                if(myArray[i][j] == 3){
                    System.out.print(" X   ");; 
                }else{
                    System.out.printf("%-5.1f",myArray[i][j]);
                }
            }
//            System.out.printf("= %-3.1f",sum[i]);
            this.printSum(sum[i]);
            System.out.println();
            
        }
        System.out.println("Сортированный список участников по убыванию:");
        this.sortArrayForSum(myArray, sum);
        this.printArray(myArray, sum, false);
        
        System.out.println("Сортированный список участников по возрастанию:");
        this.printArray(myArray, sum, true);
    }
    //Выводит пронумерованный список результатов и сумму баллов
    private void printArray(double[][] array,double[] sum, boolean abc){
        if(abc){
            for (int i = 0; i < array.length; i++) {
                this.printNumber(i,true);
                for (int j = 0; j < array[i].length; j++) {
                    if(array[i][j] == 3.0){
                        System.out.print(" X   ");; 
                    }else{
                        System.out.printf("%-5.1f",array[i][j]);
                    }
                }
//                System.out.printf("= %-3.1f",sum[i]);
                this.printSum(sum[i]);
                System.out.println();

            }
        }else{
            for (int i = array.length-1; i >= 0; i--) {
                this.printNumber(i,false);
                for (int j = 0; j < array[i].length; j++) {
                    if(array[i][j] == 3.0){
                        System.out.print(" X   ");; 
                    }else{
                        System.out.printf("%-5.1f",array[i][j]);
                    }
                }
                //System.out.printf("= %3.1f",sum[i]);
                this.printSum(sum[i]);
                System.out.println();
            }
        }
    }
    //сортирует массив по строчно (по индексу i), т.е. меняет строчки списка
    private void sortList(double[][] array, int i){
        for (int j = 0; j < array[i].length-1; j++) {
            if(i < array.length){
                array[i][j] = array[i][j] + array[i+1][j];
                array[i+1][j] = array[i][j] - array[i+1][j];
                array[i][j] = array[i][j] - array[i+1][j];
            }
        }
    }
    private void sortArrayForSum(double[][] array, double[] sum){
        for (int i = 0; i < sum.length; i++) {
            for (int j = 0; j < sum.length; j++) {
                if(j+1 < sum.length){
                    if(sum[j] > sum[j+1]){
                        sum[j] = sum[j] + sum[j+1];
                        sum[j+1] = sum[j] - sum[j+1];
                        sum[j] = sum[j] - sum[j+1];
                        this.sortList(array, j);
                    }
                }
            }
        }
    }

    private void printNumber(int i, boolean abc) {
        if(abc){
            if(i < 10){
                System.out.printf("%2d. ",1+i);
            }else if(i > 9 && i < 100){
                System.out.printf("%d. ",1+i);
            }
        }else{
            if(numGamers - i < 10){
                System.out.printf("%2d. ",numGamers - i);
            }else if(numGamers - i > 9 && numGamers - i < 100){
                System.out.printf("%d. ",numGamers - i);
            }
        }
    }
    private void printSum(double sum) {
        if(sum < 10.0){
            System.out.printf("=  %-2.1f",sum);
        }else if(sum > 9.0 && sum < 100.0){
            System.out.printf("= %-2.1f",sum);
        }
    }
}
