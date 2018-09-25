import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
//===========================================================================
/** Conway's Game of Life.
 * (See <a href="https://en.wikipedia.org/wiki/Conway's_Game_of_Life">Conway's Game of Life</a> on wikipedia;
 * <a href="https://www.youtube.com/watch?v=R9Plq-D1gEk">this</a> is interesting too on youtube.)
 *
 * <pre>
 * v1
 *     {@link #initializeBoard()}
 *     {@link #toString()}
 * v2
 *     {@link #countNeighbors(int, int)}
 * v3
 *     {@link #nextGeneration()}
 * v4
 *     {@link LifePanel#mouseClicked(MouseEvent)}
 * v5
 *     {@link LifePanel#keyTyped(KeyEvent)}
 * v6
 *     {@link #save()}
 *     {@link #load()}
 * </pre>
 */
public class ConwaysGameOfLife extends JFrame {
    /** file name for loads and saves */
    static final String sFname = "cgol.dat";
    /** cell width in pixels */
    static final int sCellW = 10;
    /** cell height in pixels */
    static final int sCellH = 10;

    /** columns in board */
    final int mBoardW;
    /** rows in board */
    final int mBoardH;
    /** window width in pixels */
    final int mWindowW;
    /** window height in pixels */
    final int mWindowH;

