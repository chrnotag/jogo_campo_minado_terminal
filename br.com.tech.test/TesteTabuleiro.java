import br.com.tech.excessoes.ExplosaoException;
import br.com.tech.models.Campo;
import br.com.tech.models.Tabuleiro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TesteTabuleiro {
    private int linhas;
    private int colunas;
    private int minas;
    private Tabuleiro tabuleiro;
    private String stringTabuleiro;

    @BeforeEach
    void gerarTabuleiro() {
        linhas = 3;
        colunas = 3;
        minas = 3;
        tabuleiro = new Tabuleiro(linhas, colunas, minas);
    }

    @BeforeEach
    void gerarStringTabuleiro() {
        stringTabuleiro =
                "  0 1 2 \n" +
                "0 ? ? ?\n" +
                "1 ? ? ?\n" +
                "2 ? ? ?\n";
    }

    @Test
    void testeAssociarVizinhos() {
        Campo campo1 = tabuleiro.tabuleiro.getFirst();
        Campo campo2 = tabuleiro.tabuleiro.get(1);
        Campo campo3 = tabuleiro.tabuleiro.get(2);

        assertTrue(campo1.getVizinhos().contains(campo2));
        assertTrue(campo2.getVizinhos().contains(campo3));
        assertTrue(campo2.getVizinhos().contains(campo1));
    }

    @Test
    void testarMinasTabuleiro(){
        assertEquals(minas, tabuleiro.tabuleiro.stream().filter(Campo::isMinado).count());
    }

    @Test
    void testeExibirTabuleiro() {
        System.out.println(tabuleiro.toString());
        assertEquals(stringTabuleiro, tabuleiro.toString());
    }


    @Test
    void testeAbrirCampoTabuleiroCoordenada() {
        Campo campo = tabuleiro.tabuleiro.stream().filter(c -> !c.isMinado()).findFirst().get();
        tabuleiro.abrirCampoTabuleiroCoordenada(campo.getLinha(), campo.getColuna());
        System.out.println(tabuleiro.toString());
        assertNotEquals(stringTabuleiro, tabuleiro.toString());
    }

    @Test
    void testeAbrirCampoTabuleiroCoordenadaMinado(){
        Campo campoComMina = tabuleiro.tabuleiro.stream().filter(c -> c.isMinado()).findFirst().get();
        assertThrows(ExplosaoException.class, () -> tabuleiro.abrirCampoTabuleiroCoordenada(campoComMina.getLinha(), campoComMina.getColuna()));
    }

    @Test
    void testeAlterarMarcacaoCampoCoordenada(){
        Campo campoComMina = tabuleiro.tabuleiro.stream().filter(c -> c.isMinado()).findFirst().get();
        tabuleiro.alterarMarcacaoCampoCoordenada(campoComMina.getLinha(), campoComMina.getColuna());
        System.out.println(tabuleiro.toString());
        assertNotEquals(stringTabuleiro, tabuleiro.toString());
    }

    @Test
    void testeCompletarJogo(){
        for (Campo c: tabuleiro.tabuleiro){
            if(c.isMinado()){
                c.alternarMarcacao();
            }else{
                c.abrirCampo();
            }
        }
        System.out.println(tabuleiro.toString());
        assertTrue(tabuleiro.objetivoAlcancado());
    }

    @Test
    void testeReiniciarJogo(){
        for (Campo c: tabuleiro.tabuleiro){
            if(c.isMinado()){
                c.alternarMarcacao();
            }else{
                c.abrirCampo();
            }
        }
        System.out.println(tabuleiro.toString());
        tabuleiro.reiniciar();
        System.out.println(tabuleiro.toString());
        assertEquals(stringTabuleiro, tabuleiro.toString());
    }
}
