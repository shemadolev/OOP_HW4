import java.awt.*;

public class Panel implements ColorChangeObserver {
    private Color _color;

    @Override
    public void onColorChange(Color color) {
        this._color = color;
    }

    public Color getColor(){
        return _color;
    }
}
