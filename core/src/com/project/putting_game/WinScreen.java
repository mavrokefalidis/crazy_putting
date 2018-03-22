package com.project.putting_game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;



public class WinScreen implements Screen
{
    final com.project.putting_game.Project2 game;
    OrthographicCamera camera;
    String playButton;
    private Stage stage;
    private TextButton playAgain;
    TextButton.TextButtonStyle textButtonStyle;
    Skin skin;
    private Texture golfImg;
    private Rectangle golf;



    public WinScreen(final Project2 game)
    {
        System.out.println("WORKS");
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800,480);

        golfImg = new Texture(Gdx.files.internal("Golf.jpg"));
        golf = new Rectangle(); //create a Rectangle which can contain the picture
        golf.width = Gdx.graphics.getWidth(); //set it to cover the entire width of the screen
        golf.height = Gdx.graphics.getWidth()*721/1280; //but not the entire height, just what is needed to keep the original size
        golf.x = 0;
        golf.y = Gdx.graphics.getHeight() - golf.height;



        skin = new Skin();
        skin.add("default", game.font);
        Pixmap backgroundButton = new Pixmap((int)Gdx.graphics.getWidth()/2,(int)Gdx.graphics.getHeight()/10,Pixmap.Format.RGB888);//format is enum: how to store color values
        backgroundButton.setColor(Color.WHITE);
        backgroundButton.fill();
        skin.add("background",new Texture(backgroundButton));
        System.out.println("WORKS2");


        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.down = skin.newDrawable("background", Color.BLACK); //let the button turn black if you press your mouse when standing on the button
        textButtonStyle.checked = skin.newDrawable("background", Color.BLACK);
        textButtonStyle.over = skin.newDrawable("background", Color.BROWN); //let the button turn brown when the mouse is standing on the button
        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);
        backgroundButton.dispose();
        System.out.println("WORKS3");


        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        playAgain = new TextButton("Play again!", skin);
        playAgain.setPosition(Gdx.graphics.getWidth()/2 - playAgain.getWidth()/2,2*Gdx.graphics.getHeight()/6);
        playAgain.addListener(new ClickListener(){
            public void clicked(InputEvent event,float x, float y){
                game.setScreen(new com.project.putting_game.Game(game));
                dispose();
            }
        });
        stage.addActor(playAgain);
        dispose();
        System.out.println("WORKS4");


    }
    public void render(float delta)
    {
        Gdx.gl.glClearColor(0, 0.7f, 0, 0); //set color of screen/background
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        stage.act();
        stage.getBatch().setProjectionMatrix(camera.combined);
        stage.getBatch().begin();
        stage.getBatch().draw(golfImg, golf.x, golf.y, golf.width, golf.height);//draw background picture
        stage.getBatch().end();
        stage.draw();//draw stage (so the elements of the stage)
        System.out.println("WORKS5");


    }
    @Override
    public void resize(int width, int height) {
    }
    @Override
    public void show(){
    }
    @Override
    public void hide(){
    }
    @Override
    public void pause() {
    }
    @Override
    public void resume() {
    }
    @Override
    public void dispose(){
        golfImg.dispose();
        stage.dispose();

    }

}