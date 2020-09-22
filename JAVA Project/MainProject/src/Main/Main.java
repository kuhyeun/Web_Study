package Main;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



public class Main extends JFrame{
	
	static int day, time;
	static int[][] table_state = new int[5][16];  // �ð�ǥ�� ����
	static int[][][][] student_num = new int[5][16][8][5];   // �й�
	static String[][][][] student_name = new String[5][16][8][5]; // �л� �̸�
	
	public Main() {
		setTitle("�ڸ� ���� �ý���");	// Ÿ��Ʋ �̸�
		setLocation(600,150);		//ȭ�鿡 ��Ÿ�� ��ġ
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = getContentPane();
		contentPane.setLayout(null);
		
		
		contentPane.setBackground(Color.WHITE);	// ���� : ���
		
		// �뱸���б� �̹���
		ImageIcon icon = new ImageIcon("Image/Daegu.jpg");	
		JLabel label = new JLabel(icon);
		label.setSize(350,350);
		label.setLocation(160,-30);
		
		// ������
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image img = toolkit.getImage("Image/bono.png");
		setIconImage(img);
		
		//�ֻ�� ����
		JLabel label2 = new JLabel("�ڸ����� �ý���",SwingConstants.CENTER);
		label2.setFont(new Font("���",Font.BOLD,50));
		label2.setSize(400,100);
		label2.setLocation(130,270);
		
		// ��ư : �ڸ�����
		JButton button = new JButton("�ڸ�����");		
		button.setFont(new Font("���",Font.BOLD,30));
		button.setBackground(new Color(244,244,244));
		button.setSize(300,100);
		button.setLocation(180,380);
		button.addActionListener(new Timetable()); // ��ư Ŭ���� �ð�ǥ��� �۵�
		
		// ��ư : �ð�ǥ ����
		JButton button2 = new JButton("�ð�ǥ����");	
		button2.setFont(new Font("���",Font.BOLD,30));
		button2.setBackground(new Color(244,244,244));
		button2.setSize(300, 100);
		button2.setLocation(180, 490);
		button2.addActionListener(new SetTimetable()); // ��ư Ŭ���� �ð�ǥ���� �۵�
		
		//��ư : ����
		JButton button3 = new JButton("��  ��");	
		button3.setFont(new Font("���",Font.BOLD,30));
		button3.setBackground(new Color(244,244,244));
		button3.setSize(300, 100);
		button3.setLocation(180, 600);
		button3.addActionListener(new Quit()); // ��ư Ŭ���� ���α׷� ����
		
		// �� ��ư, �󺧵� ȭ�鿡 ǥ��
		contentPane.add(label);
		contentPane.add(label2);
		contentPane.add(button);
		contentPane.add(button2);
		contentPane.add(button3);
		
		setSize(700,750);
		setVisible(true);
		
		
	}
	
	private class Timetable implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			new TimeTable();
			
		}
	}	
	
	private class SetTimetable implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String num = JOptionPane.showInputDialog("�����ڸ� ������ �����մϴ� \n��й�ȣ�� �Է��ϼ���");
				if("0000".equals(num))
				{
					new SetTimeTable();
				}
				else
					JOptionPane.showMessageDialog(null,"��й�ȣ �����Դϴ�.","Message",JOptionPane.ERROR_MESSAGE);
			
			
			
		}
	}
	
	private class Quit implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
	
	public static void main(String[] args) {
		new Main();
	}

}
	