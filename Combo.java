package treinamento_boxe;

import java.util.StringTokenizer;

public class Combo {
	private String codigo;
	private String[] golpes;
	private String categoria;
	private String legenda = "";
	private String[] notacao = {"1", "2", "3", "4", "5", "6", "b", 
								"\\[\\]", "\\(\\)", "\\{\\}", "\\/\\/", "\\<\\>",
								"p", "pccw", "bp", "ls", "rs", "bs", "f", "t", "sl", "sr"};
	private String[] significado = {"jab", "direto ou cruzado de direita", "gancho de esquerda", 
									"gancho de direita ou overhand", "upper de esquerda", "upper de direita",
									"no corpo (exemplo: 1b = jab no corpo)",
									"bloqueio", "defesa de giro de ombros", "pendulo (rolar por baixo)",
									"esquivar ou inclinar-se para longe", "pivô para longe",
									"pivô sobre o pé da frente em sentido horário",
									"pivô sobre o pé da frente em sentido anti-horário",
									"pivô sobre o pé de trás em sentido horário ",
									"passo para esquerda", "passo para direita", "passo para trás", "fake (finta)",
									"tap (toque leve)", "esquivar para esquerda", "esquivar para direita"};
	
	public Combo(String categoria, String codigo) {
		this.categoria = categoria;
		this.codigo = codigo;
		setGolpes(codigo);
		setLegenda();
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getGolpes(int i) {
		return golpes[i];
	}
	
	public void setGolpes(String codigo) {
		StringTokenizer stk = new StringTokenizer(codigo, "-");
		golpes = new String[stk.countTokens()];
		while (stk.hasMoreTokens()) {
			for (int i = 0; i < golpes.length; i++) golpes[i] = stk.nextToken();			
		}
	}
	
	public String getLegenda() {
		return legenda;
	}
	
	public void setLegenda() {		
		for (String g : golpes) {
			for (int i = 0; i < notacao.length; i++) {
				if (g.matches(notacao[i]))	legenda += "\n" + significado[1] ;			
			}
		}
	}
	
	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String toString() {
		String combo = getCategoria() + "\n\n" + getCodigo() + getLegenda();
		return combo;
	}
}
