import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.concurrent.TimeUnit;

//Singleton
public final class ColorGenerator {
    private MatrixSorter<ColorChangeObserver> _matrixSorter;
    private ColorChangeObserver[][] _panelsMatrix;
    Color _color;

    private static ColorGenerator INSTANCE;

    public void setObserversMatrix(ColorChangeObserver[][] panelsMatrix){
        _panelsMatrix = panelsMatrix;
    }

    private ColorGenerator(){
        //Update color every 2 seconds
        Timer timer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateNewColor();
            }
        });
    }

    public static ColorGenerator getInstance(){
        if(INSTANCE == null)
            INSTANCE = new ColorGenerator();
        return INSTANCE;
    }

    private void generateNewColor(){
        //todo change color

        notifyObservers();
    }

    private void notifyObservers(){
        //get sorted list of Panels
        List<ColorChangeObserver> panelList = _matrixSorter.getSorted(_panelsMatrix);
        //notify each ColorChangeObserver, in a 40ms delay
        for(ColorChangeObserver ColorChangeObserver : panelList){
            ColorChangeObserver.onColorChange(_color);
            try{
                TimeUnit.MILLISECONDS.sleep(40);
            } catch (Exception ignored){
            }
        }
    }

    public void setSorter(MatrixSorter<ColorChangeObserver> sorter){
        _matrixSorter = sorter;
    }
}
