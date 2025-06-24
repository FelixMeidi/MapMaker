package com.mygdx.game.worldbuilding;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.map.Map;
import com.mygdx.game.tools.vector.Vector2Int;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.w3c.dom.Text;

import javax.swing.*;
import java.io.File;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Project
{
    public Project()
    {
        usedTextureRegions = new HashSet<TextureRegion>();
        maps = new HashSet<Map>();
    }



    @Getter
    @Setter
    public String name = "";
    private Set<Map> maps;
    @Getter
    public Set<TextureRegion> usedTextureRegions;

    @Getter
    @Setter
    private String path = "";





    public void addMap(Map map)
    {
        maps.add(map);
        map.setProject(this);
    }





    public void addUsedTextureRegion(TextureRegion tx)
    {
        usedTextureRegions.add(tx);
    }





}
