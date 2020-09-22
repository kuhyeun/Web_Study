package Main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class TimeTable extends JFrame {

	String[] weeks = {"월","화","수","목","금"};
	String[] times = {"1","2","3","4","5","6","7","8"};
	JButton[][] table = new JButton[5][16];
	
	public TimeTable() {
		setTitle("시간표");
		setLocation(600,150);
		
		Container contentPane = getContentPane();
		contentPane.setLayout(null);
		
		// 테두리 표시를 위한 LineBorder
		LineBorder bb = new LineBorder(Color.black,1,false);
		contentPane.setBackground(Color.WHITE);	//바탕색 흰색
	
		// 최상단 제목
		JLabel title = new JLabel("강의실 수업현황",SwingConstants.CENTER);
		title.setFont(new Font("고딕",Font.BOLD,50));
		title.setSize(400,100);
		title.setLocation(160,5);
		
		// 돌아가기 버튼
		JButton back = new JButton("돌아가기");
		back.setBackground(new Color(244,244,244));
		back.setFont(new Font("고딕",Font.BOLD,50));
		back.setSize(700,85);
		back.addActionListener(new Bt2());
		back.setLocation(0,625);
		
		// 표 : 빈칸
		JLabel b = new JLabel();
		b.setBackground(Color.white);
		b.setOpaque(true);
		b.setBorder(bb);
		b.setSize(135,50);
		b.setLocation(0,95);

		// 표 : 요일 표시
		for(int i = 1; i<6; i++)
		{
			JLabel week = new JLabel(weeks[i-1],SwingConstants.CENTER);
			week.setFont(new Font("고딕",Font.BOLD,30));
			week.setOpaque(true);
			week.setBackground(Color.white);
			week.setBorder(bb);
			week.setSize(110,50);
			week.setLocation(25+(i*110),95);
			contentPane.add(week);
		}

		// 표 : 교시 교시
		for(int i = 1; i<9;i++)
		{
			JLabel time = new JLabel(times[i-1]+"교시",SwingConstants.CENTER);
			time.setFont(new Font("고딕",Font.BOLD,30));
			time.setOpaque(true);
			time.setBackground(Color.white);
			time.setBorder(bb);
			time.setSize(135,60);
			time.setLocation(0,85+(i*60));
			contentPane.add(time);
		}
		
		// 버튼 : 각 요일,시간별 버튼
		for(int i = 0; i<5;i++)
		{
			for(int j = 0; j<16; j++)
			{
				table[i][j] = new JButton();
				table[i][j].setSize(110, 30);
				if(Main.table_state[i][j] == 0)
					table[i][j].setBackground(new Color(244,244,244));
				else if(Main.table_state[i][j] == 1)
					table[i][j].setBackground(Color.orange);
				if(i == 0)
					table[i][j].setLocation(135,145+(j*30));
				else if(i == 1)
					table[i][j].setLocation(245,145+(j*30));
				else if(i == 2)
					table[i][j].setLocation(355,145+(j*30));
				else if(i == 3)
					table[i][j].setLocation(465,145+(j*30));
				else if(i == 4)
					table[i][j].setLocation(575,145+(j*30));
				table[i][j].addActionListener(new Bt());
				contentPane.add(table[i][j]);
			}
		}
		
		// 각 버튼, 라벨들 화면에 표시
		contentPane.add(title);
		contentPane.add(b);
		contentPane.add(back);
		
		setSize(700,750);
		setVisible(true);
	}
	
	private class Bt implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton)e.getSource(); // 눌린버튼의 정보를 받음
			for(int i=0;i<5;i++) {
				for(int j=0;j<16;j++) // for문을 돌며 버튼정보 확인
				{
					if(b == table[i][j]) // 눌린버튼과 같은버튼을 찾음
					{
						if(Main.table_state[i][j] == 0) // 해당 시간표의 내용이 0이면 
						{
							// 수업이 없음으로 오류 발생
							JOptionPane.showMessageDialog(null,"해당 시간에는 수업이 없습니다","Message",JOptionPane.ERROR_MESSAGE);
						}
						else if(Main.table_state[i][j] == 1) // 해당 시간표의 내용이 1이면
						{
							Main.day = i;
							Main.time = j;
							// 자리예약 진행
							new SeatReservation();
						}
					}
				}
			}
		}
	}
	
	private class Bt2 implements ActionListener{
		public void actionPerformed(ActionEvent a) {
			setVisible(false);
			dispose();
		}
	}
}
