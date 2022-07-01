import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MatrixSortRandom<T> extends MatrixSorter<T> {

    /**
     * @param width
     * @param height
     * @requires width, height > 0
     */
    public MatrixSortRandom(int width, int height) {
        super(width, height);
    }

    @Override
    public List<T> getSorted(T[][] matrix) {
        ArrayList<T> list = new ArrayList<>();
        for(T[] row : matrix)
            list.addAll(List.of(row));
        Collections.shuffle(list);
        return list;
    }
}
