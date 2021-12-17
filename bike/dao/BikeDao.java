package bike.dao;

import bike.SharedBikeCompany;

import java.util.Scanner;

/**
 * @author :林富豪
 * @version :v.1
 * @className：BikeDao
 * @description:
 * @date:2021/12/7 19:49
 * @since :jdk1.8
 */
public interface BikeDao {
    //定义方法 查看单车信息
    void findAllBikeInfo(SharedBikeCompany[] companies);

    void putBike(SharedBikeCompany company, int putNum);

    void deleteBikeId(SharedBikeCompany company, Scanner input);

    void borrowBikeMenu(SharedBikeCompany company, Scanner input);

    void returnBikeMenu(SharedBikeCompany company, Scanner input);

}
