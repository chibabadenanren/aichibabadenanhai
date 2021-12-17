package com.lin.fingerGame2;

import java.util.Scanner;

/**
 * @author :林富豪
 * @version :v.1
 * @className：Game
 * @description:
 * @date:2021/12/1 20:00
 * @since :jdk1.8
 */
public class Game {
    Scanner input = new Scanner(System.in);
    User user = new User();

    public void startGame(){
        System.out.println("     ********************");
        System.out.println("     **    猜拳，开始    **");
        System.out.println("     ********************");
        System.out.println("出拳规则：1.剪刀 2.石头 3.布");
        user.xuanjuese();
        String s;
        do {


            user.user();
            user.com();
            user.showFist();
            System.out.println();
            System.out.print("是否开始下一轮（y/n）");
            s=input.next();
            System.out.println();
        } while (s.equals("y"));

        System.out.println(" "+user.name+"\t\tvs"+" \t你");
        System.out.print(user.name+"得分:"+user.comFenShu);
        System.out.print("\t\t");
        System.out.println("你得分"+user.userFenShu);
        if (user.userFenShu>user.comFenShu){
            System.out.println("你的技术吊炸天了！！！！！！！！！！");
            return;
        }else if (user.userFenShu==user.comFenShu){
            System.out.println("好家伙你俩不相上下！！！");
            return;
        }
        System.out.println(user.name+"赢了！ 你不太行多练练！！！");
    }


}
