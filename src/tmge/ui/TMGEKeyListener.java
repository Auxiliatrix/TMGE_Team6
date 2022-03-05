package tmge.ui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

// This class is to support key-operation bindings
// To use it, add this object into your JFrame
public class TMGEKeyListener extends KeyAdapter {
    public void keyPressed(KeyEvent e)
    {
        int keyCode = e.getKeyCode();
        switch( keyCode ) {
            // player1's commands
            case KeyEvent.VK_UP:
                // TODO:call the game API to move the selected tiles.
                break;
            case KeyEvent.VK_DOWN:

                // handle down
                break;
            case KeyEvent.VK_LEFT:
                // handle left
                break;
            case KeyEvent.VK_RIGHT :
                // handle right
                break;
            // player2's commands
            case KeyEvent.VK_W:
                // handle up
                break;
            case KeyEvent.VK_S:
                // handle down
                break;
            case KeyEvent.VK_A:
                // handle left
                break;
            case KeyEvent.VK_D:
                // handle right
                break;
            /*
            case KeyEvent.:
                // handle right
                break;
             */
            default:
                System.out.println("testing...key was pressed");
        }
    }
}
