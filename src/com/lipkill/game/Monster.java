package com.lipkill.game;

import state.Play;

public class Monster extends Hero{
	private HandleInputMonster comportement;
	

	private boolean movingRight=true;

	public Monster(Play s, float x, float y) {
		super(s, x, y);
		
	}

	@Override
	public void update(float dt) {
		setPosition((b2body.getPosition().x-getWidth()/2),( b2body.getPosition().y-getHeight()/2));
		currentState=stateManager();
		//autoMove(dt);
		//comportement.monsterComportement(dt);
		setRegion(getFrame(dt));
		
		if(!isRight)
			setFlip(true,false);
		if(isCrouching || isThrowing){
			stopX();
		}
		if( b2body.getLinearVelocity().y==0){
			isJumping=false;
			/*if(currentState==State.fall )
				currentState=State.stand;*/
		}
		
		for(int i=0;i<fires.size();i++){
			if(!fires.get(i).isVisible()){
				fires.remove(i);
			}
		}
		
	}
	
	public HandleInputMonster getComportement() {
		return comportement;
	}

	public void setComportement(HandleInputMonster comportement,float delta) {
		comportement.monsterComportement(delta);;
	}

	public boolean isMovingRight() {
		return movingRight;
	}

	public void setMovingRight(boolean movingRight) {
		this.movingRight = movingRight;
	}

	public void autoMove(float dt){
		if(movingRight)
			this.moveRight(dt);
		else
			this.moveLeft(dt);
		
		
	}
	@Override
	public void moveRight(float dt) {
		if(!isCrouching && !isThrowing){
			b2body.setLinearVelocity((conf.speedX-100)*dt,b2body.getLinearVelocity().y);
			//b2body.applyLinearImpulse(new Vector2(conf.speedX*dt,b2body.getLinearVelocity().y), b2body.getWorldCenter(),true);//
		}
		isRunning=true;
		isRight=true;
	}

	@Override
	public void moveLeft(float dt) {
		if(!isCrouching && !isThrowing){
			b2body.setLinearVelocity(-(conf.speedX-100)*dt,b2body.getLinearVelocity().y);
			//b2body.applyLinearImpulse(new Vector2(-conf.speedX*dt,b2body.getLinearVelocity().y), b2body.getWorldCenter(),true);
		}
			
		
		isRunning=true;
		isRight=false;
	}
	

}
