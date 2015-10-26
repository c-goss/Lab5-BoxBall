import java.awt.Color;

/**
 * Class BallDemo - a short demonstration showing animation with the 
 * Canvas class. 
 *
 * @author Michael KÃ¶lling and David J. Barnes, boxBounce by Colin P. Goss
 * @version 2015.10.23
 */

public class BallDemo   
{
    private Canvas myCanvas;

    /**
     * Create a BallDemo object. Creates a fresh canvas and makes it visible.
     */
    public BallDemo()
    {
        myCanvas = new Canvas("Ball Demo", 600, 500);
    }

    /**
     * Create a box on the canvas and fill it with BoxBall objects.
     * @param the number of BoxBall objects to be created.
     */
    public void boxBounce(int ballNum)
    {
        if (5 > ballNum || ballNum > 50){
        System.out.println("There must be between 5 and 50 balls.");
        }else{
            Color bgColor = new Color(0,0,0);
            myCanvas.setVisible(true);
            int keepBouncing = 0;
            //establish balls
            BoxBall[] balls = new BoxBall[ballNum];
            final int OFFSET = 50;
           
           for (int i = 0; i < balls.length; i++){
            balls[i] = new BoxBall(OFFSET, myCanvas);
            balls[i].draw();
            }
            
           //bounce your balls 
           
           while (keepBouncing < 1000){
               myCanvas.wait(50);
               keepBouncing++;
            for (BoxBall ball : balls){
                ball.move();
                myCanvas.setForegroundColor(bgColor);
                myCanvas.drawLine(OFFSET, OFFSET,
                OFFSET, (myCanvas.getSize().height - OFFSET));//left wall
                myCanvas.drawLine(OFFSET, OFFSET,
                (myCanvas.getSize().width - OFFSET), OFFSET); //top wall
                myCanvas.drawLine((myCanvas.getSize().width - OFFSET), (myCanvas.getSize().height - OFFSET),
                          (myCanvas.getSize().width - OFFSET), OFFSET); //right wall
                          myCanvas.drawLine(OFFSET, (myCanvas.getSize().height - OFFSET),
                          (myCanvas.getSize().width - OFFSET),(myCanvas.getSize().height - OFFSET)); //bottom wall
                }//end for
            }//end while 
        }//end if-else 
    }//end boxBounce
    
    /**
     * Simulate two bouncing balls
     */
    public void bounce()
    {
        int ground = 400;   // position of the ground line

        myCanvas.setVisible(true);

        // draw the ground
        myCanvas.drawLine(50, ground, 550, ground);

        // crate and show the balls
        BouncingBall ball = new BouncingBall(50, 50, 16, Color.BLUE, ground, myCanvas);
        ball.draw();
        BouncingBall ball2 = new BouncingBall(70, 80, 20, Color.RED, ground, myCanvas);
        ball2.draw();
        
        // make them bounce
        boolean finished =  false;
        while(!finished) {
            myCanvas.wait(50);           // small delay
            ball.move();
            ball2.move();
            // stop once ball has travelled a certain distance on x axis
            if(ball.getXPosition() >= 550 || ball2.getXPosition() >= 550) {
                finished = true;
            }
        }
    }
}
