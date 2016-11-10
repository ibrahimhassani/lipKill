package com.lipkill.game;

import state.Play;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class MapParser {
	World world;
	BodyDef bdef;
	PolygonShape shape;
	FixtureDef fdef;
	Body body;
	Hero m1;
	Play play;
	private TmxMapLoader maploader;
	private TiledMap map;
	private OrthogonalTiledMapRenderer mapRendrer;
	
	public MapParser(Play p, World world,TiledMap map){
		this.map=map;
		this.world=world;
		this.play=p;
		

		maploader=new TmxMapLoader();
		map=maploader.load("therightone.tmx");
		mapRendrer=new OrthogonalTiledMapRenderer(map,1/conf.PPM);
		
		bdef=new BodyDef();
		shape=new PolygonShape();
		fdef=new FixtureDef();
		
		//hero
		getHero(15);
		//ground
		getRectangularLayers(3);
		//barrel
		getRectangularLayers(6);
		//coin
		getCoin(16);
		
	}
	
	public void getRectangularLayers(int i){
		for(MapObject object : map.getLayers().get(i).getObjects().getByType(RectangleMapObject.class)){
			Rectangle rect=((RectangleMapObject)object).getRectangle();
			bdef.type=BodyType.StaticBody;
			bdef.position.set((rect.getX()+rect.getWidth()/2)/conf.PPM,(rect.getY()+rect.getHeight()/2)/conf.PPM);
			System.out.println("-------------------------"+(rect.getX()+rect.getWidth()/2)/conf.PPM);
			body=world.createBody(bdef);
			shape.setAsBox(rect.getWidth()/2/conf.PPM,rect.getHeight()/2/conf.PPM);
			fdef.shape=shape;
			body.createFixture(fdef);
			}
	}
	
	public void getHero(int i){
		for(MapObject object : map.getLayers().get(i).getObjects().getByType(RectangleMapObject.class)){
			Rectangle rect=((RectangleMapObject)object).getRectangle();
			m1=new Hero(play,rect.getX(),rect.getY());
		}
	}
	public void getCoin(int i){
		for(MapObject object : map.getLayers().get(i).getObjects().getByType(RectangleMapObject.class)){
			new Coin(play, object, this);
			}
	}
	public TiledMap getMap() {
		return map;
	}

	public void setMap(TiledMap map) {
		this.map = map;
	}

	public void render(){
		mapRendrer.setView(play.getGameCam());
		mapRendrer.render();
	}
	
	public Hero getHero(){
		return m1;
	}
}
