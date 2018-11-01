import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Interface extends JPanel implements Runnable {
    public static final int height = 500;
    public static final int weight = 500;

    private boolean GameOver;
    private boolean GameWin;
    private int     Score;

    private List<Snake> Snakes;
    private Snake SnakeHead;

    private Graphics2D graphics2D;
    private BufferedImage bufferedImage;
    private Food food;

    private int PaceX;
    private int PaceY;

    private boolean Up;
    private boolean Down;
    private boolean Left;
    private boolean Right;

    public Interface() {
        InitGame();
        if (isFocusable()) { setFocusable(true); }
        requestFocusInWindow(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        Up = true;
                        break;
                    case KeyEvent.VK_DOWN:
                        Down = true;
                        break;
                    case KeyEvent.VK_LEFT:
                        Left = true;
                        break;
                    case KeyEvent.VK_RIGHT:
                        Right = true;
                        break;
                    default:
                        break;
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        Up = false;
                        break;
                    case KeyEvent.VK_DOWN:
                        Down = false;
                        break;
                    case KeyEvent.VK_LEFT:
                        Left = false;
                        break;
                    case KeyEvent.VK_RIGHT:
                        Right = false;
                        break;
                    default:
                        break;
                }
            }
        });
    }
    private void InitGame() {
        Snakes = new ArrayList<>();
        SnakeHead = new Snake(10);
        bufferedImage = new BufferedImage(weight,height,BufferedImage.TYPE_INT_ARGB);
        graphics2D = bufferedImage.createGraphics();
        food = new Food(10);
        GameOver  = false;
        GameWin   = false;
        Score = 0;
        PaceX = 0;
        PaceY = 0;
        SnakeHead.setLocation(height / 2,weight / 2);
        Snakes.add(SnakeHead);
        for (int i = 0;i < 3;i++) {
            Snakes.add(new Snake(10));
        }
        CreateFood();
    }
    private void Color_function() {
        graphics2D.setBackground(Color.BLACK);
        graphics2D.clearRect(0,0,weight,height);
        for (Snake Snake : Snakes) {
            Snake.Render(graphics2D);
        }
        graphics2D.setColor(Color.RED);
        food.Render(graphics2D);
        graphics2D.setColor(Color.WHITE);
        graphics2D.drawString("Score: " + Score,10,10);
        Graphics graphics = getGraphics();
        graphics.drawImage(bufferedImage,0,0,null);
        graphics.dispose();
    }
    @Override
    public void addNotify() {
        super.addNotify();
        new Thread(this).start();
    }
    @Override
    public void run() {
        while (true) {
            Update();
            Color_function();

            try {
                Thread.sleep(100);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private void Update() {
        if (GameOver) {
            System.out.println("Game Over");
            Runtime.getRuntime().exit(1);
        }
        if (GameWin) {
            System.out.println("Game win");
            Runtime.getRuntime().exit(1);
        }
        if (Up && PaceY == 0) { PaceY = -10; PaceX = 0; }
        if (Down && PaceY == 0) { PaceY = 10; PaceX = 0; }
        if (Left && PaceX == 0) { PaceX = -10; PaceY = 0; }
        if (Right && PaceX == 0) { PaceX = 10; PaceY = 0; }
        if (SnakeHead.getPointX() < 0) { SnakeHead.setPointX(weight); }
        if (SnakeHead.getPointX() > weight) { SnakeHead.setPointX(0); }
        if (SnakeHead.getPointY() < 0) { SnakeHead.setPointY(weight); }
        if (SnakeHead.getPointY() > height) { SnakeHead.setPointY(0); }
        if (PaceX != 0 || PaceY != 0) {
            for (int i = Snakes.size() - 1;i > 0;i--) {
                Snakes.get(i).setLocation(Snakes.get(i - 1).getPointX(),Snakes.get(i - 1).getPointY());
            }
            SnakeHead.move(PaceX,PaceY);
        }
        for (Snake Snake : Snakes) {
            if (Snake.eatSelf(SnakeHead)) { GameOver = true; }
        }
        if (food.snake_food(SnakeHead)) {
            if (Score == 5) {
                GameWin = true;
            }
            Snake Snake = new Snake(10);
            Snakes.add(Snake);
            Score += 1;
            CreateFood();
        }
    }
    private void CreateFood() {
        int x;
        int y;
        x = (int) (Math.random() * (height - 10));
        y = (int) (Math.random() * (weight - 10));
        x -= (x % 10);
        y -= (y % 10);
        food.setLocation(x,y);
    }
}
