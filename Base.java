package treinamento_boxe;

import java.awt.Toolkit;
import java.security.SecureRandom;
import java.util.Arrays;

import javax.swing.JTextArea;

public class Base {
	// códigos dos combos por categorias
	private final String[] aberturasBasicas = {"1", "1-2", "1-6b", "2-1"};
	private final String[] aberturasParaDefensivos = {"1-3-2", "1b-2", "1-2b", "1-2b-3", "4-1b", "4b-6-3-2",
												"bs-2", "1f-3", "6-2", "2-2", "1t-2", "1t-2t-3"};
	private final String[] combinacoesBasicas = {"1-1", "1-1-2", "1-2", "1-2-1", "1-2-1-2", "1-2-3-2", "1-6-3-2"};
	private final String[] combinacoesInFighting = {"6-5-2-1p", "6-3", "6-3p", "4b-3b-2-1-2", "rs-5",
											  "1-2-3b-p-4b-4-1", "1-4b-3b-6-1"};
	private final String[] combinacoesEvasivas = {"1-2-/1/-2", "1-2-/1/-2-3-2", "1-2-{3}-2", "1-2-{3}-/2/-3-2",
											"1-2-{3}-3-2-3", "1-2-3-{2}-3-2", "1-/1/-2-1-1", "1-/1/-2-3-2"};
	private final String[] combinacoesAvancadas = {"1-bs-1", "1-bs-1-2"};
	private final String[] combinacoesOrtVsSp = {"2-2-1bs", "1-2b", "2-rs-2-1-2b", "2-1-2b", "1-1-2b-3"};
	private final String[] combinacoesSpVsOrt = {"1-2", "2-2", "2-3-2", "2-3-2b", "1-1-2b-3", "6-1-2", "6b-6-2-1", "2-1-1-6-1"};
	private final String[] finalizacoesPoderosas = {"1p-2"};
	private final String[] finalizacoesEvasivas = {"1p", "1b-bs", "4bpccw", "3p", "2-p-2"};
	// Combos por categoria
	private Combo[] aberturasBasicas1 = new Combo[4];
	private Combo[] aberturasParaDefensivos1 = new Combo[12];
	private Combo[] combinacoesBasicas1 = new Combo[7];
	private Combo[] combinacoesInFighting1 = new Combo[7];
	private Combo[] combinacoesEvasivas1 = new Combo[8];
	private Combo[] combinacoesAvancadas1 = new Combo[2];
	private Combo[] combinacoesOrtVsSp1 = new Combo[5];
	private Combo[] combinacoesSpVsOrt1 = new Combo[8];
	private Combo[] finalizacoesPoderosas1 = new Combo[1];
	private Combo[] finalizacoesEvasivas1 = new Combo[5];
	// tabela de combos
	private final Combo[][][] combosTable = { { {}, aberturasBasicas1, aberturasParaDefensivos1 },
										{ {}, combinacoesBasicas1, combinacoesInFighting1 },
										{ {}, combinacoesEvasivas1, combinacoesAvancadas1 },
										{ {}, combinacoesOrtVsSp1, combinacoesSpVsOrt1 },
										{ {}, finalizacoesPoderosas1, finalizacoesEvasivas1 } };
	private Combo[] combos = new Combo[5]; // combos divididos por seção
	private SecureRandom rand = new SecureRandom();
	
	public Base() {
		for (int i = 0; i < aberturasBasicas1.length; i++) {
			aberturasBasicas1[i] = new Combo("Aberturas Básicas", aberturasBasicas[i]);
		}
		
		for (int i = 0; i < aberturasParaDefensivos1.length; i++) {
			aberturasParaDefensivos1[i] = new Combo("Aberturas contra oponentes defensivos", aberturasParaDefensivos[i]);
		}
		
		for (int i = 0; i < combinacoesBasicas1.length; i++) {
			combinacoesBasicas1[i] = new Combo("Combinações básicas", combinacoesBasicas[i]);
		}
		
		for (int i = 0; i < combinacoesInFighting1.length; i++) {
			combinacoesInFighting1[i] = new Combo("Combinações In-Fighting", combinacoesInFighting[i]);
		}
		
		for (int i = 0; i < combinacoesEvasivas1.length; i++) {
			combinacoesEvasivas1[i] = new Combo("Combinações evasivas", combinacoesEvasivas[i]);
		}
		
		for (int i = 0; i < combinacoesAvancadas1.length; i++) {
			combinacoesAvancadas1[i] = new Combo("Combinações avançadas", combinacoesAvancadas[i]);
		}

		for (int i = 0; i < combinacoesOrtVsSp1.length; i++) {
			combinacoesOrtVsSp1[i] = new Combo("Combos para ortodoxos vs southpaw", combinacoesOrtVsSp[i]);
		}

		for (int i = 0; i < combinacoesSpVsOrt1.length; i++) {
			combinacoesSpVsOrt1[i] = new Combo("Combos para southpaw vs ortodoxos", combinacoesSpVsOrt[i]);
		}

		for (int i = 0; i < finalizacoesPoderosas1.length; i++) {
			finalizacoesPoderosas1[i] = new Combo("Finalizações poderosas", finalizacoesPoderosas[i]);
		}

		for (int i = 0; i < finalizacoesEvasivas1.length; i++) {
			finalizacoesEvasivas1[i] = new Combo("Finalizações evasivas", finalizacoesEvasivas[i]);
		}		
	}

	public void definirCombo(int secao, int selecao) {
		if (selecao == 0) return;
		if (selecao == 1 || selecao == 2) {
			combos[secao] = combosTable[secao][selecao][rand.nextInt(combosTable[secao][selecao].length)];
		} else {
			int rnd = rand.nextInt(combosTable[secao][1].length + combosTable[secao][2].length);
			if (rnd < combosTable[secao][1].length) {
				combos[secao] = combosTable[secao][1][rnd];
			} else {
				combos[secao] = combosTable[secao][2][rnd - combosTable[secao][1].length];
			}
		}
	}

	public void exibirCombo(int i, JTextArea painel) {
		if (combos[i] != null) { // pula os elementos vazios
			Toolkit.getDefaultToolkit().beep();
			painel.setText(combos[i].toString());
		};
	}

	public void limparCombos() {
		Arrays.fill(combos, null);
	}
	
	public int getTime(int velocidade) {
		if (velocidade == 0) {
			return 2000;			
		} else if (velocidade == 1) {
			return 1500;			
		} else if (velocidade == 2) {
			return 1000;			
		} else {
			return 500;			
		}
	}
}
