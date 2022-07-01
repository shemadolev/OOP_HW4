import java.util.ArrayList;
import java.util.List;

public class MatrixSortAscending<T> extends MatrixSorter<T> {

    /**
     * @param width
     * @param height
     * @requires width, height > 0
     */
    public MatrixSortAscending(int width, int height) {
        super(width, height);
    }

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
