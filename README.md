# 🕹️ **Jogo de Campo Minado - Console**

Este é um **projeto de console** em Java, baseado no clássico **Campo Minado**. O objetivo do jogo é abrir os campos de um tabuleiro sem pisar nas bombas, utilizando um menu interativo que permite personalizar o tabuleiro, visualizar informações sobre o jogo e começar uma nova partida.

## 🎮 Funcionalidades

- **Menu Interativo**:
  - Opções para iniciar o jogo, personalizar o tabuleiro, acessar informações sobre o jogo ou sair.
    
    - ![image](https://github.com/user-attachments/assets/199ac2d6-3e29-4ced-9757-0e144be25f09)
- **Personalização do Tabuleiro**:
  - Defina o número de linhas, colunas e bombas para um novo jogo.
    
    - ![image](https://github.com/user-attachments/assets/68097ce5-0640-47b8-b87e-6d88d2f8369e)

- **Modo Jogo**:
  - O jogador pode abrir campos ou marcar bombas no tabuleiro.
    
    - ![image](https://github.com/user-attachments/assets/2e2aa34f-9838-4404-a8c8-0678d0774a6a)

- **Informações do Jogo**:
  - Exibe dados sobre o desenvolvedor, regras e informações do jogo.
- **Mecânica de Jogo**:
  - Se o jogador abrir uma bomba, o jogo é reiniciado.
  - O objetivo é abrir todos os campos sem detonar as bombas.

## ⚙️ **Tecnologias Utilizadas**

- **Java**: Linguagem de programação principal.
- **JDK 23**: Ambiente de desenvolvimento Java.
- **Scanner**: Para entrada do usuário no terminal.
- **JUnit**: Para testes unitários.
- **Mockito**: Para testes com mock de dados.
- **SPR**: Principios de SOLID.
- **Design MVVM**: Separação entre lógica de visualização (View) e lógica de negócios (ViewModel).

## 🛠️ **Estrutura de Diretórios**

A arquitetura do projeto segue o padrão **MVVM** (Model-View-ViewModel), que separa a lógica de apresentação da lógica de negócios, facilitando a manutenção e extensibilidade do código.

```
src/
│
├── br/com/tech/
│   ├── constantes/ # Constantes utilizadas no jogo
│   │   ├── MensagemInformacoesJogo.java
│   │   ├── MensagensEmJogo.java
│   │   └── MensagensPersonalizarTabuleiro.java
│   │   
│   ├── models/ # Modelo do jogo (Tabuleiro, Campo) 
│   │   ├── Campo.java
│   │   └── Tabuleiro.java
│   │ 
│   ├── views/ # Camada de visualização
│   │   └── IniciarJogoView.java
│   │ 
│   ├── viewmodel/ # Camada de lógica de apresentação
│   │    └── IniciarJogoViewModel.java
└── README.md # Documentação do projeto
```


## 🖥️ **Como Rodar o Projeto**

1. **Clonar o repositório**:
   ```bash
   git clone https://github.com/seu-usuario/campo-minado.git
   ```

## Customizar o Tabuleiro:
Ao iniciar o jogo, você poderá personalizar o número de linhas, colunas e bombas no tabuleiro.

## 🧩 Lógica do Jogo
O jogo possui um tabuleiro que contém campos. Cada campo pode ser aberto ou marcado com uma bomba. Se o jogador abrir um campo com uma bomba, o jogo termina e é reiniciado. O objetivo é abrir todos os campos sem detonar as bombas.

### Classes Principais:
**IniciarJogoView**: Responsável por exibir o menu inicial, capturar as opções do usuário e interagir com a camada ViewModel.
**IniciarJogoViewModel**: Contém a lógica de controle do jogo, como a inicialização, personalização do tabuleiro e controle da partida.
**Tabuleiro**: Representa o tabuleiro do jogo, com a quantidade de linhas, colunas e bombas. É responsável por manter o estado do jogo.
**Campo**: Representa cada célula no tabuleiro, que pode ser aberto ou marcado.

## 📝 Comandos no Jogo
1: Abrir um campo.
2: Marcar ou desmarcar um campo como uma bomba.
3: Sair do jogo.
*Durante o jogo, o jogador verá as mensagens de informação do tabuleiro e status do jogo.*

## 🚀 Possíveis Melhorias
1: **Interface Gráfica (GUI)**: Implementar uma interface gráfica com JavaFX ou Swing.
2: **Dificuldades**: Implementar níveis de dificuldade com diferentes tamanhos de tabuleiro e quantidades de bombas.
3: **Timer**: Adicionar um cronômetro para medir o tempo de jogo.

## 📑 Licença
**Este projeto está sob a licença MIT.**
