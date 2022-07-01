import java.awt.*;
import java.util.List;

public abstract class MatrixSorter<T> {
    protected final int _width, _height;

    /**
     * @requires width, height > 0
     */
    public MatrixSorter(int width, int height){
        _width = width;
        _height = height;
    }

    public abstract List<T> getSorted(T[][] matrix);

}
