package DP;

import java.util.Arrays;

public class MatrixCalculations {

    public static double[][] add(double[][] a, double[][] b) {
        if (a.length != b.length || a[0].length != b[0].length)
            throw new IllegalStateException("Matrix dimensions should be the same to add them");

        double[][] matrix = new double[a.length][b[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                matrix[i][j] = a[i][j] + b[i][j];
            }
        }

        return matrix;
    }

    public static double[][] subtract(double[][] a, double[][] b) {
        if (a.length != b.length || a[0].length != b[0].length)
            throw new IllegalStateException("Matrix dimensions should be the same to subtract them");

        double[][] matrix = new double[a.length][b[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                matrix[i][j] = a[i][j] - b[i][j];
            }
        }

        return matrix;
    }

    // liczenie wyznacznika jest napisane, żeby działało. Dla dużych macierzy będzie działał bardzo długo
    public static double determinant(double[][] matrix) {
        if (matrix.length != matrix[0].length)
            throw new IllegalStateException("Matrix should have equal dimensions to calculate determinant");

        if (matrix.length == 1)
            return matrix[0][0];

        if (matrix.length == 2)
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];

        double det = 0;
        for (int i = 0; i < matrix[0].length; i++)
            det += Math.pow(-1, i) * matrix[0][i]
                    * determinant(submatrix(matrix, 0, i));
        return det;
    }

    // liczenie odwrotnej macierzy jest napisane, żeby działało. Dla dużych macierzy będzie działał bardzo długo
    public static double[][] inverse(double[][] matrix) throws Exception{
        double[][] inverse = new double[matrix.length][matrix.length];

        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[i].length; j++)
                inverse[i][j] = Math.pow(-1, i + j)
                        * determinant(submatrix(matrix, i, j));

        double det = determinant(matrix);
        if (det == 0)
            throw new Exception("determinant is equal to 0 - you cant inverse this matrix");
        det = 1.0 / det;
        for (int i = 0; i < inverse.length; i++) {
            for (int j = 0; j <= i; j++) {
                double temp = inverse[i][j];
                inverse[i][j] = inverse[j][i] * det;
                inverse[j][i] = temp * det;
            }
        }

        return inverse;
    }

    public static double[][] submatrix(double[][] matrix, int row, int column) {
        double[][] submatrix = new double[matrix.length - 1][matrix.length - 1];

        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; i != row && j < matrix[i].length; j++)
                if (j != column)
                    submatrix[i < row ? i : i - 1][j < column ? j : j - 1] = matrix[i][j];
        return submatrix;
    }

    public static double[][] multiply(double[][] a, double[][] b) {
        if (a[0].length != b.length)
            throw new IllegalStateException("First matrix's should have second dimension equal to second matrix's first dimension to multiply them (a.y == b.x)");

        double[][] matrix = new double[a.length][b[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                double sum = 0;
                for (int k = 0; k < a[i].length; k++)
                    sum += a[i][k] * b[k][j];
                matrix[i][j] = sum;
            }
        }

        return matrix;
    }

    public static double[][] multiply(double[][] a, double s) {
        double[][] matrix = new double[a.length][a[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                matrix[i][j] = a[i][j] * s;
            }
        }

        return matrix;
    }

    public static void show(double[][] matrix) {
        for (int i = 0; i < matrix.length; i++)
            System.out.println(Arrays.toString(matrix[i]));
    }
}
