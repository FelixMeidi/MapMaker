package de.meidi.game.worldbuilding;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import de.meidi.game.map.Map;
import de.meidi.game.tools.ProjectManager;
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
        if(name.isEmpty())name = ProjectManager.findName("Input Project title...");
        return name;
    }

    @Setter
    private String path = "";
    public String getPath()
    {
        if(path.isEmpty())path = ProjectManager.findPath();
        return path;
    }


    @Getter
    private final Set<Map> maps;



    @Getter
    @Setter
    private TextureRegion[] textures;





    public void addMap(Map map)
    {
        maps.add(map);
        map.setProject(this);
    }
}
