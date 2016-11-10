package state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.lipkill.game.Edge;
import com.lipkill.game.HandleInputMonster;
import com.lipkill.game.Hero;
import com.lipkill.game.MainActivity;
import com.lipkill.game.Monster;
import com.lipkill.game.Projectile;
import com.lipkill.game.WorldContactListener;
import com.lipkill.game.conf;

public class Play implements Screen {

	private MainActivity game;
	private Viewport gamePort;
	private OrthographicCamera gameCam;
	private Hero m1;
	private World world;
	private Box2DDebugRenderer b2dr;
	public TextureAtlas ta;
	private long lastFire;
	private TmxMapLoader maploader;
	private TiledMap map;
	private OrthogonalTiledMapRenderer mapRendrer;
	private Monster monster;
	private HandleInputMonster comportement;
	private Hud hud;
	public Play(MainActivity game){
		
		this.game=game;
		gameCam=new OrthographicCamera();
		//g//ameCam.zoom-=gameCam.zoom/100;
		gamePort=new FitViewport(conf.viewPortWidth/conf.PPM,conf.viewPortHeight/conf.PPM, gameCam);
		hud=new Hud(game.batch);
		world=new World(new Vector2(0,conf.gravity), true);
		b2dr=new Box2DDebugRenderer();
		ta=new TextureAtlas("hero/AmiSprite.pack");
		
		
		//loading map
		maploader=new TmxMapLoader();
		map=maploader.load("therightone.tmx");
		mapRendrer=new OrthogonalTiledMapRenderer(map,1/conf.PPM);
		gameCam.position.set(gamePort.getWorldWidth()/2,gamePort.getWorldHeight()/2,0);
		world.setContactListener(new WorldContactListener());
		box2dMap(map,world);
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		if(Gdx.input.isTouched())
			gameCam.position.x+=0.5f;
		world.step(1/60f,6,2);
		gameCam.update();
		gameCam.position.x=m1.b2body.getPosition().x;
		m1.update(delta);
		monster.setComportement(new HandleInputMonster(m1, monster), delta);
		monster.update(delta);
		handleInput(delta);
		
		//Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		

		mapRendrer.setView(gameCam);
		mapRendrer.render();
		b2dr.render(world,gameCam.combined);
		hud.stage.draw();
		game.batch.setProjectionMatrix(gameCam.combined);
		game.batch.begin();
		
		
		m1.draw(game.batch);
		monster.draw(game.batch);
		for(Projectile f : m1.fires){
			f.update(delta);
			f.draw(game.batch);
		}
			
		game.batch.end();
		
		System.out.println("x:"+m1.b2body.getPosition().x+" y :"+m1.b2body.getPosition().y+" vx:"+m1.b2body.getLinearVelocity().x+" vy:"+m1.b2body.getLinearVelocity().y+" state :"+m1.currentState);
	}
	

	public OrthographicCamera getGameCam() {
		return gameCam;
	}

	public void box2dMap(TiledMap map,World world){
		BodyDef bdef=new BodyDef();
		PolygonShape shape=new PolygonShape();
		FixtureDef fdef=new FixtureDef();
		Body body;
		//hero 
		for(MapObject object : map.getLayers().get(15).getObjects().getByType(RectangleMapObject.class)){
			Rectangle rect=((RectangleMapObject)object).getRectangle();
			m1=new Hero(this,rect.getX(),rect.getY());
		}
		//ground
		for(MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){
			Rectangle rect=((RectangleMapObject)object).getRectangle();
			bdef.type=BodyType.StaticBody;
			bdef.position.set((rect.getX()+rect.getWidth()/2)/conf.PPM,(rect.getY()+rect.getHeight()/2)/conf.PPM);
			System.out.println("-------------------------"+(rect.getX()+rect.getWidth()/2)/conf.PPM);
			body=world.createBody(bdef);
			shape.setAsBox(rect.getWidth()/2/conf.PPM,rect.getHeight()/2/conf.PPM);
			fdef.shape=shape;
			body.createFixture(fdef);
			//m1=new Monster(this,(rect.getX()+rect.getWidth()/2),(rect.getY()+rect.getHeight()/2));
		}
		//red barrel
		/*for(MapObject object : map.getLayers().get(7).getObjects().getByType(RectangleMapObject.class)){
			Rectangle rect=((RectangleMapObject)object).getRectangle();
			bdef.type=BodyType.StaticBody;
			bdef.position.set(rect.getX()+rect.getWidth()/2/conf.PPM,rect.getY()+rect.getHeight()/2/conf.PPM);
			
			body=world.createBody(bdef);
			shape.setAsBox(rect.getWidth()/2/conf.PPM,rect.getHeight()/2/conf.PPM);
			fdef.shape=shape;
			body.createFixture(fdef);
		}*/
		
		
		//green barel
		for(MapObject object : map.getLayers().get(6).getObjects().getByType(RectangleMapObject.class)){
			Rectangle rect=((RectangleMapObject)object).getRectangle();
			bdef.type=BodyType.StaticBody;
			bdef.position.set((rect.getX()+rect.getWidth()/2)/conf.PPM,(rect.getY()+rect.getHeight()/2)/conf.PPM);
			System.out.println("-------------------------"+(rect.getX()+rect.getWidth()/2)/conf.PPM);
			body=world.createBody(bdef);
			shape.setAsBox(rect.getWidth()/2/conf.PPM,rect.getHeight()/2/conf.PPM);
			fdef.shape=shape;
			body.createFixture(fdef);
					}
		
		for(MapObject object : map.getLayers().get(16).getObjects().getByType(RectangleMapObject.class)){
			Rectangle rect=((RectangleMapObject)object).getRectangle();
			monster=new Monster(this, rect.x, rect.y);
					}
		for(MapObject object : map.getLayers().get(17).getObjects().getByType(RectangleMapObject.class)){
			
			new Edge(this, object,map);
					}
	}
	 public void handleInput(float dt){
	       // if(m1.currentState != Mario.State.DEAD) {
	            if (Gdx.input.isKeyJustPressed(Input.Keys.UP) && m1.b2body.getLinearVelocity().y==0){
	                m1.jump(dt);
	            }
	            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
	            	m1.moveRight(dt);
	            	
	            }
	            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
	            	m1.moveLeft(dt);
	 			}
	            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)){
	            	if(System.currentTimeMillis() - lastFire>conf.fireDephasage){
	            		m1.fire();
	            		lastFire = System.currentTimeMillis();
	            	}
	            }else{
	            	m1.stopShooting();
	            }
	            if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
	            	m1.crouch();
	            	
	            }else{
	            	m1.stand();
	            }
	            if(Gdx.input.isKeyJustPressed(Input.Keys.P)){
	            	m1.thrown();
	            }else{
	            	m1.stopThrowing();
	            }
	            if (!Gdx.input.isKeyPressed(Input.Keys.RIGHT)&& !Gdx.input.isKeyPressed(Input.Keys.LEFT) /*&& m1.b2body.getLinearVelocity().x !=0*/){
	            	m1.stopX();
	            	
	            }
	            
	            
	       // }

	    }
	 
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		gamePort.update(width,height);
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
		
	}

	public World getWorld() {
		return world;
	}
	public void setWorld(World world) {
		this.world = world;
	}
	public TextureAtlas getTa() {
		return ta;
	}
	public void setTa(TextureAtlas ta) {
		this.ta = ta;
	}
	public Box2DDebugRenderer getB2dr() {
		return b2dr;
	}

	public void setB2dr(Box2DDebugRenderer b2dr) {
		this.b2dr = b2dr;
	}
}
