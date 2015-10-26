import java.util.Random;
import java.awt.*;
import java.awt.geom.*;
/**
 * Simulates a bouncing ball inside a box
 * 
 * @author Colin P. Goss 
 */
public class BoxBall
{
    private Random rand;
    private Ellipse2D.Double circle;
    private Color color;
    private int diameter;
    private int xPosition;
    private int yPosition;
    private int xMax;
    private int yMax;
    private int offset;
    private Canvas canvas;
    private int ySpeed;
    private int xSpeed;
    private int colorDeterminator;

    /**
     * Constructor for objects of class BouncingBall
     *
     * @param xPos  the horizontal coordinate of the ball
     * @param yPos  the vertical coordinate of the ball
     * @param ballDiameter  the diameter (in pixels) of the ball
     * @param drawingCanvas  the canvas to draw this ball on
     */
    public BoxBall(int boxOffset, Canvas drawingCanvas)
    {
        rand = new Random();
        offset = boxOffset;
        diameter = 15;
        canvas = drawingCanvas;
        xMax = (canvas.getSize().width - offset * 2);
        yMax = (canvas.getSize().height - offset * 2);
        xPosition = rand.nextInt(xMax - diameter - 1) + offset;
        yPosition = rand.nextInt(yMax - diameter - 1) + offset;
        colorDeterminator = rand.nextInt(3);
        if (colorDeterminator == 0){
            color = new Color(rand.nextFloat(), rand.nextFloat()*.5f + .5f,
                rand.nextFloat()*.5f + .5f);
        } else if (colorDeterminator ==1){
            color = new Color(rand.nextFloat()*.5f + .5f, rand.nextFloat(),
                rand.nextFloat()*.5f + .5f);
        } else {
            color = new Color(rand.nextFloat()*.5f + .5f,rand.nextFloat()*.5f + .5f,
                rand.nextFloat());
        }
        ySpeed = rand.nextInt(20) - 10;
        if (ySpeed == 0){
            ySpeed = 1;
        }
        xSpeed = (rand.nextInt(20)) - 10;
        if (xSpeed == 0){
            xSpeed = 1;
        }
    }

    /**
     * Draw this ball at its current position onto the canvas.
     **/
    public void draw()
    {
        canvas.setForegroundColor(color);
        canvas.fillCircle(xPosition, yPosition, diameter);
    }

    /**
     * Erase this ball at its current position.
     **/
    public void erase()
    {
        canvas.eraseCircle(xPosition, yPosition, diameter);
    }    

    /**
     * Move this ball according to its position and speed and redraw.
     **/
    public void move()
    {
        // remove from canvas at the current position
        this.erase();
        
        // compute new position
        yPosition += ySpeed;
        xPosition += xSpeed;
        
        // check if it has hit the box edge on x axis
        if (xPosition - diameter <= offset){ //minimum bound
            xPosition = offset + (xPosition - offset);
            xSpeed = Math.abs(xSpeed);
        } else if (xPosition + diameter >= xMax + offset){ //max bound
            xPosition = xMax - (xMax - xPosition);
            xSpeed = 0 - xSpeed;
        }//end x else-if
        // check if it has hit the box edge on y axis
        if (yPosition - diameter <= offset){//minimum bound
            yPosition = offset + (yPosition - offset);
            ySpeed = Math.abs(xSpeed);
        } else if (yPosition + diameter >= yMax + offset){
            yPosition = yMax - (yMax - yPosition);
            ySpeed = 0 - ySpeed;
        }//end y else-if
        
        // draw again at new position
        this.draw();
    }    

    /**
     * return the horizontal position of this ball
     */
    public int getXPosition()
    {
        return xPosition;
    }

    /**
     * return the vertical position of this ball
     */
    public int getYPosition()
    {
        return yPosition;
    }
}