    /** conway's game of life board. true = alive; false = dead/empty */
    boolean[][] mBoard;
    /** generation number (starting at 0) */
    int mGen;
    //-----------------------------------------------------------------------
    /** create with default size */
    public ConwaysGameOfLife ( ) {
        this( 50, 50 );  //50 x 50 is the default
    }
    //-----------------------------------------------------------------------
    /** create with specified size
     * @param w is the width.
     * @param h is the height.
     */
    public ConwaysGameOfLife ( int w, int h ) {
        assert( w>0 && h>0 );
        mBoardW = w;
        mBoardH = h;
        mBoard  = new boolean[ mBoardH ][ mBoardW ];

        setTitle( "Conway's Game of Life: generation=" + mGen );
        int winW = mBoardW * sCellW;
        int winH = mBoardH * sCellH;
        setSize( winW, winH );
        setLocation( 50, 50 );
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        LifePanel  lp = new LifePanel( this );
        add( lp );
        addMouseListener( lp );
        addKeyListener( lp );

        initializeBoard();
        System.out.println( this );
        setVisible( true );
        createBufferStrategy( 2 );  //double buffer
        Insets in = getInsets();
        mWindowW = winW + in.left;
        mWindowH = winH + in.top;
        setSize( mWindowW, mWindowH );
    }
    //-----------------------------------------------------------------------
    /** code to set up the initial board configuration.
     * @todo for students v1, add code here so your toString has something to show.
     */
    protected void initializeBoard ( ) {
        /*
        for(int i = 0;i < sCellW; i++){
            for(int j=0; j <sCellH; j++){
                mBoard[i][j] = false;
            }
        }
*/
        int relativePosition = 0;
        int absoluteXPosition = 0;
        int absoluteYPosition = 0;
        mBoard[absoluteXPosition + relativePosition][absoluteYPosition + relativePosition]         =  true;
        mBoard[absoluteXPosition + relativePosition][absoluteYPosition + relativePosition + 1]     =  true;
        mBoard[absoluteXPosition + relativePosition + 1][absoluteYPosition + relativePosition]     =  true;
        mBoard[absoluteXPosition + relativePosition + 1][absoluteYPosition + relativePosition + 1] =  true;


        int absoluteXPosition1 = 17;
        int absoluteYPosition1 = 10;
        mBoard[absoluteXPosition1 + relativePosition][absoluteYPosition1 + relativePosition + 1]       =  true;
        mBoard[absoluteXPosition1 + relativePosition][absoluteYPosition1 + relativePosition + 2]       =  true;
        mBoard[absoluteXPosition1 + relativePosition + 1][absoluteYPosition1 + relativePosition]       =  true;
        mBoard[absoluteXPosition1 + relativePosition + 1][absoluteYPosition1 + relativePosition + 3]   =  true;
        mBoard[absoluteXPosition1 + relativePosition + 2][absoluteYPosition1 + relativePosition + 1]   =  true;
        mBoard[absoluteXPosition1 + relativePosition + 2][absoluteYPosition1 + relativePosition + 2]   =  true;


        int absoluteXPosition2 = 24;
        int absoluteYPosition2 = 10;
        mBoard[absoluteXPosition2 + relativePosition][absoluteYPosition2 + relativePosition + 1]       =  true;
        mBoard[absoluteXPosition2 + relativePosition][absoluteYPosition2 + relativePosition + 2]       =  true;
        mBoard[absoluteXPosition2 + relativePosition + 1][absoluteYPosition2 + relativePosition]       =  true;
        mBoard[absoluteXPosition2 + relativePosition + 1][absoluteYPosition2 + relativePosition + 3]   =  true;
        mBoard[absoluteXPosition2 + relativePosition + 2][absoluteYPosition2 + relativePosition + 1]   =  true;
        mBoard[absoluteXPosition2 + relativePosition + 2][absoluteYPosition2 + relativePosition + 3]   =  true;
        mBoard[absoluteXPosition2 + relativePosition + 3][absoluteYPosition2 + relativePosition + 2]   =  true;


        int absoluteXPosition3 = 37;
        int absoluteYPosition3 = 10;
        mBoard[absoluteXPosition3 + relativePosition][absoluteYPosition3 + relativePosition + 1]       =  true;
        mBoard[absoluteXPosition3 + relativePosition + 1][absoluteYPosition3 + relativePosition]       =  true;
        mBoard[absoluteXPosition3 + relativePosition + 1][absoluteYPosition3 + relativePosition + 2]   =  true;
        mBoard[absoluteXPosition3 + relativePosition + 2][absoluteYPosition3 + relativePosition + 1]   =  true;


        int absoluteXPosition4 = 17;
        int absoluteYPosition4 = 30;
        mBoard[absoluteXPosition4 + relativePosition][absoluteYPosition4 + relativePosition ]       =  true;
        mBoard[absoluteXPosition4 + relativePosition][absoluteYPosition4 + relativePosition + 1]       =  true;
        mBoard[absoluteXPosition4 + relativePosition + 1][absoluteYPosition4 + relativePosition]       =  true;
        mBoard[absoluteXPosition4 + relativePosition + 1][absoluteYPosition4 + relativePosition + 2]   =  true;
        mBoard[absoluteXPosition4 + relativePosition + 2][absoluteYPosition4 + relativePosition + 1]   =  true;

    /*
        mBoard[10][10] = true;
        mBoard[11][11] = true;
        mBoard[mBoardH-1][mBoardW-1] = true;
    */
        mBoard[49][49] =true;
        mBoard[48][48] =true;
        mBoard[48][49] =true;
    }
    //-----------------------------------------------------------------------
    /**
     * this function replaces the current generation with the next.
     * see http://en.wikipedia.org/wiki/Conway's_Game_of_Life (especially Rules)
     * for a description of how to calculate the next generation.
     * @todo for students v3.
     */
    public void nextGeneration ( ) {
        ++mGen;
        System.out.println( "creating next generation " + mGen );
        boolean[][]  next = new boolean[ mBoardH ][ mBoardW ];
        // todo for students v3.
        //



        // .
        // .
        // .

        mBoard = next;  //replace old generation with new one
        setTitle( "Conway's Game of Life: generation=" + mGen );
        System.out.println( this );
    }
    //-----------------------------------------------------------------------
    /** count the number of (living) neighbors around x = (r,c).
     * do not count the center, (r,c).
     * <pre>
     *     - - -
     *     - x -
     *     - - -
     * </pre>
     * @param r is the row
     * @param c is the column
     * @return the count of living neighbors.
     * @todo for students v2.
     */
    @VisibleForTesting  //really should be private instead of public
    public int countNeighbors ( int r, int c ) {
        int countNeighbors = 0;

    if( r>0 ){
        if( mBoard[r-1][c-1]  && (c-1) > -1  ){  // L , T
            countNeighbors++;
        }
        if( mBoard[r-1][c]){    // L , M
            countNeighbors++;
        }
        if( c < mBoardH-1 &&  mBoard[r-1][c+1]  ){  // L , B
            countNeighbors++;
        }
    }

        if(  (c-1) > -1 && mBoard[r][c-1] ) {    // M , T
            countNeighbors++;
        }
        if( c < mBoardH-1 && mBoard[r][c+1]  ){   // M , B
            countNeighbors++;
        }


        if( r < mBoardH-1 ) {
            if ((c-1) > -1 && mBoard[r + 1][c - 1]  ) {  // R , T
                countNeighbors++;
            }
            if (mBoard[r + 1][c]) {    // R , M
                countNeighbors++;
            }
            if ( c < mBoardH - 1 && mBoard[r + 1][c + 1] ) {  // R , B
                countNeighbors++;
            }
        }
            return countNeighbors;
    }
    //-----------------------------------------------------------------------
    /** ye olde toe stringe methode. returns a string representing the board
     * (alive and dead cells) that can be printed or saved to some other output
     * stream.
     * @return a string that represents the board.
     * @todo for students v1.
     */
    @Override
    public String toString ( ) {
        String s = "\n";
        s += "gen=" + this.mGen + "\n";
        // todo for students v1.
      //  System.out.println(s);
        for(int i = 0;i < mBoardW; i++){
            for(int j=0; j <mBoardH; j++){
                if(mBoard[i][j]==true) {
                    s += "1";
                }else{
                    s +="0";
                }
            }
            s +="\n";

        }
        return s;
    }

    //-----------------------------------------------------------------------
    /** load a saved game. load it from file named sFname.
     * @todo for students v6.
     */
    public void load ( ) {
    }

    //-----------------------------------------------------------------------
    /** save the current game. save it to file named sFname.
     * @todo for students v6.
     */
    public void save ( ) {
    }

    //-----------------------------------------------------------------------
    public static void main ( String[] args ) {
        ConwaysGameOfLife start =  new ConwaysGameOfLife();

        //Test1
        int countBoard1 =start.countNeighbors(0,0);
        System.out.println( countBoard1 );

        //Test2
        int countBoard2 =start.countNeighbors(49,49);
        System.out.println( countBoard2 );
    }
}
//===========================================================================
