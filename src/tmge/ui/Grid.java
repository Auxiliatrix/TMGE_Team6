package tmge.ui;
import tmge.main.Constants;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import tmge.game.base.Board;
import tmge.game.base.TiledBoard;

import static tmge.main.Constants.UI_HEIGHT;
import static tmge.main.Constants.UI_WIDTH;


public class Grid{
    private int height;
    private int width;
    // gridFrame
    private JFrame frame;
    public Grid()    {
        initialize();
    }
    public void initialize(){
        frame = new JFrame("TMGE");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setContentPane(new GridPane());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    /**
     * repaint the tiledboard.
     * @param board TiledBoard contains tiles information
     * @return
     */
    public void update(TiledBoard board){
        for(int i=0; i<board.height; i++)
        {
            for(int j=0; j<board.width; j++)
            {
                int index = board.width * i + j;
                // structure: frame->gridPanel->tilePanels
                TilePane tp = (TilePane)frame.getContentPane().getComponent(index);
                tp.setBackground(Color.CYAN);//TODO:: replace the argument with the board.getColor(x,y) API
            }
        }
    }

    public class GridPane extends JPanel {

        public GridPane() {
            setLayout(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();
            for (int row = 0; row < Constants.BOARD_HEIGHT; row++) {
                for (int col = 0; col < Constants.BOARD_WIDTH; col++) {
                    gbc.gridx = col;
                    gbc.gridy = row;

                    TilePane tilePane = new TilePane();
                    Border border = null;
                    if (row < Constants.BOARD_HEIGHT-1) {
                        if (col < Constants.BOARD_WIDTH-1) {
                            border = new MatteBorder(1, 1, 0, 0, Color.GRAY);
                        } else {
                            border = new MatteBorder(1, 1, 0, 1, Color.GRAY);
                        }
                    } else {
                        if (col < Constants.BOARD_WIDTH-1) {
                            border = new MatteBorder(1, 1, 1, 0, Color.GRAY);
                        } else {
                            border = new MatteBorder(1, 1, 1, 1, Color.GRAY);
                        }
                    }
                    tilePane.setBorder(border);
                    add(tilePane, gbc);
                }
            }
        }
    }

    public class TilePane extends JPanel {

        private Color defaultBackground;

        public TilePane() {

            /*TODO: comment out for future use. Bejeweled game UI needs click action listener.
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    defaultBackground = getBackground();
                    setBackground(Color.BLUE);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    setBackground(defaultBackground);
                }
            });

             */
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(50, 50);
        }
    }
}