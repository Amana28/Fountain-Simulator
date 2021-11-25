import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;

public class Fountain extends JPanel{
    public static final int WIDTH = 1024;
    public static final int HEIGHT = 768;
    public static final int FOUNTAIN_WIDTH = 50;
    public static final int FOUNTAIN_HEIGHT = 100;
    
    public static final int FPS = 60;
    public static final Color MAMMOTH_PURPLE = new Color(63, 31, 105);
    public static final Color DARK_GREY = new Color(66, 66, 66);

    World world;

    public Fountain(){
        world = new World(WIDTH, HEIGHT);
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    public void run()
    {
        while(true){
            world.update(1.0 / (double)FPS);
            repaint();
            try{
                Thread.sleep(1000/FPS);
            }
            catch(InterruptedException e){}
        }

    }

    
    public static void main(String[] args){
        JFrame frame = new JFrame("Fountain");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Fountain mainInstance = new Fountain();
        frame.setContentPane(mainInstance);
        frame.pack();
        frame.setVisible(true);
        mainInstance.run();
    }

    public void drawBackground (Graphics g) {
        //pale blue sky
	    g.setColor(new Color(135, 206, 206));
        g.fillRect(0, 0, WIDTH, 19*HEIGHT/20);
        //background landscape
        g.setColor(new Color(151, 124, 83));
        g.fillRect(0, 19*HEIGHT/20, WIDTH, HEIGHT/20);
        //sun
        g.setColor(new Color( 243, 130, 53));
        g.fillOval(200, 100, 150, 150);
    }

    public void drawForeground (Graphics g) {
	    g.setColor(DARK_GREY);
	    //first part
	    g.fillRect((WIDTH - FOUNTAIN_WIDTH) / 2, (HEIGHT - FOUNTAIN_HEIGHT),
                FOUNTAIN_WIDTH, FOUNTAIN_HEIGHT);
	    //second part
        g.fillRect(WIDTH / 2 - FOUNTAIN_WIDTH, HEIGHT - FOUNTAIN_HEIGHT/2,
                2 * FOUNTAIN_WIDTH, FOUNTAIN_HEIGHT/2);
        //third part
        g.fillRect(WIDTH/ 2 -  2 * FOUNTAIN_WIDTH, HEIGHT - FOUNTAIN_HEIGHT/4,
                4 * FOUNTAIN_WIDTH, FOUNTAIN_HEIGHT/4);
    }

    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);            
	    drawBackground(g);
        world.drawShapes(g);
	    drawForeground(g);
    }
}
