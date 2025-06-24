package com.mygdx.game.tools;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.map.Chunk;
import com.mygdx.game.map.Map;
import com.mygdx.game.map.Tile;
import com.mygdx.game.tools.vector.Vector2IntLim;
import com.mygdx.game.worldbuilding.Project;
import javax.swing.*;
import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public abstract class DataManager
{
    public static void saveProject(Project project) throws IOException
    {
        FileWriter fw = null;
        try
        {
            //region create Project path
            try
            {
                Files.createDirectory(Paths.get(project.getPath() + "/" + project.getName() + "/"));
                Files.createDirectory(Paths.get(project.getPath() + "/" + project.getName() + "/maps/"));
            }
            catch (FileAlreadyExistsException e)
            {
                System.out.println("File " + e.getMessage() + " already exists.");
            }
            //endregion

            //region get reqired size
            Integer width = 0;
            Integer height = 0;

            Set<Integer> usedTexturesIndexes = new LinkedHashSet<>();

            List<Integer> usedTextureIndexesAmounts = new ArrayList<>();

            for (Map m : project.getMaps())
            {
                for (int c = 0; c < m.usedTextureIndexes.size(); c++)
                {
                    if (usedTexturesIndexes.add(m.usedTextureIndexes.get(c))) ;
                    {
                        usedTextureIndexesAmounts.add(0);
                    }
                    usedTextureIndexesAmounts.set(
                            c,
                            usedTextureIndexesAmounts.get(c) + m.usedTextureIndexesAmounts.get(c)
                                                 );
                }
            }


            for (Integer usedTexturesIndex : usedTexturesIndexes)
            {
                TextureRegion tr = TextureLoader.get(usedTexturesIndex);
                height += tr.getRegionHeight();
                if (tr.getRegionWidth() > width) width = tr.getRegionWidth();
            }


            //endregion

            //region create meta file
            File f;
            try
            {
                f = Files.createFile(Paths.get(project.getPath() + "/" + project.getName() + "/resourcesmeta.mmmeta")).toFile();
            }
            catch (FileAlreadyExistsException e)
            {
                System.out.println("File " + e.getMessage() + " already exists.");
                f = Paths.get(project.getPath() + "/" + project.getName() + "/resourcesmeta.mmmeta").toFile();
            }
            //endregion

            //region write png and write meta file
            fw = new FileWriter(f);
            fw.write(width + "/" + height + "\r\n");

            Iterator<Integer> amountIterator = usedTextureIndexesAmounts.iterator();
            Iterator<Integer> iterator = usedTexturesIndexes.iterator();
            Pixmap fullTexture = new Pixmap(width, height, Pixmap.Format.RGBA8888);
            Integer completedHeight = 0;
            while (iterator.hasNext())
            {
                TextureRegion textureRegion = TextureLoader.get(iterator.next());
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
                completedHeight += textureRegion.getRegionHeight();
                fw.write(TextureLoader.getIndex(textureRegion) + ": " + textureRegion.getRegionWidth() + "/" + textureRegion.getRegionHeight() + ": "+amountIterator.next()+ "\r\n");
            }
            PixmapIO.writePNG(new FileHandle(project.getPath() + "/" + project.getName() + "/textures.png"),
                    fullTexture);
            fw.flush();
            //endregion

            //region save all maps
            for (Map map : project.getMaps())
            {
                saveMap(map, project);
            }
            //endregion
        }
        finally
        {
            assert fw != null;
            fw.flush();
        }
    }


    public static void saveMap(Map m, Project project) throws IOException
    {
        File f;
        try
        {
            Files.createDirectory(Paths.get(project.getPath()+"/"+project.getName()+"/maps/"));
        }
        catch (FileAlreadyExistsException e)
        {
            System.out.println("File " + e.getMessage() + " already exists.");
        }

        try
        {
            f = Files.createFile(Paths.get(project.getPath()+"/"+project.getName()+"/maps/"+m.getName()+".makermap")).toFile();
        }
        catch (FileAlreadyExistsException e)
        {
            f = Paths.get(project.getPath()+"/"+project.getName()+"/maps/"+m.getName()+".makermap").toFile();
            System.out.println("File " + e.getMessage() + " already exists.");
        }

        try
        {

            FileWriter fw = new FileWriter(f);

            Vector2IntLim anchor = m.getAnchor();
            fw.write(anchor.getX() + "/" + anchor.getY() + "//" + anchor.getLim());

            ArrayList<ArrayList<Chunk>> chunkLists2D = m.getMapChunkLists2D();
            for (int c1 = 0; c1 < chunkLists2D.size(); c1++)
            {
                for (int c2 = 0; c2 < chunkLists2D.get(c1).size(); c2++)
                {
                    Chunk c = chunkLists2D.get(c1).get(c2);
                    Tile[][] tiles = chunkLists2D.get(c1).get(c2).getTiles();
                    if (c.isHasTiles()) fw.write("\r\n " + (c1 * m.getBase() * tiles.length) + "/" + (c2 * m.getBase() * tiles.length) + "\r\n");

                    for (int c3 = 0; c3 < tiles.length; c3++)
                    {
                        for (int c4 = 0; c4 < tiles.length; c4++)
                        {
                            Tile t = tiles[c3][c4];
                            if (t != null)
                            {
                                fw.write("  "+t.getTexturesIndex()+":" + (c3 * m.getBase()) + "/" + (c4 * m.getBase()));
                            }

                        }
                    }
                }
            }
            fw.flush();
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage()+"6");
        }

    }

    public void loadProject()
    {
        String path = findPath();
        File f = new File(path);
        String name = f.getName();
        String[] files =  f.list();
        for(String fileName : files)
        {
            //if(fileName.equals())
        }
    }

    public static String findName(String text)
    {
        String name;
        do
        {
            name = JOptionPane.showInputDialog(null,text);
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
