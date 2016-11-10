package com.lipkill.game;

import state.Play;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.math.Vector2;

public class Coin extends InteractiveTileObject {
    private static TiledMapTileSet tileSet;
    private final int BLANK_COIN = 28;

    public Coin(Play screen, MapObject object,MapParser parser){
        super(screen, object,parser);
        fixture.setUserData(this);
        
    }

   @Override
    public void onHit() {
       System.out.println("coiin detected");
       getCell(2).setTile(null);
       setCategoryFilter(conf.BIT_DESTROYED);
    }
}