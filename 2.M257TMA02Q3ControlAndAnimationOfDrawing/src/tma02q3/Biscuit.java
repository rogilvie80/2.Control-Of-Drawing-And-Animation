/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tma02q3;

import java.awt.Color;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author ross
 */
public class Biscuit
{
    private int width, height;
    private Color biscuitColour;
    private final int biscuitWidth = 10;
    private final int biscuitHeight = 5;
    private Point biscuitPos;
    private boolean outOfBox;
    private boolean eaten;

    public Biscuit(int width, int height)
    {
        this.width = width;
        this.height = height;
        biscuitColour = Color.BLACK;
        biscuitPos = new Point();
        biscuitPos.x = (int) (Math.random() * (width - biscuitWidth));
        biscuitPos.y = (int) (Math.random() * (height - biscuitHeight));
        outOfBox = false;
        eaten = false;
    }

    public void updatePictureState()
    {
        outOfBox = true;
    }

    public void draw(Graphics g)
    {
        if(outOfBox)
        {
            g.setColor(biscuitColour);
            g.fillRect(biscuitPos.y, biscuitPos.x, biscuitWidth, biscuitHeight);
        }
    }
    
    public Rectangle getBiscuitArea()
    {
        return new Rectangle(biscuitPos.x, biscuitPos.y, biscuitWidth, biscuitHeight);
    }

    public void changeBiscuitColour()
    {
        biscuitColour = Color.WHITE;
    }

    public boolean getBiscuitState()
    {
        return eaten;
    }

    public boolean contactWithPicture(Point point)
    {
         return this.getBiscuitArea().contains(point);
    }
}
