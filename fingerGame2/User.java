package com.lin.fingerGame2;

import java.util.Scanner;

/**
 * @author :林富豪
 * @version :v.1
 * @className：User
 * @description:
 * @date:2021/12/1 17:51
 * @since :jdk1.8
 */
public class User {
    Scanner input = new Scanner(System.in);
    int userNumber;
    public void user() {
        System.out.print("请出拳：1.剪刀 2.石头 3.布（输入相应的数字）");
        userNumber = input.nextInt();

        switch (userNumber) {
            case 1:
                System.out.println("你出拳：剪刀");
                break;
            case 2:
                System.out.println("你出拳：石头");
                break;
            case 3:
                System.out.println("你出拳：布");
                break;
        }

    }

    //选择对战角色
    String name;

    public void xuanjuese() {
        System.out.print("请选择对战角色1：刘备 2:孙权 3:张飞");
        int juese = input.nextInt();
        switch (juese) {
            case 1:
                name = "刘备";
                break;
            case 2:
                name = "孙权";
                break;
            case 3:
                name = "张飞";
                break;
        }
        System.out.println("你选择了" + name + "对战");
        System.out.println();

    }

    //机器人随机出拳
    int suiji;
    public void com() {

        suiji = (int) (Math.random() * 3 + 1);
        switch (suiji) {
            case 1:
                System.out.println(name + "出拳：剪刀");
                break;
            case 2:
                System.out.println(name + "出拳：石头");
                break;
            case 3:
                System.out.println(name + "出拳：布");
                break;
        }
    }


    //比较人和电脑谁嬴
    int comFenShu=0;
    int userFenShu=0;
    public void showFist(){
        if ((userNumber==1&&suiji==2)||(userNumber==2&&suiji==3)||(userNumber==3&&suiji==1)){
            System.out.println(name+"赢了");
            comFenShu++;
            return;
        }else if(userNumber==suiji){
            System.out.println("真晦气，平局了！！！！！");
            return;
        }
        System.out.println("你赢了");
        userFenShu++;
    }
}
