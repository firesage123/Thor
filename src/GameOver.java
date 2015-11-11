import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import info.gridworld.actor.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JLabel;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
public class GameOver extends JFrame implements ActionListener
{
	private ActorWorld world;
	private JButton vButton;
	public GameOver(ActorWorld w)
	{
		world = w;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		vButton = new JButton(" Game Over! ");
        vButton.addActionListener(this);
        vButton.setActionCommand("Open");
		panel.add(vButton);
		add(panel);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent e)
    {
        String cmd = e.getActionCommand();

        if(cmd.equals("Open"))
        {
            
        }
    }
}
