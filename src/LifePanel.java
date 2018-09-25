import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.JPanel;
//===========================================================================
/** this class allows one to display and interact with a Life board. */
public class LifePanel extends JPanel implements KeyListener, MouseListener
{
    private ConwaysGameOfLife  mGame;
    private boolean timerIsOn = false;
    private static final String helpText = "Press ' ' (or 'n' or 'g') to advance to next generation. \n"
            + "Press 'q' (or 'x') to quit. \n"
            + "Press 'l' to load. \n"
            + "Press 's' to save. \n"
            + "Press 'h' or '?' for help. \n"
            + "Press 't' to toggle a timer on and off \n"
            + "Click on a cell to toggle on and off. \n";
    //-----------------------------------------------------------------------
    public LifePanel ( ConwaysGameOfLife c ) {
        mGame = c;
        System.out.println( "Press 'h' or '?' for help." );
    }
    //-----------------------------------------------------------------------
    @Override
    public void paint ( Graphics g ) {
        //erase the entire window first
        g.setColor( Color.black );
        g.fillRect( 0, 0, mGame.getWidth(), mGame.getHeight() );

        //draw the contents of the board
        g.setColor( Color.white );
        for (int r=0; r<mGame.mBoardH; r++) {
            for (int c=0; c<mGame.mBoardW; c++) {
                if (mGame.mBoard[r][c])
                    g.fill3DRect( c*ConwaysGameOfLife.sCellW, r*ConwaysGameOfLife.sCellH,
                            ConwaysGameOfLife.sCellW,   ConwaysGameOfLife.sCellH,
                            false );
            }
        }
    }
    //-----------------------------------------------------------------------
    /** handle clicks. when the user clicks, this will cause that particular
     * cell to be tooggle on or off.
     * @todo for students v4.
     */
    @Override
    public void mouseClicked ( MouseEvent e ) {
        Insets in = mGame.getInsets();
        int x = e.getX() - in.left;
        int y = e.getY() - in.top;
        System.out.println( "LifePanel.mouseClicked: x=" + x + " y=" + y );

        // todo code here

    }

    /** unused */
    @Override public void mouseEntered  ( MouseEvent unused ) { }
    /** unused */
    @Override public void mouseExited   ( MouseEvent unused ) { }
    /** unused */
    @Override public void mousePressed  ( MouseEvent unused ) { }
    /** unused */
    @Override public void mouseReleased ( MouseEvent unused ) { }
    //-----------------------------------------------------------------------
    /**
     * respond to keys.
     * @todo for students v5. add a timer that calls nextGeneration and repaint
     * every second. the timer should be turned on and off with 't'.
     */
    @Override
    public void keyTyped ( KeyEvent e ) {
        char ch = e.getKeyChar();
        System.out.println( "keyTyped: " + ch );
        switch (ch) {
            case ' ' :
            case 'g' :
            case 'G' :
            case 'n' :
            case 'N' :
                mGame.nextGeneration();
                repaint();
                break;
            case 'h' :
            case 'H' :
            case '?' :
            case '/' :
                System.out.println( helpText );
                break;
            case 'q' :
            case 'Q' :
            case 'x' :
            case 'X' :
                System.exit( 0 );
                break;
            case 'l' :
            case 'L' :
                mGame.save();
                break;
            case 's' :
            case 'S' :
                mGame.load();
                break;
            case 't' :
            case 'T' :
                this.timerIsOn = !timerIsOn;
                if (timerIsOn)    System.out.println( "timer is on" );
                else              System.out.println( "timer is off" );
                //todo code here
                break;
        }
    }

    /** unused */
    @Override
    public void keyPressed ( KeyEvent unused ) { }

    /** unused */
    @Override
    public void keyReleased ( KeyEvent unused ) { }

}
//===========================================================================
