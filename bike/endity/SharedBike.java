package bike.endity;

import bike.CodeEnum;
import lombok.*;

/**
 * @author :林富豪
 * @version :v.1
 * @className：SharedBike
 * @description:
 * @date:2021/12/7 19:40
 * @since :jdk1.8
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public  class SharedBike {
    private int bld;//单车编号
    private String bikeName;//单车名称
    private int status;//单车状态
    private String borrowTime;//单车借出时间


    @Override
    public String toString() {
        return "SharedBike{" +
                "bld=" + bld +
                ", bikeName='" + bikeName + '\'' +
                ", status=" + ((status==CodeEnum.BORROWED.getCode())?CodeEnum.BORROWED.getMsg():CodeEnum.CAN_BORROW.getMsg())+
                ", borrowTime='" + borrowTime + '\'' +
                '}';
    }


}
