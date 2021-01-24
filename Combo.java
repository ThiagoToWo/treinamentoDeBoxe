package treinamento_boxe;

public class Combo {
	private String codigo;
	private String[] golpes;
	private String categoria;
	private String legenda = "";
	private final String[] notacao = {"1", "2", "3", "4", "5", "6", "B", 
									  "[]", "()", "{}", "//", "<>",
									  "P", "Pccw", "bP", "bpccw", "ls", "rs", "bs", "f", "t", "sl", "sr"};
	private final String[] significado = {"jab", "direto ou cruzado de direita", "gancho de esquerda", 
									"gancho de direita ou overhand", "upper de esquerda", "upper de direita",
									"no corpo (exemplo: 1b = jab no corpo)",
									"bloqueio", "defesa de giro de ombros", "pendulo (rolar por baixo)",
									"esquivar ou inclinar-se para longe", "piv� para longe",
									"piv� sobre o p� da frente em sentido hor�rio",
									"piv� sobre o p� da frente em sentido anti-hor�rio",
									"piv� sobre o p� de tr�s em sentido hor�rio", "piv� sobre o p� de tr�s em sentido anti-hor�rio",
									"passo para esquerda", "passo para direita", "passo para tr�s", "fake (finta)",
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
		golpes = codigo.split("-");
	}
	
	public String getLegenda() {
		return legenda;
	}
	
	public void setLegenda() {
		for (String g : golpes) {
			for (int i = 0; i < notacao.length; i++) {
				if (g.contains(notacao[i])) {
					legenda += "\n" + notacao[i] // v� se cont�m
							+ " = " + significado[i]; // coloca a nota��o + legenda
				} else if (i == 7 && g.contains("[")) {
					legenda += "\n" + notacao[i] // v� se cont�m
							+ " = " + significado[i]; // coloca a nota��o + legenda
				} else if (i == 8 && g.contains("(")) {
					legenda += "\n" + notacao[i] // v� se cont�m
							+ " = " + significado[i]; // coloca a nota��o + legenda
				} else if (i == 9 && g.contains("{")) {
					legenda += "\n" + notacao[i] // v� se cont�m
							+ " = " + significado[i]; // coloca a nota��o + legenda
				} else if (i == 10 && g.contains("/")) {
					legenda += "\n" + notacao[i] // v� se cont�m
							+ " = " + significado[i]; // coloca a nota��o + legenda
				} else if (i == 11 && g.contains("<")) {
					legenda += "\n" + notacao[i] // v� se cont�m
							+ " = " + significado[i]; // coloca a nota��o + legenda
				}
			}
		}
	}
	
	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {		
		String combo = getCategoria() + "\n\n" + getCodigo() + "\n" + getLegenda();
		return combo;	
	}
}
	
