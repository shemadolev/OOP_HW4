import java.util.ArrayList;
import java.util.List;

/**
 * MatrixSortAscending creates a sorted list of a 2D matrix's elements by each element index (using rows traversal).
 *
 * @param <T> The element of the 2D matrix
 */
public class MatrixSortAscending<T> extends MatrixSorter<T> {

    /**
     * @requires width, height > 0
     * @modifies this
     */
    public MatrixSortAscending(int width, int height) {
        super(width, height);
    }

    /**
     * @return A sorted form the elements in the given matrix.
     * Sort elements by rows' traversal.
     * @requires matrix dimensions == (_width,_height)
     */
    @Override
    public List<T> getSorted(T[][] matrix) {
        ArrayList<T> list = new ArrayList<>();
        for (int i = 0; i < _width; i++) {
            for (int j = 0; j < _height; j++) {
                list.add(matrix[i][j]);
            }
        }
        return list;
    }
}
