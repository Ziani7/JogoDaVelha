import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.Stack;

import javax.swing.SwingConstants;

public class Tela extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField c00;
	private JTextField c01;
	private JTextField c02;
	private JTextField c10;
	private JTextField c11;
	private JTextField c12;
	private JTextField c20;
	private JTextField c21;
	private JTextField c22;
	private JLabel status;
	private JButton btVoltar;
	private boolean win = false;
	LinkedList<JTextField> celulas;
	Tabuleiro tabuleiro = new Tabuleiro();
	Stack<String[][]> pilha = new Stack<>();
	Jogador p = new Jogador("Jogador", "X");
	Jogador pc = new Jogador("PC", "O");

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela frame = new Tela();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Tela() {
		initComponents();
		preencheLista();
		tabuleiro.preencher();
		pilha.push(tabuleiro.cloneTabuleiro());
		tabuleiro.mostrarTabuleiro();
	}

	public void preencheCel(JTextField tf, int l, int c, Jogador p) {
		if (!tabuleiro.verificaVitoria()) {
			if (tf.getText().isEmpty()) {
				tabuleiro.jogar(l, c, p);
				tabuleiro.mostrarTabuleiro();
				tf.setText("X");
			} else {
				JOptionPane.showMessageDialog(null, "Casa já ocupada!", "Ops", JOptionPane.INFORMATION_MESSAGE);
			}
			if (tabuleiro.verificaVitoria()) {
				win = true;
				JOptionPane.showMessageDialog(null, "Parabéns você venceu!", "Fim de jogo!",
						JOptionPane.INFORMATION_MESSAGE);
				status.setText("Status: Você venceu!");
			} else if (!tabuleiro.espacoDisponivel(tabuleiro)) {
				JOptionPane.showMessageDialog(null, "Empate!", "Acabou", JOptionPane.INFORMATION_MESSAGE);
				status.setText("Status: Empate!");
			} else {
				if (!win) {
					tabuleiro.jogadaPC(pc);
					pilha.push(tabuleiro.cloneTabuleiro());
					tabuleiro.mostrarTabuleiro();
					tabToTela(tabuleiro);
				}
				if (tabuleiro.verificaVitoria()) {
					win = true;
					JOptionPane.showMessageDialog(null, "O computador venceu!", "Fim de jogo!",
							JOptionPane.INFORMATION_MESSAGE);
					status.setText("Status: O computador venceu!");
				}
			}
		}
	}

	public void tabToTela(Tabuleiro tab) {
		int c = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (tab.getTabuleiro()[i][j].equals("O")) {
					this.celulas.get(c).setText("O");
				}
				c++;
			}
		}
	}
	
	public void voltaTela(Tabuleiro tab) {
		int c = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
					if(tab.getTabuleiro()[i][j].equals("_")) {
						this.celulas.get(c).setText("");
					}
				c++;
			}
		}
	}
	
	public void voltaJogada() {
		 if (pilha.size() > 1) {
             pilha.pop();
             tabuleiro.setTabuleiro(pilha.peek());
             tabuleiro.mostrarTabuleiro();
             voltaTela(tabuleiro);
         } else {
        	 JOptionPane.showMessageDialog(null, "Não há mais jogadas para voltar", "Ops", JOptionPane.INFORMATION_MESSAGE);
         }
	}
	
	public void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 102, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][grow][::5px][grow][::5px][grow][]", "[][][][100px:n,grow][::5px][100px:n,grow][::5px][100px:n,grow][][][]"));

		status = new JLabel("Status:");
		status.setForeground(new Color(255, 255, 255));
		status.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(status, "cell 1 1 5 1,alignx center");

		c00 = new JTextField();
		c00.setForeground(Color.BLACK);
		c00.setHorizontalAlignment(SwingConstants.CENTER);
		c00.setFont(new Font("Dialog", Font.PLAIN, 80));
		c00.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				preencheCel(c00, 0, 0, p);
			}
		});
		c00.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		c00.setBackground(new Color(102, 204, 255));
		c00.setEditable(false);
		contentPane.add(c00, "cell 1 3,grow");
		c00.setColumns(10);

		c01 = new JTextField();
		c01.setForeground(Color.BLACK);
		c01.setHorizontalAlignment(SwingConstants.CENTER);
		c01.setFont(new Font("Dialog", Font.PLAIN, 80));
		c01.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				preencheCel(c01, 0, 1, p);
			}
		});
		c01.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		c01.setBackground(new Color(102, 204, 255));
		c01.setEditable(false);
		contentPane.add(c01, "cell 3 3,grow");
		c01.setColumns(10);

		c02 = new JTextField();
		c02.setForeground(Color.BLACK);
		c02.setHorizontalAlignment(SwingConstants.CENTER);
		c02.setFont(new Font("Dialog", Font.PLAIN, 80));
		c02.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				preencheCel(c02, 0, 2, p);
			}
		});
		c02.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		c02.setBackground(new Color(102, 204, 255));
		c02.setEditable(false);
		contentPane.add(c02, "cell 5 3,grow");
		c02.setColumns(10);

		c10 = new JTextField();
		c10.setForeground(Color.BLACK);
		c10.setHorizontalAlignment(SwingConstants.CENTER);
		c10.setFont(new Font("Dialog", Font.PLAIN, 80));
		c10.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				preencheCel(c10, 1, 0, p);
			}
		});
		c10.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		c10.setBackground(new Color(102, 204, 255));
		c10.setEditable(false);
		contentPane.add(c10, "cell 1 5,grow");
		c10.setColumns(10);

		c11 = new JTextField();
		c11.setForeground(Color.BLACK);
		c11.setHorizontalAlignment(SwingConstants.CENTER);
		c11.setFont(new Font("Dialog", Font.PLAIN, 80));
		c11.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				preencheCel(c11, 1, 1, p);
			}
		});
		c11.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		c11.setBackground(new Color(102, 204, 255));
		c11.setEditable(false);
		contentPane.add(c11, "cell 3 5,grow");
		c11.setColumns(10);

		c12 = new JTextField();
		c12.setForeground(Color.BLACK);
		c12.setHorizontalAlignment(SwingConstants.CENTER);
		c12.setFont(new Font("Dialog", Font.PLAIN, 80));
		c12.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				preencheCel(c12, 1, 2, p);
			}
		});
		c12.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		c12.setBackground(new Color(102, 204, 255));
		c12.setEditable(false);
		contentPane.add(c12, "cell 5 5,grow");
		c12.setColumns(10);

		c20 = new JTextField();
		c20.setForeground(Color.BLACK);
		c20.setHorizontalAlignment(SwingConstants.CENTER);
		c20.setFont(new Font("Dialog", Font.PLAIN, 80));
		c20.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				preencheCel(c20, 2, 0, p);
			}
		});
		c20.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		c20.setBackground(new Color(102, 204, 255));
		c20.setEditable(false);
		contentPane.add(c20, "cell 1 7,grow");
		c20.setColumns(10);

		c21 = new JTextField();
		c21.setForeground(Color.BLACK);
		c21.setFont(new Font("Dialog", Font.PLAIN, 80));
		c21.setHorizontalAlignment(SwingConstants.CENTER);
		c21.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				preencheCel(c21, 2, 1, p);
			}
		});
		c21.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		c21.setBackground(new Color(102, 204, 255));
		c21.setEditable(false);
		contentPane.add(c21, "cell 3 7,grow");
		c21.setColumns(10);

		c22 = new JTextField();
		c22.setForeground(Color.BLACK);
		c22.setHorizontalAlignment(SwingConstants.CENTER);
		c22.setFont(new Font("Dialog", Font.PLAIN, 80));
		c22.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				preencheCel(c22, 2, 2, p);
			}
		});
		c22.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		c22.setBackground(new Color(102, 204, 255));
		c22.setEditable(false);
		contentPane.add(c22, "cell 5 7,grow");
		c22.setColumns(10);

		btVoltar = new JButton("Voltar jogada");
		btVoltar.setForeground(new Color(255, 255, 255));
		btVoltar.setBackground(new Color(0, 51, 102));
		btVoltar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!win) {
				voltaJogada();
				}
			}
		});
		contentPane.add(btVoltar, "cell 1 9 5 1,growx");
	}

	public void preencheLista() {
		celulas = new LinkedList<JTextField>();
		celulas.add(c00);
		celulas.add(c01);
		celulas.add(c02);
		celulas.add(c10);
		celulas.add(c11);
		celulas.add(c12);
		celulas.add(c20);
		celulas.add(c21);
		celulas.add(c22);
	}

}
