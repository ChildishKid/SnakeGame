import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConfirmExitWindow extends JFrame implements ActionListener 
{
	private final int WINDOW_WIDTH = 200;
	private final int WINDOW_HEIGHT = 100;
	
	public ConfirmExitWindow()
	{
		super();
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		JLabel confirmation = new JLabel("Are you sure you want to exit?");
		add(confirmation, BorderLayout.CENTER);
		
		JButton exitButton = new JButton("yes");
		exitButton.addActionListener(this);
		JButton cancelButton = new JButton("cancel");
		cancelButton.addActionListener(this);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.add(exitButton);
		buttonPanel.add(cancelButton);
		
		add(buttonPanel, BorderLayout.SOUTH);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		String command = e.getActionCommand();
		
		if(command.equals("yes"))
			System.exit(0);
		else if (command.equals("cancel"))
			dispose();
		else
		{
			System.out.println("Unexpected error!");
		}
			
	}
}
