import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

class World {
    public static final int ACCEL = 200;
    public static final int DROPLET_SIZE = 4;
    public static final int CLOUD_WIDTH = 50;
    public static final int CLOUD_HEIGHT = 25;
    public static final Color CLOUD_WHITE = new Color(208, 204, 204);

    
    private int width;
    private int height;
    private Random rand;
    public double timeInterval = 2.0;

    private ShapeCollection shapes;
    private static double timeElapsed;

    public World(int width, int height){
        this.width = width;
        this.height = height;
        rand = new Random();
	    shapes = new ShapeCollection();
	    timeElapsed = 0.0;
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }


    
    ////////////////////////////////////////////////////////////
    // method: void drawShapes(Graphics g)
    // 
    // description: Draws all shapes in the World. For the basic
    // program, it will iterate over all shapes in droplets
    // draw each one. If you implement an extension that draws
    // other shapes as well, they should be drawn in this method
    // as well
    ////////////////////////////////////////////////////////////

    public void drawShapes(Graphics g){

        Shape curr = shapes.getFirst();

        while(curr != null){

            curr.draw(g);
            curr = shapes.getNext();

        }
    }

    ////////////////////////////////////////////////////////////
    // method: void update (double time)
    // 
    // description: Updates the World to the next frame, where
    // time is the amount of time elapsed between consecutive
    // frames. Each update state should do the following:
    //   1. add one or more new shapes using the addDroplet()
    //      method
    //   2. iterate over all shapes in droplets and (a) update
    //      the droplet's state (using Shape's update
    //      method), and (b) if the shape is no longer visible
    //      it should be removed from droplets.
    ////////////////////////////////////////////////////////////

    public void update (double time){

        timeElapsed += time;

        int dropletCount = rand.nextInt(5);

        for(int i=0; i<= dropletCount; i++){
            addDroplet();
        }

        //check if enough time has elapsed
        if(timeElapsed >= timeInterval){
            addCloud();
            //reset timeElapsed
            timeElapsed = 0.0;
            //get a random time interval
            timeInterval = rand.nextInt(4) + 2;
        }

        Shape curr = shapes.getFirst();

        while (curr!= null){

            if(curr.isVisible()){
                curr.update(time);

            } //else {
                //shapes.remove(curr);
            //}
            curr = shapes.getNext();
        }

    }

    ////////////////////////////////////////////////////////////
    // method: void addDroplet ()
    // 
    // description: Creates a new shape--specifically, a Disk--
    // and appends it to the droplets collection. The initial
    // position of the disk should be at the top of the
    // Fountain drawn in the "drawForeground" method in
    // Fountain.java. The initial velocity should be randomized
    // so that not all droplets follow the same trajectory.
    // Reasonable values for the initial velocity are between
    // (approximately) -50 to 50 (pixels per second) in the
    // horizontal direction, and between 100 and 300 in the
    // vertical direction. The acceleration should be set to
    // 0 in the horizontal direction, and ACCEL in the vertical
    // direction.
    ////////////////////////////////////////////////////////////
    
    private void addDroplet () {

        Pair position = new Pair(width/ 2.0,
                (height - Fountain.FOUNTAIN_HEIGHT));
        Pair velocity = new Pair(rand.nextInt(50)-25,rand.nextInt(200)- 500);
        Pair acceleration = new Pair(0.0, ACCEL);

        //central faster part of the fountain
        shapes.append(new Disk(this, DROPLET_SIZE, position, velocity, acceleration, Color.white));

        //surrounding slower part of the fountain
        //left part
        // append with a left shifted position, lower -y velocity, and increased -x velocity
        shapes.append(new Disk(this, DROPLET_SIZE, position.add(new Pair(-Fountain.FOUNTAIN_WIDTH/4.0, 0.0)),
                velocity.add(new Pair(-50, 200)), acceleration, Color.white));
        //right part
        // append with a right shifted position, lower -y velocity, and increased +x velocity
        shapes.append(new Disk(this, DROPLET_SIZE, position.add(new Pair(Fountain.FOUNTAIN_WIDTH/4.0, 0.0)),
                velocity.add(new Pair(50, 200)), acceleration, Color.white));
    }
    private void addCloud () {

        int factor = rand.nextInt(4) + 1;

        //create a randomized height for the clouds
        Pair position = new Pair(width + CLOUD_WIDTH, (height/10.0) * factor);
        Pair velocity = new Pair(-60,0.0);
        Pair acceleration = new Pair(0.0, 0.0);

        //crete a randomized cloud size
        shapes.append(new Cloud(this, CLOUD_WIDTH * factor, CLOUD_HEIGHT * factor,
                position, velocity, acceleration, CLOUD_WHITE));


    }


}
