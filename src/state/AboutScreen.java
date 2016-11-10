package com.kilobolt.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.kilobolt.zombiebird.ZBGame;

public class AboutScreen implements Screen{

	private Texture texture;
	private TextureRegion TR;
	private OrthographicCamera cam;
	private SpriteBatch batcher;
	private ZBGame zbg;
	
	public AboutScreen(ZBGame zbg){
		this.zbg = zbg;
		cam = new OrthographicCamera();
	    cam.setToOrtho(true, 600, 510);
	    batcher = new SpriteBatch();
	    batcher.setProjectionMatrix(cam.combined);
	    texture = new Texture("badlogic.jpg");
	    
	    texture = new Texture(Gdx.files.internal("badlogic.jpg"));
	    texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
	    
	    TR = new TextureRegion(texture, 0,0, 256,256);
	     TR.flip(false, true);
	     
	     
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
		batcher.begin();
		batcher.draw(TR, 0, 0, 600, 510);
		batcher.end();
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		texture.dispose();
		
	}

}
