package br.com.tech.models;

import java.util.*;

public class Tabuleiro {
    public List<Campo> tabuleiro = new ArrayList<>();
    private int linhas;
    private int colunas;
    private int quantidadeMinas;

    public Tabuleiro(int linhas, int colunas, int quantidadeMinas) {
        this.linhas = linhas;
        this.colunas = colunas;
        this.quantidadeMinas = quantidadeMinas;
        gerarTabuleiro();
        associarVizinhos();
        sortearMinas();
    }

    private void gerarTabuleiro() {
        for (int l = 0; l < linhas; l++) {
            for (int c = 0; c < colunas; c++) {
                tabuleiro.add(new Campo(l, c));
            }
        }
    }

    public int getQuantidadeMinas(){
        return quantidadeMinas;
    }

    private void associarVizinhos() {
        for (Campo c : tabuleiro) {
            for (Campo cc : tabuleiro) {
                c.adicionarVizinho(cc);
            }
        }
    }

    private void sortearMinas() {
        Set<Integer> posicaoMinada = new HashSet<>();
        while (posicaoMinada.size() < quantidadeMinas) {
            int random = (int) (Math.random() * tabuleiro.size());
            if (!posicaoMinada.contains(random)) {
                tabuleiro.get(random).minar();
                posicaoMinada.add(random);
            }
        }
    }

    public Campo resgatarCampoCoordenada(int linha, int coluna){
        Optional<Campo> possivelCampo = tabuleiro.stream().filter(c -> c.getLinha() == linha && c.getColuna() == coluna).findFirst();
        return possivelCampo.orElse(null);
    }

    public Campo abrirCampoTabuleiroCoordenada(int linha, int coluna){
        Campo campo = resgatarCampoCoordenada(linha, coluna);
        campo.abrirCampo();
        return campo;
    }

    public Campo alterarMarcacaoCampoCoordenada(int linha, int coluna){
        Campo campo = resgatarCampoCoordenada(linha, coluna);
        campo.alternarMarcacao();
        return campo;
    }

    public boolean objetivoAlcancado() {
        return tabuleiro.stream().allMatch(Campo::objetivoAlcancado);
    }

    public void reiniciar() {
        tabuleiro.forEach(Campo::reiniciarCampo);
        sortearMinas();
    }

    public List<Integer> separaCoordenadas(String coordenada){
        int posicaoVirguda = coordenada.indexOf(",");
        int coordenadaLinha = Integer.parseInt(coordenada.substring(0, posicaoVirguda));
        int coordenadaColuna = Integer.parseInt(coordenada.substring(posicaoVirguda + 1));

        List<Integer> coordenadas = new ArrayList<>();
        coordenadas.add(coordenadaLinha);
        coordenadas.add(coordenadaColuna);
        return coordenadas;
    }

    @Override
    public String toString() {
        StringBuilder montarTabuleiro = new StringBuilder();
        montarTabuleiro.append("  ");
        for (int i = 0; i < colunas; i++) {
            montarTabuleiro.append(i)
                    .append(" ");
        }
        montarTabuleiro.append("\n");
        for (Campo c : tabuleiro) {
            if (c.getColuna() == 0) {
                montarTabuleiro.append(c.getLinha())
                        .append(" ")
                        .append(c);
            } else if (c.getColuna() == colunas - 1) {
                montarTabuleiro.append(" ").append(c).append("\n");
            } else {
                montarTabuleiro.append(" ").append(c);
            }
        }

        return montarTabuleiro.toString();
    }
}
