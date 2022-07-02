import java.util.ArrayList;
import java.util.List;

/**
 * MatrixSortTwoTraverse creates a sorted list of a 2D matrix's elements making two traverses:
 * First Traversal of even elements and then odd elements, starting count indices from 0.
 *
 * @param <T> The element of the 2D matrix
 */
public class MatrixSortTwoTraverse<T> implements MatrixSorter<T> {
    /* Abstraction function : none */

    /* Representation invariant : none*/

    /**
     * @return A sorted form the elements in the given matrix.
     * First ascending order of even indices, then ascending order of odd indices.
     * @requires matrix[i].length == matrix[j].length for every i,j in [0,matrix.length)
     */
    @Override
    public List<T> getSorted(T[][] matrix) {
        int height = matrix.length;
        int width = matrix[0].length;
        List<T> sortedList = new ArrayList<>();
        // traverse even indices (0,2,4,...)
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j += 2)
                sortedList.add(matrix[i][j]);
        }
        // traverse odd indices (1,3,5,...)
        for (int i = 0; i < height; i++) {
            for (int j = 1; j < width; j += 2)
                sortedList.add(matrix[i][j]);
        }
        return sortedList;
    }
}
