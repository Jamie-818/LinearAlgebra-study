import model.Vector;

/**
 * 向量测试类
 * @author show
 */
public class MainVector {

    public static void main(String[] args) {
        // 向量初始化
        Vector vec1 = new Vector(new int[]{5, 2});
        Vector vec2 = new Vector(new int[]{3, 1});
        // 打印向量
        System.out.println(vec1.print());
        // 向量维度
        System.out.println(vec1.length());
        // 向量坐标值
        System.out.println("vec1[0]=" + vec1.getItem(0) + ",vec1[1]=" + vec1.getItem(1));
        //向量相加(5,2)+(3,1)
        System.out.println(vec1.print() + "+" + vec2.print() + "=" + vec1.add(vec2).print());
        //向量相减 (5,2)-(3,1)
        System.out.println(vec1.print() + "-" + vec2.print() + "=" + vec1.sub(vec2).print());
        //向量乘法(5,2)*3
        System.out.println(vec1.print() + "*" + 3 + "=" + vec1.mul(3).print());
        //获取向量取正
        System.out.println("+" + vec1.print() + "=" + vec1.pos().print());
        //获取向量取负
        System.out.println("-" + vec1.print() + "=" + vec1.neg().print());
    }
}
