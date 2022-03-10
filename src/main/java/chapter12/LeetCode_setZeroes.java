package chapter12;

import java.util.*;

/**
 *
 * 矩阵置零
 * 给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。
 *
 * 输入：matrix = [[1,1,1],[1,0,1],[1,1,1]]
 * 输出：[[1,0,1],[0,0,0],[1,0,1]]
 *
 * 输入：matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
 * 输出：[[0,0,0,0],[0,4,5,0],[0,3,1,0]]
 *
 * @author luwu
 * @date 2022/3/9 10:06
 */
public class LeetCode_setZeroes {

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{0,1,2,0}, {3,4,5,2}, {1,3,1,5}};
        setZeroes(matrix);
        System.out.println(Arrays.deepToString(matrix));
    }

    /**
     * 空间复杂度O(m + n)
     * @param matrix
     */
    public static void setZeroes(int[][] matrix) {

        Set<Integer> row = new HashSet<>(), column = new HashSet<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    row.add(i);
                    column.add(j);
                }
            }
        }

        for (Integer oneRow : row) {
            for (int i = 0; i < matrix[oneRow].length; i++) {
                matrix[oneRow][i] = 0;
            }
        }

        for (Integer oneColumn : column) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][oneColumn] = 0;
            }
        }
    }
}
