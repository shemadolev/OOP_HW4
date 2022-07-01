import java.awt.*;

public class Panel implements ColorChangeObserver {
    private Color _color;

    @Override
    public void onColorChange(Color color) {
        this._color = color;
    }

    public Color getColor(){
        if(_color == null)
            return Color.black;
        return _color;
    }
}
