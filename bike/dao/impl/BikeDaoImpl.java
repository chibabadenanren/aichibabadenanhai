package bike.dao.impl;


import bike.CodeEnum;
import bike.SharedBikeCompany;
import bike.Star;
import bike.dao.BikeDao;
import bike.endity.HaloBike;
import bike.endity.MoBaiBike;
import bike.endity.OfoBike;
import bike.endity.SharedBike;
import sun.security.util.Length;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;


/**
 * @author :林富豪
 * @version :v.1
 * @className：BikeDaoImpl
 * @description:
 * @date:2021/12/7 19:53
 * @since :jdk1.8
 */
public class BikeDaoImpl implements BikeDao {
    @Override
    public void findAllBikeInfo(SharedBikeCompany[] companies) {
        for (SharedBikeCompany company : companies) {
            System.out.println("+---------+--------+---------——+--------------+");
            System.out.println("| 公司序号 | 公司名称 | 公司单车数量 | 公司单车借出次数 |");
            System.out.println("+---------+--------+--------——-+----------——--+");
            System.out.println(company);


            getBikeInfo(company);
            System.out.println();
        }
    }

    private void getBikeInfo(SharedBikeCompany company) {
        System.out.println("单车序号\t单车品牌\t单车状态\t借出时间");
        SharedBike[] sharedBikes = company.getSharedBikes();
        for (int index = 0; index < company.getSum(); index++) {
            System.out.println(sharedBikes[index].toString());
        }
    }

    @Override
    public void putBike(SharedBikeCompany company, int putNum) {
        SharedBike[] bikes = company.getSharedBikes();
        int companyId = company.getCompanyId();
        int code = CodeEnum.CAN_BORROW.getCode();
        int sum = company.getSum();

        //判断剩余空间是否足够
        int length = bikes.length;
        if (putNum>(length-sum)){
            bikes=Arrays.copyOf(bikes,(length+putNum)*2);
        }

        for (int i = 1; i <= putNum; i++) {
            SharedBike newBike=null;
            switch (companyId){
                case 1:
                    newBike=new OfoBike(Star.ofoId, "ofo单车"+Star.ofoId++, code, "");
                    break;
                case 2:
                    newBike=new HaloBike(Star.haloId, "halo单车"+Star.haloId++, code, "");
                    break;
                case 3:
                    newBike=new MoBaiBike(Star.mobaiId, "mobai单车"+Star.mobaiId++, code, "");
                    break;
            }
            bikes[sum++]=newBike;
        }
        company.setSum(sum);
        company.setSharedBikes(bikes);
        System.out.println("投放"+putNum+"辆车到<<"+company.getCompanyName()+">>成功");
    }

    @Override
    public void deleteBikeId(SharedBikeCompany company, Scanner input) {
        String companyName = company.getCompanyName();
        System.out.println("<<"+companyName+">>单车信息如下：");
        getBikeInfo(company);
        System.out.println("请录入要删除的单车的编号：");
        int bikeId = input.nextInt();

        //找到单车的索引位置
        SharedBike[] bikes = company.getSharedBikes();
        int sum = company.getSum();
        for (int index = 0; index < sum; index++) {
            SharedBike bike =bikes[index];
            if (bike.getBld()==bikeId){
                //找到了
                if (bike.getStatus()==CodeEnum.BORROWED.getCode()){
                    System.out.println("单车id为："+bikeId+"已经借出");
                    return;
                }
                System.arraycopy(bikes, index + 1, bikes, index, sum - 1 - index);
                bikes[--sum]=null;
                break;
            }
        }
        System.out.println("删除单车id<<"+bikeId+">>成功了");
        company.setSum(sum);
    }

    @Override
    public void borrowBikeMenu(SharedBikeCompany company, Scanner input) {
        String companyName = company.getCompanyName();
        System.out.println("<<"+companyName+">>单车信息如下：");
        getBikeInfo(company);
        System.out.println("请录入要借出的单车的编号：");
        int bikeId = input.nextInt();


        SharedBike[] bikes = company.getSharedBikes();
        int sum = company.getSum();
        for (int index = 0; index < sum; index++) {
            SharedBike bike =bikes[index];
            if (bike.getBld()==bikeId){
                //找到了
                if (bike.getStatus()==CodeEnum.BORROWED.getCode()){
                    System.out.println("单车id为："+bikeId+"已经借出  无法再被借出 ");
                    return;
                }
                System.out.println("请输入借车的时间：(yyyy-MM-dd HH:mm:ss)");
                input.nextLine();
                bike.setBorrowTime(input.nextLine());

                bike.setStatus(CodeEnum.BORROWED.getCode());
                break;
            }
        }
        System.out.println("借出单车id<<"+bikeId+">>成功了");
        company.setCount(company.getCount()+1);
    }

    @Override
    public void returnBikeMenu(SharedBikeCompany company, Scanner input) {
        String companyName = company.getCompanyName();
        System.out.println("<<"+companyName+">>单车信息如下：");
        getBikeInfo(company);
        System.out.println("请录入要归还的单车编号：");
        int bikeId = input.nextInt();


        SharedBike[] bikes = company.getSharedBikes();
        int sum = company.getSum();
        for (int index = 0; index < sum; index++) {
            SharedBike bike =bikes[index];
            if (bike.getBld()==bikeId){
                //找到了
                if (bike.getStatus()==CodeEnum.CAN_BORROW.getCode()){
                    System.out.println("单车id为："+bikeId+"未借出  无法归还 ");
                    return;
                }
                System.out.println("请输入还车的时间：(yyyy-MM-dd HH:mm:ss)");
                input.nextLine();
                String backTime = input.nextLine();
                String borrowTime = bike.getBorrowTime();
                System.out.println("你的接车时间："+borrowTime);
                System.out.println("需要支付"+getMoney(backTime,borrowTime)+"元");
                bike.setStatus(CodeEnum.CAN_BORROW.getCode());
                bike.setBorrowTime("");
                break;
            }
        }
        System.out.println("归还单车id<<"+bikeId+">>成功了");
    }

    private long getMoney(String backTime, String borrowTime) {
            //1. 字符串类型数据 ----> java.util.Date  -----> 数值类型的数据

            //1.1 字符串转成java.util.Date  (java.text.SimpleDateFormat) 必须指定pattern格式
            //将一个什么格式字符串的日期数据转换成Date对象
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date borrowDate = null;
            Date backDate = null;
            try {
                borrowDate = dateFormat.parse(borrowTime);
                backDate = dateFormat.parse(backTime);
            } catch (ParseException e) {
                //指定的pattern格式数据与传过来字符串日期的数据不一致
                //e.printStackTrace();
                //Caused By: 控制台很多报错信息  想排查产生问题的根本原因是什么? 看Caused By
                //必须异常信息传递
                //1. 后面开发  分层开发  dao--> service---> controller (统一的全局异常处理)
                //2. 级别高的对象与级别低异常对象直接相互传递  低-->高 父与子  throw
                try {
                    throw new Exception(e);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
            //1.2 Date转换成数值类型的数据
            long time1 = borrowDate.getTime();
            long time2 = backDate.getTime();
            return Math.abs(time1 - time2) / 1000 / 3600;
    }
}
