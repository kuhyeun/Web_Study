package Main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.LineBorder;





public class SetTimeTable extends JFrame{
	
	String[] weeks = {"��","ȭ","��","��","��"};
	String[] times = {"1","2","3","4","5","6","7","8"};
	JButton[][] table = new JButton[5][16];
	
	
	public SetTimeTable() {
		setTitle("�ð�ǥ�������");
		setLocation(600,150);
		
		Container contentPane = getContentPane();
		contentPane.setLayout(null);
		
		// �׵θ� ǥ�ø� ���� LineBorder
		LineBorder bb = new LineBorder(Color.black,1,false);
		contentPane.setBackground(Color.WHITE);	//������ ���
	
		// �ֻ�� ����
		JLabel title = new JLabel("���ǽ� ������Ȳ",SwingConstants.CENTER);
		title.setFont(new Font("���",Font.BOLD,50));
		title.setSize(400,100);
		title.setLocation(160,5);
		
		// ǥ : ��ĭ
		JLabel b = new JLabel();
		b.setBackground(Color.white);
		b.setOpaque(true);
		b.setBorder(bb);
		b.setSize(135,50);
		b.setLocation(0,95);
		
		// ���ϴ� ������ư
		JButton set = new JButton("�����Ϸ�");
		set.setBackground(new Color(244,244,244));
		set.setFont(new Font("���",Font.BOLD,50));
		set.setSize(700,85);
		set.addActionListener(new BtListener2());
		set.setLocation(0,625);
		
		// ǥ : ���� ǥ��
		for(int i = 1; i<6; i++)
		{
			JLabel week = new JLabel(weeks[i-1],SwingConstants.CENTER);
			week.setFont(new Font("���",Font.BOLD,30));
			week.setOpaque(true);
			week.setBackground(Color.white);
			week.setBorder(bb);
			week.setSize(110,50);
			week.setLocation(25+(i*110),95);
			contentPane.add(week);
		}

		// ǥ : ���� ����
		for(int i = 1; i<9;i++)
		{
			JLabel timeT = new JLabel(times[i-1]+"����",SwingConstants.CENTER);
			timeT.setFont(new Font("���",Font.BOLD,30));
			timeT.setOpaque(true);
			timeT.setBackground(Color.white);
			timeT.setBorder(bb);
			timeT.setSize(135,60);
			timeT.setLocation(0,85+(i*60));
			contentPane.add(timeT);
		}
		
		// ��ư : �� ����,�ð��� ��ư
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
				table[i][j].addActionListener(new BtListener());
				contentPane.add(table[i][j]);
			}
		}
		
		// �� ��ư, �󺧵� ȭ�鿡 ǥ��
		contentPane.add(title);
		contentPane.add(b);
		contentPane.add(set);
		
		setSize(700,750);
		setVisible(true);
	}

	private class BtListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton)e.getSource();	// ������ư�� ������ ����
			for(int i=0;i<5;i++) {
				for(int j=0;j<16;j++)  // for���� ���� ��ư���� Ȯ��
				{
					if(b == table[i][j]) // ������ư�� ���� ��ư�� ã��
					{
						if(Main.table_state[i][j] == 1) // �ش� �ð�ǥ�� ������ 1�̸�
						{
							Main.table_state[i][j] = 0; // �ش� �ð�ǥ�� ������ 0����
							b.setBackground(new Color(244,244,244)); // ���� ȸ��
						}
						else if(Main.table_state[i][j] == 0) // �ش� �ð�ǥ�� ������ 0�̸�
						{
							Main.table_state[i][j] = 1;  // �ش� �ð�ǥ�� ������ 1��
							b.setBackground(Color.orange); // ���� ��������
						}
					}
				}
			}
		}
	}
	
	private class BtListener2 implements ActionListener{
		public void actionPerformed(ActionEvent a) {
			// �����Ϸ� �޼��� ��� �� â ����
			JOptionPane.showMessageDialog(null,"������ �Ϸ�Ǿ����ϴ�","Message",JOptionPane.INFORMATION_MESSAGE);
			setVisible(false);
			dispose();
		}
	}
}