import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;


public class Graphics extends Canvas implements Runnable {
    private String title = "Graphics";
    private int width;
    private int height;

    private JFrame frame;
    private BufferedImage image;
    private int[] pixels;
    private int scale;

    private Thread thread;
    private boolean running = false;
    private int fps = 60;
    private int ups = 60;

    private Ball b;
    private Paddle paddle;
    private Paddle paddle1;
    private Paddle paddle2;
    private Paddle paddle3;
    private Paddle paddle4;
    private Paddle paddle5;
    private Paddle paddle6;
    private Paddle paddle7;
    private Paddle paddle8;
    private Paddle paddle9;






    public Graphics(int w, int h, int scale) {
        this.width = w;
        this.height = h;
        this.scale = scale;
        image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

        pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
        Dimension size = new Dimension(scale * width, scale * height);
        setPreferredSize(size);
        frame = new JFrame();
        frame.setTitle(title);
        frame.add(this);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        this.addKeyListener(new MyKeyListener());
        this.requestFocus();

        b = new Ball(200, 100);
        paddle = new Paddle(10, 10, 0xFF00000);
        paddle1 = new Paddle(50, 10, 0xFF00000);
        paddle2 = new Paddle(90, 10, 0xFF0f000);
        paddle3 = new Paddle(130, 10, 0xFF00f00);
        paddle4 = new Paddle(170, 10, 0xFF00000);
        paddle5 = new Paddle(210, 10, 0xFF00ff0);
        paddle6 = new Paddle(250, 10, 0xFF00000);
        paddle7 = new Paddle(290, 10, 0xFF00000);
        paddle8 = new Paddle(330, 10, 0xFF00000);
        paddle9 = new Paddle(370, 10, 0xFF00000);






    }

    private void draw() {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = 0xFF000000;
        }
        b.draw(pixels, width);
        paddle.draw(pixels, width);
        paddle1.draw(pixels, width);
        paddle2.draw(pixels, width);
        paddle3.draw(pixels, width);
        paddle4.draw(pixels, width);
        paddle5.draw(pixels, width);
        paddle6.draw(pixels, width);
        paddle7.draw(pixels, width);
        paddle8.draw(pixels, width);
        paddle9.draw(pixels, width);







        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        java.awt.Graphics g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        g.dispose();
        bs.show();
    }



    private void update() {

        b.update(paddle.getBoundingBox());
        b.update(paddle1.getBoundingBox());
        b.update(paddle2.getBoundingBox());
        b.update(paddle3.getBoundingBox());
        b.update(paddle4.getBoundingBox());
        b.update(paddle5.getBoundingBox());
        b.update(paddle6.getBoundingBox());
        b.update(paddle7.getBoundingBox());
        b.update(paddle8.getBoundingBox());
        b.update(paddle9.getBoundingBox());



        paddle.update();
        paddle1.update();
        paddle2.update();
        paddle3.update();
        paddle4.update();
        paddle5.update();
        paddle6.update();
        paddle7.update();
        paddle8.update();
        paddle9.update();



    }



    public synchronized void start() {
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        double frameUpdateinteval = 1000000000.0 / fps;
        double stateUpdateinteval = 1000000000.0 / ups;
        double deltaFrame = 0;
        double deltaUpdate = 0;
        long lastTime = System.nanoTime();

        while (running) {
            long now = System.nanoTime();
            deltaFrame += (now - lastTime) / frameUpdateinteval;
            deltaUpdate += (now - lastTime) / stateUpdateinteval;
            lastTime = now;

            while (deltaUpdate >= 1) {
                update();
                deltaUpdate--;
            }

            while (deltaFrame >= 1) {
                draw();
                deltaFrame--;
            }
        }
        stop();
    }

    private class MyKeyListener implements KeyListener {
        @Override
        public void keyTyped(KeyEvent keyEvent) {

        }

        @Override
        public void keyPressed(KeyEvent keyEvent) {
            b.keyPressed(keyEvent);
        }

        @Override
        public void keyReleased(KeyEvent keyEvent) {
            b.keyReleased(keyEvent);
        }
    }
}


