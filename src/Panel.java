import java.awt.*;

/**
 * Panel is a colored object that can be notified with a demand of color changing.
 */
public class Panel implements ColorChangeObserver {
    /* Abstraction function
        _color holds the color of the panel.
        _color == null, stands for black.
     */

    /* Representation invariant: none */

    private Color _color;

    /**
     * @effects Inform the object of the color changing, perform necessary actions.
     * @modifies this
     */
    @Override
    public void onColorChange(Color color) {
        this._color = color;
    }

    /**
     * @return The color of the panel.
     */
    public Color getColor(){
        if(_color == null)
            return Color.black;
        return _color;
    }
}
