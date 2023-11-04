package Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

public class Bird {
    private Vector3 position; // vi tri x y
    private Vector3 velocity;
    private float gravity = -250;
    private Texture birdImg;
    public Bird(int x,int y) {
        position = new Vector3(x,y,0);
        velocity = new Vector3(0,0,0);
        birdImg = new Texture("bird.png");
    }

    public void update(float deltaTime) {
        velocity.add(0,gravity,0);
        velocity.scl(deltaTime);
        position.add(0,velocity.y,0);
        velocity.scl(1/deltaTime);
    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getBirdImg() {
        return birdImg;
    }
}
