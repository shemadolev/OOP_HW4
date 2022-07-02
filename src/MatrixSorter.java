import java.util.List;

/**
 * MatrixSorter supplies a sorted list of a 2D matrix's elements.
 *
 * @param <T> The element of the 2D matrix
 */
public interface MatrixSorter<T> {
    /* Abstraction function : none */

    /* Representation invariant : none*/

    /**
     * @return A sorted form the elements in the given matrix.
     * @requires matrix[i].length == matrix[j].length for every i,j in [0,matrix.length)
     */
    List<T> getSorted(T[][] matrix);
}
