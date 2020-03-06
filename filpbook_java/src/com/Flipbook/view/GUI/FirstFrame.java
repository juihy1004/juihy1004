package com.Flipbook.view.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.Flipbook.Run.Run;
import com.Flipbook.manager.FlipbookManager;
import com.Flipbook.model.Flipbook;

public class FirstFrame extends JFrame {

	private JButton button;
	private JLabel iconLabel;
	private JPanel wholePanel;
	FlipbookManager fm = new FlipbookManager();

	public FirstFrame() {
		File flipbookfolder = new File(ClassLoader.getSystemClassLoader().getResource(".").getPath()+"\\flipbookImages");
		flipbookfolder.mkdir();
		
		this.setResizable(false);
		
		URL icon2URL = Run.class.getClassLoader().getResource("first_cheese2.jpg");
		URL icon3URL = FirstFrame.class.getClassLoader().getResource("soloCheese2.png");
		URL icon4URL = FirstFrame.class.getClassLoader().getResource("cheese4.png");
		URL icon5URL = FirstFrame.class.getClassLoader().getResource("rotation_chesse3.gif");		

		ImageIcon icon2 = new ImageIcon(icon2URL);//배경
		ImageIcon icon3 = new ImageIcon(icon3URL);//버튼
		ImageIcon icon4 = new ImageIcon(icon4URL); // 아이콘
		ImageIcon icon5 = new ImageIcon(icon5URL);//돌아감
				

		wholePanel = new JPanel() {

			public void paintComponent(Graphics g) {

				g.drawImage(icon2.getImage(), 0, 0, null);
				setOpaque(false); // 그림을 표시하게 설정,투명하게 조절
				super.paintComponent(g);
			}
		};

		wholePanel.setBounds(0, 0, 1400, 910);
		wholePanel.setLayout(null);

		Font font = new Font("맑은 고딕", Font.BOLD, 23);
		Font font2 = new Font("맑은 고딕", Font.BOLD, 40);
		this.setLayout(null);

		this.setSize(1400, 910);
		this.setLocation(100, 10);
		this.setTitle("플립북 만들기");
		this.setIconImage(icon4.getImage());
		this.getContentPane().setBackground(new Color(0xEEEE49));

		JLabel j1 = new JLabel("플립북 이름");
		j1.setLocation(350, 700);
		j1.setSize(140, 70);
		j1.setFont(font);

		JTextField tf = new JTextField(100);
		tf.setLocation(500, 700);
		tf.setSize(350, 70);

		JLabel label1 = new JLabel("LET'S MAKE TOGETHER!");
		label1.setLocation(470, 530);
		label1.setSize(600, 100);
		label1.setFont(font2);

		JButton btn = new JButton(icon3);
	    btn.setBorderPainted(false);
	    btn.setBackground(new Color(233,222,204));
	    btn.setLocation(870, 700);
	    btn.setSize(140, 70);

		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String title = tf.getText();
				if (title.equals("")) {
					setVisible(false);
					new FirstFrame();
					return;
				}

				MainFrame ms = new MainFrame(title);

				ms.mainFrame();

				setVisible(false);

			}

		});
		
		btn.addMouseListener(new MouseListener() { 

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				JButton b = (JButton)e.getSource();
				b.setIcon(icon5);
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				JButton b = (JButton)e.getSource();
				b.setIcon(icon3);
				
			}
			
			
		});

		wholePanel.add(label1);
		wholePanel.add(j1);
		wholePanel.add(tf);
		wholePanel.add(btn);
		this.add(wholePanel);

		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
