package com.Flipbook.view.GUI;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;

import com.Flipbook.controller.ColorBoardFunction;
import com.Flipbook.controller.ImageInOutFunction;
import com.Flipbook.manager.FlipbookManager;
import com.Flipbook.model.Brush;

public class MainFrame {

	private FlipbookManager fm = new FlipbookManager();
	// �׸��ǿ� ���̴� �̹��� bi / ���ϸ鼭 ��µǴ� �̹����� �ٲٴµ��� �̿�Ǵ� bi2
	private BufferedImage bi = null;
	private BufferedImage bi2 = null;

	private Brush b = new Brush();

	// ��� �� �׸� ����Ʈ ��� ���� ��ü
	private String totalStr;
	private JLabel label_e = null;

	// �Է¹޴� �ð� ���� ��ü
	private Timer tm = new Timer();
	private TimerTask tt;

	// �ݺ��� �����ϰ� ���ִ� cnt
	private int cnt = 0;

	public MainFrame(String title) {

		fm.passTitle(title);
		bi = fm.firstpic();

	}

	public void mainFrame() {

		ColorBoardFunction cbf = new ColorBoardFunction();
		ImageInOutFunction ifo = new ImageInOutFunction();
		LineBorder border = new LineBorder(new Color(0xE7D0A5), 4);
		Font font = new Font("���� ���", Font.BOLD, 20);
		URL icon2URL = MainFrame.class.getClassLoader().getResource("cheese4.png");
		ImageIcon icon2 = new ImageIcon(icon2URL);

		// ������ ����
		JFrame frame = new JFrame();
		frame.setTitle("�ø��� �����");
		frame.setIconImage(icon2.getImage());
		frame.setBounds(100, 10, 1400, 910);
		frame.setLayout(null);
		frame.setResizable(false);
		// ��ü �г� ����


		URL iconURL = MainFrame.class.getClassLoader().getResource("real_cheese2.png");
		ImageIcon icon = new ImageIcon(iconURL);

		JPanel wholePanel = new JPanel() {

			public void paintComponent(Graphics g) {
				g.drawImage(icon.getImage(), 0, 0, null);
				setOpaque(false); // �׸��� ǥ���ϰ� ����,�����ϰ� ����
				super.paintComponent(g);
			}
		};
		wholePanel.setBackground(new Color(0xEEEE49));

		GridBagLayout grid = new GridBagLayout();
		wholePanel.setLayout(grid);
		wholePanel.setBounds(0, 0, 1400, 870);
		frame.add(wholePanel);

		// �׸��г�
		JPanel drawPanel = new JPanel();
		drawPanel.setLayout(null);
		drawPanel.setBackground(Color.white);

		GridBagConstraints constraint1 = new GridBagConstraints();
		constraint1.insets = new Insets(0, 0, 0, 0);
		constraint1.gridx = 0;
		constraint1.gridy = 0;
		constraint1.ipadx = 840;
		constraint1.ipady = 750;
		constraint1.gridheight = 3;
		constraint1.fill = GridBagConstraints.NONE;
		grid.setConstraints(drawPanel, constraint1);
		wholePanel.add(drawPanel);

		// �׸��� ����
		BufferedImage bi2 = fm.getImagef().getPicInfo().getPic();

		for (int i = 0; i < bi.getWidth(); i++)
			for (int j = 0; j < bi.getHeight(); j++) {
				bi.setRGB(i, j, bi2.getRGB(i, j));
			}

		JLabel l = new JLabel(new ImageIcon(bi));
		l.setBounds(0, 0, 840, 750);
		l.setBorder(border);
		drawPanel.add(l);

		// �귯����� Ŭ������ �����Ѵ�. �Ʒ��ʿ� ������.
		b.setBounds(0, 0, 840, 750); // ũ�Ⱑ l�� ���ƾ� �Ѵ�.
		drawPanel.add(b);

		// ���콺 ��� �̺�Ʈ
		l.addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseDragged(MouseEvent e) {

				b.xx = e.getX();
				b.yy = e.getY();
				b.repaint();
				b.printAll(bi.getGraphics()); // �귯���� BufferedImage �� �׸���.
			}

			@Override
			public void mouseMoved(MouseEvent e) {

			}
		});

		// ����Ʈ �г�

		JPanel listPanel = new JPanel();
		listPanel.setLayout(null);
		listPanel.setBackground(Color.white);
		listPanel.setBorder(border);

		GridBagConstraints constraint2 = new GridBagConstraints();
		constraint2.insets = new Insets(0, 30, 18, 10);// ���鼳��
		constraint2.gridx = 2;
		constraint2.gridy = 0;
		constraint2.ipadx = 300;
		constraint2.ipady = 550;
		constraint2.fill = GridBagConstraints.NONE;
		grid.setConstraints(listPanel, constraint2);
		wholePanel.add(listPanel);

		// ����Ʈ �г� - ��(list �̸�)
		JLabel label_l = new JLabel("IMAGE LIST");
		label_l.setFont(font);
		label_l.setHorizontalAlignment(label_l.CENTER);
		label_l.setVerticalAlignment(label_l.CENTER);
		label_l.setBounds(0, 0, 300, 100);

		LineBorder borderLlist = new LineBorder(new Color(0xE7D0A5), 4);
		label_l.setBorder(borderLlist);
		listPanel.add(label_l);

		// ����Ʈ �г� - label
		JLabel label_a = new JLabel();

		totalStr = fm.showList();
		label_a.setText(totalStr);

		label_a.setBounds(0, 100, 300, 450);
		label_a.setFont(font);
		label_a.setBorder(border);
		label_a.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		label_a.setHorizontalAlignment(label_a.CENTER);
		label_a.setVerticalAlignment(label_a.TOP);

		// ��ũ���г�
		JScrollPane scroll = new JScrollPane(label_a);
		scroll.setBounds(0, 100, 300, 450);
		scroll.getViewport().setBackground(Color.white);
		scroll.setBorder(border);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		// scroll.set
		listPanel.add(scroll);

		// �ð��г�
		JPanel timepanel = new JPanel();
		timepanel.setLayout(null);
		timepanel.setBackground(Color.white);
		timepanel.setBorder(border);

		GridBagConstraints constraint3 = new GridBagConstraints();
		constraint3.insets = new Insets(0, 22, 0, 0);// ���鼳��
		constraint3.gridx = 2;
		constraint3.gridy = 1;
		constraint3.ipadx = 300;
		constraint3.ipady = 80;
		constraint3.fill = GridBagConstraints.NONE;
		grid.setConstraints(timepanel, constraint3);
		wholePanel.add(timepanel);

		// �ð��г� - �ѱ�� �ð� �Է�(��)
		JLabel label_t = new JLabel("�ѱ�� �ð�(��) : ");
		label_t.setBounds(0, 0, 200, 80);
		label_t.setBorder(border);
		label_t.setFont(new Font("���� ���", Font.BOLD, 20));
		label_t.setHorizontalAlignment(label_t.CENTER);
		timepanel.add(label_t);

		// �ð��г� - �� �Է¹ޱ�

		JTextField input_time = new JTextField(10);
		input_time.setBounds(200, 0, 100, 80);
		input_time.setBorder(border);
		input_time.setHorizontalAlignment(input_time.CENTER);
		input_time.setFont(font);
		timepanel.add(input_time);

		// �����г�
		JPanel exepanel = new JPanel();
		exepanel.setLayout(null);
		exepanel.setBackground(Color.white);
		exepanel.setBorder(border);

		GridBagConstraints constraint4 = new GridBagConstraints();
		constraint4.insets = new Insets(10, 22, 0, 0);// ���鼳��
		constraint4.gridx = 2;
		constraint4.gridy = 2;
		constraint4.ipadx = 300;
		constraint4.ipady = 80;
		constraint4.fill = GridBagConstraints.NONE;
		grid.setConstraints(exepanel, constraint4);
		wholePanel.add(exepanel);

		// �����г� - ���� ��ư
		URL exeButtonURL = MainFrame.class.getClassLoader().getResource("play.png");
		JButton exeButton = new JButton(new ImageIcon(exeButtonURL));
		exeButton.setBounds(50, 10, 200, 60);
		exeButton.setHorizontalAlignment(exeButton.CENTER);
		exeButton.setBackground(Color.white);
		exeButton.setBorderPainted(false);
		exepanel.add(exeButton);

		// �����г� - ����â mainFrame
		Dialog exeWindow = new Dialog(frame, "����â");
		exeWindow.setBounds(100, 10, 1400, 910);
		exeWindow.setLayout(null);
		exeWindow.setBackground(new Color(233, 222, 204));

		// �����г� - ����â panel
		JPanel exePanel_r = new JPanel();
		// exePanel_r.setLayout();
		exePanel_r.setBounds(100, 120, 1200, 700);
		exePanel_r.setBackground(Color.white);
		
		
		label_e = new JLabel();
		label_e.setSize(840, 750);
		exePanel_r.add(label_e);
		exeWindow.add(exePanel_r, "Center");

		// �����г� - ���� ��ư

		URL exitImageURL = MainFrame.class.getClassLoader().getResource("exit2.png");
		ImageIcon exitImage = new ImageIcon(exitImageURL);
		JButton exitButton = new JButton(exitImage);
		exitButton.setBounds(1300, 60, 60, 60);
		exitButton.setContentAreaFilled(false);
		exitButton.setBorderPainted(false);
		exeWindow.add(exitButton);

		// exitbutton
		exitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				tt.cancel();
				exeWindow.dispose();

			}

		});

		// exebutton
		exeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				exeWindow.setVisible(true);
				String time = input_time.getText();
				double real_time = Double.parseDouble(time);

				BufferedImage[] barr = fm.imageList();

				tt = new TimerTask() {

					@Override
					public void run() {
						if (cnt == barr.length) {
							cnt = 0;
						}
						int x = barr[cnt].getWidth()-1;
						int y = barr[cnt].getHeight()-1;
						
						Color color = new Color(barr[cnt].getRGB(x, y));
						
						Color color2 = new Color(88,87,85);
						
						if(color.getRGB() == color2.getRGB()) {
							exePanel_r.setBackground(color2);
						}
						else {
							exePanel_r.setBackground(Color.white);
						}
						
						label_e.setIcon(new ImageIcon(barr[cnt]));
						cnt++;
					}
				};

				tm.schedule(tt, 0, (long) (real_time * 1000));

			}

		});

		// �׸������г�
		JPanel toolpanel = new JPanel();
		toolpanel.setLayout(null);
		toolpanel.setBackground(Color.white);
		toolpanel.setBorder(border);

		GridBagConstraints constraint5 = new GridBagConstraints();
		constraint5.insets = new Insets(0, 10, 0, 0);// ���ʿ���
		constraint5.gridx = 1;
		constraint5.gridy = 0;
		constraint5.ipadx = 80;
		constraint5.ipady = 750;
		constraint5.gridheight = 3;
		constraint5.fill = GridBagConstraints.NONE;
		grid.setConstraints(toolpanel, constraint5);
		wholePanel.add(toolpanel);

		// �׸����� �г� - ��ư(red)
		
		URL redButtonURL = MainFrame.class.getClassLoader().getResource("red.png");
		JButton redButton = new JButton(new ImageIcon(redButtonURL));
		redButton.setBorderPainted(false);
		redButton.setBackground(Color.white);
		redButton.setBounds(10, 10, 60, 60);
		toolpanel.add(redButton);

		redButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				b.col = fm.changeRedPen();
			}
		});

		// �׸����� �г� - ��ư(blue)
		URL blueButtonURL = MainFrame.class.getClassLoader().getResource("blue.png");
		JButton blueButton = new JButton(new ImageIcon(blueButtonURL));
		blueButton.setBorderPainted(false);
		blueButton.setBackground(Color.white);
		blueButton.setBounds(10, 80, 60, 60);
		toolpanel.add(blueButton);

		blueButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ColorBoardFunction cbf = new ColorBoardFunction();
				b.col = fm.changeBluePen();

			}
		});

		// �׸����� �г� - ��ư(green)

		URL greenButtonURL = MainFrame.class.getClassLoader().getResource("green.png");
		JButton greenButton = new JButton(new ImageIcon(greenButtonURL));
		greenButton.setBorderPainted(false);
		greenButton.setBackground(Color.white);
		greenButton.setBounds(10, 150, 60, 60);
		toolpanel.add(greenButton);

		greenButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ColorBoardFunction cbf = new ColorBoardFunction();
				b.col = fm.changeGreedPen();

			}
		});

		// �׸����� �г� - ��ư(black)
		
		URL blackButtonURL = MainFrame.class.getClassLoader().getResource("black.png");
		JButton blackButton = new JButton(new ImageIcon(blackButtonURL));
		blackButton.setBorderPainted(false);
		blackButton.setBackground(Color.white);
		blackButton.setBounds(10, 220, 60, 60);
		toolpanel.add(blackButton);

		blackButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ColorBoardFunction cbf = new ColorBoardFunction();
				b.col = fm.changeBlackPen();

			}
		});

		// �׸����� �г� - �Ϻ� �����
		URL eraserButton_sURL = MainFrame.class.getClassLoader().getResource("eraser_s.png");
		JButton eraserButton_s = new JButton(new ImageIcon(eraserButton_sURL));
		LineBorder borderL = new LineBorder(Color.yellow, 5);
		eraserButton_s.setBorderPainted(false);
		eraserButton_s.setBackground(Color.white);
		eraserButton_s.setBounds(10, 290, 60, 60);
		toolpanel.add(eraserButton_s);

		eraserButton_s.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ColorBoardFunction cbf = new ColorBoardFunction();
				b.col = fm.changeEraser();

			}
		});

		// �׸����� �г� - ���������
		URL eraserButton_aURL = MainFrame.class.getClassLoader().getResource("eraser_a.png");
		JButton eraserButton_a = new JButton(new ImageIcon(eraserButton_aURL));
		eraserButton_a.setOpaque(true);
		eraserButton_a.setBorderPainted(false);
		eraserButton_a.setBackground(Color.white);
		eraserButton_a.setBounds(10, 360, 60, 60);
		toolpanel.add(eraserButton_a);

		eraserButton_a.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				ColorBoardFunction cbf = new ColorBoardFunction();
				fm.allEraser(bi);
				b.repaint();

			}
		});
		
		//��������
		URL eraserButton_bURL = MainFrame.class.getClassLoader().getResource("real_black.png");
		JButton eraserButton_b = new JButton(new ImageIcon(eraserButton_bURL));
		eraserButton_b.setOpaque(true);
		eraserButton_b.setBorderPainted(false);
		eraserButton_b.setBackground(Color.white);
		eraserButton_b.setBounds(10, 430, 60, 60);
		toolpanel.add(eraserButton_b);

		eraserButton_b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
		
				ColorBoardFunction cbf = new ColorBoardFunction();
				fm.allEraser_b(bi);
				b.repaint();

			}
		});

		// �׸����� �г� - �߰�
		URL newButtonURL = MainFrame.class.getClassLoader().getResource("new_plus.png");
		JButton newButton = new JButton(new ImageIcon(newButtonURL));
		newButton.setBorderPainted(false);
		newButton.setBackground(Color.white);
		newButton.setBounds(10, 510, 60, 60);
		toolpanel.add(newButton);

		newButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				BufferedImage bi2 = fm.newPic();

				for (int x = 0; x < 840; x++) {
					for (int y = 0; y < 750; y++) {
						bi.setRGB(x, y, bi2.getRGB(x, y));
					}
				}

				b.repaint();

				totalStr = fm.showList();
				label_a.setText(totalStr);

			}

		});

		// �׸����� �г� - �����׸�
		URL nextButtonURL = MainFrame.class.getClassLoader().getResource("go.png");
		JButton nextButton = new JButton(new ImageIcon(nextButtonURL));
		nextButton.setBorderPainted(false);
		nextButton.setBackground(Color.white);
		nextButton.setBounds(10, 565, 60, 60);
		toolpanel.add(nextButton);

		nextButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {

					BufferedImage bi2 = fm.takeNextPic();

					for (int x = 0; x < 840; x++) {
						for (int y = 0; y < 750; y++) {
							bi.setRGB(x, y, bi2.getRGB(x, y));
						}
					}

					b.repaint();

				} catch (NullPointerException e1) {

				}

				totalStr = fm.showList();
				label_a.setText(totalStr);

			}

		});

		// �׸����� �г� - �����׸�
		URL preButtonURL = MainFrame.class.getClassLoader().getResource("back.png");
		JButton preButton = new JButton(new ImageIcon(preButtonURL));
		preButton.setBorderPainted(false);
		preButton.setBackground(Color.white);
		preButton.setBounds(10, 623, 60, 60);
		toolpanel.add(preButton);

		preButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {

					BufferedImage bi2 = fm.takePrePic();

					for (int x = 0; x < 840; x++) {
						for (int y = 0; y < 750; y++) {
							bi.setRGB(x, y, bi2.getRGB(x, y));
						}
					}

					b.repaint();

				} catch (NullPointerException e1) {

				}

				totalStr = fm.showList();
				label_a.setText(totalStr);

			}

		});

		// �׸����� �г� - ����
		URL saveButtonURL = MainFrame.class.getClassLoader().getResource("save.png");
		JButton saveButton = new JButton(new ImageIcon(saveButtonURL));
		saveButton.setBorderPainted(false);
		saveButton.setBackground(Color.white);
		saveButton.setBounds(10, 680, 60, 60);
		toolpanel.add(saveButton);

		saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				fm.getImagef().getPicInfo().setPic(bi);
				fm.savePic();

			}

		});

		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
