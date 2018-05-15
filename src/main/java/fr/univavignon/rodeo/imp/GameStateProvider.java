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
		
		String fileName = FILEPATH+gameState.getName()+".txt";
		
		try{
			PrintWriter pw = new PrintWriter(fileName, "UTF-8");
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
		if (name == null)
			throw new IllegalArgumentException("null argument in function get()");
		
		GameState gameState = null;
		
		String fileName = FILEPATH + name + ".txt";
		
		try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			gameState = new GameState(name);
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		        sb.append(line);
		        line = br.readLine();
		    }
		    String lines = sb.toString();
		    
		    int progression = Integer.parseInt(lines);
		    gameState.setProgress(progression);
		    
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
			
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return gameState;
	}

}
