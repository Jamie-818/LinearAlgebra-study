import exception.ZeroDivisionError;
import model.Vector;

/**
 * 向量测试类
 * @author show
 */
public class MainVector {
    /** 向量初始化*/
    /** 二维向量：(5,2)*/
    private static Vector vec1 = new Vector(5, 2);
    /** 二维向量：(3,1)*/
    private static Vector vec2 = new Vector(3, 1);
    /** 二维零向量：(0,0)*/
    private static Vector zero2 = new Vector(2);

    /**
     * 向量的基本运算及零算量的验证
     * @author show
     */
    private static void vector1() {
        // 向量初始化
        Vector vec1 = new Vector(5, 2);
        Vector vec2 = new Vector(3, 1);
        // 打印向量
        System.out.println(vec1.print());
        // 向量维度
        System.out.println(vec1.length());
        // 向量坐标值
        System.out.println("vec1[0]=" + vec1.getItem(0) + ",vec1[1]=" + vec1.getItem(1));
        //向量相加(5,2)+(3,1)
        System.out.println(vec1.print() + "+" + vec2.print() + "=" + Vector.add(vec1, vec2).print());
        //向量相减 (5,2)-(3,1)
        System.out.println(vec1.print() + "-" + vec2.print() + "=" + Vector.sub(vec1, vec2).print());
        //向量乘法(5,2)*3
        System.out.println(vec1.print() + "*" + 3 + "=" + vec1.mul(3).print());
        //向量乘法(5,2)/3
        System.out.println(vec1.print() + "/" + 3 + "=" + vec1.div(3).print());
        //获取向量取正
        System.out.println("+" + vec1.print() + "=" + vec1.pos().print());
        //获取向量取负
        System.out.println("-" + vec1.print() + "=" + vec1.neg().print());
    }

    /**
     * 向量的高级运算(向量的模，单位向量，零向量的模)
     * @author show
     */
    private static void vector2() {
        //二维零向量
        System.out.println(zero2.print());
        //任何向量加零向量都等于他本身
        System.out.println(vec1.print() + "+" + zero2.print() + "=" + Vector.add(vec1, zero2).print());
        //求 vec1 vec2 的模
        System.out.println("norm(" + vec1.print() + ") = " + vec1.norm());
        System.out.println("norm(" + vec2.print() + ") = " + vec2.norm());
        System.out.println("norm(" + zero2.print() + ") = " + zero2.norm());
        // 求 vec1 vec2 的单位向量
        System.out.println("normalize " + vec1.print() + " is " + vec1.normalize().print());
        System.out.println("normalize " + vec2.print() + " is " + vec2.normalize().print());
        // 测试 vec1 vec2 的单位向量的模是否为1
        System.out.println(vec1.normalize().norm());
        System.out.println(vec2.normalize().norm());
        //求零向量的模
        try {
            System.out.println(zero2.normalize().print());
        } catch (ZeroDivisionError error) {
            System.out.println("Cannot normalize zero vector {" + zero2.print() + "}");
        }
    }

    /**
     * 向量的高级运算(向量相乘的点乘(向量相乘的内积))
     * # 向量点乘的应用：如果可以知道向量的点乘 根据cos x = 向量的点乘/向量的模
     * # 可得 当x=90°时，向量的点乘为0，
     * # 如果向量的点乘为0，两个向量垂直
     * # 如果向量的点乘>0，两个向量夹角为锐角，反之为钝角
     * ## 判断两个向量的相似程度(推荐系统)
     * ## 如果点乘结果大于0，说明相似
     * ## 如果点乘结果等于0，说明无关
     * ## 如果点乘结果小于0，说明背离
     * @author show
     */
    private static void vector3() {
        // vec1和vec2的点乘结果
        System.out.println(Vector.dot(vec1, vec2).toString());

    }

    public static void main(String[] args) {

        vector1();
        vector2();
        vector3();
    }
}
