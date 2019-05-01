import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ExitListener extends WindowAdapter 
{
	public void windowClosing(WindowEvent e)
	{
		ConfirmExitWindow check = new ConfirmExitWindow();
		check.setVisible(true);
	}
}
