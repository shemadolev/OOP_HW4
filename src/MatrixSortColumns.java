import java.util.ArrayList;
import java.util.List;

/**
 * MatrixSortColumns creates a sorted list of a 2D matrix's elements by each element index (using columns traversal).
 *
 * @param <T> The element of the 2D matrix
 */
public class MatrixSortColumns<T> extends MatrixSorter<T> {

    /**
     * @requires width, height > 0
     * @modifies this
     */
    public MatrixSortColumns(int width, int height) {
        super(width, height);
    }

    /**
     * @return A sorted form the elements in the given matrix.
     * Sort elements by columns' traversal.
     * @requires matrix dimensions == (_width,_height)
     */
    @Override
    public List<T> getSorted(T[][] matrix) {
        List<T> list = new ArrayList<>();
        for (int j = 0; j < _height; j++) {
            for (int i = 0; i < _width; i++) {
                list.add(matrix[i][j]);
            }
        }
        return list;
    }
}
