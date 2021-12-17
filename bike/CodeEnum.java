package bike;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author :林富豪
 * @version :v.1
 * @className：CodeEnum
 * @description:
 * @date:2021/12/7 20:13
 * @since :jdk1.8
 */
@Getter
@AllArgsConstructor
@ToString
public enum CodeEnum {
    CAN_BORROW(1,"可借"),
    BORROWED(0,"已借出");
    private final int code;
    private final String msg;

}
