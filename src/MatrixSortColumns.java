import java.util.ArrayList;
import java.util.List;

public class MatrixSortColumns<T> extends MatrixSorter<T> {

    /**
     * @param width
     * @param height
     * @requires width, height > 0
     */
    public MatrixSortColumns(int width, int height) {
        super(width, height);
    }

    @Override
    public List<T> getSorted(T[][] matrix) {
        ArrayList<T> list = new ArrayList<>();
        for (int j = 0; j < _height; j++) {
            for (int i = 0; i < _width; i++) {
                list.add(matrix[i][j]);
            }
        }
        return list;
    }
}
