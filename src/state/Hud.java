package state;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.lipkill.game.conf;

public class Hud {
	
	public Stage stage;
	private Viewport viewport;
	
	private Integer worldTimer;
	private Integer score;
	private float timeCounter;
	
	Label scoreLabel;
	Label timeLabel;
	Label levelLabel;
	Label marioLabel;
	Label worldLabel;
	Label countDownLabel;
	
	public Hud(SpriteBatch sb){
		worldTimer=300;
		score=0;
		timeCounter=0;
		
		viewport=new FitViewport(conf.viewPortWidth,conf.viewPortHeight,new OrthographicCamera());
		stage=new Stage(viewport, sb);
		
		Table table=new Table();
		table.top();
		table.setFillParent(true);
		
		//countDownLabel=new Label(String.format("%03d", worldTimer),new Label.LabelStyle(new BitmapFont(),Color.WHITE));
		scoreLabel=new Label(String.format("%04d", score),new Label.LabelStyle(new BitmapFont(),Color.WHITE));
		timeLabel=new Label("TIME",new Label.LabelStyle(new BitmapFont(),Color.WHITE));
		levelLabel=new Label("1-1",new Label.LabelStyle(new BitmapFont(),Color.WHITE));
		worldLabel=new Label("WORLD",new Label.LabelStyle(new BitmapFont(),Color.WHITE));
		marioLabel=new Label("MARIO",new Label.LabelStyle(new BitmapFont(),Color.WHITE));
		
		//table.add(marioLabel).expandX().pad(10);
		//table.add(worldLabel).expandX().pad(10);
		//table.add(timeLabel).expandX().pad(10);
		//table.row();
		table.add(scoreLabel).expandX().top().left();
		//table.add(levelLabel).expandX();
		//table.add(countDownLabel).expandX();
		
		stage.addActor(table);
		
	}

}
