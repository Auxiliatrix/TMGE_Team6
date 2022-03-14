package tmge.ui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import tmge.game.base.GameEngine;
import tmge.game.base.TiledBoard;
import util.tokens.Coordinate;

/**
 * UserInterface that displays the game as its being played.
 */
// structure: frame->gridPanel->spls
public abstract class ColorInterface {

    protected double score;
    
	protected int height;
    protected int width;
    // gridFrame
    protected JFrame frame;
    
    protected GameEngine ge;
    
    public ColorInterface(int height, int width, GameEngine engine) {
    	this.height = height;
    	this.width = width;
    	this.ge = engine;
        initialize();
    }
    
    public void initialize() {
        frame = new JFrame("TMGE");
        frame.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyPressed(KeyEvent e)
            {
        		onPress(e);
            }
        }); // enables key inputs
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                ge.signalClosed();
            }
        });
        frame.setLayout(new BorderLayout());
        frame.setContentPane(new GridPane());
        // testing scoring ui pane
        frame.setJMenuBar( new MenuPane()  );

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    /**
     * Destroy the interface.
     */
    public void takedown() {
    	frame.setVisible(false);
    	frame.dispose();
    }
    
    /**
     * Select the Tile at the given Coordinate
     * @param coordinate Coordinate location to select
     * @return whether the selection went through
     */
    public boolean select(Coordinate coordinate) {
    	int index = width*coordinate.y + coordinate.x;
    	SelectablePane sp = (SelectablePane) frame.getContentPane().getComponent(index);
    	sp.select();
    	return sp.isSelected();
    }
    
    /**
     * repaint the tiledboard.
     * @param board TiledBoard contains tiles information
     * @return
     */
    public void update(TiledBoard<Color> board, double s){
        for(int i=0; i<board.height; i++)
        {
            for(int j=0; j<board.width; j++)
            {
                int index = board.width * i + j;
                SelectablePane tp = (SelectablePane)frame.getContentPane().getComponent(index);
                tp.setBackground(board.get(new Coordinate(i,j)));
            }
        }
        score = s;
        frame.getJMenuBar().getMenu(0).setText("Current Score: " + score);
    }

    @SuppressWarnings("serial")
	public class GridPane extends JPanel {

        public GridPane() {
            setLayout(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();
            for (int row = 0; row < height; row++) {
                for (int col = 0; col < width; col++) {
                    gbc.gridx = col;
                    gbc.gridy = row;

                    Border border = null;
                    if (row < height-1) {
                        if (col < width-1) {
                            border = new MatteBorder(1, 1, 0, 0, Color.GRAY);
                        } else {
                            border = new MatteBorder(1, 1, 0, 1, Color.GRAY);
                        }
                    } else {
                        if (col < width-1) {
                            border = new MatteBorder(1, 1, 1, 0, Color.GRAY);
                        } else {
                            border = new MatteBorder(1, 1, 1, 1, Color.GRAY);
                        }
                    }
                    
                    SelectablePane sp = new SelectablePane(row, col, border, c -> onSelect(c));
                    add(sp, gbc);
                }
            }
        }
    }
    
    
    @SuppressWarnings("serial")
	public class MenuPane extends JMenuBar {

        public MenuPane() {
            JMenu testMenu = new JMenu("Testing Score: " + score);
            add(testMenu);
        }
    }
    
    /**
     * Function to execute when the given Coordinate location is selected.
     * @param coordinate Coordinate location to select
     */
    public abstract void onSelect(Coordinate coordinate);
    
    /**
     * Function to execute when the given key is pressed.
     * @param key KeyEvent that was executed
     */
    public abstract void onPress(KeyEvent key);

}
    