package com.lipkill.game;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;

import state.Play;

public class Edge extends InteractiveTileObject{

	public Edge(Play screen, MapObject object, TiledMap map) {
		super(screen, object, map);
		this.body.getFixtureList().get(0).setUserData("edge");
	}

	@Override
	public void onHit() {
		// TODO Auto-generated method stub
		
	}

}
