/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tma02q3;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author laura
 */
public class BiscuitBox
{
    private int width, height;
    private final Color boxColour = Color.PINK;
    private final int boxWidth = 20;
    private final int boxHeight = 60;
    private int boxXPos;
    private int boxYPos;

    public BiscuitBox(int width, int height)
    {
        this.width = width;
        this.height = height;
        boxXPos = width / 2;
        boxYPos = height / 2;
    }

    public void draw(Graphics g)
    {
        g.setColor(boxColour);
        g.fillRect(boxXPos - boxWidth / 2, boxYPos - boxHeight / 2, boxWidth, boxHeight);  
    }

    public Rectangle getBoxArea()
    {
        return new Rectangle(boxXPos, boxYPos, boxWidth, boxHeight);
    }
}
