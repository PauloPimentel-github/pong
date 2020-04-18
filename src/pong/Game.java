package pong;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable, KeyListener {
    private static final long serialVersionUID = 1L;

    /* Atributos da classe */
    public static final int WIDTH = 160;
    public static final int HEIGHT = 120;
    private static final int SCALE = 3;
    private BufferedImage bufferedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    public static Player player;
    public static Enemy enemy;
    public static Ball ball;

    public Game() {
        this.setPreferredSize(this.dimension(WIDTH, HEIGHT, SCALE));
        this.addKeyListener(this);
        this.player = new Player(100, HEIGHT - 5);
        this.enemy = new Enemy(100, 0);
        this.ball = new Ball(100, HEIGHT / 2 - 1);
    }

    public static void main(String[] args) {
        Game game = new Game();
        JFrame frame = new JFrame("Pong");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(game);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        new Thread(game).start();
    }

    private void tick() {
        this.player.tick();
        this.enemy.tick();
        this.ball.tick();
    }

    private void render() {
        BufferStrategy bufferStrategy = this.getBufferStrategy();
        if (bufferStrategy == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics graphics = this.bufferedImage.getGraphics();
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, WIDTH, HEIGHT);
        this.player.render(graphics);
        this.enemy.render(graphics);
        this.ball.render(graphics);

        graphics = bufferStrategy.getDrawGraphics();
        graphics.drawImage(this.bufferedImage, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);

        bufferStrategy.show();
    }

    @Override
    public void run() {
        while (true) {
            this.tick();
            this.render();
            try {
                Thread.sleep(1000 / 60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Retorna a dimensão da janela em altura e largura
     * @param width
     * @param height
     * @param scale
     * @return Dimension
     */
    private Dimension dimension(int width, int height, int scale) {
        Dimension dimension = new Dimension(width * scale, height * scale);
        return dimension;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
            this.player.setRight(true);
        }
        else if (keyEvent.getKeyCode() == KeyEvent.VK_LEFT) {
            this.player.setLeft(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
            this.player.setRight(false);
        }
        else if (keyEvent.getKeyCode() == KeyEvent.VK_LEFT) {
            this.player.setLeft(false);
        }
    }
}
