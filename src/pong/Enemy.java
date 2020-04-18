package pong;

import java.awt.*;

public class Enemy {

    private double x, y;
    private int width, height;

    public Enemy(double x, double y) {
        this.x = x;
        this.y = y;
        this.width = 40;
        this.height = 5;
    }

    public void tick() {
        this.x += (Game.ball.getX() - this.getX() - 6) * 0.06;
    }

    public void render(Graphics graphics) {
        graphics.setColor(Color.RED);
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
}
