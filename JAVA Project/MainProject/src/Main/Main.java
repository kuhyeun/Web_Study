package Main;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



public class Main extends JFrame{
	
	static int day, time;
	static int[][] table_state = new int[5][16];  // 시간표의 상태
	static int[][][][] student_num = new int[5][16][8][5];   // 학번
	static String[][][][] student_name = new String[5][16][8][5]; // 학생 이름
	
	public Main() {
		setTitle("자리 예약 시스템");	// 타이틀 이름
		setLocation(600,150);		//화면에 나타날 위치
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = getContentPane();
		contentPane.setLayout(null);
		
		
		contentPane.setBackground(Color.WHITE);	// 배경색 : 흰색
		
		// 대구대학교 이미지
		ImageIcon icon = new ImageIcon("Image/Daegu.jpg");	
		JLabel label = new JLabel(icon);
		label.setSize(350,350);
		label.setLocation(160,-30);
		
		// 아이콘
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image img = toolkit.getImage("Image/bono.png");
		setIconImage(img);
		
		//최상단 제목
		JLabel label2 = new JLabel("자리예약 시스템",SwingConstants.CENTER);
		label2.setFont(new Font("고딕",Font.BOLD,50));
		label2.setSize(400,100);
		label2.setLocation(130,270);
		
		// 버튼 : 자리예약
		JButton button = new JButton("자리예약");		
		button.setFont(new Font("고딕",Font.BOLD,30));
		button.setBackground(new Color(244,244,244));
		button.setSize(300,100);
		button.setLocation(180,380);
		button.addActionListener(new Timetable()); // 버튼 클릭시 시간표목록 작동
		
		// 버튼 : 시간표 관리
		JButton button2 = new JButton("시간표관리");	
		button2.setFont(new Font("고딕",Font.BOLD,30));
		button2.setBackground(new Color(244,244,244));
		button2.setSize(300, 100);
		button2.setLocation(180, 490);
		button2.addActionListener(new SetTimetable()); // 버튼 클릭시 시간표관리 작동
		
		//버튼 : 종료
		JButton button3 = new JButton("종  료");	
		button3.setFont(new Font("고딕",Font.BOLD,30));
		button3.setBackground(new Color(244,244,244));
		button3.setSize(300, 100);
		button3.setLocation(180, 600);
		button3.addActionListener(new Quit()); // 버튼 클릭시 프로그램 종료
		
		// 각 버튼, 라벨들 화면에 표시
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
			String num = JOptionPane.showInputDialog("관리자만 접근이 가능합니다 \n비밀번호를 입력하세요");
				if("0000".equals(num))
				{
					new SetTimeTable();
				}
				else
					JOptionPane.showMessageDialog(null,"비밀번호 오류입니다.","Message",JOptionPane.ERROR_MESSAGE);
			
			
			
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
	