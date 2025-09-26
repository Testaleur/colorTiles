import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel implements Runnable {

	private Thread gameThread;
	private List<Tile> tilesList = new ArrayList<>();
  private Color backgroundColor = new Color(0,0,0);
	final int FPS;
  private int columnNumber = 6;
  private int rowNumber    = 8;

	public GamePanel() {
		this.FPS = 60;

		// displayed window
		setBackground(backgroundColor);
		setFocusable(true);
		this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    this.createTiles();
	}

	public void launchGame(){
		gameThread = new Thread(this);
		gameThread.start();
	}

  @Override
	public void run() {

		// game loop
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;

		while (gameThread!=null) {
			currentTime = System.nanoTime();
			delta += (currentTime-lastTime)/drawInterval;
			lastTime = currentTime;

			if(delta>=1){
				repaint();
				delta-=1;
			}
		}
		
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	public void createTiles() {
    setLayout(new GridLayout(rowNumber, columnNumber, 2, 2));

    java.util.Random rand = new java.util.Random();

    for (int row = 0; row < rowNumber; row++) {
      for (int col = 0; col < columnNumber; col++) {
        Color randomColor = new Color(
          rand.nextInt(256),
          rand.nextInt(256),
          rand.nextInt(256)
        );

        Tile tile = new Tile(0, 0, randomColor);

        tilesList.add(tile);
        this.add(tile);
      }
    }
  }


}