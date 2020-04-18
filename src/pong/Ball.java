package pong;

import java.awt.*;
import java.util.Random;

public class Ball {

    private double x, y;
    private int width, height;
    private double dx, dy;
    private double speed = 1.7;

    public Ball(double x, double y) {
        this.x = x;
        this.y = y;
        this.width = 4;
        this.height = 4;

        int angle = new Random().nextInt(120 - 45) + 45;
        this.dx = Math.cos(Math.toRadians(angle));
        this.dy = Math.sin(Math.toRadians(angle));
    }

    public void tick() {
        if (this.getX() + (this.getDx() * this.getSpeed()) >= Game.WIDTH) {
            this.dx *= -1;
        } else if (this.getX() + (this.getDx() * this.getSpeed()) < 0) {
            this.dx *= -1;
        }

        if (this.getY() >= Game.HEIGHT) {
            //Ponto do inimigo
            System.out.println("Ponto do inimigo!");
            new Game();
            return;
        } else if (this.getY() < 0) {
            //Ponto do jogador
            System.out.println("Ponto nosso!  YAYY!");
            new Game();
            return;
        }

        /* Sistema de colisão */
        Rectangle bounds = new Rectangle((int)(this.getX() + (this.getDx() * this.getSpeed())),
                                            (int)(this.getY() + (this.getDy() * this.getSpeed())),
                                            this.getWidth(), this.getHeight());
        Rectangle boundsPlayer = new Rectangle(Game.player.getX(), Game.player.getY(),
                                                Game.player.getWidth(), Game.player.getWidth());
        Rectangle boundsEnemy = new Rectangle((int) Game.enemy.getX(), (int) Game.enemy.getY(),
                                                Game.enemy.getWidth(), Game.enemy.getHeight());
        /* Se tiver colisão */
        if (bounds.intersects(boundsPlayer)) {
            int angle = new Random().nextInt(120 - 45) + 45;
            this.dx = Math.cos(Math.toRadians(angle));
            this.dy = Math.sin(Math.toRadians(angle));
            if (this.getDy() > 0) this.dy *= -1;
        } else if (bounds.intersects(boundsEnemy)) {
            int angle = new Random().nextInt(120 - 45) + 45;
            this.dx = Math.cos(Math.toRadians(angle));
            this.dy = Math.sin(Math.toRadians(angle));
            if (this.getDy() < 0) this.dy *= -1;
        }

        this.x += this.dx * this.getSpeed();
        this.y += this.dy * this.getSpeed();
    }

    public void render(Graphics graphics) {
        graphics.setColor(Color.YELLOW);
        graphics.fillRect((int) this.x,  (int) this.y, this.width, this.height);
    }

    /* GETTERS AND SETTERS */
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getDx() {
        return dx;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public double getDy() {
        return dy;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
}
