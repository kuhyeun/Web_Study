package Main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class SeatReservation extends JFrame {
	
	JButton[][] seat = new JButton[8][5];
	public SeatReservation() {
		setTitle("자리 예약");
		setLocation(600,150);
		
		Container contentPane = getContentPane();
		contentPane.setLayout(null);
		
		contentPane.setBackground(Color.WHITE);
		
		//최상단 제목
		JLabel label = new JLabel("자리 예약",SwingConstants.CENTER);
		label.setFont(new Font("고딕",Font.BOLD,50));
		label.setSize(400,100);
		label.setLocation(380,5);
		
		//최하단 버튼
		JButton bt = new JButton("뒤로가기");
		bt.setFont(new Font("고딕",Font.BOLD,30));
		bt.addActionListener(new Back());
		bt.setBackground(new Color(244,244,244));
		bt.setSize(1160,60);
		bt.setLocation(0,400);
		
		// 버튼 : 40자리
		for(int i = 0; i<8;i++)
		{
			for(int j = 0; j<5; j++)
			{
				seat[i][j] = new JButton();
				seat[i][j].addActionListener(new SeatBt());
				// 버튼에 번호를 붙이기 위한 부분 
				if(Main.student_num[Main.day][Main.time][i][j] == 0) //자리가 예약되지 않았을 경우
				{
					int x = (j*5)+i+1;
					String num = Integer.toString(x);
					seat[i][j].setText(num); // 1~40의 자리번호가 입력됨
				}
				else  // 자리가 예약되었을경우
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
		
		// 각 버튼, 라벨들 화면에 표시
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
			JButton b = (JButton)e.getSource();  // 눌린 버튼의 정보를 받음
			for(int i=0;i<8;i++) {
				for(int j=0;j<5;j++)  // for 문을 돌며 버튼정보 확인
				{
					if(b == seat[i][j]) // 눌린 버튼과 같은 버튼을 찾으면
					{
						if(Main.student_num[Main.day][Main.time][i][j] == 0)   // 예약되지 않은 자리의 경우
						{
							// 학번입력 요구
							String num = JOptionPane.showInputDialog("학번을 입력하세요");
							if(num != null)  // 학번이 입력됬을 경우
							{
								if (isNumeric(num) == false) // 숫자로만 입력되었는지 확인
									JOptionPane.showMessageDialog(null,"숫자만 입력하세요","Message",JOptionPane.ERROR_MESSAGE);
								else // 숫자로만 입력이 되었다면
								{
									int error = 0;
									for(int k=0;k<8;k++)
										for(int l=0;l<5;l++)
											if(Main.student_num[Main.day][Main.time][k][l] == Integer.parseInt(num))
											{
												JOptionPane.showMessageDialog(null,"이미 등록된 학번입니다.","Message",JOptionPane.ERROR_MESSAGE);
												error = 1;
											}
									if(error == 0)
									{
									Main.student_num[Main.day][Main.time][i][j] = Integer.parseInt(num); // int형으로 변환하여 저장
									String name = JOptionPane.showInputDialog("이름을 입력하세요");
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
						else if(Main.student_num[Main.day][Main.time][i][j] != 0)  // 예약된 경우
						{
							int result = JOptionPane.showConfirmDialog(null,"이미 예약된 자리입니다. 예약을 취소하시겠습니까?","Message",JOptionPane.YES_NO_OPTION);
							if(result == JOptionPane.YES_OPTION)
							{
								int x = 0;
								String num = JOptionPane.showInputDialog("학번을 입력하세요");
								if(num != null)  // 학번이 입력됬을 경우
								{
									if (isNumeric(num) == false) // 숫자로만 입력되었는지 확인
										JOptionPane.showMessageDialog(null,"숫자만 입력하세요","Message",JOptionPane.ERROR_MESSAGE);
									else
									{
										x = Integer.parseInt(num);
										String name = JOptionPane.showInputDialog("이름을 입력하세요"); 
										// 입력받은 이름이 학번과 같은지 검사
										if((Main.student_num[Main.day][Main.time][i][j] == x) && (name.equals(Main.student_name[Main.day][Main.time][i][j])))
										{
											JOptionPane.showMessageDialog(null,"예약이 취소되었습니다.","Message",JOptionPane.INFORMATION_MESSAGE);
											Main.student_num[Main.day][Main.time][i][j] = 0;
											int y = (j*5)+i+1;
											String number = Integer.toString(y);
											seat[i][j].setText(number);
										}
										else
											JOptionPane.showMessageDialog(null,"학번과 이름이 일치하지 않습니다.","Message",JOptionPane.ERROR_MESSAGE);
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
