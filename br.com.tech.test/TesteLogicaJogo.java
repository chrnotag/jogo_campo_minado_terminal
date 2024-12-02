import br.com.tech.excessoes.ExplosaoException;
import br.com.tech.models.Campo;
import br.com.tech.models.Tabuleiro;
import br.com.tech.views.IniciarJogoView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TesteLogicaJogo {
    Tabuleiro tabuleiroMock;
    Campo campoMock;

    @BeforeEach
    void iniciarNovoJogo() {
        tabuleiroMock = mock(Tabuleiro.class);
        campoMock = mock(Campo.class);
    }

    @Test
    void testeJogarEVencer() {
        //Escolhas do mock: Iniciar novo jogo > abrir campo > sair do jogo.
        String opcoesIniciarNovoJogo = "1\n2\n";
        Scanner sc = new Scanner(opcoesIniciarNovoJogo);

        IniciarJogoView jogo = new IniciarJogoView(sc, tabuleiroMock);

        when(tabuleiroMock.resgatarCampoCoordenada(0, 0)).thenReturn(campoMock);
        when(tabuleiroMock.objetivoAlcancado()).thenReturn(true);

        jogo.menuInicial();

        verify(tabuleiroMock).resgatarCampoCoordenada(0, 0);
        verify(tabuleiroMock).objetivoAlcancado();
    }

    @Test
    void testeJogarEPerder(){
        //Escolhas do mock: Iniciar novo jogo > abrir campo > digitar coordenadas > sair do jogo.
        String opcoesIniciarNovoJogo = "1\n1\n0,0\n4\n";
        Scanner sc = new Scanner(opcoesIniciarNovoJogo);

        IniciarJogoView jogo = new IniciarJogoView(sc, tabuleiroMock);

        when(campoMock.isMinado()).thenReturn(true);
        when(tabuleiroMock.resgatarCampoCoordenada(0, 0)).thenReturn(campoMock);
        when(campoMock.abrirCampo()).thenThrow(ExplosaoException.class);

        jogo.menuInicial();

        verify(tabuleiroMock).resgatarCampoCoordenada(0, 0);
        verify(tabuleiroMock).objetivoAlcancado();

        assertThrows(ExplosaoException.class, campoMock::abrirCampo);
    }

    @Test
    void testePersonalizarTabuleiro(){
        //Escolhas do mock: Personalizar Tabuleiro > definir linha > definir coluna > definir bombas
        // > jogar > sair do jogo.
        String opcoesIniciarNovoJogo = "2\n10\n10\n10\n1\n3\n";
        Scanner sc = new Scanner(opcoesIniciarNovoJogo);

        IniciarJogoView jogo = new IniciarJogoView(sc, tabuleiroMock);

        jogo.menuInicial();

        verify(jogo).menuInicial();
        verify(tabuleiroMock.getQuantidadeMinas()).compareTo(10);
    }

    @Test
    void testeExibirInformacoesDoJogo(){
        //Escolhas do mock: Exibir informacoes > voltar > sair do jogo.
        String opcoesIniciarNovoJogo = "3\n \n4\n";
        Scanner sc = new Scanner(opcoesIniciarNovoJogo);

        IniciarJogoView jogo = new IniciarJogoView(sc, tabuleiroMock);

        jogo.menuInicial();
    }
}
