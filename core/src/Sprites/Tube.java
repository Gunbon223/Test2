package Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Tube implements ISprites {
    private static final Texture TOPTUBE_IMG = new Texture("toptube.png");;
    private static final Texture BOTTOMTUBE_IMG = new Texture("bottomTube.png");
    private Vector2 positionTop;
    private Vector2 positionBot;
    private Random random;
    private Rectangle topTubeRectangle;
    private Rectangle bottomTubeRectangle;
    private static final  int FLUCTUATION=130;
    private static final int TUBE_GAP =200;
    private static final int LOWEST_OPENING =120;
    public static final int TUBE_WIDTH = 52;

    public Tube(float x) {

        random = new Random();

        positionTop=new Vector2(x,random.nextInt(FLUCTUATION)+TUBE_GAP+LOWEST_OPENING);
        positionBot= new Vector2(x,positionTop.y-TUBE_GAP- BOTTOMTUBE_IMG.getHeight());
        //
        topTubeRectangle = new Rectangle(positionTop.x,positionTop.y, TOPTUBE_IMG.getWidth(), TOPTUBE_IMG.getHeight());
        bottomTubeRectangle = new Rectangle(positionBot.x,positionBot.y, BOTTOMTUBE_IMG.getWidth(), BOTTOMTUBE_IMG.getHeight());

    }

    public Texture getTopTube() {
        return TOPTUBE_IMG;
    }

    public Texture getBottomTube() {
        return BOTTOMTUBE_IMG;
    }

    public Vector2 getPositionTop() {
        return positionTop;
    }

    public Vector2 getPositionBot() {
        return positionBot;
    }

    public void reposition(float x) {
        positionTop.set(x,random.nextInt(FLUCTUATION)+TUBE_GAP+LOWEST_OPENING);
        positionBot.set(x,positionTop.y-TUBE_GAP- BOTTOMTUBE_IMG.getHeight());
        topTubeRectangle.setPosition(positionTop.x,positionTop.y);
        bottomTubeRectangle.setPosition(positionBot.x,positionBot.y);
    }

    public boolean collision(Rectangle player) {
        return player.overlaps(topTubeRectangle) || player.overlaps(bottomTubeRectangle);
    }

    @Override
    public void update(float deltaTime) {

    }

    public void dispose() {

    }
}
