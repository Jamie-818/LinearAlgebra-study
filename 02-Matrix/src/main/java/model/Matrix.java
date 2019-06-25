package model;

import java.util.Arrays;

/**
 * 矩阵
 * @author show
 */
public class Matrix {
    private Object[][] self;

    /**
     * 构造函数
     * @author show
     */
    public Matrix(double[]... rSelf) {

        int rowLength = rSelf.length;
        int cowLength = rSelf[0].length;
        self = new Object[rowLength][cowLength];
        for (int i = 0; i < rowLength; i++) {
            if (rSelf[i].length != cowLength) {
                throw new RuntimeException("cow 长度必须一致");
            }
            for (int i1 = 0; i1 < rSelf[i].length; i1++) {
                self[i][i1] = rSelf[i][i1];
            }
        }
    }

    /**
     * 构造函数
     * @author show
     */
    public Matrix(int[]... rSelf) {

        int rowLength = rSelf.length;
        int cowLength = rSelf[0].length;
        self = new Object[rowLength][cowLength];
        for (int i = 0; i < rowLength; i++) {
            if (rSelf[i].length != cowLength) {
                throw new RuntimeException("cow 长度必须一致");
            }
            for (int i1 = 0; i1 < rSelf[i].length; i1++) {
                self[i][i1] = rSelf[i][i1];
            }
        }
    }

    /**
     * 构造函数
     * 根据行列长度
     * @author show
     */
    public Matrix(int rowNum, int colNum) {

        if (rowNum < 1 || colNum < 0) {
            throw new RuntimeException("行列不能小于0");
        }
        //这里如果用object数组，就会出现null，你要重新每个赋值为0，那不如直接用int;
        int[][] ints = new int[rowNum][colNum];
        self = new Matrix(ints).self;
    }

    /**
     * 构造函数
     * @author show
     */
    private Matrix(Object[]... rSelf) {

        int rowLength = rSelf.length;
        int cowLength = rSelf[0].length;
        self = new Object[rowLength][cowLength];
        for (int i = 0; i < rowLength; i++) {
            if (rSelf[i].length != cowLength) {
                throw new RuntimeException("cow 长度必须一致");
            }
            System.arraycopy(rSelf[i], 0, self[i], 0, rSelf[i].length);
        }

    }

    /**
     * 零矩阵
     * @author show
     * @param rowNum 行数
     * @param colNum 列数
     * @return model.Matrix
     */
    public static Matrix zero(int rowNum, int colNum) {

        return new Matrix(rowNum, colNum);

    }

