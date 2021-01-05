package treinamento_boxe;

import java.awt.Toolkit;
import java.security.SecureRandom;
import java.util.Scanner;

public class ShadowBoxe {

	private String[][][] combosTable= {{{}, {"1", "1-6b", "2-1"}, {"1-3-2", "1b-2", "bs-2"}}, 
						          {{}, {"1-1-2", "1-2-1", "1-6-3-2"}, {"6-5-2-1p", "rs-5"}}, 
						          {{}, {"1p", "1b-bs", "2-p-2"}, {"1p-2"}}};
	private static String[] combos = new String[3];
	private static int velocidade;
	private SecureRandom rand = new SecureRandom();
	
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
	
	public void exibirCombo(String combo) {		
		contagem(velocidade);
		if (combo != null) {
			Toolkit.getDefaultToolkit().beep();	
			System.out.println(combo + "\n");
		}		 
	}
	
	private void contagem(int velocidade2) {
		if (velocidade == 0) {
			try {
				Thread.sleep(2 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else if (velocidade == 1) {
			try {
				Thread.sleep(15 * 100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else if (velocidade == 2) {
			try {
				Thread.sleep(1 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			try {
				Thread.sleep(5 * 100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/*public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		ShadowBoxe sb = new ShadowBoxe();
		
		// entra os valores
		System.out.println("Escolha a abertura: Nenhuma = 0, Contra defensivos = 1, Básicas = 2 ou Todas = 3");
		int selecao1 = in.nextInt();
		System.out.println("Escolha os tipos de combinação: Nenhuma = 0, Comuns = 1, In-Fighting = 2 ou Todas = 3");
		int selecao2 = in.nextInt();
		System.out.println("Escolha a finalização: Nenhuma = 0, Evasivas = 1, Poderosa = 2 ou Todas = 3");
		int selecao3 = in.nextInt();
		System.out.println("Escolha a velocidade: 2 segundos = 0, 1,5 segundo = 1, 1 segundo = 2 ou 0,5 segundo = 3");
		ShadowBoxe.velocidade = in.nextInt();
		
		String opcao = null;
		// loop principal
		do {
		// definir os combos a aparecer
		sb.definirCombo(0, selecao1);
		sb.definirCombo(1, selecao2);
		sb.definirCombo(2, selecao3);
		
		// exibir os combos
		sb.exibirCombo(ShadowBoxe.combos[0]);
		sb.exibirCombo(ShadowBoxe.combos[1]);
		sb.exibirCombo(ShadowBoxe.combos[2]);
		
		// escolher continuar
		System.out.println("Deseja continuar a mesma seleção? s = sim, n = não)");
		opcao = in.next();
		} while(opcao.equals("s"));
	}*/

}
