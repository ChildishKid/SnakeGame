import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

public class Controls extends JFrame
{
	private final int WINDOW_WIDTH = 200;
	private final int WINDOW_HEIGHT = 100;
	
	public Controls()
	{
		super();
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setBackground(Color.DARK_GRAY);
        setLocationRelativeTo(null);
		setLayout(new BorderLayout());
	}
	
	public void paint(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.drawString("SPACE - Pause", WINDOW_WIDTH/2 - 50, WINDOW_HEIGHT / 2);
		g.drawString("ArrowKeys - Move", WINDOW_WIDTH/2 - 50, WINDOW_HEIGHT / 2 + 25);
	}
	
	public static void main(String[] args)
	{
		Controls test = new Controls();
		test.setVisible(true);
	}
}
