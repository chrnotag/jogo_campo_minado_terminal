package br.com.tech.viewmodel;

import br.com.tech.constantes.MensagemInformacoesJogo;
import br.com.tech.constantes.MensagensEmJogo;
import br.com.tech.constantes.MensagensPersonalizarTabuleiro;
import br.com.tech.excessoes.ExplosaoException;
import br.com.tech.interfaces.IniciarJogoCallBack;
import br.com.tech.models.Campo;
import br.com.tech.models.Tabuleiro;
import br.com.tech.views.IniciarJogoView;

import java.util.List;
import java.util.Scanner;

public class IniciarJogoViewModel {
    private Tabuleiro tabuleiro;
    private Campo campoAlvo = null;
    private String opcaoSelecionada = "";
    private Scanner sc;
    private IniciarJogoCallBack callBack;

    public IniciarJogoViewModel(Tabuleiro tabuleiro, Scanner sc){
        this.tabuleiro = tabuleiro;
        this.sc = sc;
    }

    public void iniciarCallBack(IniciarJogoCallBack callBack){
        this.callBack = callBack;
    }

    public void iniciarJogo() {

        while (!tabuleiro.objetivoAlcancado()) {
            System.out.println(MensagemInformacoesJogo.INFORMACOES_TABULEIRO(tabuleiro.getQuantidadeMinas()));
            System.out.println(tabuleiro);
            System.out.println(MensagensEmJogo.ESCOLHER_ABRIR_MARCAR_SAIR);
            opcaoSelecionada = sc.next();

            switch (opcaoSelecionada) {
                case "1":
                    campoAlvo();
                    if (campoAlvo != null) {
                        try {
                            campoAlvo.abrirCampo();
                        } catch (ExplosaoException e) {
                            System.out.println(tabuleiro);
                            System.out.println(MensagensEmJogo.PERDEU_JOGO);
                            tabuleiro.reiniciar();
                            callBack.exibirMenuInicial();
                            sair();
                            break;
                        }
                    } else {
                        System.out.println(MensagensEmJogo.CAMPO_INVALIDO);
                    }
                    break;
                case "2":
                    campoAlvo();
                    campoAlvo.alternarMarcacao();
                    break;
                case "3":
                    sair();
                    break;
                default:
                    System.out.println(MensagensEmJogo.OPCAO_INVALIDA);
                    break;
            }
        }
        jogoVencido();
    }

    public void personalizarTabuleiro(int quantidadeLinhas, int quantidadeColunas, int quantidadeBombas, String opcaoSelecionada){
        boolean cicloFechado = false;
        while (!cicloFechado){
            switch (opcaoSelecionada) {
                case "1":
                    tabuleiro = montarTabuleiro(quantidadeLinhas, quantidadeColunas, quantidadeBombas);
                    iniciarJogo();
                    cicloFechado = true;
                    break;
                case "2":
                    sair();
                    cicloFechado = true;
                    break;
                default:
                    System.out.println(MensagensEmJogo.CAMPO_INVALIDO);
                    System.out.println(MensagensPersonalizarTabuleiro.TABULEIRO_PRONTO);
                    break;
            }
        }
    }

    private Tabuleiro montarTabuleiro(int linhas, int colunas, int minas) {
        return new Tabuleiro(linhas, colunas, minas);
    }

    private void campoAlvo() {
        System.out.println(MensagensEmJogo.ESCOLHER_COORDENADA);
        String coordenada = sc.next();
        int linha = 0;
        int coluna = 0;
        try {
            List<Integer> coordenadaSeparada = tabuleiro.separaCoordenadas(coordenada);
            linha = coordenadaSeparada.get(0);
            coluna = coordenadaSeparada.get(1);
        } catch (Exception _) {
        }
        campoAlvo = tabuleiro.resgatarCampoCoordenada(linha, coluna);
    }

    private void jogoVencido() {
        tabuleiro.reiniciar();
        System.out.println(MensagensEmJogo.VENCEU_JOGO);
        System.out.println(MensagensEmJogo.REINICIAR_JOGO);
        opcaoSelecionada = sc.next();
        switch (opcaoSelecionada) {
            case "1":
                tabuleiro.reiniciar();
                callBack.exibirMenuInicial();
                break;
            case "2":
                sair();
                break;
        }
    }

    public void sair(){
        System.exit(0);
    }

}
