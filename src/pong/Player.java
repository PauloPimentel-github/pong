package pong;

import java.awt.*;

public class Player {

    private boolean right, left;
    private int x,y;
    private int width, height;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = 40;
        this.height = 5;
    }

    public void tick() {
        if (this.right) {
            this.x++;
        }
        else if (this.left) {
            this.x--;
        }

        if (this.x + this.width > Game.WIDTH) {
            this.x = Game.WIDTH - this.width;
        }
        else if (this.x < 0) {
            this.x = 0;
        }
    }

    public void render(Graphics graphics) {
        graphics.setColor(Color.BLUE);
        graphics.fillRect(this.x,  this.y, this.width, this.height);
    }

    /* GETTERS AND SETTERS */
    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
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
