import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Billboard extends JFrame implements ActionListener {

    private static final int WINDOW_WIDTH = 600;
    private static final int WINDOW_HEIGHT = 600;
    private final Panel[][] _panelsMat;

    public static final int PANELS_X = 6;
    public static final int PANELS_Y = 6;
    private static final MatrixSorter<ColorChangeObserver> DEFAULT_SORTER = new MatrixSortAscending<>(PANELS_X,
            PANELS_Y);

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
    public void paint(Graphics g) { //fixme either sorting is bad, or objects x/y are reversed
        super.paint(g);
        Graphics paneGraphics = getContentPane().getGraphics(); //This is the internal painting are, without the toolbar

        int panelWidth = getContentPane().getWidth() / PANELS_X;
        int panelHeight = getContentPane().getHeight() / PANELS_Y;

        for (int i = 0; i < PANELS_X; i++) {
            for (int j = 0; j < PANELS_Y; j++) {
                paneGraphics.setColor(_panelsMat[i][j].getColor());
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
            colorGenerator.setSorter(new MatrixSortAscending<>(PANELS_X, PANELS_Y));
        }
        if (source.equals(columnsItem)) {
            colorGenerator.setSorter(new MatrixSortColumns<>(PANELS_X, PANELS_Y));
        }
        if (source.equals(twoTraverseItem)) {
            colorGenerator.setSorter(new MatrixSortTwoTraverse<>(PANELS_X, PANELS_Y));
        }
        if (source.equals(randomItem)) {
            colorGenerator.setSorter(new MatrixSortRandom<>(PANELS_X, PANELS_Y));
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
