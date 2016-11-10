package com.lipkill.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class MyInputHandler {

	public boolean shouldMoveRigh(){
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean shouldMoveLeft(){
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean shouldGoDown(){
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean shouldGoUp(){
		if(Gdx.input.isKeyPressed(Input.Keys.UP)){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean shouldShoot(){
		if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
			return true;
		}else{
			return false;
		}
	}
}
