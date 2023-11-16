package Sprites;

import Utility.ISprites;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Ground implements ISprites<Texture,Vector2> {
    private Texture groundImg;
    private Vector2 groundPosition;
    private Vector2 groundPosition2;
    private Rectangle position1Rectangle;
    private Rectangle position2Rectangle;
    public static final int GROUND_Y_OFFSCREEN=-50;


    public Ground(OrthographicCamera camera, int i) {
        groundImg = new Texture("ground.png");
        groundPosition = new Vector2(camera.position.x - camera.viewportWidth / 2 -140 +i*groundImg.getWidth(), GROUND_Y_OFFSCREEN);
        position1Rectangle = new Rectangle(groundPosition.x,groundPosition.y,groundImg.getWidth(),groundImg.getHeight());
    }

        // add like lube
    public void updateGround(float x) {
        groundPosition.set(x,GROUND_Y_OFFSCREEN);
        position1Rectangle.setPosition(groundPosition.x,groundPosition.y);
    }
    @Override
    public Texture getImg() {
        return groundImg;
    }

    @Override
    public Vector2 getPosition() {
        return groundPosition;
    }

    public Rectangle getPosition1Rectangle() {
        return position1Rectangle;
    }

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void dispose() {

    }


}
