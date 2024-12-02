package br.com.tech.constantes;

public class MensagensEmJogo {
    public static final String ESCOLHER_ABRIR_MARCAR_SAIR =
            """
                    1 - Abrir Campo.
                    2 - Marcar Campo.
                    3 - Sair do jogo.""";
    public static final String ESCOLHER_COORDENADA =
            """
                    Digite a coordenada do campo desejado
                    Exemplo: Para escolher o campo linha 0 e coluna 5 digite 0,5.""";
    public static final String CAMPO_INVALIDO = "Campo inválido, selecione outro\n";
    public static final String OPCAO_INVALIDA = "Opção inválida...\n";
    public static final String VENCEU_JOGO = "Parabéns, você venceu o jogo!\n";
    public static final String PERDEU_JOGO = "Que pena, voce perdeu.\n";
    public static final String REINICIAR_JOGO =
            """
                    Deseja começar um novo jogo?
                    1 - Sim
                    2 - Não""";
    public static final String ENCERRANDO_JOGO = "Encerrando o jogo... Obrigado por jogar!\n";

    private MensagensEmJogo() {
    }
}
