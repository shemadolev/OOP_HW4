import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * MatrixSortRandom creates a list of a 2D matrix's elements in a random order.
 *
 * @param <T> The element of the 2D matrix
 */
public class MatrixSortRandom<T> extends MatrixSorter<T> {

    /**
     * @param width
     * @param height
     * @requires width, height > 0
     */
    public MatrixSortRandom(int width, int height) {
        super(width, height);
    }

    /**
     * @return A sorted form the elements in the given matrix.
     * Generate a random list of the elements.
     * @requires matrix dimensions == (_width,_height)
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
