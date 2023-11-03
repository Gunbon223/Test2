package sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

import java.util.Vector;

public class Bird {
    private Vector3 position; // vi tri x y
    private Vector3 velocity;
    private Texture bird;
    public Bird(int x,int y) {
        position = new Vector3(x,y,0);
        velocity = new Vector3(0,0,0);
        bird = new Texture("bird.png");
    }

    public void update(float deltaTime) {

    }

}
