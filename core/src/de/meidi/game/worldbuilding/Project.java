package de.meidi.game.worldbuilding;

import de.meidi.game.map.Map;
import de.meidi.game.tools.DataManager;
import lombok.Getter;
import lombok.Setter;
import java.util.*;

public class Project
{
    public Project()
    {

        maps = new HashSet<>();
    }



    @Setter
    private String name = "";
    public String getName()
    {
        if(name.isEmpty())name = DataManager.findName("Input Project title...");
        return name;
    }

    @Getter
    private final Set<Map> maps;



    @Setter
    private String path = "";
    public String getPath()
    {
        if(path.isEmpty())path = DataManager.findPath();
        return path;
    }




    public void addMap(Map map)
    {
        maps.add(map);
        map.setProject(this);
    }
}
