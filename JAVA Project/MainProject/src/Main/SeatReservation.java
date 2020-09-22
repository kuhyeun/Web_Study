package Main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class SeatReservation extends JFrame {
	
	JButton[][] seat = new JButton[8][5];
	public SeatReservation() {
		setTitle("�ڸ� ����");
		setLocation(600,150);
		
		Container contentPane = getContentPane();
		contentPane.setLayout(null);
		
		contentPane.setBackground(Color.WHITE);
		
		//�ֻ�� ����
		JLabel label = new JLabel("�ڸ� ����",SwingConstants.CENTER);
		label.setFont(new Font("���",Font.BOLD,50));
		label.setSize(400,100);
		label.setLocation(380,5);
		
		//���ϴ� ��ư
		JButton bt = new JButton("�ڷΰ���");
		bt.setFont(new Font("���",Font.BOLD,30));
		bt.addActionListener(new Back());
		bt.setBackground(new Color(244,244,244));
		bt.setSize(1160,60);
		bt.setLocation(0,400);
		
		// ��ư : 40�ڸ�
		for(int i = 0; i<8;i++)
		{
			for(int j = 0; j<5; j++)
			{
				seat[i][j] = new JButton();
				seat[i][j].addActionListener(new SeatBt());
				// ��ư�� ��ȣ�� ���̱� ���� �κ� 
				if(Main.student_num[Main.day][Main.time][i][j] == 0) //�ڸ��� ������� �ʾ��� ���
				{
					int x = (j*5)+i+1;
					String num = Integer.toString(x);
					seat[i][j].setText(num); // 1~40�� �ڸ���ȣ�� �Էµ�
				}
				else  // �ڸ��� ����Ǿ������
				{
					seat[i][j].setText(Main.student_name[Main.day][Main.time][i][j]);
				}
				
				seat[i][j].setSize(120, 40);
				seat[i][j].setBackground(new Color(244,244,244));
				if(i == 0)
					seat[i][j].setLocation(60,100+(j*60));
				else if(i == 1)
					seat[i][j].setLocation(180,100+(j*60));
				else if(i == 2)
					seat[i][j].setLocation(300,100+(j*60));
				else if(i == 3)
					seat[i][j].setLocation(420,100+(j*60));
				else if(i == 4)
					seat[i][j].setLocation(620,100+(j*60));
				else if(i == 5)
					seat[i][j].setLocation(740,100+(j*60));
				else if(i == 6)
					seat[i][j].setLocation(860,100+(j*60));
				else if(i == 7)
					seat[i][j].setLocation(980,100+(j*60));
				contentPane.add(seat[i][j]);
			}
		}
		
		// �� ��ư, �󺧵� ȭ�鿡 ǥ��
		contentPane.add(label);
		contentPane.add(bt);
		
		setSize(1160,500);
		setVisible(true);
	}
	
	public static boolean isNumeric(String s) {
		try {
			Double.parseDouble(s);
			return true;
		}catch (NumberFormatException e) {
			return false;
		}
	}
	
	private class Back implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			dispose();
		}
	}
	
	private class SeatBt implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton)e.getSource();  // ���� ��ư�� ������ ����
			for(int i=0;i<8;i++) {
				for(int j=0;j<5;j++)  // for ���� ���� ��ư���� Ȯ��
				{
					if(b == seat[i][j]) // ���� ��ư�� ���� ��ư�� ã����
					{
						if(Main.student_num[Main.day][Main.time][i][j] == 0)   // ������� ���� �ڸ��� ���
						{
							// �й��Է� �䱸
							String num = JOptionPane.showInputDialog("�й��� �Է��ϼ���");
							if(num != null)  // �й��� �Է��� ���
							{
								if (isNumeric(num) == false) // ���ڷθ� �ԷµǾ����� Ȯ��
									JOptionPane.showMessageDialog(null,"���ڸ� �Է��ϼ���","Message",JOptionPane.ERROR_MESSAGE);
								else // ���ڷθ� �Է��� �Ǿ��ٸ�
								{
									int error = 0;
									for(int k=0;k<8;k++)
										for(int l=0;l<5;l++)
											if(Main.student_num[Main.day][Main.time][k][l] == Integer.parseInt(num))
											{
												JOptionPane.showMessageDialog(null,"�̹� ��ϵ� �й��Դϴ�.","Message",JOptionPane.ERROR_MESSAGE);
												error = 1;
											}
									if(error == 0)
									{
									Main.student_num[Main.day][Main.time][i][j] = Integer.parseInt(num); // int������ ��ȯ�Ͽ� ����
									String name = JOptionPane.showInputDialog("�̸��� �Է��ϼ���");
									Main.student_name[Main.day][Main.time][i][j] = name;
									seat[i][j].setText(Main.student_name[Main.day][Main.time][i][j]);
									}
									else if(error == 1)
										error = 0;
								}
							}
							else
								;
						}
						else if(Main.student_num[Main.day][Main.time][i][j] != 0)  // ����� ���
						{
							int result = JOptionPane.showConfirmDialog(null,"�̹� ����� �ڸ��Դϴ�. ������ ����Ͻðڽ��ϱ�?","Message",JOptionPane.YES_NO_OPTION);
							if(result == JOptionPane.YES_OPTION)
							{
								int x = 0;
								String num = JOptionPane.showInputDialog("�й��� �Է��ϼ���");
								if(num != null)  // �й��� �Է��� ���
								{
									if (isNumeric(num) == false) // ���ڷθ� �ԷµǾ����� Ȯ��
										JOptionPane.showMessageDialog(null,"���ڸ� �Է��ϼ���","Message",JOptionPane.ERROR_MESSAGE);
									else
									{
										x = Integer.parseInt(num);
										String name = JOptionPane.showInputDialog("�̸��� �Է��ϼ���"); 
										// �Է¹��� �̸��� �й��� ������ �˻�
										if((Main.student_num[Main.day][Main.time][i][j] == x) && (name.equals(Main.student_name[Main.day][Main.time][i][j])))
										{
											JOptionPane.showMessageDialog(null,"������ ��ҵǾ����ϴ�.","Message",JOptionPane.INFORMATION_MESSAGE);
											Main.student_num[Main.day][Main.time][i][j] = 0;
											int y = (j*5)+i+1;
											String number = Integer.toString(y);
											seat[i][j].setText(number);
										}
										else
											JOptionPane.showMessageDialog(null,"�й��� �̸��� ��ġ���� �ʽ��ϴ�.","Message",JOptionPane.ERROR_MESSAGE);
									}
								}
							}
						}
					}
				}
			}
		}
	}
}
