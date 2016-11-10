package com.lipkill.game;

import state.Play;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape.Type;
import com.badlogic.gdx.physics.box2d.World;

public class Projectile extends Sprite{
	private World world;
	private Body b2body;
	private TextureRegion region;
	private Play screen;
	private boolean fireRight;
	private float coorX;
	private float width;
	
	public Projectile(Play s,float x,float y,float width, boolean isRight){
		super(new Texture(Gdx.files.internal("Blue (1).png")));
		world=s.getWorld();
		this.screen=s;
		this.fireRight=isRight;
		this.world=screen.getWorld();
		this.width=width;
		//region=new TextureRegion(getTexture(),0,0,722,667);
		setBounds(x,y,30/conf.PPM,22/conf.PPM);
		//setRegion(region);
		defineFireBody();
	}

	private void defineFireBody() {
		BodyDef bdef=new BodyDef();
		if(fireRight){
			bdef.position.set(getX(),getY());
		}else{
			bdef.position.set(getX()-width,getY());
			setFlip(true,false);
		}
		
		bdef.type=BodyDef.BodyType.KinematicBody;
		b2body=world.createBody(bdef);
		FixtureDef fdef=new FixtureDef();
		PolygonShape ps=new PolygonShape();
		ps.setAsBox(8/conf.PPM,8/conf.PPM);
		
		fdef.shape=ps;
		fdef.isSensor=true;
		b2body.createFixture(fdef);
		
		//b2body.setTransform(10, 0,0);
		b2body.setLinearVelocity(fireRight?conf.fireSpeed/conf.PPM:-conf.fireSpeed/conf.PPM, 0);
	}
	
	public boolean isVisible(){
		if(getX()-coorX>Gdx.graphics.getWidth()||-getX()+coorX>Gdx.graphics.getWidth()){
			return false;
		}
		return true;
	}

	public void update(float dt){
		//b2body.getPosition().x+=100;
		setPosition((b2body.getPosition().x-getWidth()/2),( b2body.getPosition().y-getHeight()/2));
	}
}
