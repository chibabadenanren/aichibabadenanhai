package bike.endity;

import lombok.ToString;

/**
 * @author :林富豪
 * @version :v.1
 * @className：HaloBike
 * @description:
 * @date:2021/12/7 19:44
 * @since :jdk1.8
 */
public class HaloBike extends SharedBike {
    public HaloBike(int bld, String bikeName, int status, String borrowTime) {
        super(bld, bikeName, status, borrowTime);
    }

    public HaloBike() {
    }
}
