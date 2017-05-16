package com.bubblerunner.game.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.*;
import com.badlogic.gdx.utils.Align;
import com.bubblerunner.game.BubbleRunner;
import com.bubblerunner.game.actors.MenuTableActor;
import com.bubblerunner.game.screens.BubbleScreen;
import com.bubblerunner.game.screens.MenuScreen;
import com.bubblerunner.game.utils.gui.GraphicsManager;

/**
 * Created by Mario on 08/05/2017.
 */

public class MenuStage extends Stage {

    private BubbleRunner game;
    private GraphicsManager graphicsManager;

    private TextButtonStyle style;
    private final int buttonHeight = Gdx.graphics.getHeight()/3;
    private final int buttonWidth = Gdx.graphics.getWidth()/4;
    private final int buttonCenter = Gdx.graphics.getWidth()/2-buttonWidth/2;
    private final int buttonStartPos = Gdx.graphics.getHeight();

    private MoveToAction animationPlay;
    private MoveToAction animationHighscores;
    private MoveToAction animationExit;
    private final int finalPosPlay = Gdx.graphics.getHeight()/2;
    private final int finalPosHighscores = Gdx.graphics.getHeight()/3;
    private final int finalPosExit = Gdx.graphics.getHeight()/6;

    private TextButton buttonPlayGame;
    private TextButton buttonHighscores;
    private TextButton buttonExit;
    public boolean pressed;
    private Label outputLabel;


    private MenuTableActor menuTableActor;

    public MenuStage (BubbleRunner game){

        this.menuTableActor = new MenuTableActor(GraphicsManager.getInstance());
        this.addActor(menuTableActor);

    /*    pressed = false;
        this.game = game;
        this.graphicsManager = graphicsManager.getInstance();

        this.clear();
        Gdx.input.setInputProcessor(this);

        setupButtonsStyle();
        setupMoveAnimation();
        createButtons();

        this.addActor(this.buttonPlayGame);
        this.addActor(this.buttonHighscores);
        this.addActor(this.buttonExit);*/
    }

    private void createButtons(){

        //Construct buttons
        this.buttonPlayGame = new TextButton("PLAY GAME", style);
        this.buttonHighscores = new TextButton("HIGHSCORES", style);
        this.buttonExit = new TextButton("EXIT", style);

        //Set buttons geometry + position
        setGeometry(buttonPlayGame);
        setGeometry(buttonHighscores);
        setGeometry(buttonExit);

        //Set buttons fancy animations
        buttonPlayGame.addAction(animationPlay);
        buttonHighscores.addAction(animationHighscores);
        buttonExit.addAction(animationExit);

        //Set button actions
        setActions();

    }

    private void setGeometry(TextButton button){
        button.setHeight(buttonHeight);                       // Set button height
        button.setWidth(buttonWidth);                         // Set button width
        button.setPosition(buttonCenter, buttonStartPos);     // Set button initial position
    }


    private void setupButtonsStyle(){

        BitmapFont font = new BitmapFont();
        TextureAtlas atlas = new TextureAtlas("button.pack");
        Skin skin = new Skin();
        skin.addRegions(atlas);

        style = new TextButtonStyle();
        style.up = skin.getDrawable("buttonOff");
        style.down = skin.getDrawable("buttonOn");
        style.font = font;
    }

    private void setupMoveAnimation(){
        animationPlay = new MoveToAction();    // Dynamic animation
        animationPlay.setPosition(buttonCenter, finalPosPlay);
        animationPlay.setDuration(1.5f);

        animationHighscores = new MoveToAction();    // Dynamic animation
        animationHighscores.setPosition(buttonCenter, finalPosHighscores);
        animationHighscores.setDuration(1.5f);

        animationExit = new MoveToAction();    // Dynamic animation
        animationExit.setPosition(buttonCenter, finalPosExit);
        animationExit.setDuration(1.5f);
    }

    private void setActions(){

        buttonPlayGame.addListener(new InputListener() {

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
              pressed = true;
                //  game.setScreen(new BubbleScreen(game));
               // dispose();
            }
        });

        buttonHighscores.addListener(new InputListener() {

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("ole","ole");
                //game.setScreen(new HighscoresScreen(game));
            }
        });

        buttonExit.addListener(new InputListener() {

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("oli","oli");
               // Gdx.app.exit();
            }
        });
    }

    @Override
    public void act(float delta)
    {
        super.act(delta);
        if(pressed == true){
            Gdx.app.log("ole","CHEGOU AQUI!!");
            pressed = false;
        }
    }


}
