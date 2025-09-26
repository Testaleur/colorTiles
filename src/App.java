import javax.swing.*;

public class App {
	public static void main(String[] args) {
		JFrame window = new JFrame("Color Tiles");
		int width = 400;
		int height = 600;

		GamePanel gamePanel = new GamePanel();

		window.add(gamePanel);
		window.setSize(width+18, height+32);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		window.setVisible(true);
        window.setResizable(false);

		gamePanel.launchGame();
	}
}