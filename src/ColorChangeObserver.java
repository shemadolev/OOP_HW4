import java.awt.*;

/**
 * ColorChangeObserver is an interface that exposes a notifier of a color changing demand.
 */
public interface ColorChangeObserver {
    /* Abstraction function : none */

    /* Representation invariant : none */

    /**
     * @effects Inform the object of the color changing, perform necessary actions.
     */
    void onColorChange(Color color);
}
