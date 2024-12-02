import br.com.tech.models.Tabuleiro;
import br.com.tech.views.IniciarJogoView;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Tabuleiro tabuleiro = new Tabuleiro(6,6,3);
        IniciarJogoView iniciarJogoView = new IniciarJogoView(sc, tabuleiro);
        iniciarJogoView.menuInicial();
    }
}
