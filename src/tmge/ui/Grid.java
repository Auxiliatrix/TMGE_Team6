package tmge.ui;
import tmge.main.Constants;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import javax.swing.BorderFactory;
import tmge.game.base.Board;
import tmge.game.base.TiledBoard;

import static tmge.main.Constants.UI_HEIGHT;
import static tmge.main.Constants.UI_WIDTH;

// structure: frame->gridPanel->tilePanels
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
        frame.addKeyListener(new TMGEKeyListener()); // enables key inputs
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

                TilePane tp = (TilePane)frame.getContentPane().getComponent(index);
                //tp.setBackground(Color.CYAN);//TODO:: replace the argument with the board.getColor(x,y) API
            }
        }
    }

    public void select(int row, int col)
    {
        int index = Constants.BOARD_WIDTH*row + col;
        TilePane tp = (TilePane)frame.getContentPane().getComponent(index);
        tp.select();
    }

    public class GridPane extends JPanel {

        public GridPane() {
            setLayout(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();
            for (int row = 0; row < Constants.BOARD_HEIGHT; row++) {
                for (int col = 0; col < Constants.BOARD_WIDTH; col++) {
                    gbc.gridx = col;
                    gbc.gridy = row;

                    TilePane tilePane = new TilePane(row, col);
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
                    tilePane.setDefaultBorder(border);
                    add(tilePane, gbc);
                }
            }
        }
    }

    public class TilePane extends JPanel {

        private Border defaultBorder;
        private boolean selected;
        private int row;
        private int col;
        public TilePane(int y,int x) {
            this.row = y;
            this.col = x;
            selected = false;
            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    select();
                    System.out.println(row +"row" + col + "col has been clicked");
                    informGameEngine();
                }

            });
        }

        public void setDefaultBorder(Border defaultBorder) {
            this.defaultBorder = defaultBorder;
        }
        /*
        / This method is to highlight the tile being selected, click it again to unselect it.
         */
        public void select()
        {
            if(selected)
            {
                setBorder(defaultBorder);
            }else
            {
                setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
            }
            selected = !selected;
        }
        public void informGameEngine()
        {
            //TODO: tell the game that (row,col) has been clicked, and let it handle the logic
            //TODO:  make API in gameEngine to recieve this info
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(50, 50);
        }
    }
}