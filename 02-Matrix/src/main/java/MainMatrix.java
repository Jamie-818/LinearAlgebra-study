import model.Matrix;

import java.util.Arrays;

/**
 * 矩阵类验证
 * @author show
 */
public class MainMatrix {
    private static Matrix matrix1 = new Matrix(new int[][]{{1, 2}, {3, 4}});
    private static Matrix matrix2 = new Matrix(new int[][]{{5, 6}, {7, 8}});

    public static void main(String[] args) {

        matrixMain1();
        matrixMain2();

    }

    /**
     * 矩阵的基本元素验证
     * @author show
     */
    private static void matrixMain1() {

        Matrix doubleMatrix = new Matrix(new double[][]{{1.1, 2}, {3.3, 2.1}});
        Matrix intMatrix = new Matrix(new int[][]{{1, 2, 3}, {3, 4, 6}});
        System.out.println(doubleMatrix);
        System.out.println(intMatrix);
        System.out.println("doubleMatrix.shape = { " + Arrays.toString(doubleMatrix.shape()) + " }");
        System.out.println("doubleMatrix.size = " + doubleMatrix.size());

        System.out.println("doubleMatrix[0][1] = " + doubleMatrix.getItem(0, 1));
        System.out.println("intMatrix 第0行：" + intMatrix.rowVector(0));
        System.out.println("intMatrix 第0列：" + intMatrix.colVector(0));
    }

    /**
     * 矩阵的基本运算
     * @author show
     */
    private static void matrixMain2() {

    }
}
