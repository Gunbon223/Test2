package com.gunbon.game;

import Utility.Constrain;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.gunbon.game.FlappyGame;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(120);
		config.setWindowedMode(Constrain.WIDTH,Constrain.HEIGHT);
		config.setTitle("FlappyBird");
		new Lwjgl3Application(new FlappyGame(), config);
	}
}
