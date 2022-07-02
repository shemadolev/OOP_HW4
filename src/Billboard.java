import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Billboard is a window GUI application that shows a grid of Panel objects, which update their colors based on
 * color changes invoked by ColorGenerator. The user can select different update orders for the panel in this GUI
 * (default is ascending order).
 */
public class Billboard extends JFrame implements ActionListener {

    /* Abstraction function
     * _panelsMat is a 2D array of Panel objects, where _panelsMat[i][j] = Panel in column i and row j.
     * The size of the window is [WINDOW_WIDTH X WINDOW_HEIGHT] (width and height accordingly).
     * The number of panels in the X and Y axis are PANELS_X, PANELS_Y.
     * [ascendingItem, columnsItem, twoTraverseItem, randomItem] are radio buttons to choose different sorting methods.
     */

    /* Representation invariant
     * _panelsMat is a 2D matrix with PANELS_Y rows and PANELS_X columns, each element is not null.
     */

    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;
    private final Panel[][] _panelsMat;

    public static final int PANELS_X = 6;
    public static final int PANELS_Y = 6;

    private JRadioButtonMenuItem ascendingItem, columnsItem, twoTraverseItem, randomItem;

    /**
     * @modifies this
     * @effects create a new instance of this; insert new panels in _panelsMat and register them for ColorGenerator
     * changes; repaint every 40ms.
     */
    public Billboard() {
        super("HW4 Billboard");

        _panelsMat = new Panel[PANELS_Y][PANELS_X];
        for (int i = 0; i < PANELS_Y; i++) {
            for (int j = 0; j < PANELS_X; j++) {
                _panelsMat[i][j] = new Panel();
            }
        }
        ColorGenerator.getInstance().setSorter(new MatrixSortAscending<>()); //default sorter
        //register all panels to be updated by ColorGenerator color changes
        ColorGenerator.getInstance().setObserversMatrix(_panelsMat);

        // create main panel and menubar
        JPanel mainPanel = createMainPanel();
        getContentPane().add(mainPanel);
        JMenuBar menuBar = createMenuBar();
        setJMenuBar(menuBar);

        Timer timer = new Timer(40, evt -> repaint());
        timer.start();
        checkRep();
    }

    /**
     * @modifies g
     * @effects Paint the panels in the content pane. Each panel has the same height/width.
     */
    public void paint(Graphics g) {
        checkRep();
        super.paint(g);
        Graphics paneGraphics = getContentPane().getGraphics(); //This is the internal painting are, without the toolbar

        int panelWidth = getContentPane().getWidth() / PANELS_X;
        int panelHeight = getContentPane().getHeight() / PANELS_Y;

        for (int i = 0; i < PANELS_X; i++) {
            for (int j = 0; j < PANELS_Y; j++) {
                paneGraphics.setColor(_panelsMat[j][i].getColor());
                int x = i * panelWidth, y = j * panelHeight;
                paneGraphics.fillRect(x, y, panelWidth, panelHeight);
            }
        }
        checkRep();
    }

    /**
     * @return A new menu bar with radio buttons for each of the different sorters for panels update. Clicking a
     * radio button will trigger this.actionPerformed.
     * @modifies this
     */
    private JMenuBar createMenuBar() {
        checkRep();
        JMenuBar menuBar = new JMenuBar();

        menuBar.add(ascendingItem = new JRadioButtonMenuItem("Ascending", true));
        menuBar.add(columnsItem = new JRadioButtonMenuItem("Columns", false));
        menuBar.add(twoTraverseItem = new JRadioButtonMenuItem("Two Traverse", false));
        menuBar.add(randomItem = new JRadioButtonMenuItem("Random", false));

        ButtonGroup sortTypes = new ButtonGroup();
        sortTypes.add(ascendingItem);
        sortTypes.add(columnsItem);
        sortTypes.add(twoTraverseItem);
        sortTypes.add(randomItem);

        ascendingItem.addActionListener(this);
        columnsItem.addActionListener(this);
        twoTraverseItem.addActionListener(this);
        randomItem.addActionListener(this);

        checkRep();
        return menuBar;
    }

    private JPanel createMainPanel() {
        checkRep();
        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(
                new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        mainPanel.setBorder(BorderFactory.createLoweredBevelBorder());
        mainPanel.setBackground(Color.WHITE);

        checkRep();
        return mainPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        checkRep();
        JMenuItem source = (JMenuItem) e.getSource();
        ColorGenerator colorGenerator = ColorGenerator.getInstance();

        if (source.equals(ascendingItem)) {
            colorGenerator.setSorter(new MatrixSortAscending<>());
        }
        if (source.equals(columnsItem)) {
            colorGenerator.setSorter(new MatrixSortColumns<>());
        }
        if (source.equals(twoTraverseItem)) {
            colorGenerator.setSorter(new MatrixSortTwoTraverse<>());
        }
        if (source.equals(randomItem)) {
            colorGenerator.setSorter(new MatrixSortRandom<>());
        }
        checkRep();
    }

    public static void main(String[] args) {
        Billboard application = new Billboard();

        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        application.setResizable(false);
        application.pack();
        application.setVisible(true);

    }

    private void checkRep() {
        assert _panelsMat.length == PANELS_Y : "number of rows in _panelsMat is not PANELS_Y";
        for (int i = 0; i < PANELS_Y; i++) {
            assert _panelsMat[i].length == PANELS_X : "number of elements (columns) _panelsMat[" + i + "] is not " +
                    "PANELS_X";
            for (int j = 0; j < PANELS_X; j++) {
                assert _panelsMat[i][j] != null : "_panelsMat[" + i + "][" + j + "] is null";
            }
        }
    }
}
