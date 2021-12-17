package bike;


import bike.dao.BikeDao;
import bike.dao.impl.BikeDaoImpl;
import bike.endity.HaloBike;
import bike.endity.MoBaiBike;
import bike.endity.OfoBike;
import bike.endity.SharedBike;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author :林富豪
 * @version :v.1
 * @className：Star
 * @description:
 * @date:2021/12/7 19:28
 * @since :jdk1.8
 */
public class Star {
    private static Scanner input;
    private static BikeDao bikeDao;
    private static SharedBikeCompany[] companies;
    public static int ofoId = 1000;
    public static int haloId = 2000;
    public static int mobaiId = 3000;

    static {
        input = new Scanner(System.in);
        bikeDao = new BikeDaoImpl();
        companies = new SharedBikeCompany[3];
        SharedBike[] bikes1 = new OfoBike[3];
        SharedBike[] bikes2 = new HaloBike[3];
        SharedBike[] bikes3 = new MoBaiBike[3];
        int code = CodeEnum.CAN_BORROW.getCode();
        for (int i = 0; i < 3; i++) {
            bikes1[i] = new OfoBike(ofoId, "ofo单车"+ofoId++, code, "");
            bikes2[i] = new HaloBike(haloId, "halo单车"+haloId++, code, "");
            bikes3[i] = new MoBaiBike(mobaiId, "mobai单车"+mobaiId++, code, "");
        }
        companies[0] = SharedBikeCompany.builder().companyId(1).companyName("Ofo单车公司").count(100).sharedBikes(bikes1).sum(3).build();
        companies[1] = SharedBikeCompany.builder().companyId(1).companyName("halo单车公司").count(100).sharedBikes(bikes2).sum(3).build();
        companies[2] = SharedBikeCompany.builder().companyId(1).companyName("mobai单车公司").count(100).sharedBikes(bikes3).sum(3).build();
    }

    public void man() {
        String answer;
        do {
            System.out.println("欢迎使用迷你共享单车管理系统");
            System.out.println("*****************");
            System.out.println("**  1.投放Bike  **");
            System.out.println("**  2.查看Bike  **");
            System.out.println("**  3.删除Bike  **");
            System.out.println("**  4.借出Bike  **");
            System.out.println("**  5.归还Bike  **");
            System.out.println("**  6.Bike排行榜  **");
            System.out.println("**    7.退出            **");
            System.out.println("*****************");
            System.out.print("请选择：");
            int chioce = input.nextInt();
            switch (chioce) {
                case 1:
                    System.out.println("---->1.投放Bike");
                    putBikeMenu();
                    break;
                case 2:
                    System.out.println("---->2.查看Bike");
                    bikeDao.findAllBikeInfo(companies);
                    break;
                case 3:
                    System.out.println("---->3.删除Bike");
                    deleteBikeMenu();
                    break;
                case 4:
                    System.out.println("---->4.借出Bike");
                    borrowBikeMenu();
                    break;
                case 5:
                    System.out.println("---->5.归还Bike");
                    returnBikeMenu();
                    break;
                case 6:
                    System.out.println("---->6.Bike排行榜");
                    rankMenu();
                    break;
                case 7:
                    input.close();
                    System.out.println("程序退出");
                    return;
            }
            System.out.println("是否继续？：Y/N");
            answer = input.next();
        } while ("y".equals(answer));
        System.out.println("程序结束");
        input.close();
    }

    private void rankMenu() {
        SharedBikeCompany[] copyOf = Arrays.copyOf(companies, companies.length);
        Arrays.sort(copyOf);
        System.out.println("公司名称\t借车次数");
        for (SharedBikeCompany Company : copyOf) {
            System.out.println(Company.getCompanyName()+"\t"+Company.getCount());
        }
    }

    private void returnBikeMenu() {
        int companyId = chooseCompany();
        bikeDao.returnBikeMenu(companies[companyId-1],input);
    }

    private void deleteBikeMenu() {
        int companyId = chooseCompany();
        bikeDao.deleteBikeId(companies[companyId-1],input);
    }

    private void putBikeMenu() {
        int companyId = chooseCompany();
        System.out.println("请录入要投入单车的数量");
        int putNum=input.nextInt();
        bikeDao.putBike(companies[companyId-1],putNum);
    }

    private int chooseCompany() {
        for (SharedBikeCompany company : companies) {
            System.out.println(company.getCompanyId() + ":" + company.getCompanyName());
        }
        System.out.println("请选择要操作的单车的品牌");
        return input.nextInt();
    }
    private void borrowBikeMenu(){
        int companyId = chooseCompany();
        bikeDao.borrowBikeMenu(companies[companyId-1],input);
    }
}
