package com.mygdx.game.tools.data;

import com.mygdx.game.map.Map;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public abstract class DataManager
{
    public static boolean saveMap(Map m)
    {
        try
        {
            File dir = new File(System.getProperty("user.home")+"/documents/MapMaker");
            if(!dir.exists())
            {
                dir.mkdir();
            }

            File dir1 = new File(System.getProperty("user.home")+"/documents/MapMaker/pack1");
            if(!dir1.exists())
            {
                dir1.mkdir();
            }

            File dir2 = new File(System.getProperty("user.home")+"/documents/MapMaker/pack1/textures");
            if(!dir2.exists())
            {
                dir2.mkdir();
            }



            File f = new File(System.getProperty("user.home")+"/documents/MapMaker/pack1/map1.map32");
            if(!f.exists())
            {
                f.createNewFile();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(System.getProperty("user.home")+"/documents/MapMaker/pack1/map1.map32"));

            writer.write("teest");
            writer.close();
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
}
