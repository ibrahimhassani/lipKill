package com.lipkill.game;

import state.Play;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public abstract class InteractiveTileObject {
    protected World world;
    protected TiledMap map;
    protected Rectangle bounds;
    protected Body body;
    protected Play screen;
    protected MapObject object;

    protected Fixture fixture;

    public InteractiveTileObject(Play screen ,MapObject object,TiledMap map){
        this.object = object;
        this.screen = screen;
        this.world = screen.getWorld();
        this.map = map;
        this.bounds = ((RectangleMapObject) object).getRectangle();

        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set((bounds.getX() + bounds.getWidth() / 2) / conf.PPM, (bounds.getY() + bounds.getHeight() / 2) / conf.PPM);

        body = world.createBody(bdef);

        shape.setAsBox(bounds.getWidth() / 2 / conf.PPM, bounds.getHeight() / 2 / conf.PPM);
        fdef.shape = shape;
        fdef.isSensor=true;
        fixture = body.createFixture(fdef);

    }

    public abstract void onHit();
    public void setCategoryFilter(short filterBit){
        Filter filter = new Filter();
        filter.categoryBits = filterBit;
        fixture.setFilterData(filter);
    }

    public TiledMapTileLayer.Cell getCell(int i){
    	
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(i);
        return layer.getCell((int)(body.getPosition().x * conf.PPM/32 ),
                (int)(body.getPosition().y * conf.PPM/32 ));
    }
    

}