package state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.yassir.game.mainClass;

public class MenuScreen implements Screen{
	
	private MainActivity zbg;
	private ShapeRenderer shapeRenderer;
	
	private OrthographicCamera cam;
	
	private SpriteBatch batcher;
	private Texture txt1;
	
	private Texture Window_Elements;
	private Texture GUI_Templates_01;
	private TextureRegion playBtn, fbBtn, optionBtn, panierBtn, background;
	private TextureRegion musicBtn, sandBtn, aboutBtn, optionCercle, optionTube, cercleRouge, geree;
	
	
	
	//private GameScreen gameScreen;
	
	 public MenuScreen(MainActivity zbg){
		 float screenWidth = Gdx.graphics.getWidth();
	        float screenHeight = Gdx.graphics.getHeight();
	        float gameWidth = 600;//136;
	        float gameHeight = screenHeight / (screenWidth / gameWidth);
		 this.zbg = zbg;
		 
		 cam = new OrthographicCamera();
	    cam.setToOrtho(true, 600, 510);
	        
	    shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);
	     
	     
	     batcher = new SpriteBatch();
	     batcher.setProjectionMatrix(cam.combined);
	     
	     txt1 = new Texture(Gdx.files.internal("background.png"));
	     txt1.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
	     background = new TextureRegion(txt1, 0, 0, 1280, 720);
	     background.flip(false, true);
	     
	     Window_Elements = new Texture(Gdx.files.internal("Window_Elements.png"));
	     Window_Elements.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
	     GUI_Templates_01 = new Texture(Gdx.files.internal("GUI_Templates_01.png"));
	     GUI_Templates_01.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
	     
	     geree = new TextureRegion(GUI_Templates_01, 340, 710, 59, 60);
	     geree.flip(false, true);
	     cercleRouge = new TextureRegion(GUI_Templates_01, 320, 784, 96, 99);
	     cercleRouge.flip(false, true);
	     cercleRouge = new TextureRegion(GUI_Templates_01, 320, 784, 96, 99);
	     cercleRouge.flip(false, true);
	     optionTube = new TextureRegion(GUI_Templates_01, 239, 522, 69, 342);
	     optionTube.flip(false, true);
	     optionCercle = new TextureRegion(GUI_Templates_01, 142, 622, 70, 73);
	     optionCercle.flip(false, true);
	     musicBtn = new TextureRegion(GUI_Templates_01, 92, 636, 36, 41);
	     musicBtn.flip(false, true);
	     sandBtn = new TextureRegion(GUI_Templates_01, 84, 720, 40, 36);
	     sandBtn.flip(false, true);
	     aboutBtn = new TextureRegion(GUI_Templates_01, 92, 554, 30, 43);
	     aboutBtn.flip(false, true);
	     playBtn = new TextureRegion(Window_Elements,588,455,333,147);
	     playBtn.flip(false, true);
	     fbBtn = new TextureRegion(Window_Elements,708,611,303,123);
	     fbBtn.flip(false, true);
	     optionBtn = new TextureRegion(Window_Elements,206,433,130,379);
	     optionBtn.flip(false, true);
	     
	     panierBtn = new TextureRegion(Window_Elements,1250,690,109,128);
	     panierBtn.flip(false, true);
	     
	     
	 }
	
	 public void Input(){
		 if(Gdx.input.justTouched()){
			 if(isClick(160,180,200,this.getHeight(playBtn, 200))){
			  //zbg.setGameScreen();
				 zbg.setScreen(new Play(zbg));
			 }
		 }
		 
		 if(Gdx.input.justTouched()){
			 if(isClick(50, 275, 20, this.getHeight(aboutBtn, 20))){
				 System.out.println("booooooooooooooooooooooob");
				// zbg.setAboutScreen();
			 }
		 }
		 
		 System.out.println(Gdx.input.getX() +"      "+   Gdx.input.getY());
	 }
	 
	 boolean sand = true;
	 public void getSand(){
		 if(sand==true){
			 batcher.draw(sandBtn, 47, 378, 27, this.getHeight(sandBtn, 27));
			 if(Gdx.input.justTouched() && isClick(47, 378, 27, this.getHeight(sandBtn, 27)))	
		 		sand = false;
		 }
		else{
			//batcher.draw(fbBtn, 200,260,180,this.getHeight(fbBtn, 180));
			 if(Gdx.input.justTouched() && isClick(47, 378, 27, this.getHeight(sandBtn, 27)))
				 sand = true;
		}
	}
	 
	 boolean musik = true;
	 public void getMusik(){
		 if(musik==true){
			 batcher.draw(musicBtn, 50, 325, 22, this.getHeight(musicBtn, 22));
			 if(Gdx.input.justTouched() && isClick(50, 325, 22, this.getHeight(musicBtn, 22)))	
		 		musik = false;
		 }
		else{
			//batcher.draw(fbBtn, 200,260,180,this.getHeight(fbBtn, 180));
			 if(Gdx.input.justTouched() && isClick(50, 325, 22, this.getHeight(musicBtn, 22)))
				 musik = true;
		}
	}
 
	 
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// TODO Auto-generated method stub
		  shapeRenderer.begin(ShapeType.Filled);

	    // Draw Background color
	    shapeRenderer.setColor(55 / 255.0f, 80 / 255.0f, 100 / 255.0f, 1);
	    shapeRenderer.rect(0, 0, 600, 600);
	    shapeRenderer.end();
        
        batcher.begin();
//        batcher.disableBlending();
        
        batcher.draw(background,0,0, 600, 510);
        batcher.draw(playBtn, 160,180,200,this.getHeight(playBtn, 200));//333,147);
        batcher.draw(fbBtn, 200,260,180,this.getHeight(fbBtn, 180));//303,123);
        batcher.draw(optionBtn, 20, 250, 80, this.getHeight(optionBtn, 80));
        batcher.draw(panierBtn, 470, 400, 60, this.getHeight(panierBtn, 60));
        
        batcher.draw(optionTube, 41, 255, 40, getHeight(optionTube,  40));
        batcher.draw(optionCercle, 40, 269, 40, this.getHeight(optionCercle, 40));
        batcher.draw(optionCercle, 40, 320, 40, this.getHeight(optionCercle, 40));
        batcher.draw(optionCercle, 40, 370, 40, this.getHeight(optionCercle, 40));
        batcher.draw(cercleRouge, 26, 416, 70, this.getHeight(cercleRouge, 70));
        batcher.draw(geree, 40, 430, 40, this.getHeight(geree, 40));
        batcher.draw(aboutBtn, 50, 275, 20, this.getHeight(aboutBtn, 20));
//        batcher.draw(musicBtn, 50, 325, 22, this.getHeight(musicBtn, 22));
//        batcher.draw(sandBtn, 47, 378, 27, this.getHeight(sandBtn, 27));
        this.getSand();
        this.getMusik();
        batcher.end();
        
        
      Input();
        
//        System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh:"+Window_Elements.getWidth() );
       
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

	

	
	public boolean isClick(int x, int y, int width, int height){
		if(Gdx.input.getX()> x && Gdx.input.getX() < x+width) {
			if(Gdx.input.getY() > y && Gdx.input.getY() < y+height)	
				return true;
		}
		return false;
	}
	
	public void dispose() {
        // We must dispose of the texture when we are finished.
		Window_Elements.dispose();
		GUI_Templates_01.dispose();
    }
	
	private int getHeight(TextureRegion tr, int width){
		return tr.getRegionHeight()*width/tr.getRegionWidth();
	}
	 

}
