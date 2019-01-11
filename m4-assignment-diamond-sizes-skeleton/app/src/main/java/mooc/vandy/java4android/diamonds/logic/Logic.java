package mooc.vandy.java4android.diamonds.logic;

import android.util.Log;
import mooc.vandy.java4android.diamonds.ui.OutputInterface;

/**
 * This is where the logic of this App is centralized for this assignment.
 * <p>
 * The assignments are designed this way to simplify your early
 * Android interactions.  Designing the assignments this way allows
 * you to first learn key 'Java' features without having to beforehand
 * learn the complexities of Android.
 */
public class Logic
       implements LogicInterface {
    /**
     * This is a String to be used in Logging (if/when you decide you
     * need it for debugging).
     */
    public static final String TAG = Logic.class.getName();

    /**
     * This is the variable that stores our OutputInterface instance.
     * <p>
     * This is how we will interact with the User Interface [MainActivity.java].
     * <p>
     * It is called 'out' because it is where we 'out-put' our
     * results. (It is also the 'in-put' from where we get values
     * from, but it only needs 1 name, and 'out' is good enough).
     */
    private OutputInterface mOut;

    /**
     * This is the constructor of this class.
     * <p>
     * It assigns the passed in [MainActivity] instance (which
     * implements [OutputInterface]) to 'out'.
     */
    public Logic(OutputInterface out){
        mOut = out;
    }

    /**
     * This is the method that will (eventually) get called when the
     * on-screen button labeled 'Process...' is pressed.
     */
    public void process(int size) {
        int h = (size * 2) + 1; // height of the diamond structure
        int w = (size * 2) + 2; // width of the diamond structure
        int symbols = -(size+1); // number of symbols to be inserted

        for(int i=1;i<=h;i++){
            symbols++;
            for (int j=1;j<=w;j++) {
                if((i == 1 || i == h) && (j == 1 || j == w))
                    mOut.print("+");
                else if((i == 1 || i == h) && !(j == 1 || j == w))
                    mOut.print("-");
                else if(!(i == 1 || i == h) && (j == 1 || j == w))
                    mOut.print("|");
                else {
                    diamondStructure(size, i, j, symbols);
                }
            }
            mOut.print("\n");
        }
    }

    public void diamondStructure(int size, int i, int j, int symbols){
        //this method specifies the structure of the diamond.
        // it means how the diamond structure will be displayed in response to the input size
        int rowThickness; //amount of symbols result in thickness of the row!
        if (symbols <= 0){
            rowThickness = i*2-2;
        } else {
            rowThickness = (i-symbols*2)*2-2;
        }
        int midpoint = size + 1; //displays the mid-point of the diamond
        int diamondBoundLeft = midpoint - (rowThickness/2-1);
        int diamondBoundRight = midpoint + (rowThickness/2);
        int frameTop = 1;
        int frameBottom = size * 2 + 1;

        if (j >= diamondBoundLeft && j <= diamondBoundRight) {
            if (j == diamondBoundLeft || j == diamondBoundRight) {
                if (i < midpoint && i > frameTop) {
                    if (j == diamondBoundLeft) {
                        mOut.print("/");
                    } else {
                        mOut.print("\\");
                    }
                } else if (i == midpoint) {
                    if (j == diamondBoundLeft) {
                        mOut.print("<");
                    } else {
                        mOut.print(">");
                    }
                } else if (i > midpoint && i < frameBottom) {
                    if (j == diamondBoundLeft) {
                        mOut.print("\\");
                    } else {
                        mOut.print("/");
                    }
                }
            } else {
                if (i % 2 == 0) {
                    mOut.print("=");
                } else {
                    mOut.print("-");
                }
            }
        } else {
            mOut.print(" ");
        }
    }


}
