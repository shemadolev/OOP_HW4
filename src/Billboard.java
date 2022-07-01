import java.awt.*;

public class Billboard {
    private PanelUpdateMethod _panelUpdateMethod;
    Panel[][] _panelsMat;


    public Billboard(int width, int height){
        _panelsMat = new Panel[width][height];
    }

    public void setUpdateMethod(PanelUpdateMethod updateMethod){
        _panelUpdateMethod = updateMethod;
    }

    public void updateColor(Color color){
        _panelUpdateMethod.update(color, _panelsMat);
    }
}
