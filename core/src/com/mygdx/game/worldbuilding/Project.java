package com.mygdx.game.worldbuilding;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.map.Map;
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

    public String name = "";
    private Set<Map> maps;
    public Set<TextureRegion> usedTextureRegions;
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

    public void save()
    {
        if(name.isEmpty())name=getName();
        if(path.isEmpty())path=getPath();

        Iterator<TextureRegion> iterator = usedTextureRegions.iterator();
        Integer counter = 0;
        while(iterator.hasNext())
        {
            new File(path+"/"+name+"/textures/"+counter);
            TextureData textureData = iterator.next().getTexture().getTextureData();
            textureData.prepare();
            PixmapIO.writePNG(new FileHandle(path+"/"+name+"/textures/"+counter+".png"), textureData.consumePixmap());
        }
    }

    private String getName()
    {
        do
        {
            name = JOptionPane.showInputDialog(null,"Input Project Title...");
        }
        while (name.isEmpty());
        return name;
    }

    private String getPath()
    {
        String path = "";
        try
        {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        }
        catch (Exception e)
        {
            System.out.println("fuck, well whatever");
        }
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Select MapMaker Path");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
        {
            path = chooser.getSelectedFile().getAbsolutePath();
            new File(path+"/"+name).mkdirs();
            new File(path+"/"+name+"/textures").mkdirs();
        }
        return path;
    }
}
