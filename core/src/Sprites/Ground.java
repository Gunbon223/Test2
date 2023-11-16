package Sprites;

import Utility.Constrain;
import Utility.ISprites;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Ground implements ISprites<Texture,Vector2> {
    private Texture groundImg;
    private Vector2 groundPosition;
    private Rectangle positionRectangle;

    public Ground(OrthographicCamera camera, int i) {
        groundImg = new Texture("ground.png");
        groundPosition = new Vector2(camera.position.x - camera.viewportWidth / 2 -140 +i*groundImg.getWidth(), Constrain.GROUND_Y_OFFSCREEN);
        positionRectangle = new Rectangle(groundPosition.x,groundPosition.y,groundImg.getWidth(),groundImg.getHeight());
    }

        // add like lube
    public void updateGround(float x) {
        groundPosition.set(x, Constrain.GROUND_Y_OFFSCREEN);
        positionRectangle.setPosition(groundPosition.x,groundPosition.y);
    }
    @Override
    public Texture getImg() {
        return groundImg;
    }

    @Override
    public Vector2 getPosition() {
        return groundPosition;
    }

    public Rectangle getPositionRectangle() {
        return positionRectangle;
    }

    @Override
    public void update(float deltaTime) {
    }
    @Override
    public void dispose() {
        groundImg.dispose();
    }


}
