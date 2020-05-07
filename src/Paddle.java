import java.awt.*;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Paddle {
    private int xDirection;
    private int yDirection;
    private int[] pixels;
    private Rectangle boundingBox;
    private int width = 30;
    private int height = 60;

    public Paddle(int x, int y, int col){
        boundingBox = new Rectangle(x, y, width, height);
        pixels = new int[width*height];
        for (int i = 0 ; i < pixels.length ; i++) {
            pixels[i] = col;
        }

        Random r = new Random();
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
}


  /* public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == e.VK_LEFT){
            setXDirection(-1);
        }
        if(e.getKeyCode() == e.VK_RIGHT){
            setXDirection(1);
        }
        if(e.getKeyCode() == e.VK_UP){
            setYDirection(-1);
        }
        if(e.getKeyCode() == e.VK_DOWN){
            setYDirection(1);
        }
    }

    public void keyReleased(KeyEvent e){
        if(e.getKeyCode() == e.VK_LEFT){
            setXDirection(0);
        }
        if(e.getKeyCode() == e.VK_RIGHT){
            setXDirection(0);
        }
        if(e.getKeyCode() == e.VK_UP){
            setYDirection(0);
        }
        if(e.getKeyCode() == e.VK_DOWN){
            setYDirection(0);
        }

}*/

    public void setXDirection(int xdir) {
        xDirection = 3;
    }

    public void setYDirection(int ydir){
        yDirection = 2;
    }

    public Rectangle getBoundingBox() {
        return boundingBox;
    }

    public void update(){
        boundingBox.x += xDirection;
        if(boundingBox.x <= 1) {
            boundingBox.x = 1;
        }
        if(boundingBox.x >= 380) {
            boundingBox.x = 380;
        }
        boundingBox.y += yDirection;
        if(boundingBox.y <= 0) {
            boundingBox.y = 0;
        }
        if(boundingBox.y >= 280) {
            boundingBox.y = 280;
        }
    }

    public void draw(int[] Screen, int screenWidth){
        for (int i = 0 ; i < height ; i++) {
            for (int j = 0 ; j < width ; j++) {
                Screen[(boundingBox.y+i)*screenWidth + boundingBox.x+j] = pixels[i*width+j];
            }
        }
    }
}