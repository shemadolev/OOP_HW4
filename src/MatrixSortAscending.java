import java.util.ArrayList;
import java.util.List;

/**
 * MatrixSortAscending creates a sorted list of a 2D matrix's elements by each element index (using rows traversal).
 *
 * @param <T> The element of the 2D matrix
 */
public class MatrixSortAscending<T> implements MatrixSorter<T> {
    /* Abstraction function : none */

    /* Representation invariant : none*/

    /**
     * @return A sorted form the elements in the given matrix.
     * Sort elements by rows' traversal.
     * @requires matrix[i].length == matrix[j].length for every i,j in [0,matrix.length)
     */
    @Override
    public List<T> getSorted(T[][] matrix) {
        int height = matrix.length;
        int width = matrix[0].length;
        ArrayList<T> list = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                list.add(matrix[i][j]);
            }
        }
        return list;
    }
}
