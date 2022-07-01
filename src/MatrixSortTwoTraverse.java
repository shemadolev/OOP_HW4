import java.util.ArrayList;
import java.util.List;

// MatrixSortTwoTraverse creates a sorted list of a 2D matrix's elements making two traverses.
// First Traversal of even elements and then odd elements, starting count indices from 0.
public class MatrixSortTwoTraverse<T> extends MatrixSorter<T> {

    /**
     * @requires width, height > 0
     * @modifies this
     */
    public MatrixSortTwoTraverse(int width, int height) {
        super(width, height);
    }

    /**
     * @requires matrix dimensions == (_width,_height)
     * @return A sorted form the elements in the given matrix.
     *         First ascending order of even indices, then ascending order of odd indices.
     */
    @Override
    public List<T> getSorted(T[][] matrix) {
        List<T> sortedList = new ArrayList<>();
        // traverse even indices (0,2,4,...)
        for (int i = 0; i < _height; i++) {
            for (int j = 0; j < _width; j += 2)
                sortedList.add(matrix[i][j]);
        }
        // traverse odd indices (1,3,5,...)
        for (int i = 0; i < _height; i++) {
            for (int j = 1; j < _width; j += 2)
                sortedList.add(matrix[i][j]);
        }
        return sortedList;
    }
}
