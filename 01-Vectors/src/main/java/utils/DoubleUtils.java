package utils;

/**
 * Double 工具类
 * @author xuanweiyao
 * @date 22:58 2019/6/21
 */
public class DoubleUtils {
    /**
     * 判断double是否没小数，是的话，返回int，否则返回自身
     * @author show
     * @param d 入参
     * @return java.lang.Object
     */
    public static Object doubleByInt(Double d) {

        if (d % 1 == 0) {
            return d.intValue();
        } else {
            return d;
        }

    }
}
