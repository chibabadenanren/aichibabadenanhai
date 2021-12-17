package bike;


import bike.endity.SharedBike;
import lombok.*;


/**
 * @author :林富豪
 * @version :v.1
 * @className：SharedBike
 * @description:
 * @date:2021/12/7 19:46
 * @since :jdk1.8
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class SharedBikeCompany implements Comparable<SharedBikeCompany>{
    private int companyId;//公司id
    private String companyName;//单车品牌
    private SharedBike[] sharedBikes;//公司持有共享单车
    private int sum;//公司单车总量
    private int count;//公司单车借出次数

    @Override
    public String toString() {
        return "SharedBikeCompany{" +
                "companyId=" + companyId +
                ", companyName='" + companyName + '\'' +
                ", sharedBikes=" + sharedBikes.length+
                ", sum=" + sum +
                ", count=" + count +
                '}';
    }

    @Override
    public int compareTo(SharedBikeCompany o) {
        return o.getCount()-this.getCount();
    }
}
