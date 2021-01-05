package treinamento_boxe;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import treinamento_boxe.Gui.IniciarListener;
import treinamento_boxe.Gui.PararListener;

public class Gui extends JFrame {	

	private JPanel painelDeFundo;
	private JPanel painelDeSecao;
	private JPanel painelDeExibicao;
	private JPanel painelDeBotao;
	private String[] categDeAbertura = {"Nenhuma", "Aberturas Básicas", "Aberturas contra oponentes defensivos", "Todas"};
	private String[] categDeCombBasica = {"Nenhuma", "Combinações básicas", "Combinações In-Fighting", "Todas"};
	private String[] categDeCombAvancada = {"Nenhuma", "Combinações evasivas", "Combinações avançadas", "Todas"};
	private String[] categDeCombOutros = {"Nenhuma", "Combos para ortodoxos vs southpaw", "Combos para southpaw vs ortodoxos", "Todas"};
	private String[] categDeFinal = {"Nenhuma", "Finalizações poderosas", "Finalizações evasivas", "Todas"};
	private String[] velocidades = {"Primeiro contato (5 a 24 segundos)", "Iniciante (5 a 16 segundos)", "Praticante (5 a 8 segundos)", "Avançado (1 a 3 segundos)"};
	private JComboBox<String> categDeAbertura1;
	private JComboBox<String> categDeAberturaDef1;
	private JComboBox<String> categDeCombBasica1;
	private JComboBox<String> categDeCombAvancada1;
	private JComboBox<String> categDeCombOutros1;
	private JComboBox<String> categDeFinal1;
	private JComboBox<String> velocidades1;
	private JButton botaoIniciar;
	private JButton botaoParar;
	private Base base;
	private boolean round;
	
	public Gui() {
		setTitle("SHADOW BOXING");
		
		painelDeFundo = new JPanel();
		painelDeFundo.setLayout(new GridLayout(1, 2));
		painelDeSecao = new JPanel(new GridLayout(6, 2));
		painelDeExibicao = new JPanel();
		painelDeBotao = new JPanel();
		
		categDeAbertura1 = new JComboBox<String>(categDeAbertura);
		categDeCombBasica1 = new JComboBox<String>(categDeCombBasica);
		categDeCombAvancada1 = new JComboBox<String>(categDeCombAvancada);
		categDeCombOutros1 = new JComboBox<String>(categDeCombOutros);
		categDeFinal1 = new JComboBox<String>(categDeFinal);
		velocidades1 = new JComboBox<String>(velocidades);
		
		botaoIniciar = new JButton("Iniciar");
		botaoIniciar.addActionListener(new IniciarListener());
		botaoParar = new JButton("Parar");
		botaoParar.addActionListener(new PararListener());
		
		painelDeSecao.add(new JLabel("Aberturas"));
		painelDeSecao.add(categDeAbertura1);
		painelDeSecao.add(new JLabel("Combinaçoes comuns"));
		painelDeSecao.add(categDeCombBasica1);
		painelDeSecao.add(new JLabel("Combinações avançadas"));
		painelDeSecao.add(categDeCombAvancada1);
		painelDeSecao.add(new JLabel("Combinações especiais"));
		painelDeSecao.add(categDeCombOutros1);
		painelDeSecao.add(new JLabel("Finalizações"));
		painelDeSecao.add(categDeFinal1);
		painelDeSecao.add(new JLabel("Ritmo"));
		painelDeSecao.add(velocidades1);
		
		painelDeBotao.add(botaoIniciar);
		painelDeBotao.add(botaoParar);
		
		painelDeFundo.add(painelDeSecao);
		painelDeFundo.add(painelDeExibicao);
		
		base = new Base();
		
		getContentPane().add(BorderLayout.CENTER, painelDeFundo);
		getContentPane().add(BorderLayout.SOUTH, painelDeBotao);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 500);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public class PararListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			round = false;
		}

	}

	public class IniciarListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			round = true;
			base.definirCombo(0, categDeAbertura1.getSelectedIndex());
			base.definirCombo(1, categDeCombBasica1.getSelectedIndex());
			base.definirCombo(2, categDeCombAvancada1.getSelectedIndex());
			base.definirCombo(3, categDeCombOutros1.getSelectedIndex());
			base.definirCombo(4, categDeFinal1.getSelectedIndex());
			
			while (round) {
				base.exibirCombo(velocidades1.getSelectedIndex(), painelDeExibicao);
			}
		}

	}
	
}
