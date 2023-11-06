package Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Animation {
    private Array<TextureRegion> frames;
    private float maxFrameTime;
    private float currentFrameTime;
    private int frameCount;
    private int frame;
    private Texture birdAnimation ;

    public Animation(TextureRegion reigon,int frameCount,float cycleTime) {
        frames = new Array<TextureRegion>();
        int frameWidth = reigon.getRegionWidth() / frameCount;
        //Chia 1 phan hinh anh de lam anh dong, add cac phan do vao 1 array de su dung
        for (int i = 0; i < frameCount; i++) {
            frames.add(new TextureRegion(reigon,i * frameWidth,0,frameWidth, reigon.getRegionHeight()));
        }
        this.frameCount=frameCount;
        maxFrameTime =cycleTime /frameCount;
        frame =0;
    }

    public void update(float deltaTime) {
        currentFrameTime += deltaTime;
        if(currentFrameTime > maxFrameTime) {
            frame++;
            currentFrameTime = 0;
        }
        if (frame >= frameCount) {
            frame =0;
        }
    }

    public TextureRegion getFrame() {
        return frames.get(frame);
    }
}
