/*
 * Picture.java
 *
 * This class includes a constructor that can be used to set up a Picture object
 * to allow drawing on an area of known size
 * The method draw receives a graphics context 
 *
 */
package tma02q3;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Point;

/**
 *
 * @author M257 CT
 */
public class Picture
{
    //TODO add instance variables

    private int width, height;
    private Color catHead;
    private final Color EARS_AND_MOUTH = Color.WHITE;
    private final Color EYES_AND_NOSE = Color.BLACK;
    private final Color TONGUE = Color.RED;
    private Boolean tongueLeft;
    public Point pos;
    private int tongueXPos;
    private final int RADIUS = 20;
    private int angle;
    private int move;


    //TODO complete constructor
    public Picture(int width, int height)
    {
        this.width = width;
        this.height = height;

        //TODO complete
        catHead = Color.ORANGE;
        tongueLeft = true;
        pos = new Point();
        pos.x = (int) (Math.random() * (width - RADIUS));
        pos.y  = (int)(Math.random() * (height/2 - RADIUS));
        tongueXPos = pos.x;
        angle = -45;
        move = 3;

        
    }

    //TODO add updatePictureState method
    //parameters can be removed/changed if required
    //This method does not do any drawing - it changes the state of the
    //picture object(s). The draw method does the drawing
    public void updatePictureState()
    {
        //TODO complete
        if(tongueLeft)
        {
            angle = 45;
            tongueXPos = tongueXPos - RADIUS / 2;
            tongueLeft = false;
        }
        else
        {
            angle = -45;
            tongueXPos = tongueXPos + RADIUS / 2;
            tongueLeft = true;
        }
    }

    //TODO complete draw method
    public void draw(Graphics g)
    {
        //TODO complete
        g.setColor(catHead);
        g.fillRect(pos.x - RADIUS, pos.y - RADIUS, RADIUS*2, RADIUS);
        g.setColor(EARS_AND_MOUTH);
        g.fillRect(pos.x - (RADIUS - 2), pos.y - (RADIUS - 2), RADIUS*2 - 4, RADIUS - 4);
        g.setColor(catHead);
        g.fillOval(pos.x - RADIUS, pos.y - RADIUS, RADIUS*2, RADIUS*2);
        g.setColor(EARS_AND_MOUTH);
        g.fillOval(pos.x - (RADIUS / 2), pos.y, RADIUS, RADIUS);
        g.setColor(EYES_AND_NOSE);
        g.fillOval(pos.x - (RADIUS / 4) / 2, pos.y, RADIUS / 4, RADIUS / 4);
        g.fillOval(pos.x - ((RADIUS / 2) / 2), pos.y - (RADIUS / 2) / 2, RADIUS / 4, RADIUS / 4);
        g.fillOval(pos.x + (((RADIUS / 2) / 2) - (((RADIUS / 2) / 2)) / 2), pos.y - (RADIUS / 2) / 2, RADIUS / 4, RADIUS / 4);
        g.setColor(TONGUE);
        g.fillArc(tongueXPos, pos.y + RADIUS / 2, RADIUS / 2, RADIUS / 2, angle, -180);        

    }
    //TODO add further methods here if required - up to you!
    public void moveUp()
    {
        if(pos.y > 0 + RADIUS)
        {
            pos.y = pos.y - move;
        }
    }

    public void moveDown()
    {
        if(pos.y < height - RADIUS)
        {
            pos.y = pos.y + move;
        }
    }

    public void moveLeft()
    {
        if((pos.x > 0 + RADIUS) && (tongueXPos > 0 + RADIUS))
        {
        pos.x = pos.x - move;
        tongueXPos = tongueXPos - move;
        }
    }

    public void moveRight()
    {
        if((pos.x < width - RADIUS) && (tongueXPos < width - RADIUS))
        {
        pos.x = pos.x + move;
        tongueXPos = tongueXPos + move;
        }
    }

    public Point getPoint()
    {
        return pos;
    }
}
