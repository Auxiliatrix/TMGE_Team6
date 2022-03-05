package tmge.ui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import tmge.game.base.TiledBoard;
import util.tokens.Coordinate;

// structure: frame->gridPanel->spls
public abstract class Grid {
    
	protected int height;
    protected int width;
    // gridFrame
    protected JFrame frame;
    
    public Grid(int height, int width) {
    	this.height = height;
    	this.width = width;
        initialize();
    }
    
    public void initialize(){
        frame = new JFrame("TMGE");
        frame.addKeyListener(new TMGEKeyListener()); // enables key inputs
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setContentPane(new GridPane());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    public boolean select(Coordinate coordinate) {
    	int index = width*coordinate.x + coordinate.y;
    	SelectablePane sp = (SelectablePane) frame.getContentPane().getComponent(index);
    	sp.select();
    	return sp.isSelected();
    }
    
    /**
     * repaint the tiledboard.
     * @param board TiledBoard contains tiles information
     * @return
     */
    public void update(TiledBoard<Color> board){
        for(int i=0; i<board.height; i++)
        {
            for(int j=0; j<board.width; j++)
            {
                int index = board.width * i + j;
                SelectablePane tp = (SelectablePane)frame.getContentPane().getComponent(index);
                tp.setBackground(board.get(new Coordinate(i,j)));
            }
        }
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
                    
                    SelectablePane sp = new SelectablePane(row, col, border, c -> {onSelect(c);});
                    add(sp, gbc);
                }
            }
        }
    }
    
    public abstract void onSelect(Coordinate coordinate);
    
    public abstract void onPress(String key);

}
    