//import java.awt.*;
//import java.awt.event.*;
//import java.util.Iterator;
//import java.util.LinkedList;
//import javax.swing.*;
//import java.util.Random;
//
//
//public class BillboardWindow extends JFrame implements ActionListener {
//
//    // preferred frame width and height.
//    private static final int WINDOW_WIDTH = 600;
//    private static final int WINDOW_HEIGHT = 600;
//
//    // graphical components
//    private JMenuBar menuBar;
//    private JMenu fileMenu, insertMenu, helpMenu;
//    private JMenuItem newItem, exitItem,
//            triangleItem, ovalItem,
//            numberedOvalItem, sectorItem, aboutItem;
//    private JCheckBoxMenuItem animationCheckItem;
//    private JPanel mainPanel;
//
//    // shapes that have been added to this
//    private final LinkedList<Shape> shapes = new LinkedList<>();
//
//    /**
//     * @modifies this
//     * @effects Initializes the GUI and enables a timer that steps animation
//     * of all shapes in this 25 times per second while animation
//     * checkbox is selected.
//     */
//    public BillboardWindow() {
//        super("homework1.Animator");
//
//        // create main panel and menubar
//        mainPanel = (JPanel) createMainPanel();
//        getContentPane().add(mainPanel);
//        menuBar = (JMenuBar) createMenuBar();
//        setJMenuBar(menuBar);
//
//        // enable animation timer (ticks 25 times per second)
//        // If the animation doesn't work on your computer, increase the first argument of the Timer constructor
//        // until you see the animation. Return the number to 40 before submitting the code.
//        Timer timer = new Timer(40, new ActionListener() {
//            public void actionPerformed(ActionEvent evt) {
//                if (animationCheckItem.isSelected()) {
//                    Rectangle bounds = new Rectangle(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
//                    Iterator<Shape> iterator = shapes.iterator();
//                    while (iterator.hasNext()) {
//                        Shape shape = iterator.next();
//                        //If the shape implements homework1.Animatable, call its step()
//                        if (shape instanceof Animatable) {
//                            ((Animatable) shape).step(bounds);
//                        }
//                    }
//                    repaint();    // make sure that the shapes are redrawn
//                }
//            }
//        });
//        timer.start();
//    }
//
//
//    /**
//     * @return main GUI panel.
//     */
//    private JComponent createMainPanel() {
//        JPanel mainPanel = new JPanel();
//        mainPanel.setPreferredSize(
//                new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
//        mainPanel.setBorder(BorderFactory.createLoweredBevelBorder());
//        mainPanel.setBackground(Color.WHITE);
//
//        return mainPanel;
//    }
//
//
//    /**
//     * @return main GUI menubar.
//     */
//    private JMenuBar createMenuBar() {
//        JMenuBar menuBar = new JMenuBar();
//
//        fileMenu = new JMenu("File");
//        newItem = new JMenuItem("New");
//        newItem.addActionListener(this);
//        fileMenu.add(newItem);
//        animationCheckItem = new JCheckBoxMenuItem("Animation");
//        fileMenu.add(animationCheckItem);
//        exitItem = new JMenuItem("Exit");
//        exitItem.addActionListener(this);
//        fileMenu.add(exitItem);
//        menuBar.add(fileMenu);
//
//        insertMenu = new JMenu("Insert");
//        triangleItem = new JMenuItem("Triangle");
//        triangleItem.addActionListener(this);
//        insertMenu.add(triangleItem);
//        ovalItem = new JMenuItem("Oval");
//        ovalItem.addActionListener(this);
//        insertMenu.add(ovalItem);
//        numberedOvalItem = new JMenuItem("Numbered Oval");
//        numberedOvalItem.addActionListener(this);
//        insertMenu.add(numberedOvalItem);
//        sectorItem = new JMenuItem("Sector");
//        sectorItem.addActionListener(this);
//        insertMenu.add(sectorItem);
//        menuBar.add(insertMenu);
//
//        helpMenu = new JMenu("Help");
//        aboutItem = new JMenuItem("About");
//        aboutItem.addActionListener(this);
//        helpMenu.add(aboutItem);
//        menuBar.add(helpMenu);
//
//        return menuBar;
//    }
//
//
//    /**
//     * @modifies g
//     * @effects Paint this including all its shapes to g. This method is
//     * invoked by Swing to draw components. It should not be invoked
//     * directly, but the repaint method should be used instead in
//     * order to schedule the component for redrawing.
//     */
//    public void paint(Graphics g) {
//        super.paint(g);
//        Graphics paneGraphics = getContentPane().getGraphics(); //This is the internal painting are, without the toolbar
//        Iterator<Shape> shapeIterator = shapes.iterator();
//        while (shapeIterator.hasNext()) {
//            shapeIterator.next().draw(paneGraphics);
//        }
//    }
//
//
//    /**
//     * @modifies this
//     * @effects Invoked when the user selects an action from the menubar
//     * and performs the appropriate operation.
//     */
//    public void actionPerformed(ActionEvent e) {
//        JMenuItem source = (JMenuItem) (e.getSource());
//
//        // File->New : clear all shapes
//        if (source.equals(newItem)) {
//            shapes.clear();
//            repaint();
//            LocationChangingNumberedOval.resetInstanceCount();
//        }
//
//        // File->Exit: close application
//        else if (source.equals(exitItem)) {
//            dispose();
//        }
//
//        // Insert a shape
//        else if ((source.equals(triangleItem)) ||
//                (source.equals(ovalItem)) ||
//                (source.equals(numberedOvalItem)) ||
//                (source.equals(sectorItem))) {
//
//            // Create the appropriate shape such that:
//            // 		 it is completely inside the window's bounds &&
//            //		 its location and size are randomly selected &&
//            //		 1/10*WINDOW_WIDTH <= shape.width < 3/10*WINDOW_WIDTH &&
//            //		 1/10*WINDOW_HEIGHT <= shape.height < 3/10*WINDOW_HEIGHT
//
//            int minShapeWidth = WINDOW_WIDTH / 10;
//            int maxShapeWidth = WINDOW_WIDTH * 3 / 10;
//            int minShapeHeight = WINDOW_HEIGHT / 10;
//            int maxShapeHeight = WINDOW_HEIGHT * 3 / 10;
//
//            Random rand = new Random();
//            // Generate a random size to follow the restrictions
//            Dimension size = new Dimension();
//            size.height = rand.nextInt(maxShapeHeight - minShapeHeight) + minShapeHeight;
//            size.width = rand.nextInt(maxShapeWidth - minShapeWidth) + minShapeWidth;
//
//            // Generate a random location for the new shape within the window's bounds
//            Point location = new Point();
//            location.x = rand.nextInt(WINDOW_WIDTH - size.width + 1);
//            location.y = rand.nextInt(WINDOW_HEIGHT - size.height + 1);
//
//            Color color = new Color(rand.nextInt(COLOR_RANGE),
//                    rand.nextInt(COLOR_RANGE),
//                    rand.nextInt(COLOR_RANGE));
//
//            //Switch-case for selected shape: Create shape with the random size + color + any other if needed
//            Shape newShape;
//
//            if (source.equals(triangleItem)) {
//                newShape = new LocationAndColorChangingTriangle(location, color, size);
//            } else if (source.equals(ovalItem)) {
//                newShape = new LocationChangingOval(location, color, size);
//            } else if (source.equals(numberedOvalItem)) {
//                newShape = new LocationChangingNumberedOval(location, color, size);
//            } else { //(source.equals(sectorItem))
//                newShape = new AngleChangingSector(location, color, size, rand.nextInt(MAX_DEGREE),
//                        rand.nextInt(MAX_DEGREE));
//            }
//
//            shapes.add(newShape);
//
//            // Paint the shapes including the new one
//            repaint();
//        }
//
//        // Help->About : show about message dialog
//        else if (source.equals(aboutItem)) {
//            JOptionPane.showMessageDialog(
//                    this,
//                    "homework1.Animator - 1st" +
//                            " homework assignment",
//                    "About",
//                    JOptionPane.INFORMATION_MESSAGE);
//        }
//    }
//
//
//    /**
//     * @effects homework1.Animator application.
//     */
//    public static void main(String[] args) {
//        BillboardWindow application = new BillboardWindow();
//
//        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        application.setResizable(false);
//        application.pack();
//        application.setVisible(true);
//    }
//}
