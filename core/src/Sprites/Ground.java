package Sprites;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class Ground {
    private Texture groundImg;
    private Vector2 groundPosition;
    private Vector2 groundPosition2;
    private Rectangle position1Rectangle;
    private Rectangle position2Rectangle;
    private ArrayList<Ground> grounds;
    public static final int GROUND_Y_OFFSCREEN=-50;


    public Ground(OrthographicCamera camera, int i) {
        groundImg = new Texture("ground.png");
        groundPosition = new Vector2(camera.position.x - camera.viewportWidth / 2 -40, GROUND_Y_OFFSCREEN);
        if (i>2) {
            groundPosition = new Vector2(camera.position.x - camera.viewportWidth / 2 -40 +i*groundImg.getWidth(), GROUND_Y_OFFSCREEN);

        }

    }
        // add like lube
    public void updateGround(OrthographicCamera camera) {
        if(camera.position.x - (camera.viewportWidth)/2 +150 > groundPosition1.x + groundImg.getWidth() ) {
            groundPosition1.add(groundImg.getWidth()*2,0);
        }
        if(camera.position.x - (camera.viewportWidth)/2 +150 > groundPosition2.x + groundImg.getWidth() ) {
            groundPosition2.add(groundImg.getWidth()*2,0);
        }
    }



    public Texture getGroundImg() {
        return groundImg;
    }

    public void setGroundImg(Texture groundImg) {
        this.groundImg = groundImg;
    }

    public Vector2 getGroundPosition1() {
        return groundPosition1;
    }

    public void setGroundPosition1(Vector2 groundPosition1) {
        this.groundPosition1 = groundPosition1;
    }

    public Vector2 getGroundPosition2() {
        return groundPosition2;
    }

    public void setGroundPosition2(Vector2 groundPosition2) {
        this.groundPosition2 = groundPosition2;
    }

    public Rectangle getPosition1Rectangle() {
        return position1Rectangle;
    }

    public void setPosition1Rectangle(Rectangle position1Rectangle) {
        this.position1Rectangle = position1Rectangle;
    }

    public Rectangle getPosition2Rectangle() {
        return position2Rectangle;
    }

    public void setPosition2Rectangle(Rectangle position2Rectangle) {
        this.position2Rectangle = position2Rectangle;
    }
}
