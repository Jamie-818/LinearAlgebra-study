import model.Matrix;
import model.Vector;

import java.util.Arrays;

/**
 * 矩阵类验证
 * @author show
 */
public class MainMatrix {
    private static Matrix ma = new Matrix(new int[][]{{1, 2}, {3, 4}});
    private static Matrix mb = new Matrix(new int[][]{{5, 6}, {7, 8}});

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
        //矩阵加法
        System.out.println("add: " + Matrix.add(ma, mb));
        //矩阵减法
        System.out.println("subtract: " + Matrix.sub(ma, mb));
        //矩阵数量乘法
        System.out.println("scalar-mul: " + ma.mul(2));
        //矩阵取负
        System.out.println("neg: " + ma.neg());
        //零矩阵(23)
        System.out.println("zero_2_3: " + Matrix.zero(2, 3));

    }

    /**
     * 矩阵的高级运算
     * @author show
     */
    private static void matrixMain3() {

        Matrix t = new Matrix(new double[][]{{1.5, 0}, {0, 2}});
        Vector p1 = new Vector(5, 3);
        Matrix p2 = new Matrix(new double[][]{{0, 4, 5}, {0, 0, 3}});
        // 矩阵和向量的点乘(内积)
        System.out.println(t + ".dot( " + p1 + " ) = " + Matrix.dot(t, p1));
        // 矩阵和矩阵的点乘(内积)
        // (A矩阵的行数必须等于B矩阵的列数)
        System.out.println(t + ".dot( " + p2 + " ) = " + Matrix.dot(t, p2));
        //  验证：矩阵的点乘是否符合交换律
        // (一般情况下，两个不同形状AB的矩阵，A.dot(B)未必可以B.dot(A),所以验证交换律是否成立，要找两个相同形状的矩阵)
        //结果可得，矩阵的点乘不支持交换律
        System.out.println(ma + ".dot( " + mb + " ) = " + Matrix.dot(ma, mb));
        System.out.println(mb + ".dot( " + ma + " ) = " + Matrix.dot(mb, ma));

        //矩阵的转置
        System.out.println(p2 + ".T = " + p2.t());

    }

    /**
     * 矩阵乘法的性质
     * @author show
     */
    private static void matrixMain4() {

        Matrix ma = new Matrix(new int[][]{{1, 2}, {3, 4}});
        Matrix mb = new Matrix(new int[][]{{5, 6}, {7, 8}});
        Matrix mc = new Matrix(new int[][]{{9, 10}, {11, 12}});
        // 验证：AB != BA (交换律)
        // (一般情况下，两个不同形状AB的矩阵，A.dot(B)未必可以B.dot(A),所以验证交换律是否成立，要找两个相同形状的矩阵)
        Matrix a_dot_b = Matrix.dot(ma, mb);
        Matrix b_dot_a = Matrix.dot(mb, ma);
        Matrix b_add_c = Matrix.add(mb, mc);
        System.out.println(ma + " * " + mb + " = " + a_dot_b);
        System.out.println(mb + " * " + ma + " = " + b_dot_a);
        System.out.println("A.dot(B) == B.dot(A) is " + a_dot_b.equals(b_dot_a));
        // 验证：矩阵的点乘是否遵守结合律 (AB)C == A(BC)
        Matrix ab_dot_c = Matrix.dot(a_dot_b, mc);
        Matrix a_dot_bc = Matrix.dot(ma, Matrix.dot(mb, mc));
        System.out.println("ABC == A(BC) is " + ab_dot_c.equals(a_dot_bc));
        // 验证：矩阵是否遵守分配率 A(B + C) == AB + AC   (B + C)A == BA + CA
        boolean b2 = Matrix.dot(ma, b_add_c).equals(Matrix.add(a_dot_b, (Matrix.dot(ma, mc))));
        System.out.println("A(B + C) == AB + AC  is " + b2);
        boolean b3 = Matrix.dot(b_add_c, ma).equals(Matrix.add(b_dot_a, (Matrix.dot(mc, ma))));
        System.out.println("(B + C)A == BA + CA  is " + b3);
        // 验证：对任意r*c的矩阵A，存在c*x的矩阵O，满足AO(cx)=O(rx);
        int r = ma.rowNum();
        int c = ma.colNum();
        int x = 3;
        Matrix zero_cx = new Matrix(c, x);
        Matrix zero_rx = new Matrix(r, x);
        boolean b4 = Matrix.dot(ma, zero_cx).equals(zero_rx);
        System.out.println("A(rc)*O(cx) == O(rx) is " + b4);
        // 验证：对任意r*c的矩阵A，存在x*r的矩阵O，满足O(xr)A=O(xc);
        Matrix zero_xr = new Matrix(x, r);
        Matrix zero_xc = new Matrix(x, c);
        boolean b5 = Matrix.dot(zero_xr, ma).equals(zero_xc);
        System.out.println("O(xr)*A(rc) == O(xc) is " + b5);
        // 矩阵的幂，因为矩阵点乘必须矩阵A的行数=矩阵B的列数，所以，只有方阵(row=col)才可以进行幂运算
        Matrix square_ma = Matrix.square(ma);
        System.out.println(ma + "^2 = " + square_ma);
        // 验证：(A + B)^2 != A^2 + 2AB +B^2 因为不遵守交换律 只能等于 A^2 +A*B + B*A +B^2
        Matrix a_add_c = Matrix.add(ma, mb);
        Matrix square_mb = Matrix.square(mb);
        //(A + B)^2
        Matrix square1 = Matrix.square(a_add_c);
        //A^2 + 2AB + B^2
        Matrix add = Matrix.add(Matrix.add(square_ma, a_dot_b.mul(2)), square_mb);
        System.out.println("(A + B)^2 == A^2 + 2AB +B ^2 is " + square1.equals(add));
        //A^2 +AB
        Matrix add2 = Matrix.add(square_ma, a_dot_b);
        //BA + B^2
        Matrix add3 = Matrix.add(b_dot_a, square_mb);
        //A^2 +AB + BA + B^2
        Matrix add4 = Matrix.add(add2, add3);
        System.out.println("(A + B)^2 == A^2 + AB + BA + B^2 is " + square1.equals(add4));

    }

    public static void main(String[] args) {

        System.out.println("矩阵的基本元素：");
        matrixMain1();
        System.out.println("矩阵的基本运算：");
        matrixMain2();
        System.out.println("矩阵的高级运算：");
        matrixMain3();
        System.out.println("矩阵乘法的性质:");
        matrixMain4();

    }

}
