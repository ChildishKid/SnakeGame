import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;




public class Board extends JFrame implements ActionListener
{
	private static final String TITLE = "Snake Game"; 
	
	private SnakeGame game;
	private String difficulty;
	
	public Board()
	{
		super();
		difficulty = "NORMAL";
        game = new SnakeGame(difficulty);
		setSize(game.SCREEN_WIDTH, game.SCREEN_HEIGHT);
		setTitle(TITLE);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		getContentPane().setBackground(Color.WHITE);
		addWindowListener(new ExitListener());
		setLayout(new BorderLayout());	
        setLocationRelativeTo(null);
        initializeMenu();
        add(game);
	}
	
	public void initializeMenu()
	{
		JMenu file = new JMenu("File");
		JMenuItem restart = new JMenuItem("New Game");
		restart.addActionListener(this);
		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(this);
		file.add(restart);
		file.add(exit);
		
		JMenu view = new JMenu("View");
		JMenuItem controls = new JMenuItem("Controls");
		controls.addActionListener(this);
		view.add(controls);
		
		JMenu edit = new JMenu("Edit");
		JMenu difficulty = new JMenu("Difficulty");
		JMenuItem easy = new JMenuItem("Easy");
		easy.addActionListener(this);
		JMenuItem normal = new JMenuItem("Normal");
		normal.addActionListener(this);
		JMenuItem hard = new JMenuItem("Hard");		
		hard.addActionListener(this);
		difficulty.add(easy);
		difficulty.add(normal);
		difficulty.add(hard);
		edit.add(difficulty);
		
		JMenuBar bar = new JMenuBar();
		bar.add(file);
		bar.add(view);
		bar.add(edit);
		setJMenuBar(bar);
	}

	public static void main(String[] args)
	{
		Board attempt = new Board();
		attempt.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) 
	{
		String action = e.getActionCommand();
		
		if (action.equals("New Game"))
		{
			remove(game);
			game = new SnakeGame(difficulty);
			add(game);
			revalidate();
			repaint();
			game.grabFocus();
		}
		else if (action.equals("Exit"))
			System.exit(0);
		
		else if (action.equals("Easy"))
			difficulty = "EASY";
		
		else if (action.equals("Normal"))
			difficulty = "NORMAL";
		
		else if (action.equals("Hard"))
			difficulty = "HARD";
			
		else if (action.equals("Controls"))
		{
			Controls window = new Controls();
			window.setVisible(true);
		}
	
	}
}
