package br.com.tech.constantes;

public class MensagensPersonalizarTabuleiro {
    public static final String DEFINIR_LINHAS = "Digite o numero de linhas desejada no tabuleiro\n";
    public static final String DEFINIR_COLUNAS = "Digite o numero de colunas desejadas no tabuleiro\n";
    public static final String DEFINIR_BOMDAS = "Digite o numero de bombas totais no campo\n";
    public static final String TABULEIRO_PRONTO =
            """
                    Tabuleiro configurado.
                    Deseja iniciar o jogo?
                    1 - Sim
                    2 - Cancelar""";
    private MensagensPersonalizarTabuleiro() {
    }
}
