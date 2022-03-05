package tmge.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.Consumer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

import util.tokens.Coordinate;

@SuppressWarnings("serial")
public class SelectablePane extends JPanel {

    protected boolean selected;
    protected int row;
    protected int col;
	protected Border defaultBorder;
    
    public SelectablePane(int y,int x, Border defaultBorder, Consumer<Coordinate> selectFunction) {
        this.row = y;
        this.col = x;
        selected = false;
        this.defaultBorder = defaultBorder;
        setBorder(defaultBorder);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                select();
                selectFunction.accept(new Coordinate(y,x));
            }
        });
    }

	public boolean isSelected() {
    	return selected;
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
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(50, 50);
    }
}
