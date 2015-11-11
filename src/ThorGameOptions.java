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

public class ThorGameOptions extends JFrame implements ActionListener
{
	private JButton vButton;
	private JButton cButton;
	private ActorWorld world;
	private JTextArea display;
	private static final int FRAME_WIDTH = 500;
	private static final int FRAME_LENGTH = 500;

	public ThorGameOptions(ActorWorld w)
	{
			world = w;
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        Container contentPane = getContentPane();
	        contentPane.setLayout(new BorderLayout());

	        final JLayeredPane layeredPane = new JLayeredPane();
	        contentPane.add(layeredPane,BorderLayout.CENTER);

	        final JPanel btnPane = new JPanel(new GridBagLayout());
	        btnPane.setOpaque(false);

	        Icon i = new ImageIcon("Thor_GIFS\\play_button.png");
	        JButton btn = new JButton(i);
	        btn.addActionListener(this);
	        btn.setActionCommand("Open");
	        btn.setFont(btn.getFont().deriveFont(48.0f));
	        GridBagConstraints gbc = new GridBagConstraints();
	        gbc.gridx = 200;
	        gbc.gridy = 200;
	        gbc.ipady = 15;
	        gbc.ipadx = 40;
	        gbc.weightx = 0.0;
	        gbc.gridwidth = 200;
	        gbc.weighty = 1.0;
	        gbc.anchor = GridBagConstraints.CENTER;
	        btnPane.add(btn,gbc);

	        final JPanel lblPane = new JPanel(new GridBagLayout());

	        JLabel lbl = new JLabel(new ImageIcon("Thor_GIFS\\thor.jpg"));
	        gbc = new GridBagConstraints();
	        gbc.weightx = 1.0;
	        gbc.weighty = 1.0;
	        gbc.anchor = GridBagConstraints.CENTER;
	        lblPane.add(lbl,gbc);

	        layeredPane.add(btnPane,0);
	        layeredPane.add(lblPane,1);
	        layeredPane.addComponentListener(new ComponentAdapter() {
	            @Override
	            public void componentResized(ComponentEvent e) {
	                lblPane.setBounds(0, 0, layeredPane.getWidth(), layeredPane.getHeight());
	                btnPane.setBounds(0, 0, layeredPane.getWidth(), layeredPane.getHeight());
	            }
	        });

	        setExtendedState(JFrame.MAXIMIZED_BOTH);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setSize(1700, 745);
			setVisible(true);
	    }
	public void actionPerformed(ActionEvent e)
    {
        String cmd = e.getActionCommand();

        if(cmd.equals("Open"))
        {
            dispose();
            world.show();
        }
    }
	}
