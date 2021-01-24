package treinamento_boxe;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class BarraDeMenu extends JMenuBar {
	
	private static final long serialVersionUID = 1L;
	private String autor = "Autor: Thiago de Oliveira Alves\ntowo497@gmail.com";
	private String versao = "Versão: 1.0 \n 13-04-2020\n\n";
	private JMenu menuSobre;
	private JMenuItem autoriaItem;
	private JMenuItem versaoItem;
	private Font fonteVisivel = new Font(getName(), Font.BOLD, 17);
	
	public BarraDeMenu() {		
		menuSobre = new JMenu("Informações");
		autoriaItem = new JMenuItem("Autor");
		autoriaItem.addActionListener(new AutorListener());
		versaoItem = new JMenuItem("Sobre o aplicativo");
		versaoItem.addActionListener(new VersaoListener());
		setFonte(fonteVisivel);
		menuSobre.add(autoriaItem);
		menuSobre.add(versaoItem);		
		add(menuSobre);
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getVersao() {
		return versao;
	}

	public void setVersao(String versao) {
		this.versao = versao;
	}

	private void setFonte(Font font) {
		menuSobre.setFont(font);
		autoriaItem.setFont(font);
		versaoItem.setFont(font);	
	}
	private class AutorListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {			
			JOptionPane.showMessageDialog(null, autor, "Sobre mim", JOptionPane.INFORMATION_MESSAGE);

		}

	}
	
	private class VersaoListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, versao, "Sobre este", JOptionPane.INFORMATION_MESSAGE);

		}

	}	
}
