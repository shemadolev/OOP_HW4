import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// MatrixSorter is an abstract class that supplies a sorted list of a 2D matrix's elements.
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
     * @requires matrix dimensions == (_width,_height)
     * @return A sorted form the elements in the given matrix.
     *         Generate a random list of the elements.
     */
    @Override
    public List<T> getSorted(T[][] matrix) {
        ArrayList<T> list = new ArrayList<>();
        for(T[] row : matrix)
            list.addAll(List.of(row));
        Collections.shuffle(list);
        return list;
    }
}
