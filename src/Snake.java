import java.awt.*;

public class Snake {
    private int PointX;
    private int PointY;
    private int Size;

    public Snake(int Size) {
        this.Size = Size;
    }
    public int getPointX() {
        return PointX;
    }
    public void setPointX(int pointX) {
        PointX = pointX;
    }
    public int getPointY() {
        return PointY;
    }
    public void setPointY(int pointY) {
        PointY = pointY;
    }
    public void setLocation(int pointX,int pointY) {
        PointX = pointX;
        PointY = pointY;
    }
    public void Render(Graphics2D graphics2D) {
        graphics2D.fillRect(PointX + 1,PointY + 1,Size - 2,Size - 2);
    }
    public void move(int x,int y) {
        PointX += x;
        PointY += y;
    }
    public Boolean eatSelf(Snake Snake) {
        if (Snake == this) { return false; }
        return get_Current_Node().intersects(Snake.get_Current_Node());
    }
    public Rectangle get_Current_Node(){
        return new Rectangle(PointX,PointY,Size,Size);
    }
}
