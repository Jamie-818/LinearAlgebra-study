package model;

import java.util.Arrays;
import java.util.StringJoiner;

/**
 * 向量对象
 * @author show
 */
public class Vector {
    private int[] self;

    /**
     * 构造函数
     * @author show
     */
    public Vector(int[] self) {

        this.self = self;
    }

    /**
     * 复制一个 Vector 防止污染源对象
     * @author xuanweiyao
     * @date 11:30 2019/6/20
     */
    private Vector copy(int[] self) {

        int[] selfCopy = new int[self.length];
        System.arraycopy(self, 0, selfCopy, 0, selfCopy.length);
        return new Vector(selfCopy);

    }

    /**
     * 打印向量
     * @author show
     */
    public String print() {

        StringBuilder str = new StringBuilder();
        str.append("(");
        int selfLength = self.length;
        for (int i = 0; i < selfLength; i++) {
            str.append(self[i]);
            if (i < selfLength - 1) {
                str.append(",");
            }
        }
        str.append(")");
        return str.toString();
    }

    /**
     * 获取零向量
     * @author show
     */
    public static Vector zero(int dim) {

        return new Vector(new int[dim]);
    }

    @Override
    public String toString() {

        return new StringJoiner(", ", Vector.class.getSimpleName() + "(", ")")
                .add(Arrays.toString(self))
                .toString();
    }

    /**
     * 向量加法
     * @author show
     */
    public Vector add(Vector vector) {

        if (self.length != vector.length()) {
            throw new RuntimeException("两个向量的维度不一致，无法计算");
        }
        Vector vectorCopy = copy(self);
        int[] self = this.self;
        for (int i = 0; i < this.self.length; i++) {
            vectorCopy.self[i] += vector.self[i];
        }
        return new Vector(self);
    }

    /**
     * 向量减法
     * @author show
     */
    public Vector sub(Vector vector) {

        if (self.length != vector.length()) {
            throw new RuntimeException("两个向量的维度不一致，无法计算");
        }
        Vector vectorCopy = copy(self);
        for (int i = 0; i < self.length; i++) {
            vectorCopy.self[i] -= vector.self[i];
        }
        return vectorCopy;
    }

    /**
     * 向量乘法
     * @date 10:57 2019/6/20
     */
    public Vector mul(int k) {

        Vector vectorCopy = copy(self);
        for (int i = 0; i < self.length; i++) {
            vectorCopy.self[i] *= k;
        }
        return vectorCopy;
    }

    /**
     * 返回向量取正的结果向量
     * @author show
     */
    public Vector pos() {

        return copy(self);
    }

    /**
     * 返回向量取负的结果向量
     * @author show
     */
    public Vector neg() {

        Vector vectorCopy = copy(self);
        for (int i = 0; i < vectorCopy.self.length; i++) {
            vectorCopy.self[i] *= -1;
        }
        return vectorCopy;
    }

    /**
     * 取向量的第Index个元素
     * @author show
     */
    public int getItem(int index) {

        if (index > self.length) {
            throw new RuntimeException("查询的下标超过向量的维度");
        }
        return self[index];
    }

    /**
     * 获取向量的维度
     * @author show
     */
    public int length() {

        return self.length;
    }

}
