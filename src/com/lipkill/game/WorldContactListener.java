package com.lipkill.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

public class WorldContactListener implements ContactListener {

	@Override
	public void beginContact(Contact contact) {
		// TODO Auto-generated method stub
		Fixture fixA=contact.getFixtureA();
		Fixture fixB=contact.getFixtureB();
		
		if(fixA.getUserData()=="hero" || fixB.getUserData()=="hero"){
			
			Fixture head=fixA.getUserData()=="hero"?fixA:fixB;
			Fixture object=fixA==head?fixB:fixA;

			if( object.getUserData()!=null && InteractiveTileObject.class.isAssignableFrom(object.getUserData().getClass())){
				((InteractiveTileObject)object.getUserData()).onHit();
			}

		}
if(fixA.getUserData()=="edge" || fixB.getUserData()=="edge"){
			
	Fixture caracter=fixB.getUserData()=="edge" ? fixA : fixB;
	Fixture edge=caracter==fixA ? fixB :fixA;
	if(caracter.getUserData()!=null && Monster.class.isAssignableFrom(caracter.getUserData().getClass())){
		((Monster) caracter.getUserData()).jump(Gdx.graphics.getDeltaTime() );
		
		if(((Monster) caracter.getUserData()).isMovingRight() && ((Monster) caracter.getUserData()).isRunning)
			((Monster) caracter.getUserData()).moveRight(Gdx.graphics.getDeltaTime());
		else if(((Monster) caracter.getUserData()).isMovingRight() && ((Monster) caracter.getUserData()).isRunning)
			((Monster) caracter.getUserData()).moveLeft(Gdx.graphics.getDeltaTime());
			
		}

		}
	}

	@Override
	public void endContact(Contact contact) {
		// TODO Auto-generated method stub

	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub

	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub

	}

}
