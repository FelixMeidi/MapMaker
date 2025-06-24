package com.mygdx.game.tools;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.map.Map;
import com.mygdx.game.worldbuilding.Project;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

public abstract class DataManager
{
    public static void saveProject(Project project)
    {
        if(project.getName().isEmpty())project.setName(findName());
        if(project.getPath().isEmpty())project.setPath(findPath());
        new File(project.getPath()+"/"+project.getName());
        new File(project.getPath()+"/"+project.getName()+"/textures");






        Integer width = 0;
        Integer height = 0;
        Iterator<TextureRegion> iterator = project.getUsedTextureRegions().iterator();
        while(iterator.hasNext())
        {
            TextureRegion txr = iterator.next();
            if(width==0||txr.getRegionWidth()>width) width = txr.getRegionWidth();
            height+=txr.getRegionHeight();

        }
        iterator = project.getUsedTextureRegions().iterator();







        Pixmap fullTexture = new Pixmap(width,height, Pixmap.Format.RGBA8888);
        Integer completedHeight = 0;
        while(iterator.hasNext())
        {
            TextureRegion textureRegion = iterator.next();
            textureRegion.getTexture().getTextureData().prepare();
            fullTexture.drawPixmap
                    (
                            textureRegion.getTexture().getTextureData().consumePixmap(),
                            0,
                            completedHeight,
                            textureRegion.getRegionX(),
                            textureRegion.getRegionY(),
                            textureRegion.getRegionWidth(),
                            textureRegion.getRegionHeight()
                    );
            completedHeight+=textureRegion.getRegionHeight();
        }

        PixmapIO.writePNG(new FileHandle(project.getPath() +"/"+project.getName() +"/textures/textures.png"), fullTexture);
    }


    public void loadProject()
    {
        String path = findPath();
        File f = new File(path);
        String name = f.getName();
        String[] files =  f.list();
        for(String fileName : files)
        {

        }
    }


    private static String findName()
    {
        String name;
        do
        {
            name = JOptionPane.showInputDialog(null,"Input Project Title...");
        }
        while (name == null||name.isEmpty());
        return name;
    }

    public static String findPath()
    {
        String path = "";
        try
        {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        }
        catch (Exception e)
        {
            System.out.println("fuck");
        }
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Select MapMaker Path");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
        {
            path = chooser.getSelectedFile().getAbsolutePath();

        }
        return path;
    }
}
