import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * ColorGenerator is a Singleton that generates a new random color every 2 seconds.
 * It may hold a 2D array of ColorChangeObserver objects, that will be notified on every color change; the order that
 * the observers will be notified will be dictated by a given MatrixSorter class.
 *
 * @see ColorChangeObserver
 * @see MatrixSorter
 */
public final class ColorGenerator {
    /* Abstraction function
     * _panelsMatrix is a 2D array of ColorChangeObserver objects, where _panelsMatrix[i][j] = element in row i and
       column j.
     * _matrixSorter is the object used for sorting ColorChangeObserver objects for notification order.
     * _color is the last color generated by this.
     * INSTANCE is the single instance of this class (Singleton)
     * random is a Random object used for generating new random colors.
     */

    /* Representation invariant
     * if _observersMatrix != null, _matrixSorter is set (!= null)
     */
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
        Timer timer = new Timer(CHANGE_INTERVAL, e -> generateNewColor());
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
     * @param sorter a matrix sorter of ColorChangeObserver objects
     * @modifies this
     * @effects Set sorter as the object that will sort the observers for notification order.
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
     * @modifies this
     */
    public void setObserversMatrix(ColorChangeObserver[][] observers) {
        checkRep();
        _observersMatrix = observers;
        checkRep();
    }

    /**
     * @effects  generates a new random color and notifies the change to the observers.
     * @modifies this
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
     * @effects Notifies the observers of color change, by the order the set sorter dictates.
     */
    private void notifyObservers() {
        checkRep();
        if (_observersMatrix == null) //no observers, do nothing
            return;
        //get sorted list of Panels
        List<ColorChangeObserver> observerList = _matrixSorter.getSorted(_observersMatrix);
        //notify each ColorChangeObserver, in a 40ms delay
        Iterator<ColorChangeObserver> iterator = observerList.iterator();
        Timer timer = new Timer(40, e -> {
            if (iterator.hasNext()) {
                ColorChangeObserver ColorChangeObserver = iterator.next();
                ColorChangeObserver.onColorChange(_color);
            } else
                ((Timer) e.getSource()).stop();
        });
        timer.start();
        checkRep();
    }

    private void checkRep() {
        assert (_observersMatrix == null) || _matrixSorter != null : "observers are set without a sorter object";
    }
}
