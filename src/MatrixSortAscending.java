import java.util.ArrayList;
import java.util.List;

// MatrixSortAscending creates a sorted list of a 2D matrix's elements by each element index (using rows traversal).
public class MatrixSortAscending<T> extends MatrixSorter<T> {

    /**
     * @requires width, height > 0
     * @modifies this
     */
    public MatrixSortAscending(int width, int height) {
        super(width, height);
    }

    /**
     * @requires matrix dimensions == (_width,_height)
     * @return A sorted form the elements in the given matrix.
     *         Sort elements by rows' traversal.
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
