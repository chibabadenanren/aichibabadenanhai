package bike.endity;

import lombok.ToString;

/**
 * @author :林富豪
 * @version :v.1
 * @className：OfoBike
 * @description:
 * @date:2021/12/7 19:42
 * @since :jdk1.8
 */
public class OfoBike extends SharedBike {
    public OfoBike(int bld, String bikeName, int status, String borrowTime) {
        super(bld, bikeName, status, borrowTime);
    }

    public OfoBike() {
    }
}
