import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GamePanel extends JPanel implements Runnable {

	private Thread gameThread;
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
    Random rand = new Random();
	  
    // initiate corners with random colors
    Color colorTopLeft     = randomColor(rand);
    Color colorBottomLeft  = randomColor(rand);
    Color colorTopRight    = randomColor(rand);
    Color colorBottomRight = randomColor(rand);

    // fill first and last columns
    List<Color> firstCol = getRowWithHarmonizedColors(colorTopLeft, colorBottomLeft, rowNumber);
    List<Color> lastCol  = getRowWithHarmonizedColors(colorTopRight, colorBottomRight, rowNumber);

    for (int row = 0; row < rowNumber; row++) {
      List<Color> newRow = getRowWithHarmonizedColors(firstCol.get(row), lastCol.get(row), columnNumber);
      for(int col = 0; col<columnNumber; col++){
        Tile tile = new Tile(0, 0, newRow.get(col));
        this.add(tile);
      }     
    }
  }

  public Color randomColor(java.util.Random rand){
    return new Color(
      rand.nextInt(256),
      rand.nextInt(256),
      rand.nextInt(256)
    );
  }

  public List<Color> getRowWithHarmonizedColors(Color firstColor, Color lastColor, int size) {
    List<Color> row = new ArrayList<>(size);

    int r1 = firstColor.getRed();
    int g1 = firstColor.getGreen();
    int b1 = firstColor.getBlue();

    int r2 = lastColor.getRed();
    int g2 = lastColor.getGreen();
    int b2 = lastColor.getBlue();

    for (int i = 0; i < size; i++) {
      float t = (float) i / (size - 1);
      int r = Math.round(r1 + t * (r2 - r1));
      int g = Math.round(g1 + t * (g2 - g1));
      int b = Math.round(b1 + t * (b2 - b1));
      row.add(new Color(r, g, b));
    }

    return row;
  }

}