package model;

import exception.VectorLengthError;
import exception.ZeroDivisionError;

/**
 * 向量对象
 * @author show
 */
public class Vector {
    /**向量的底层结构*/
    private Object[] self;
    /**一个无限接近于0的数*/
    private static final double EPSILON = 1e-8;

    /**
     * 构造函数
     * 根据向量数值数量
     * @author show
     */
    private Vector(Object... numbers) {

        self = new Object[numbers.length];
        System.arraycopy(numbers, 0, this.self, 0, numbers.length);
    }

    /**
     * 构造函数(浮点数)
     * @author show
     */
    public Vector(int... numbers) {

        self = new Object[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            this.self[i] = numbers[i];
        }
    }

    /**
     * 构造函数(整数)
     * @author show
     */
    public Vector(double... numbers) {

        self = new Object[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            this.self[i] = doubleIsInt(numbers[i]);
        }
    }

    /**
     * 构造函数
     * 根据向量长度，也是获取零向量
     * @author show
     */
    public Vector(int size) {

        this.self = new Object[size];
        for (int i = 0; i < size; i++) {
            this.self[i] = 0;
        }

    }

    /**
     * 取向量的第Index个元素
     * @author show
     */
    public Object getItem(int index) throws RuntimeException {

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

    /**
     * 判断double是否没小数，是的话，返回int，否则返回自身
     * @author show
     * @param d 入参
     * @return java.lang.Object
     */
    private static Object doubleIsInt(Double d) {

        if (d % 1 == 0) {
            return d.intValue();
        } else {
            return d;
        }
    }

    /**
     * 输出向量
     * @author show
     */
    @Override
    public String toString() {

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
     * 复制一个 Vector 防止污染源对象
     * @author show
     * @date 11:30 2019/6/20
     */
    private Vector copy(Object[] self) {

        Object[] selfCopy = new Object[self.length];
        System.arraycopy(self, 0, selfCopy, 0, selfCopy.length);
        return new Vector(selfCopy);
    }

    /**
     * 向量加法
     * @author show
     */
    public static Vector add(Vector vec1, Vector vec2) throws RuntimeException {

        int vec1Size = vec1.length();
        int vec2Size = vec2.length();
        if (vec1Size != vec2Size) {
            throw new VectorLengthError("两个向量的维度不一致，无法计算");
        }
        Vector vector = new Vector(vec1Size);
        for (int i = 0; i < vec1Size; i++) {
            double count = Double.parseDouble(vec1.self[i].toString()) + Double.parseDouble(vec2.self[i].toString());
            vector.self[i] = doubleIsInt(count);
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
            throw new VectorLengthError("两个向量的维度不一致，无法计算");
        }
        Vector vector = new Vector(vec1Size);
        for (int i = 0; i < vec1Size; i++) {
            double count = Double.parseDouble(vec1.self[i].toString()) - Double.parseDouble(vec2.self[i].toString());
            vector.self[i] = doubleIsInt(count);
        }
        return vector;
    }

    /**
     * 向量乘法
     * @date 10:57 2019/6/20
     */
    public Vector mul(int k) {

        Vector copyVec = copy(self);
        Object[] copyVecSelf = copyVec.self;
        for (int i = 0; i < copyVec.length(); i++) {
            copyVecSelf[i] = doubleIsInt((Double.parseDouble(copyVecSelf[i].toString())) * k);
        }
        return copyVec;
    }

    /**
     * 向量除法
     * @date 10:57 2019/6/20
     */
    public Vector div(int k) throws ZeroDivisionError {

        if (k == 0) {
            throw new ZeroDivisionError("div error，norm is zero");
        }
        Vector copyVec = copy(self);
        Object[] copyVecSelf = copyVec.self;
        for (int i = 0; i < copyVec.length(); i++) {
            copyVecSelf[i] = doubleIsInt((Double.parseDouble(copyVecSelf[i].toString())) / k);
        }
        return copyVec;
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
    public Vector normalize() throws ZeroDivisionError {

        Vector copyVec = copy(self);
        double norm = copyVec.norm();
        //判断模是否为0，因为任何数不能除0，因为double精度问题，我们要让它少于1e-8(一个非常小的数)
        if (norm < EPSILON) {
            throw new ZeroDivisionError("normalize error，norm is zero");
        }
        for (int i = 0; i < copyVec.length(); i++) {
            double v = Double.parseDouble(copyVec.self[i].toString());
            copyVec.self[i] = 1.0 / norm * v;
        }
        return copyVec;
    }

    /**
     * 向量点乘
     * @date 10:57 2019/6/20
     */
    public static Object dot(Vector vec1, Vector vec2) {

        int vec1Size = vec1.length();
        int vec2Size = vec2.length();
        if (vec1Size != vec2Size) {
            throw new VectorLengthError("两个向量的维度不一致，无法计算");
        }
        double count = 0;
        for (int i = 0; i < vec1Size; i++) {
            count += Double.parseDouble(vec1.self[i].toString()) * Double.parseDouble(vec2.self[i].toString());

        }
        return doubleIsInt(count);
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
            vec.self[i] = doubleIsInt((Double.parseDouble(self[i].toString())) * -1);
        }
        return vec;
    }
}

