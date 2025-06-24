package com.mygdx.game.worldbuilding;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.map.Map;
import com.mygdx.game.map.Tile;
import com.mygdx.game.tools.DataManager;
import com.mygdx.game.tools.exceptions.ExceptionHandler;
import com.mygdx.game.tools.exceptions.InvalidVector2IntLimException;
import com.mygdx.game.tools.vector.Vector2IntLim;

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
         DataManager.saveProject(map.getProject());
    }

    public void changeTextureIndex(int i)
    {
        textureIndex = i;
    }

    public void addToCamZoom(double z)
    {
        cam.zoom += z;
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
