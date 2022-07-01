import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Billboard extends JFrame implements ActionListener {

    Panel[][] _panelsMat;

    public static final int WIDTH = 6;
    public static final int HEIGHT = 6;

    public Billboard(){
        _panelsMat = new Panel[WIDTH][HEIGHT];
        //register all panels to be updated by ColorGenerator color changes
        ColorGenerator.getInstance().setObserversMatrix(_panelsMat);
    }

    //todo add toolbar and buttons to select different sort methods

    @Override
    public void actionPerformed(ActionEvent e) {


        //example - if selected ascending
        ColorGenerator.getInstance().setSorter(new MatrixSortAscending<>(WIDTH,HEIGHT));
    }
}
