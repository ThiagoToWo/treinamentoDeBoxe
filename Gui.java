package treinamento_boxe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class Gui extends JFrame {	

	private static final long serialVersionUID = 1L;
	private JPanel painelDeFundo;
	private JPanel painelDeSecao;
	private JPanel painelDeExibicao;
	private JTextPane categPane;
	private JLabel comboLabel;
	private JTextPane legendaPane;
	private JPanel painelDeBotao;
	private String[] categDeAbertura = {"Nenhuma", "Aberturas Básicas", "Aberturas contra oponentes defensivos", "Todas"};
	private String[] categDeCombBasica = {"Nenhuma", "Combinações básicas", "Combinações In-Fighting", "Todas"};
	private String[] categDeCombAvancada = {"Nenhuma", "Combinações evasivas", "Combinações avançadas", "Todas"};
	private String[] categDeCombOutros = {"Nenhuma", "Combos para ortodoxos vs southpaw", "Combos para southpaw vs ortodoxos", "Todas"};
	private String[] categDeFinal = {"Nenhuma", "Finalizações poderosas", "Finalizações evasivas", "Todas"};
	private String[] velocidades = {"Primeiro contato (4 a 20 segundos)", "Iniciante (3 a 15 segundos)", "Praticante (2 a 10 segundos)", "Avançado (1 a 5 segundos)"};
	private JComboBox<String> categDeAbertura1;
	private JComboBox<String> categDeCombBasica1;
	private JComboBox<String> categDeCombAvancada1;
	private JComboBox<String> categDeCombOutros1;
	private JComboBox<String> categDeFinal1;
	private JComboBox<String> velocidades1;
	private JComponent[] categorias = new JComponent[5];
	private JButton botaoIniciar;
	private JButton botaoParar;
	private Base base;
	private Timer round;
	private int passo = -1;
	private Font fonteLabel;
	private Font fonteVisivel;
	private Font fonteCategoria;
	private Font fonteCombo;
	private Font fonteLegenda;
	private BarraDeMenu barraMenu;
	private final String SIMBOLS = "Socos\r\n" + 
			"\r\n" + 
			"1 = jab\r\n" + 
			"2 = direto ou cruzado de direita\r\n" + 
			"3 = gancho de esquerda\r\n" + 
			"4 = gancho de direita (ou overhand direito)\r\n" + 
			"5 = uppercut esquerdo\r\n" + 
			"6 = uppercut direito\r\n" + 
			"B = corpo (exemplo: 1B = jab no corpo)\r\n" + 
			"\r\n" + 
			"Defesa\r\n" + 
			"\r\n" + 
			"[] = bloqueio\r\n" + 
			"() = defesa de giro de ombros\r\n" + 
			"{} = rolando sob (pendular)\r\n" + 
			"// = esquivar ou inclinar-se para longe\r\n" + 
			"<> = pivô para longe\r\n" + 
			"\r\n" + 
			"Movimento\r\n" + 
			"\r\n" + 
			"P / Pccw = quase todos os pivôs estão no pé dianteiro (\"P\" significa pivô no sentido horário no pé dianteiro, Pccw significa pivô no sentido anti-horário no pé dianteiro)\r\n" + 
			"\r\n" + 
			"bP = pivô no pé de trás. muito raramente usado (bpccw significa pivô no sentido anti-horário no seu pé traseiro)\r\n" + 
			"\r\n" + 
			"ls / rs = passo à esquerda e passo à direita. autoexplicativo\r\n" + 
			"\r\n" + 
			"bs = passo para trás (exemplo: 1bs significa que você deu um backstep ao lançar o jab, 1-bs significa que você deu um backstep DEPOIS de lançar o jab)\r\n" + 
			"\r\n" + 
			"f = falso (exemplo: 1f-3 significa fingir um soco na cabeça e, em seguida, lançar um gancho de esquerda logo em seguida)\r\n" + 
			"\r\n" + 
			"t = tap (exemplo: 1t-2 significa lançar um jab com um toque leve, seguido por um cruzado de direita)\r\n" + 
			"\r\n" + 
			"sl / sr = esquivar para a esquerda, esquivar para a direita\n "
			+ "(exemplo: 1-sl-3 significa jab, depois esquivar para a esquerda e depois gancho para a esquerda. 1sr-2 significa jab quando você esquiva\n"
			+ " para a direita e lança a mão direita depois)";
	
	public Gui() {
		setTitle("SHADOW BOXING");
		
		barraMenu = new BarraDeMenu();
		barraMenu.setVersao("Versão: 0.0 \n 24-01-2021\n\n");
		JMenu menuTreino = new JMenu("Sobre o treino");
		menuTreino.setFont(fonteVisivel);
		JMenuItem simbolos = new JMenuItem("Lista de símbolos");
		simbolos.addActionListener(new SimbolosListener());
		simbolos.setFont(fonteVisivel);
		menuTreino.add(simbolos);
		barraMenu.add(menuTreino);
		setJMenuBar(barraMenu);
		
		fonteLabel = new Font(getName(), Font.BOLD, 26);
		fonteVisivel = new Font(getName(), Font.BOLD, 17);
		fonteCategoria = new Font(getName(), Font.BOLD, 32);
		fonteCombo = new Font(getName(), Font.BOLD, 72);
		fonteLegenda = new Font(getName(), Font.BOLD, 18);
		
		painelDeFundo = new JPanel();		
		painelDeFundo.setLayout(new GridLayout(1, 2));
		painelDeSecao = new JPanel(new GridLayout(6, 2, 0, 70));
		painelDeSecao.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		painelDeExibicao = new JPanel();
		painelDeExibicao.setLayout(new GridLayout(3, 1));
		painelDeExibicao.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
		categPane = new JTextPane();
		categPane.setFont(fonteCategoria);
		categPane.setEditable(false);
		categPane.setBackground(new Color(40, 70, 90));
		categPane.setForeground(Color.ORANGE);
		comboLabel = new JLabel();
		comboLabel.setFont(fonteCombo);
		comboLabel.setHorizontalAlignment(SwingConstants.CENTER);
		legendaPane = new JTextPane();
		legendaPane.setFont(fonteLegenda);
		legendaPane.setEditable(false);
		legendaPane.setBackground(new Color(40, 70, 90));
		legendaPane.setForeground(Color.WHITE);
		painelDeBotao = new JPanel();
		
		categDeAbertura1 = new JComboBox<String>(categDeAbertura);
		categorias[0] = categDeAbertura1;
		categDeCombBasica1 = new JComboBox<String>(categDeCombBasica);
		categorias[1] = categDeCombBasica1;
		categDeCombAvancada1 = new JComboBox<String>(categDeCombAvancada);
		categorias[2] = categDeCombAvancada1;
		categDeCombOutros1 = new JComboBox<String>(categDeCombOutros);
		categorias[3] = categDeCombOutros1;
		categDeFinal1 = new JComboBox<String>(categDeFinal);
		categorias[4] = categDeFinal1;
		
		for (JComponent jc : categorias) {
			jc.setFont(fonteVisivel);
		}
		
		velocidades1 = new JComboBox<String>(velocidades);
		velocidades1.setFont(fonteVisivel);
		
		botaoIniciar = new JButton("Iniciar");
		botaoIniciar.addActionListener(new IniciarListener());
		botaoIniciar.setFont(fonteVisivel);
		botaoParar = new JButton("Parar");
		botaoParar.setFont(fonteVisivel);
		botaoParar.addActionListener(new PararListener());
		
		painelDeSecao.add(new Label("Aberturas", fonteLabel));
		painelDeSecao.add(categDeAbertura1);
		painelDeSecao.add(new Label("Combinaçoes comuns", fonteLabel));
		painelDeSecao.add(categDeCombBasica1);
		painelDeSecao.add(new Label("Combinações avançadas", fonteLabel));
		painelDeSecao.add(categDeCombAvancada1);
		painelDeSecao.add(new Label("Combinações especiais", fonteLabel));
		painelDeSecao.add(categDeCombOutros1);
		painelDeSecao.add(new Label("Finalizações", fonteLabel));
		painelDeSecao.add(categDeFinal1);
		painelDeSecao.add(new Label("Ritmo", fonteLabel));
		painelDeSecao.add(velocidades1);
		
		painelDeExibicao.add(categPane);
		painelDeExibicao.add(comboLabel);
		painelDeExibicao.add(legendaPane);
		
		painelDeBotao.add(botaoIniciar);
		painelDeBotao.add(botaoParar);
		
		painelDeFundo.add(painelDeSecao);
		painelDeFundo.add(painelDeExibicao);
		
		base = new Base();
		round = new Timer(2000, new ExecutarListener());		
		
		getContentPane().add(BorderLayout.CENTER, painelDeFundo);
		getContentPane().add(BorderLayout.SOUTH, painelDeBotao);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1350, 850);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public class PararListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			round.stop(); // para o round
			passo = -1; // reinicia o passo
			for (JComponent jc : categorias) { 
				@SuppressWarnings("unchecked")
				JComboBox<String> jcb = (JComboBox<String>) jc;
				jcb.setEnabled(true); // habilita seleções
				jcb.setSelectedIndex(0); // desfaz seleções anteriores
			}			
			
			velocidades1.setSelectedIndex(0);
			velocidades1.setEnabled(true);// reativa a seleção de velocidades
			base.limparCombos(); // limpar os combos da base
			// apaga o painel de exibição
			categPane.setText("");
			comboLabel.setText("");
			legendaPane.setText(""); 
		}

	}
	
	public class IniciarListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			round.setDelay(base.getTime(velocidades1.getSelectedIndex()));
			boolean podeIniciar = false; // para decidir se pode iniciar o round
			
			for (int i = 0; i < categorias.length; i++) {
				@SuppressWarnings("unchecked")
				JComboBox<String> categ = (JComboBox<String>) categorias[i];
				// olha todas as categorias. Se uma estiver escolhida, pode iniciar
				if (categ.getSelectedIndex() != 0) podeIniciar = true;
			}
			
			if (podeIniciar)  {
				// desabilita seleções durante o round
				for (JComponent jc : categorias) { 
					@SuppressWarnings("unchecked")
					JComboBox<String> jcb = (JComboBox<String>) jc;
					jcb.setEnabled(false);
				}
				// desabilita a mudança de velocidade durante o round
				velocidades1.setEnabled(false);
				round.start();			
			}			  
		}

	}
	
	public class ExecutarListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			passo++;
			if (passo < 5) {
				@SuppressWarnings("unchecked")
				JComboBox<String> categ = (JComboBox<String>) categorias[passo];
				base.definirCombo(passo, categ.getSelectedIndex());
				base.exibirCombo(passo, categPane, comboLabel, legendaPane);
			} else {
				passo = -1;
			}
		}
	}
	
	public class Label extends JLabel {
		
		private static final long serialVersionUID = 1L;

		public Label(String texto, Font fonte) {
			super.setText(texto);
			super.setFont(fonte);
		}
	}
	
	public class SimbolosListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, SIMBOLS, "Símbolos da legenda", JOptionPane.INFORMATION_MESSAGE);
		}		
	}
}
