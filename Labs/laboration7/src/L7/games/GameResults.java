package L7.games;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class GameResults {
	private ArrayList<Game> games = new ArrayList<Game>();
	private SimulateGames thread;
	private PropertyChangeSupport change = new PropertyChangeSupport(this);


	public GameResults(String filename) throws IOException {
		try (BufferedReader bw = new BufferedReader(new InputStreamReader(new FileInputStream(filename),"UTF-8")) ) {
			String line = bw.readLine();
			String[] teams;
			while(line!=null) {
				teams = line.split(",");
				games.add(new Game(teams[0],teams[1]));
				line = bw.readLine();
			}
		} 
	}
	public void addPropertyListener(PropertyChangeListener listener){
		change.addPropertyChangeListener(listener);
	}
	public void removeAlarmListener(PropertyChangeListener listener){
		change.removePropertyChangeListener(listener);
	}

	public void startSimulation() {
		if(thread==null) {
			thread = new SimulateGames();
			thread.start();
		}
	}

	public void stopSimulation() {
		if(thread!=null) {
			thread.interrupt();
			thread = null;
		}
	}

	private class SimulateGames extends Thread {
		public void run() {
			int gameIndex, team;
			Random rand = new Random();
			Game game;
			while(thread!=null) {
				try {
					Thread.sleep(1000);
					team = rand.nextInt(2);
					gameIndex = rand.nextInt(games.size());
					game = games.get(gameIndex);
					switch(team) {
					    case 0: game.increaseGoal1(); break;
					    case 1: game.increaseGoal2(); break;
					}
					change.firePropertyChange("game",null, game);
				} catch(InterruptedException e) {
					break;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		GameResults gr = new GameResults("C:\\Users\\lucas\\OneDrive\\Skrivbord\\Kurser\\DA343a-2022\\Labs\\laboration7\\src\\L7\\games\\files\\games.txt");
		gr.startSimulation();
		try {
			Thread.sleep(20000);
		} catch(InterruptedException e) {}
		gr.stopSimulation();
	}
}

