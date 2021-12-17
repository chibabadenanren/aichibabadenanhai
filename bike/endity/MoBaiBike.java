package bike.endity;

import lombok.ToString;

/**
 * @author :林富豪
 * @version :v.1
 * @className：MoBaiBike
 * @description:
 * @date:2021/12/7 19:43
 * @since :jdk1.8
 */
public class MoBaiBike extends SharedBike {
    public MoBaiBike(int bld, String bikeName, int status, String borrowTime) {
        super(bld, bikeName, status, borrowTime);
    }

    public MoBaiBike() {
    }
}
