package br.com.tech.views;

import br.com.tech.constantes.MensagemInformacoesJogo;
import br.com.tech.constantes.MensagensEmJogo;
import br.com.tech.constantes.MensagensMenuInicial;
import br.com.tech.constantes.MensagensPersonalizarTabuleiro;
import br.com.tech.interfaces.IniciarJogoCallBack;
import br.com.tech.models.Campo;
import br.com.tech.models.Tabuleiro;
import br.com.tech.viewmodel.IniciarJogoViewModel;
import java.util.Scanner;

public class IniciarJogoView implements IniciarJogoCallBack {

    private Integer quantidadeLinhas = null;
    private Integer quantidadeColunas = null;
    private Integer quantidadeBombas = null;

    private Scanner sc;
    private String opcaoSelecionada = null;
    private Tabuleiro tabuleiro;
    private Campo campoAlvo = null;

    private IniciarJogoViewModel viewModel;

    public IniciarJogoView(Scanner sc, Tabuleiro tabuleiro) {
        this.sc = sc;
        this.tabuleiro = tabuleiro;
        this.viewModel = new IniciarJogoViewModel(tabuleiro, sc);
        this.viewModel.iniciarCallBack(this);
    }

    public void menuInicial() {
        System.out.println(MensagensMenuInicial.MENU_INICIAR);
        opcaoSelecionada = sc.next();

        switch (opcaoSelecionada) {
            case "1":
                viewModel.iniciarJogo();
                break;
            case "2":
                personalizarTabuleiro();
                break;
            case "3":
                informacoesJogo();
                break;
            case "4":
                sair();
                break;
            default:
                System.out.println(MensagensEmJogo.CAMPO_INVALIDO);
                menuInicial();
                break;
        }
    }

    private void personalizarTabuleiro() {
        System.out.println(MensagensPersonalizarTabuleiro.DEFINIR_LINHAS);
        quantidadeLinhas = sc.nextInt();

        System.out.println(MensagensPersonalizarTabuleiro.DEFINIR_COLUNAS);
        quantidadeColunas = sc.nextInt();

        System.out.println(MensagensPersonalizarTabuleiro.DEFINIR_BOMDAS);
        quantidadeBombas = sc.nextInt();

        System.out.println(MensagensPersonalizarTabuleiro.TABULEIRO_PRONTO);
        opcaoSelecionada = sc.next();

        viewModel.personalizarTabuleiro(quantidadeLinhas, quantidadeColunas, quantidadeBombas, opcaoSelecionada);
    }

    private void informacoesJogo() {
        sc.nextLine();
        System.out.println(MensagemInformacoesJogo.INFORMACOES_DESENVOLVEDOR_JOGO);
        sc.nextLine();
        menuInicial();
    }

    private void sair() {
        System.out.println(MensagensEmJogo.ENCERRANDO_JOGO);
        System.exit(0);
    }

    @Override
    public void exibirMenuInicial() {
        menuInicial();
    }
}