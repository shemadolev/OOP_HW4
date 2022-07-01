import java.util.ArrayList;
import java.util.List;

// MatrixSortColumns is an abstract class that supplies a sorted list of a 2D matrix's elements (using columns traversal).
public class MatrixSortColumns<T> extends MatrixSorter<T> {

    /**
     * @requires width, height > 0
     * @modifies this
     */
    public MatrixSortColumns(int width, int height) {
        super(width, height);
    }

    /**
     * @requires matrix dimensions == (_width,_height)
     * @return A sorted form the elements in the given matrix.
     *         Sort elements by columns' traversal.
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
