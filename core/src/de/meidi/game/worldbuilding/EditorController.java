package de.meidi.game.worldbuilding;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import de.meidi.game.map.Map;
import de.meidi.game.map.Tile;
import de.meidi.game.tools.DataManager;
import de.meidi.game.tools.exceptions.ExceptionHandler;
import de.meidi.game.tools.exceptions.InvalidVector2IntLimException;
import de.meidi.game.tools.vector.Vector2IntLim;

import java.io.IOException;

public class EditorController
{
    public EditorController(Map map, OrthographicCamera cam)
    {
        this.map = map;
        this.cam = cam;
        textureIndex = 1;
    }






    private final Map map;
    private final OrthographicCamera cam;
    private Integer textureIndex;






    public void addToCamPosition(int x, int y)
    {
        cam.position.add(x,y,0);
    }

    public void saveProject()
    {
        try
        {
            DataManager.saveProject(map.getProject());
        }
        catch (IOException ignored)
        {

        }

    }

    public void changeTextureIndex(int i)
    {
        textureIndex = i;
    }

    public void addToCamZoom(double z)
    {
        cam.zoom += (float)z;
    }

    public double getZoom()
    {
        return cam.zoom;
    }

    public void placeTile(int x, int y)
    {
        try
        {
            Vector3 targetPosV3 = cam.unproject(new Vector3(x, y, 0));
            Vector2IntLim targetPos = new Vector2IntLim(1,(int) targetPosV3.x, (int) targetPosV3.y);
            map.add(new Tile(textureIndex-1), targetPos);
        }
        catch (InvalidVector2IntLimException e)
        {
            e.addMessage("EditorController,placeTile[via UserInput]:");
            ExceptionHandler.outputException(e);
        }
    }
}
