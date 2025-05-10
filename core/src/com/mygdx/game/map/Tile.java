package com.mygdx.game.map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.tools.textures.TextureLoader;

public class Tile extends Image
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

    public void draw(SpriteBatch spritebatch, Integer posX, Integer posY)
    {

    }


}
