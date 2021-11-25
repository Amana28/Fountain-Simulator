import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

class Cloud extends Shape {

    private final Color color;


    public Cloud(World w, int width, int height, Pair position, Pair velocity, Pair acceleration, Color color){
        super(w, width, height, position, velocity, acceleration);
        this.color = color;

    }


    public void draw(Graphics g) {
        g.setColor(color);
        //draw cloud

        //center part
        g.fillOval( (int) (position.x - width / 2),
                (int) (position.y - height / 2),
                width, height);

        //lower part
        g.fillOval( (int) (position.x - width / 4),
                (int) (position.y + height / 4),
                width/2, height/2);
        //left part
        g.fillOval( (int) (position.x -  width / 2),
                (int) (position.y - height/4 ),
                width/2, height/2);
        //top part
        g.fillOval( (int) (position.x - width / 4),
                (int) (position.y - (3 * height / 4)),
                width/2, height/2);
        //right part
        g.fillOval( (int) (position.x + width / 4),
                (int) (position.y - height / 4),
                width/4, height/4);
    }
    
}
