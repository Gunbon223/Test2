package Utility;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class HUD {
    private BitmapFont font;
    private float hudVerticalMargin;
    private float hudCentreX;
    private float hudRowTop;
    private float hudRowBottom;
    private float hudWidth;

    public HUD(int sizeFont, Color color,float cameraWidth,float cameraHeight) {
        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("Roboto-Medium.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.size = sizeFont;
        fontParameter.color = color;
        font = fontGenerator.generateFont(fontParameter);
        hudVerticalMargin = font.getCapHeight() / 2;
        hudWidth = cameraWidth;
        hudCentreX = hudWidth / 2;
        hudRowTop = cameraHeight - hudVerticalMargin;
        hudRowBottom =cameraHeight - hudVerticalMargin - font.getCapHeight() - 10;
    }
    public BitmapFont getFont() {
        return font;
    }

    public float getHudCentreX() {
        return hudCentreX;
    }

    public void setHudCentreX(float hudCentreX) {
        this.hudCentreX = hudCentreX;
    }

    public float getHudVerticalMargin() {
        return hudVerticalMargin;
    }

    public float getHudRowTop() {
        return hudRowTop;
    }

    public float getHudRowBottom() {
        return hudRowBottom;
    }

    public float getHudWidth() {
        return hudWidth;
    }
}
