import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;

public class Tile extends JPanel {
    private Color color;
    private JLabel jlabel;

    public Tile(int width, int height, Color color) {
      this.color = color;

      setLayout(new GridBagLayout()); // center the label
      jlabel = new JLabel("0");
      jlabel.setFont(new Font("Verdana", Font.BOLD, 20));
      add(jlabel);
    }

    @Override
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);

      Graphics2D g2d = (Graphics2D) g;
      g2d.setColor(color);
      g2d.fillRect(0, 0, getWidth(), getHeight());

      g2d.setStroke(new BasicStroke(2));
      g2d.setColor(Color.BLACK);
      g2d.drawRect(0, 0, getWidth(), getHeight());
    }

    public void setText(String text) {
      jlabel.setText(text);
    }
}
