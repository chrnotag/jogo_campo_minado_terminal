import br.com.tech.excessoes.ExplosaoException;
import br.com.tech.models.Campo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class TesteCampo {

    private Campo campo;
    private Campo vizinhoDiagonalCimaEsquerda;
    private Campo vizinhoBaixo;
    private Campo vizinhoCima;
    private Campo vizinhoDireita;
    private Campo vizinhoEsquerda;
    private Campo vizinhoDiagonalCimaDireita;
    private Campo vizinhoDiagonalBaixoEsquerda;
    private Campo vizinhoDiagonalBaixoDireita;

    //Inicia a váriavel a cada novo teste
    //util para manter os dados padronizados nos testes, sem alteração.
    @BeforeEach
    void iniciarCampo() {
        campo = new Campo(3, 3);
    }

    @BeforeEach
    void iniciarVizinhos() {
        vizinhoDiagonalCimaEsquerda = new Campo(2, 2);
        vizinhoDiagonalCimaDireita = new Campo(2, 4);
        vizinhoDiagonalBaixoEsquerda = new Campo(4, 2);
        vizinhoDiagonalBaixoDireita = new Campo(4, 4);
        vizinhoBaixo = new Campo(4, 3);
        vizinhoCima = new Campo(2, 3);
        vizinhoDireita = new Campo(3, 4);
        vizinhoEsquerda = new Campo(3, 2);
    }

    private Campo campo1;
    private Campo campo2;
    private Campo campo3;
    private Campo campo4;

    @BeforeEach
    void inciarVizinhosRecursivos() {
        // Criação de campos
        campo1 = new Campo(0, 0);
        campo2 = new Campo(0, 1);
        campo3 = new Campo(1, 0);
        campo4 = new Campo(1, 1);

        // Configuração de vizinhos
        campo1.adicionarVizinho(campo2);
        campo2.adicionarVizinho(campo3);
        campo3.adicionarVizinho(campo4);
        // Campo 1 não é vizinho direto do campo 4
    }

    void adicionarVizinhosAoCampo() {
        campo.adicionarVizinho(vizinhoCima);
        campo.adicionarVizinho(vizinhoBaixo);
        campo.adicionarVizinho(vizinhoEsquerda);
        campo.adicionarVizinho(vizinhoDireita);
        campo.adicionarVizinho(vizinhoDiagonalCimaEsquerda);
        campo.adicionarVizinho(vizinhoDiagonalCimaDireita);
        campo.adicionarVizinho(vizinhoDiagonalBaixoEsquerda);
        campo.adicionarVizinho(vizinhoDiagonalBaixoDireita);
    }

    @Test
    void testeAdicionarVizinhoCima() {
        boolean resultado = campo.adicionarVizinho(vizinhoDiagonalCimaEsquerda);
        assertTrue(resultado);
    }

    @Test
    void testeAdicionarVizinhoBaixo() {
        assertTrue(campo.adicionarVizinho(vizinhoBaixo));
    }

    @Test
    void testeAdicionarVizinhoEsquerda() {
        assertTrue(campo.adicionarVizinho(vizinhoEsquerda));
    }

    @Test
    void testeAdicionarVizinhoDireita() {
        assertTrue(campo.adicionarVizinho(vizinhoDireita));
    }

    @Test
    void testeAdicionarVizinhoDiagCimaEsquerda() {
        assertTrue(campo.adicionarVizinho(vizinhoDiagonalCimaEsquerda));
    }

    @Test
    void testeAdicionarVizinhoDiagCimaDireita() {
        assertTrue(campo.adicionarVizinho(vizinhoDiagonalCimaDireita));
    }

    @Test
    void testeAdicionarVizinhoDiagBaixoEsquerda() {
        assertTrue(campo.adicionarVizinho(vizinhoDiagonalBaixoEsquerda));
    }

    @Test
    void testeAdicionarVizinhoDiagBaixoDireita() {
        assertTrue(campo.adicionarVizinho(vizinhoDiagonalBaixoDireita));
    }

    @Test
    void testeNaoVizinho() {
        Campo naoVizinho = new Campo(1, 1);
        assertFalse(campo.adicionarVizinho(naoVizinho));
    }

    @Test
    void testGetVizinhosRecursivo() {
        // Inclui vizinhos
        Set<Campo> vizinhosRecursivos = campo1.getVizinhosRecursivo();

        assertTrue(vizinhosRecursivos.contains(campo3)); // Vizinho indireto via campo2
        assertTrue(vizinhosRecursivos.contains(campo4)); // Vizinho indireto via campo3
    }

    @Test
    void testeValorPadraoAlternarMarcacao() {
        assertFalse(campo.isMarcado());
    }

    @Test
    void testeAlternarMarcacaoTrue() {
        campo.alternarMarcacao();
        assertTrue(campo.isMarcado());
    }

    @Test
    void testeAlternarMarcacaoFalse() {
        campo.alternarMarcacao();
        campo.alternarMarcacao();
        assertFalse(campo.isMarcado());
    }

    @Test
    void testarMarcacaoCampoAberto() {
        campo.abrirCampo();
        campo.alternarMarcacao();
        assertFalse(campo.isMarcado());
    }

    @Test
    void abrirCampo() {
        campo.abrirCampo();
        assertTrue(campo.isAberto());
    }

    @Test
    void abrirCampoNaoMarcado() {
        campo.alternarMarcacao();
        assertFalse(campo.abrirCampo());
    }

    @Test
    void exessaoAbrirCampoMinado() {
        campo.minar();
        assertThrows(ExplosaoException.class, () -> campo.abrirCampo());
    }

    @Test
    void abrirVizinhancaSegura() {
        campo.adicionarVizinho(vizinhoDiagonalBaixoDireita);
        vizinhoDiagonalBaixoDireita.adicionarVizinho(vizinhoDireita);
        vizinhoDireita.adicionarVizinho(vizinhoDiagonalCimaDireita);
        vizinhoDiagonalCimaDireita.adicionarVizinho(vizinhoCima);
        campo.abrirCampo();
        campo.getVizinhosRecursivo().forEach(System.out::println);

        assertTrue(campo.isAberto()
                && vizinhoDiagonalBaixoDireita.isAberto()
                && vizinhoDireita.isAberto()
                && vizinhoDiagonalCimaDireita.isAberto()
                && vizinhoCima.isAberto());
    }

    @Test
    void abrirVizinhancaSeguraVizinhoAberto() {
        adicionarVizinhosAoCampo();
        vizinhoEsquerda.abrirCampo();
        campo.abrirCampo();
        campo.getVizinhos().forEach(System.out::println);

        assertTrue(campo.isAberto()
                && vizinhoCima.isAberto()
                && vizinhoBaixo.isAberto()
                && vizinhoEsquerda.isAberto()
                && vizinhoDireita.isAberto()
                && vizinhoDiagonalCimaEsquerda.isAberto()
                && vizinhoDiagonalCimaDireita.isAberto()
                && vizinhoDiagonalBaixoEsquerda.isAberto()
                && vizinhoDiagonalBaixoDireita.isAberto());
    }

    @Test
    void abrirVizinhancaSeguraVizinhoMarcado() {
        campo.adicionarVizinho(vizinhoDiagonalBaixoDireita);
        vizinhoDiagonalBaixoDireita.adicionarVizinho(vizinhoDireita);
        vizinhoDireita.adicionarVizinho(vizinhoDiagonalCimaDireita);
        vizinhoDiagonalCimaDireita.adicionarVizinho(vizinhoCima);
        vizinhoCima.alternarMarcacao();
        campo.abrirCampo();
        campo.getVizinhosRecursivo().forEach(System.out::println);

        assertFalse(campo.isAberto()
                && vizinhoDiagonalBaixoDireita.isAberto()
                && vizinhoDireita.isAberto()
                && vizinhoDiagonalCimaDireita.isAberto()
                && vizinhoCima.isAberto());
    }

    @Test
    void abrirVizinhancaNaoSegura() {
        campo.adicionarVizinho(vizinhoDiagonalBaixoDireita);
        vizinhoDiagonalBaixoDireita.adicionarVizinho(vizinhoDireita);
        vizinhoDireita.adicionarVizinho(vizinhoDiagonalCimaDireita);
        vizinhoDiagonalCimaDireita.adicionarVizinho(vizinhoCima);
        vizinhoCima.minar();
        campo.abrirCampo();
        campo.getVizinhosRecursivo().forEach(System.out::println);

        assertFalse(campo.isAberto()
                && vizinhoDiagonalBaixoDireita.isAberto()
                && vizinhoDireita.isAberto()
                && vizinhoDiagonalCimaDireita.isAberto()
                && vizinhoCima.isAberto());
    }

    @Test
    void testeObjetivoAlcancadoAberto() {
        campo.abrirCampo();
        assertTrue(campo.objetivoAlcancado());
    }

    @Test
    void testeObjetivoAlcancadoMarcado() {
        campo.minar();
        campo.alternarMarcacao();
        assertTrue(campo.objetivoAlcancado());
    }

    @Test
    void testeObjetivoNaoAlcancadoMinado() {
        campo.minar();
        assertFalse(campo.objetivoAlcancado());
    }

    @Test
    void testeObjetivoNaoAlcancadoNaoMarcado() {
        campo.minar();
        assertFalse(campo.objetivoAlcancado());
    }

    @Test
    void testeReinicarCampoAbertoMinado() {
        campo.abrirCampo();
        campo.minar();
        campo.reiniciarCampo();
        assertFalse(campo.isAberto() && campo.isMinado());
    }

    @Test
    void testeReiniciarCampoFechadoMarcado() {
        campo.alternarMarcacao();
        campo.reiniciarCampo();
        assertFalse(campo.isMarcado());
    }

    @Test
    void testeReinciarCampoAbertoSeguro() {
        campo.abrirCampo();
        campo.reiniciarCampo();
        assertFalse(campo.isAberto());
    }

    @Test
    void testeStringCampoFechado() {
        assertEquals("?", campo.toString());
    }

    @Test
    void testeStringCampoAberto() {
        campo.abrirCampo();
        assertEquals(" ", campo.toString());
    }

    @Test
    void testeStringCampoMinado() {
        campo.minar();
        try {
            campo.abrirCampo();
        } catch (ExplosaoException e) {
        }
        assertEquals("*", campo.toString());
    }

    @Test
    void testeStringCampoMarcado() {
        campo.minar();
        campo.alternarMarcacao();
        assertEquals("X", campo.toString());
    }

    @Test
    void testeObterCorPorNumero() {
        // Verificar se as cores corretas são retornadas para números válidos
        assertEquals("\u001B[34m", Campo.obterCorPorNumero("1")); // Azul
        assertEquals("\u001B[32m", Campo.obterCorPorNumero("2")); // Verde
        assertEquals("\u001B[31m", Campo.obterCorPorNumero("3")); // Vermelho
        assertEquals("\u001B[35m", Campo.obterCorPorNumero("4")); // Roxo
        assertEquals("\u001B[33m", Campo.obterCorPorNumero("5")); // Amarelo
        assertEquals("\u001B[36m", Campo.obterCorPorNumero("6")); // Ciano
        assertEquals("\u001B[30m", Campo.obterCorPorNumero("7")); // Preto
        assertEquals("\u001B[37m", Campo.obterCorPorNumero("8")); // Branco

        // Verificar o comportamento para entradas inválidas
        assertEquals("\u001B[0m", Campo.obterCorPorNumero("9")); // Reset padrão
        assertEquals("\u001B[0m", Campo.obterCorPorNumero("0")); // Reset padrão
        assertEquals("\u001B[0m", Campo.obterCorPorNumero(""));  // Reset padrão
        assertEquals("\u001B[0m", Campo.obterCorPorNumero(null)); // Reset padrão
    }
}