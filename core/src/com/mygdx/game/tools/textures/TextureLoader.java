package com.mygdx.game.tools.textures;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;
import java.util.List;

public abstract class TextureLoader
{
    private static TextureRegion[] textures;

    public static void loadTextures()
    {
        List<TextureRegion> textureList = new ArrayList<>();

        Texture spriteSheet32x32 = new Texture("assets/SpriteSheet32x32.png");
        int width = spriteSheet32x32.getWidth()/32;
        int height = spriteSheet32x32.getHeight()/32;
        for (int cHeight = 0; cHeight < height; cHeight++)
        {
            for (int cWidth = 0; cWidth < width; cWidth++)
            {
                textureList.add(new TextureRegion(spriteSheet32x32,cWidth*32,cHeight*32,32,32));
            }
        }
        textures = new TextureRegion[textureList.size()];
        textures = textureList.toArray(textures);
    }

    public static TextureRegion get(int index)
    {
        return textures[index];
    }


    public static void dispose()
    {
        textures[0].getTexture().dispose();
    }
}









/*
        List<Texture> textureList = new ArrayList<Texture>();

        File assets = new File(currentFolder);
        File[] assetsList = assets.listFiles();
        for (int c1 = 0; c1 < assetsList.length; c1++)
        {
            File currentFile = assetsList[c1];
            if (currentFile.isFile())
            {
                if(currentFile.getName().endsWith(".png"))
                {
                    textureList.add(new Texture(currentFile.getPath()));
                }
            }
            else
            {
                loadTexturesRecHelpMethod(currentFile.getPath().toString());
            }
        }*/