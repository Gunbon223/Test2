package GameState;

import Utility.Constrain;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public abstract class State {
    protected Vector3 mouse;
    protected OrthographicCamera camera;
    protected OrthographicCamera hudCamera;
    protected StateManager stateManager;

    protected State(StateManager stateManager) {
        this.stateManager=stateManager;
        camera = new OrthographicCamera();
        hudCamera = new OrthographicCamera();
        mouse = new Vector3();
    }

    protected void resetCameraToDefault() {
        camera.setToOrtho(false, Constrain.WIDTH, Constrain.HEIGHT);
    }

    public abstract void handleInput();
    public abstract void update(float deltaTime);
    public abstract void render(SpriteBatch spriteBatch);
    public abstract void  dispose();


}
