package model;

import utils.DoubleUtils;

import java.util.Arrays;
import java.util.StringJoiner;

/**
 * 向量对象
 * @author show
 */
public class Vector {
    private Object[] self;

    /**
     * 构造函数
     * @author show
     */
    public Vector(Object[] self) {

        this.self = self;
    }

    /**
     * 复制一个 Vector 防止污染源对象
     * @author xuanweiyao
     * @date 11:30 2019/6/20
     */
    private Vector copy(Object[] self) {

        Object[] selfCopy = new Object[self.length];
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

        Object[] objects = new Object[dim];
        for (int i = 0; i < objects.length; i++) {
            objects[i] = 0;
        }
        return new Vector(objects);
    }

    /**
     * 求一个向量的模
     * @author show
     */
    public double norm() {

        Object[] selfArray = copy(self).self;
        double count = 0;
        for (Object i : selfArray) {
            count += Math.pow(Double.parseDouble(i.toString()), 2);
        }
        return Math.sqrt(count);
    }

    /**
     * 求一个向量的单位向量
     * @author show
     */
    public Vector normalize() {

        Vector vector = copy(self);
        int size = vector.length();
        Object[] normalize = new Object[size];
        for (int i = 0; i < size; i++) {
            double norm = vector.norm();
            double v = Double.parseDouble(vector.self[i].toString());
            normalize[i] = 1.0 / norm * v;
        }
        return new Vector(normalize);
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
    public static Vector add(Vector vec1, Vector vec2) {

        int vec1Size = vec1.length();
        int vec2Size = vec2.length();
        if (vec1Size != vec2Size) {
            throw new RuntimeException("两个向量的维度不一致，无法计算");
        }
        Vector vector = new Vector(new Object[vec1Size]);
        for (int i = 0; i < vec1Size; i++) {
            double count = Double.parseDouble(vec1.self[i].toString()) + Double.parseDouble(vec2.self[i].toString());
            vector.self[i] = DoubleUtils.doubleByInt(count);
        }
        return vector;
    }

    /**
     * 向量减法
     * @author show
     */
    public static Vector sub(Vector vec1, Vector vec2) {

        int vec1Size = vec1.length();
        int vec2Size = vec2.length();
        if (vec1Size != vec2Size) {
            throw new RuntimeException("两个向量的维度不一致，无法计算");
        }
        Vector vector = new Vector(new Object[vec1Size]);
        for (int i = 0; i < vec1Size; i++) {
            double count = Double.parseDouble(vec1.self[i].toString()) - Double.parseDouble(vec2.self[i].toString());
            vector.self[i] = DoubleUtils.doubleByInt(count);
        }
        return vector;
    }

    /**
     * 向量乘法
     * @date 10:57 2019/6/20
     */
    public Vector mul(int k) {

        for (int i = 0; i < self.length; i++) {
            self[i] = DoubleUtils.doubleByInt((Double.parseDouble(self[i].toString())) * k);
        }
        return new Vector(self);
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

        Vector vec = copy(self);
        for (int i = 0; i < vec.length(); i++) {
            vec.self[i] = DoubleUtils.doubleByInt((Double.parseDouble(self[i].toString())) * -1);
        }
        return vec;
    }

    /**
     * 取向量的第Index个元素
     * @author show
     */
    public Object getItem(int index) {

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