    /**
     * 输出矩阵
     * @author show
     */
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("(");
        int rowLength = self.length;
        for (int row = 0; row < rowLength; row++) {
            sb.append("[");
            int cowLength = self[row].length;
            for (int cow = 0; cow < cowLength; cow++) {
                sb.append(self[row][cow]);
                if (cow < cowLength - 1) {
                    sb.append(",");
                }
            }
            sb.append("]");
            if (row < rowLength - 1) {
                sb.append(",");
            }
        }
        sb.append(")");
        return sb.toString();
    }

    /**
     * 返回矩阵的形状(行数，列数)
     * @author show
     */
    public int[] shape() {

        int[] shape = new int[2];
        shape[0] = this.self.length;
        shape[1] = this.self[0].length;
        return shape;

    }

    /**
     * 返回矩阵的行数
     * @author show
     */
    public int rowNum() {

        return this.shape()[0];
    }

    /**
     * 返回矩阵的列数
     * @author show
     */
    public int colNum() {

        return this.shape()[1];
    }

    /**
     * 返回矩阵的原数总数
     * @author show
     */
    public int size() {

        return rowNum() * colNum();
    }

    /**
     * 返回矩阵的长度(行数)
     * @author show
     */
    public int length() {

        return this.rowNum();
    }

    /**
     * 返回矩阵的某行某列的元素
     * @author show
     * @param rowNum 行数
     * @param colNum 列数
     */
    public Object getItem(int rowNum, int colNum) {

        if (rowNum + 1 > this.rowNum() || colNum + 1 > this.colNum()) {
            throw new RuntimeException("矩形下标越界");
        }

        return doubleIsInt(Double.parseDouble(this.self[rowNum][colNum].toString()));
    }

    /**
     * 返回矩阵的第 index 个行向量
     * @author show
     * @param index 行数
     */
    public Vector rowVector(int index) {

        if (index + 1 > rowNum()) {
            throw new RuntimeException("矩阵下标越界");
        }
        Object[] objects = this.self[index];
        double[] doubles = new double[objects.length];
        for (int i = 0; i < objects.length; i++) {
            doubles[i] = Double.parseDouble(objects[i].toString());
        }
        return new Vector(doubles);

    }

    /**
     * 返回矩阵的第 index 个列向量
     * @author show
     * @param index 列数
     */
    public Vector colVector(int index) {

        if (index + 1 > colNum()) {
            throw new RuntimeException("矩阵下标越界");
        }
        int rowLength = self.length;
        double[] vectorSelf = new double[rowLength];
        for (int i = 0; i < rowLength; i++) {
            vectorSelf[i] = Double.parseDouble(this.self[i][index].toString());
        }
        return new Vector(vectorSelf);

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

    /*矩阵的基本元素*/

    /**
     * 矩阵加法
     * @author show
     * @param matrix1 矩阵1
     * @param matrix2 矩阵2
     * @return model.Matrix
     */
    public static Matrix add(Matrix matrix1, Matrix matrix2) {

        int rowNum = matrix1.rowNum();
        int colNum = matrix1.colNum();
        if (!Arrays.equals(matrix1.shape(), matrix2.shape())) {
            throw new RuntimeException("两个矩阵不一致");
        }
        Matrix newMatrix = new Matrix(rowNum, colNum);
        for (int i = 0; i < rowNum; i++) {
            for (int i1 = 0; i1 < colNum; i1++) {
                newMatrix.self[i][i1] = doubleIsInt(Double.parseDouble(matrix1.self[i][i1].toString()) + Double.parseDouble(matrix2.self[i][i1].toString()));
            }
        }
        return newMatrix;
    }

    /**
     * 矩阵减法
     * @author show
     * @param matrix1 矩阵1
     * @param matrix2 矩阵2
     * @return model.Matrix
     */
    public static Matrix sub(Matrix matrix1, Matrix matrix2) {

        int rowNum = matrix1.rowNum();
        int colNum = matrix1.colNum();
        if (!Arrays.equals(matrix1.shape(), matrix2.shape())) {
            throw new RuntimeException("两个矩阵不一致");
        }
        Matrix newMatrix = new Matrix(rowNum, colNum);
        for (int i = 0; i < rowNum; i++) {
            for (int i1 = 0; i1 < colNum; i1++) {
                newMatrix.self[i][i1] = doubleIsInt(Double.parseDouble(matrix1.self[i][i1].toString()) - Double.parseDouble(matrix2.self[i][i1].toString()));
            }
        }
        return newMatrix;
    }

    /**
     * 矩阵数量乘法
     * @author show
     * @param k 数量系数
     * @return model.Matrix
     */
    public Matrix mul(int k) {

        int rowNum = self.length;
        int colNum = self[0].length;
        Matrix newMatrix = new Matrix(rowNum, colNum);
        for (int i = 0; i < rowNum; i++) {
            for (int i1 = 0; i1 < colNum; i1++) {
                newMatrix.self[i][i1] = doubleIsInt(Double.parseDouble(self[i][i1].toString()) * k);
            }
        }
        return newMatrix;
    }

    /**
     * 矩阵数量除法
     * @author show
     * @param k 数量系数
     * @return model.Matrix
     */
    public Matrix truediv(int k) {

        if (k == 0) {
            throw new RuntimeException("被除数不能为0");
        }
        // n/m = n * (1/m)
        return new Matrix(self).mul(1 / k);
    }

    /**
     * 返回矩阵取正结果
     * @author show
     * @return model.Matrix
     */
    public Matrix pos() {

        return new Matrix(self);
    }

    /**
     * 返回矩阵取负结果
     * @author show
     * @return model.Matrix
     */
    public Matrix neg() {

        return new Matrix(self).mul(-1);
    }

}
