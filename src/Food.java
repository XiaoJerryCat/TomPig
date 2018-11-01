import java.awt.*;

public class Food {
    private int PointX;
    private int PointY;
    private int Size;

    public Food(int Size) {
        this.Size = Size;
    }
    public void setLocation(int pointX,int pointY) {
        PointX = pointX;
        PointY = pointY;
    }
    public void Render(Graphics2D graphics2D) { graphics2D.fillRect(PointX + 1,PointY + 1,Size - 2,Size - 2); }
    public Boolean snake_food(Snake Snake) {
        if (get_Current_Node().intersects(Snake.get_Current_Node())) { return true; }
        return false;
    }
    public Rectangle get_Current_Node(){ return new Rectangle(PointX,PointY,Size,Size); }
}
