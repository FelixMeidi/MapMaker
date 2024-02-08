package com.mygdx.game.Map;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Textures.TextureLoader;

public class Tile
{
    public Tile(int texturesIndex)
    {
        this.texturesIndex = texturesIndex;
    }

    private int texturesIndex;

    public int getTexturesIndex()
    {
        return texturesIndex;
    }
    public TextureRegion getTextureRegion()
    {
        return TextureLoader.get(getTexturesIndex());
    }


}
