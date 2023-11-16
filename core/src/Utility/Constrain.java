package Utility;

public class Constrain {
    public static final int WIDTH = 540;
    public static final int HEIGHT = 860;
    //CAMERA
    public static final float SCALE = 1.5f;
    public static final float CAMERA_WIDTH = (float) (WIDTH / SCALE);
    public static final float CAMERA_HEIGHT = (float) (HEIGHT / SCALE);
    //BIRD
    public static final int START_BIRD_SPEED = 120;
    public static final float GRAVITY = -9.8f;
    public static final float JUMP_VELOCITY = 400f;

    //TUBE
    public static final int TUBE_SPACING=150;
    public static final int TUBE_COUNT=3;
    public static final int FLUCTUATION=250;
    public static final int TUBE_GAP =120;
    public static final int LOWEST_TOPTUBE =120;
    public static final int TUBE_WIDTH = 52;
    // PLAYSTATE
    public static final int START_POSITION_X = 0;
    public static final int START_POSITION_Y = HEIGHT/4 + 50;
    public static final int TUBE_START_POSITION_X = 600;
    //GROUND
    public static final int GROUND_Y_OFFSCREEN = -50;

}