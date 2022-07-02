import java.awt.*;

/**
 *
 */
public class Panel implements ColorChangeObserver {
    /* Abstraction function
    todo
     */

    /* Representation invariant
    todo
     */

    private Color _color;

    //todo
    @Override
    public void onColorChange(Color color) {
        this._color = color;
    }

    //todo
    public Color getColor(){
        if(_color == null)
            return Color.black;
        return _color;
    }
}
