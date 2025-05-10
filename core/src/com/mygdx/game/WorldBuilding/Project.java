package com.mygdx.game.WorldBuilding;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Map.Map;
import javax.swing.*;
import java.io.File;
import java.util.Set;

public class Project
{

    public Project(Map map)
    {
        this.map = map;
    }

    public String name = "";

    private Map map;

    public Map getMap(){return map;}

    public Set<TextureRegion> usedTextureRegions;

    public void save()
    {
        String title = JOptionPane.showInputDialog(null,"Input Project Title...");
        if(!title.equals(""))
        {
            //get path
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
                String dir = chooser.getSelectedFile().getAbsolutePath();

                //create project
                new File(dir+"/"+title).mkdirs();
                new File(dir+"/"+title+"/textures").mkdirs();


                // mainMenu.dispatchEvent(new WindowEvent(mainMenu,WindowEvent.WINDOW_CLOSING));
            }
        }
    }
}
