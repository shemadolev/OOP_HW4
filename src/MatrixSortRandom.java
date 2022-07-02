import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * MatrixSortRandom creates a list of a 2D matrix's elements in a random order.
 *
 * @param <T> The element of the 2D matrix
 */
public class MatrixSortRandom<T> implements MatrixSorter<T> {
    /* Abstraction function : none */

    /* Representation invariant : none*/

    /**
     * @return A sorted form the elements in the given matrix.
     * Generate a random list of the elements.
     * @requires matrix[i].length == matrix[j].length for every i,j in [0,matrix.length)
     */
    @Override
    public List<T> getSorted(T[][] matrix) {
        ArrayList<T> list = new ArrayList<>();
        for (T[] row : matrix)
            list.addAll(List.of(row));
        Collections.shuffle(list);
        return list;
    }
}
