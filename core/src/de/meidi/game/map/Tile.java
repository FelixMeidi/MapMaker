package de.meidi.game.map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import de.meidi.game.tools.TextureLoader;
import de.meidi.game.tools.vector.Vector2IntLim;
import lombok.Getter;

public class Tile
{
    public Tile(int texturesIndex)
    {
        this.texturesIndex = texturesIndex;
    }




    @Getter
    private final int texturesIndex;







    public TextureRegion getTextureRegion()
    {
        return TextureLoader.get(getTexturesIndex());
    }







    public void draw(SpriteBatch spritebatch, Vector2IntLim offset, Integer x, Integer y)
    {
        TextureRegion r = getTextureRegion();
        spritebatch.draw(r, offset.getX() + (x*32), offset.getY() + (y*32), r.getRegionWidth(), r.getRegionHeight());
    }
}
