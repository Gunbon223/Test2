package GameState;

import Utility.Constrain;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public abstract class State {
    protected OrthographicCamera camera;
    protected OrthographicCamera menuCamera;
    protected StateManager stateManager;

    protected State(StateManager stateManager) {
        this.stateManager=stateManager;
        camera = new OrthographicCamera();
        menuCamera = new OrthographicCamera();
    }

    public abstract void handleInput();
    public abstract void update(float deltaTime);
    public abstract void render(SpriteBatch spriteBatch);
    public abstract void  dispose();


}
