package Sprites;

import Utility.Constrain;
import Utility.ISprites;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Tube implements ISprites<Texture,Vector2> {
    private Texture topTubeImg;
    private Texture botTubeImg;
    private Vector2 positionTop;
    private Vector2 positionBot;
    private Random random;
    private Rectangle topTubeRectangle;
    private Rectangle bottomTubeRectangle;


    public Tube(float x) {
        topTubeImg = new Texture("toptube.png");
        botTubeImg = new Texture("bottomtube.png");
        random = new Random();
        positionTop=new Vector2(x,random.nextInt(Constrain.FLUCTUATION)+Constrain.TUBE_GAP+Constrain.LOWEST_OPENING);
        positionBot= new Vector2(x,positionTop.y-Constrain.TUBE_GAP- botTubeImg.getHeight());
        //
        topTubeRectangle = new Rectangle(positionTop.x,positionTop.y, topTubeImg.getWidth(), topTubeImg.getHeight());
        bottomTubeRectangle = new Rectangle(positionBot.x,positionBot.y, botTubeImg.getWidth(), botTubeImg.getHeight());

    }
    public void reposition(float x) {
        positionTop.set(x,random.nextInt(Constrain.FLUCTUATION)+Constrain.TUBE_GAP+Constrain.LOWEST_OPENING);
        positionBot.set(x,positionTop.y-Constrain.TUBE_GAP- botTubeImg.getHeight());
        topTubeRectangle.setPosition(positionTop.x,positionTop.y);
        bottomTubeRectangle.setPosition(positionBot.x,positionBot.y);
    }

    public boolean collision(Rectangle player) {
        return player.overlaps(topTubeRectangle) || player.overlaps(bottomTubeRectangle);
    }
    //create a boolean to check if the player is pass thorugh the tube
    public boolean between(Rectangle player) {
        return player.overlaps(topTubeRectangle) && player.overlaps(bottomTubeRectangle);
    }
    //create a boolean to check if the player rectangle.x is equal to the tube rectangle.x
    public boolean equal(Rectangle player) {
        return player.x == topTubeRectangle.x;
    }

    @Override
    public void update(float deltaTime) {
    }
    public void dispose() {
        topTubeImg.dispose();
        botTubeImg.dispose();
    }
    @Override
    public Texture getImg() {
        return topTubeImg;    }
    public Texture getImgBot() {
        return botTubeImg;    }
    @Override
    public Vector2 getPosition() {
        return positionTop;    }
    public Vector2 getPositionBot() {
        return positionBot;    }

}
