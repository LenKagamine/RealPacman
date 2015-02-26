import javax.swing.*;
public class Pacman{
    public static void main(String[] args){
	JFrame window = new JFrame("Pacman");
	window.setContentPane(new GamePanel());
	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	window.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
	window.setUndecorated(true);
	window.setResizable(false);
	window.pack();
	window.setLocationRelativeTo(null);
	window.setVisible(true);
    }
}
