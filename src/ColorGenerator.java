import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * ColorGenerator is a Singleton that holds a color.
 * The color is auto-changed to a new random color every 2 seconds.
 * It may hold a 2D array of ColorChangeObserver objects, that will be notified on every color change.
 *
 * @see ColorChangeObserver
 */
public final class ColorGenerator {
    private static final int CHANGE_INTERVAL = 2000; //ms
    private static ColorGenerator INSTANCE;
    private static final Random random = new Random();

    private MatrixSorter<ColorChangeObserver> _matrixSorter;
    private ColorChangeObserver[][] _observersMatrix;
    private Color _color;


    /**
     * @modifies this
     * @effects Create a new (single) instance of ColorGenerator
     */
    private ColorGenerator() {
        //Update color every 2 seconds
        Timer timer = new Timer(CHANGE_INTERVAL, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateNewColor();
            }
        });
        timer.start();
        checkRep();
    }

    /**
     * @return The single instance of ColorGenerator
     */
    public static ColorGenerator getInstance() {
        if (INSTANCE == null)
            INSTANCE = new ColorGenerator();
        return INSTANCE;
    }

    /**
     * @modifies this
     * @effects Set sorter as the object that will sort the observers for notification order.
     * @param sorter a matrix sorter of ColorChangeObserver objects
     */
    public void setSorter(MatrixSorter<ColorChangeObserver> sorter) {
        checkRep();
        _matrixSorter = sorter;
        checkRep();
    }

    /**
     * @requires this.setSorter has already been called with a valid sorter
     * @effects Set the observers of color changing. This will override any previous registration of color-changing
     * observers.
     */
    public void setObserversMatrix(ColorChangeObserver[][] observers) {
        checkRep();
        _observersMatrix = observers;
        checkRep();
    }

    /**
     * @modifies generates a new random color and notifies the change to the observers.
     */
    private void generateNewColor() {
        checkRep();
        //generate a random color
        float r = random.nextFloat();
        float g = random.nextFloat();
        float b = random.nextFloat();
        _color = new Color(r, g, b);

        notifyObservers();
        checkRep();
    }

    /**
     * @effects Notifies the observers of color change
     */
    private void notifyObservers() {
        checkRep();
        try {
            if(_observersMatrix == null) //no observers, do nothing
                return;
            //get sorted list of Panels
            List<ColorChangeObserver> panelList = _matrixSorter.getSorted(_observersMatrix);
            //notify each ColorChangeObserver, in a 40ms delay
            for (ColorChangeObserver ColorChangeObserver : panelList) {
                ColorChangeObserver.onColorChange(_color);
                TimeUnit.MILLISECONDS.sleep(40);
            }
        } catch (InterruptedException ignored) {
        } //Ignore interruption - if interrupted, observers won't be notified
        checkRep();
    }

    private void checkRep(){
        assert (_observersMatrix != null) && _matrixSorter == null : "observers are set without a sorter object";
    }

    //todo Should we have a default sorter? how can we enforce setting a sorter? if not set, don't notify?
}
