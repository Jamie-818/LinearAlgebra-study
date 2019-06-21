import model.Vector;

/**
 * 向量测试类
 * @author show
 */
public class MainVector {

    public static void main(String[] args) {
        // 向量初始化
        Vector vec1 = new Vector(new Object[]{5, 2});
        Vector vec2 = new Vector(new Object[]{3, 1});
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
        //获取向量取正
        System.out.println("+" + vec1.print() + "=" + vec1.pos().print());
        //获取向量取负
        System.out.println("-" + vec1.print() + "=" + vec1.neg().print());
        //获取二维零向量
        Vector zero2 = Vector.zero(2);
        System.out.println(zero2.print());
        //任何向量加零向量都等于他本身
        System.out.println(vec1.print() + "+" + zero2.print() + "=" + Vector.add(vec1, zero2).print());
        //求 vec1 vec2 的模
        System.out.println("norm(" + vec1.print() + ") = " + vec1.norm());
        System.out.println("norm(" + vec2.print() + ") = " + vec2.norm());
        System.out.println("norm(" + zero2.print() + ") = " + zero2.norm());
        // 求vec1的单位向量
        System.out.println("normalize " + vec1.print() + " is " + vec1.normalize().print());
        // 测试单位向量的模是否为1
        System.out.println(vec1.normalize().norm());

    }
}
