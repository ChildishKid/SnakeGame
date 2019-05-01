import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;


public class SnakeGame extends JPanel implements ActionListener
{
	
	public final int SCREEN_WIDTH = 600;
	public final int SCREEN_HEIGHT = 600;
	private final int GAME_WIDTH = 400;
	private final int GAME_HEIGHT = 400;
	private final int SQUARE_SIZE = 10;

	private final int STARTING_X = SCREEN_WIDTH/2;	
	private final int STARTING_Y = SCREEN_HEIGHT/2;
	
	private ArrayList<Integer> body_position_x;
	private ArrayList<Integer> body_position_y;
	private int size;
	
	private boolean move_left = false;
	private boolean move_right = true;
	private boolean move_up = false;
	private boolean move_down = false;
	
	private int food_x;
	private int food_y;
	
	private Timer clock;
	private int DELAY;
	private boolean paused;
	private boolean gameOver;
	private boolean start;
	private boolean winCondition;
	
	private int score;
	private String difficulty;
	
	public SnakeGame(String difficulty)
	{
		super();
		setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		setBackground(Color.BLACK);
		addKeyListener(new KeyboardAdapter());
		setLayout(new BorderLayout());
		setFocusable(true);
		setVisible(true);
		
		body_position_x = new ArrayList<Integer>();
		body_position_y = new ArrayList<Integer>();
		body_position_x.add(STARTING_X);
		body_position_y.add(STARTING_Y);
		size = 1;
		addBody(STARTING_X, STARTING_Y);
		
		spawnFood();
		
		score = 0;
		this.difficulty = difficulty;
		if (this.difficulty.equals("EASY"))
			DELAY = 80;
		else if (this.difficulty.equals("HARD"))
			DELAY = 20;
		else if (this.difficulty.equals("NORMAL"))
			DELAY = 50;
		
		clock = new Timer(DELAY, this);
		clock.start();
		
		start = false;
		paused = false;	
		gameOver = false;
		winCondition = false;
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setColor(Color.WHITE);		
		g.drawRect((SCREEN_WIDTH - GAME_WIDTH)/2, (SCREEN_HEIGHT - GAME_HEIGHT)/2, GAME_WIDTH, GAME_HEIGHT);
		g.drawString("Score: " + score, SCREEN_WIDTH/2 - SCREEN_WIDTH/50, (SCREEN_HEIGHT - GAME_HEIGHT)/4);
		
		if (start)
		{
			g.setColor(Color.GREEN);
			for (int i = 0; i < size; i++)
				g.fillRect(body_position_x.get(i), body_position_y.get(i), SQUARE_SIZE, SQUARE_SIZE);

			g.setColor(Color.RED);
			g.fillRect(food_x, food_y, SQUARE_SIZE, SQUARE_SIZE);
		}
		else
		{
			g.drawString("Press Enter to start!", SCREEN_WIDTH/2 - SCREEN_WIDTH/12, SCREEN_WIDTH/2);
		}
		
		if (gameOver && winCondition)
		{
			g.setColor(Color.WHITE);
			g.drawString("Game Over! :)", SCREEN_WIDTH/2 - SCREEN_WIDTH/25, SCREEN_HEIGHT - 75);
		}
		
		if (gameOver == true)
		{
			g.setColor(Color.WHITE);
			g.drawString("Game Over! :(", SCREEN_WIDTH/2 - SCREEN_WIDTH/25, SCREEN_HEIGHT - 75);
		}
		
	}
	
	public void pause()
	{
		clock.stop();
		paused = true;
	}
	
	public void unpause()
	{
		clock.start();
		paused = false;
	}
	
	public void spawnFood()
	{
		loop:
		while(true)
		{
			food_x = (int) (Math.random() * (GAME_WIDTH/ 10)) * 10 + (SCREEN_WIDTH - GAME_WIDTH)/2;
			food_y = (int) (Math.random() * (GAME_HEIGHT/ 10)) * 10 + (SCREEN_HEIGHT - GAME_HEIGHT)/2;
			
			for (int i = 0; i < size; i++)
				if (food_x == body_position_x.get(i) && food_y == body_position_y.get(i))
					continue loop;
			
			break;
		}
	}
	
	public void gameOver()
	{
		clock.stop();
		gameOver = true;
	}
	
	public boolean foodCollisionCheck()
	{
		if (food_x == body_position_x.get(0) && food_y == body_position_y.get(0))
			return true;
		else 
			return false;
	}
	
	public boolean wallCollisionCheck()
	{
		int border_x = (SCREEN_WIDTH - GAME_WIDTH)/2;
		int border_y = (SCREEN_HEIGHT - GAME_HEIGHT)/2;
		
		if (body_position_x.get(0) < border_x || body_position_y.get(0) < border_y 
				|| body_position_x.get(0) > border_x + GAME_WIDTH - SQUARE_SIZE || body_position_y.get(0) > border_y + GAME_HEIGHT - SQUARE_SIZE)
			return true;
		else 
			return false;
	}

	public boolean bodyCollisionCheck()
	{
		for (int i = 0; i < size - 1; i++)
			for (int j = i + 1; j < size; j++)
				if (body_position_x.get(i).equals(body_position_x.get(j)) && body_position_y.get(i).equals(body_position_y.get(j)))
					return true;
		return false;
	}

	public void addBody(int position_x, int position_y)
	{
		body_position_x.add(position_x);
		body_position_y.add(position_y);
		size++;
		score += 100;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if (start == false)
			return;
		
		int position_x = body_position_x.get(size-1);
		int position_y = body_position_y.get(size-1);
		move();
		
		if (wallCollisionCheck() || bodyCollisionCheck())
			gameOver();
		
		if (foodCollisionCheck())
		{
			addBody(position_x, position_y);
			spawnFood();
		}
		
		if (size == ((GAME_WIDTH / SQUARE_SIZE) * (GAME_HEIGHT / SQUARE_SIZE)))
		{
			gameOver();
			winCondition = true;
		}
		
		repaint();
		
	}
		
	public void move()
	{
		for (int i = size - 1; i > 0; i--)
		{
			body_position_x.set(i, body_position_x.get(i-1));
			body_position_y.set(i, body_position_y.get(i-1));
		}
		
		if (move_left)
			body_position_x.set(0, body_position_x.get(0) - 10);
				
		else if (move_right)
			body_position_x.set(0, body_position_x.get(0) + 10);
		else if (move_up)
			body_position_y.set(0, body_position_y.get(0) - 10);
		else if (move_down)
			body_position_y.set(0, body_position_y.get(0) + 10);
	}

	private class KeyboardAdapter extends KeyAdapter
	{
		public void keyPressed(KeyEvent e)
		{
			int key = e.getKeyCode();
			
			if (key == KeyEvent.VK_ENTER && !start)
			{
				start = true;
			}
			
			if (gameOver == true)
				return;
			
			if (key == KeyEvent.VK_SPACE && paused == false)
				pause();
			else if (key == KeyEvent.VK_SPACE && paused == true)
				unpause();
			
			if (paused == true)
				return;
			
			if (key == KeyEvent.VK_DOWN && move_up == false)
			{
				move_left = false;
				move_right = false;
				move_down = true;
			}
			else if (key == KeyEvent.VK_UP && move_down == false)
			{
				move_left = false;
				move_right = false;
				move_up = true;
			}
			else if (key == KeyEvent.VK_LEFT && move_right == false)
			{
				move_up = false;
				move_down = false;
				move_left = true;
			}
			else if (key == KeyEvent.VK_RIGHT && move_left == false)
			{
				move_up = false;
				move_down = false;
				move_right = true;
			}
		}
	}
}
