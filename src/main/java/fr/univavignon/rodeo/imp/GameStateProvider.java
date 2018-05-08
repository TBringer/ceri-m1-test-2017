package fr.univavignon.rodeo.imp;

import fr.univavignon.rodeo.api.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * Manages back-ups 
 * 
 * @author TB
 *
 */
public class GameStateProvider implements IGameStateProvider {

	private final String FILEPATH = "resources/gs_";
	@Override
	public void save(IGameState gameState) {
		
		if(gameState == null){
			System.out.println("GameState null, error.");
			return;
		}
		
		String savePath = FILEPATH+gameState.getName()+".txt";
		
		try{
			PrintWriter pw = new PrintWriter(savePath, "UTF-8");
			pw.print(gameState.getProgression());
			pw.close();
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
	}

	@Override
	public IGameState get(String name) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

}
