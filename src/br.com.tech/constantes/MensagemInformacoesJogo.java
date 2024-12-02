package br.com.tech.constantes;

public class MensagemInformacoesJogo {
    public static final String INFORMACOES_DESENVOLVEDOR_JOGO =
            """
                    Criador: Felippe Pinheiro de Almeida.
                    Linguagem utilizada: Java.
                    Arquitetura: MVVM (Model-View-ViewModel).
                    Objetivo: Este projeto foi desenvolvido para aprimorar meus conhecimentos 
                    em programação orientada a objetos e me desafiar a criar soluções para 
                    problemas cada vez mais complexos. A estrutura MVVM foi escolhida para 
                    separar as responsabilidades de modelo, visualização e lógica de negócios,
                    facilitando a manutenção e escalabilidade do código.
                    
                    Tecnologias utilizadas:
                    - Testes Unitários com JUnit.
                    - Streams do Java para processamento eficiente de dados.
                    - Arquitetura MVVM para organização do código e desacoplamento das camadas.
                    
                    Além disso, busquei aplicar boas práticas de clean code, reduzindo o boilerplate 
                    do código para torná-lo mais limpo, legível e fácil de manter. A redução do código 
                    repetitivo foi alcançada por meio de abstrações inteligentes e o uso eficiente de 
                    estruturas modernas do Java.
                    
                    Pressione ENTER para voltar.
                    
                    """;

    public static final String INFORMACOES_TABULEIRO(int numeroMinas){
        return numeroMinas + " minas no tabuleiro\n";
    }
    private MensagemInformacoesJogo() {
    }
}
