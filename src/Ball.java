import java.awt.*;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Ball {
    private int xDirection, yDirection;
    private int[] pixels;
    private Rectangle boundingBox;
    private int height = 20;
    private int width = 10;

    public Ball(int x, int y) {
        pixels = new int[width * height];


        for (int i = 0; i < pixels.length; i++)
            pixels[i] = 0xFFFFFFFF;

        boundingBox = new Rectangle(x, y, width, height);

 /*      Random r = new Random();
        int rDir = r.nextInt(1);
        if (rDir == 0) {
            rDir--;
        }
        setXDirection(rDir);
        int yrDir = r.nextInt(1);
        if (yrDir == 0) {
            yrDir--;
        }
        setYDirection(yrDir);
   */ }

    public void setXDirection(int xdir) {xDirection = xdir;}

    public void setYDirection(int ydir) {yDirection = ydir;}

    public int getXDirection() {return xDirection;}

    public int getYDirection() {return yDirection;}

    public void move() {
        boundingBox.x += xDirection;
        boundingBox.y += yDirection;
        System.out.println();
        //Bounce the ball when edge is detected
        if (boundingBox.x <= 0) {
            setXDirection(+1);
        }
        if (boundingBox.x >= 385) {
            setXDirection(-1);
        }
        if (boundingBox.y <= 0) setYDirection(+1);
        if (boundingBox.y >= 285) setYDirection(-1);
    }

    public void update(Rectangle r) {
        move();
        collision(r);
    }
    public void draw(int[] Screen, int screenWidth) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Screen[(boundingBox.y + i) * screenWidth + boundingBox.x + j] = pixels[i * width + j];
            }
        }
    }

    public boolean collision(Rectangle r) {
        if (boundingBox.intersects(r)) {
            return true;
        }
        return false;
    }



    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == e.VK_LEFT) {
            setXDirection(-3);
        }
        if (e.getKeyCode() == e.VK_RIGHT) {
            setXDirection(3);
        }
        if (e.getKeyCode() == e.VK_UP) {
            setYDirection(0);
        }
        if (e.getKeyCode() == e.VK_DOWN) {
            setYDirection(0);
        }
    }

    public void keyReleased(KeyEvent keyEvent) {
        setXDirection(0);
    }
}