import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * todo
 */
public class Billboard extends JFrame implements ActionListener {

    /* Abstraction function
    todo
     */

    /* Representation invariant
    todo
     */

    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;
    private final Panel[][] _panelsMat;

    public static final int PANELS_X = 6;
    public static final int PANELS_Y = 6;
    private static final MatrixSorter<ColorChangeObserver> DEFAULT_SORTER = new MatrixSortAscending<>();

    private JRadioButtonMenuItem ascendingItem, columnsItem, twoTraverseItem, randomItem;

    public Billboard() {
        super("HW4 Billboard");

        _panelsMat = new Panel[PANELS_X][PANELS_Y];
        for (int i = 0; i < PANELS_X; i++) {
            for (int j = 0; j < PANELS_Y; j++) {
                _panelsMat[i][j] = new Panel();
            }
        }
        ColorGenerator.getInstance().setSorter(DEFAULT_SORTER);
        //register all panels to be updated by ColorGenerator color changes
        ColorGenerator.getInstance().setObserversMatrix(_panelsMat);

        // create main panel and menubar
        JPanel mainPanel = createMainPanel();
        getContentPane().add(mainPanel);
        JMenuBar menuBar = createMenuBar();
        setJMenuBar(menuBar);

        Timer timer = new Timer(40, evt -> repaint());
        timer.start();
    }

    /**
     * @modifies g
     * @effects todo
     */
    public void paint(Graphics g) {
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
    }

    private JMenuBar createMenuBar() {
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

        return menuBar;
    }

    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(
                new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        mainPanel.setBorder(BorderFactory.createLoweredBevelBorder());
        mainPanel.setBackground(Color.WHITE);

        return mainPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
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
    }

    public static void main(String[] args) {
        Billboard application = new Billboard();

        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        application.setResizable(false);
        application.pack();
        application.setVisible(true);
    }
}
