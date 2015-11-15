/*
 * M257DrawingApplication.java
 *
 */
package tma02q3;

import java.awt.BorderLayout;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author M257 CT
 */
public class M257DrawingApplication extends JFrame
{
    // instance variables provided

    private final int FRAME_WIDTH = 500;
    private final int FRAME_HEIGHT = 500;
    private DrawingPanel drawingPanel;

    // TODO add further instance variables as required
    /**
     * Creates a new instance of M257DrawingApplication
     */
    public M257DrawingApplication(String title)
    {
        super(title);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setResizable(false);
        setLayout(new BorderLayout());

        //set visible here, now size is available
        setVisible(true);

        drawingPanel = new DrawingPanel(getAvailableWidth(),
                                        getAvailableHeight());
        add(drawingPanel);

        // given exiting on close
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //TODO add registering of any event handlers here
        addKeyListener(new KeyList());

    }

    //These methods should only be called after the frame is visible.
    //They tell you about the available width and height in the frame
    private int getAvailableWidth()
    {
        return getWidth() - getInsets().left - getInsets().right;
    }

    private int getAvailableHeight()
    {
        return getHeight() - getInsets().top - getInsets().bottom;
    }

    //TODO complete update method
    //this method drives the application
    public void update()
    {
        //the idea here is to loop
        //and change the picture
        //this involves updating the content of the drawingPanel
        // by calling the panel's updatePictureState method
        while(true)
        {
            drawingPanel.updatePictureState();
            super.repaint();
            try
            {
                Thread.sleep(200);
            }
            catch  (InterruptedException  e)
            {
                System.exit(0);
            }
        }
    }

    // inner class on which to draw everything - you can add
    // to this class as much as you like
    private class DrawingPanel extends JPanel // provided
    {

        private Picture myPicture;
        private BiscuitBox biscuitBox;
        private int numberOfBiscuitsLeft;
        private List<Biscuit> biscuits;
        private int biscuitNumber;
        private boolean contactWithBoxMade;

        // add further instance variables if required
        private int width, height;


        public DrawingPanel(int width, int height) // given
        {
            this.width = width;
            this.height = height;
            myPicture = new Picture(width, height);
            biscuitBox = new BiscuitBox(width, height);
            numberOfBiscuitsLeft = 20;
            biscuitNumber = 0;
            contactWithBoxMade = false;
            biscuits = new ArrayList<Biscuit>(numberOfBiscuitsLeft);
            for(int i = 0; i < numberOfBiscuitsLeft; i++)
            {
                biscuits.add(new Biscuit(this.width, this.height));
            }            
            setSize(width, height);
        }

        //this method is invoked automatically when repaint occurs in
        //the outer container
        public void paintComponent(Graphics g) // given
        {
            super.paintComponent(g);
            biscuitBox.draw(g);
            myPicture.draw(g);
            for(int i = 0; i < biscuits.size(); i++)
            {
                biscuits.get(i).draw(g);
            }
             //This does the redrawing based on current state
         }

        // TODO complete updatePictureState method
        //this is about updating the state of elements in the picture
        //not about redrawing
        public void updatePictureState()
        {
            //TODO update state of myPicture
            myPicture.updatePictureState();
            if(contactWithBox())
            {
                for(int i = 0; i < biscuits.size(); i++)
                {
                    biscuits.get(i).updatePictureState();
                    contactWithBoxMade = true;
                }
            }
            if(contactWithBoxMade)
            {
                for(int i = 0; i < biscuits.size(); i++)
                {
                    if(biscuits.get(i).contactWithPicture(myPicture.getPoint()))
                    {
                        biscuits.get(i).changeBiscuitColour();
                        biscuits.get(i).updatePictureState();
                    }
                }
            }
        }

        //TODO add further methods as required
        public Picture getPicture()
        {
            return myPicture;
        }

        public boolean contactWithBox()
        {
             return biscuitBox.getBoxArea().contains(myPicture.getPoint());
        }

        public boolean contactWithBiscuit()
        {

            boolean eat = false;
            if(biscuits.get(biscuitNumber).getBiscuitArea().contains(myPicture.getPoint()))
            {
                eat = true;
                biscuits.remove(biscuitNumber);
            }
            return eat;
        }
    }

    //TODO add further (inner) classes as required
    private class KeyList extends KeyAdapter
    {
        public void keyPressed(KeyEvent k)
        {
            if(k.getKeyCode() == KeyEvent.VK_A)
            {
                drawingPanel.getPicture().moveUp();
            }
            if(k.getKeyCode() == KeyEvent.VK_Z)
            {
                drawingPanel.getPicture().moveDown();
            }
            if(k.getKeyCode() == KeyEvent.VK_N)
            {
                drawingPanel.getPicture().moveLeft();
            }
            if(k.getKeyCode() == KeyEvent.VK_M)
            {
                drawingPanel.getPicture().moveRight();
            }
        }
    }
}
