package L7.games;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Controller implements ResultController {
	private GamesUI resultUI;
	private GameResults result;
	
	public Controller() {
		try {
		    result = new GameResults("C:\\Users\\lucas\\OneDrive\\Skrivbord\\Kurser\\DA343a-2022\\Labs\\laboration7\\src\\L7\\games\\files\\games.txt");
		    resultUI = new GamesUI(this);
			showFrame(resultUI);
			result.addPropertyListener(new WindowUpdater());
			result.addPropertyListener(new ConsoleUpdater());
		} catch(IOException e) {}
	}
	public class ConsoleUpdater implements PropertyChangeListener{

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					if (evt.getPropertyName().equals("game") && evt.getNewValue() instanceof Game){
						Game game = (Game)evt.getNewValue();
						System.out.println(game.toString());
					}
				}
			});

		}
	}
	public class WindowUpdater implements PropertyChangeListener{

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					if (evt.getPropertyName().equals("game") && evt.getNewValue() instanceof Game){
						Game game = (Game)evt.getNewValue();
						resultUI.newResult(game.toString());
					}
				}
			});

		}
	}

	private void showFrame(JPanel panel) {
		JFrame frame = new JFrame("Game results");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}
	
	@Override
	public void start() {
		if(result!=null) {
		    result.startSimulation();
		}
	}
	
	@Override
	public void stop() {
		if(result!=null) {
			result.stopSimulation();
		}
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Controller controller = new Controller();
			}
		});		
	}
}
