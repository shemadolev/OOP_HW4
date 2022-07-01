import java.util.List;

/**
 * MatrixSorter is an abstract class that supplies a sorted list of a 2D matrix's elements.
 *
 * @param <T> The element of the 2D matrix
 */
public abstract class MatrixSorter<T> {
    protected final int _width, _height;

    /**
     * @requires width, height > 0
     * @modifies this
     */
    public MatrixSorter(int width, int height) {
        _width = width;
        _height = height;
        assert height > 0 : "Sorter refers matrix with positive number of rows.";
        assert width > 0 : "Sorter refers matrix with positive number of columns.";
    }

    /**
     * @return A sorted form the elements in the given matrix.
     * @requires matrix dimensions == (_width,_height)
     */
    public abstract List<T> getSorted(T[][] matrix);
}
