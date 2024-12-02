package br.com.tech.models;

import br.com.tech.excessoes.ExplosaoException;

import java.util.*;

public class Campo {
    private int linha;
    private int coluna;

    private boolean marcado;
    private boolean aberto;
    private boolean minado;

    private List<Campo> vizinhos = new ArrayList<>();

    public List<Campo> getVizinhos() {
        return vizinhos;
    }

    public Set<Campo> getVizinhosRecursivo() {
        Set<Campo> todosVizinhos = new HashSet<>();
        obterVizinhosRecursivo(this, todosVizinhos);
        return todosVizinhos;
    }

    private void obterVizinhosRecursivo(Campo campo, Set<Campo> visitados) {
        visitados.add(campo);

        for (Campo vizinho : campo.getVizinhos()) {
            if (!visitados.contains(vizinho)) {
                obterVizinhosRecursivo(vizinho, visitados);
            }
        }
    }

    public Campo(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
    }

    //Lógica para implementar novo vizinho a lista de possiveis vizinhos
    public boolean adicionarVizinho(Campo vizinho) {
        boolean linhaDiferente = linha != vizinho.linha;
        boolean colunaDiferente = coluna != vizinho.coluna;
        boolean diagonal = linhaDiferente && colunaDiferente;

        //Calculo da diferença entre linha e coluna. Deve retornar 1 para vertical ou 2 para diagonal.
        int deltaLinha = Math.abs(linha - vizinho.linha);
        int deltaColuna = Math.abs(coluna - vizinho.coluna);
        int deltaGeral = deltaLinha + deltaColuna;

        //Caso deltaGeral = 1 adiciona vizinho das verticais
        if (deltaGeral == 1 && !diagonal) {
            vizinhos.add(vizinho);
            return true;
        } else if (deltaGeral == 2 && diagonal) { //Caso igual a 2 adiciona vizinho das diagonais
            vizinhos.add(vizinho);
            return true;
        } else { //Caso de menor ou maior que 1 ou 2 não adiciona vizinho.
            return false;
        }
    }

    public void alternarMarcacao() {
        if (!aberto) {
            marcado = !marcado;
        }
    }

    public boolean abrirCampo() {
        if (!aberto && !marcado) {
            aberto = true;
            if (minado) { //finalizar jogo caso esteja com bomba
                throw new ExplosaoException("Fim de jogo");
            }
            if (vizinhaSegura()) { //verifica se a vizinhança é segura e vai seguir abrindo vizinhos
                vizinhos.forEach(Campo::abrirCampo);
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean vizinhaSegura() { //Verifica se os vizinhos estao minados ou não.
        return vizinhos.stream().noneMatch(v -> v.minado);
    }

    public boolean isMarcado(){
        return marcado;
    }

    public boolean isAberto(){
        return aberto;
    }

    public void minar(){
        minado = !minado;
    }

    public boolean isMinado(){
        return minado;
    }

    public int getColuna() {
        return coluna;
    }

    public int getLinha() {
        return linha;
    }

    public boolean objetivoAlcancado(){
        boolean revelado = !minado && aberto;
        boolean protegido = minado && marcado;
        return revelado || protegido;
    }

    public long minasNaVizinhanca(){
        return vizinhos.stream().filter(v -> v.minado).count();
    }

    public void reiniciarCampo(){
        minado = false;
        aberto = false;
        marcado = false;
    }

    private static final Map<String, String> coresPorNumero = new HashMap<>();

    static {
        coresPorNumero.put("1", "\u001B[34m"); // Azul
        coresPorNumero.put("2", "\u001B[32m"); // Verde
        coresPorNumero.put("3", "\u001B[31m"); // Vermelho
        coresPorNumero.put("4", "\u001B[35m"); // Roxo
        coresPorNumero.put("5", "\u001B[33m"); // Amarelo
        coresPorNumero.put("6", "\u001B[36m"); // Ciano
        coresPorNumero.put("7", "\u001B[30m"); // Preto
        coresPorNumero.put("8", "\u001B[37m"); // Branco
    }

    public static String obterCorPorNumero(String numero) {
        return coresPorNumero.getOrDefault(numero, "\u001B[0m"); // Reset como padrão
    }

    @Override
    public String toString() {
        if (marcado) {
            return "X"; // Campo marcado
        } else if (aberto && minado) {
            return "*"; // Campo minado aberto
        } else if (aberto && minasNaVizinhanca() > 0) {
            String numero = Long.toString(minasNaVizinhanca());
            String cor = obterCorPorNumero(numero);
            return cor + numero + "\u001B[0m"; // Aplica cor e reseta
        } else if (aberto) {
            return " "; // Campo aberto sem minas ao redor
        } else {
            return "?"; // Campo fechado
        }
    }
}