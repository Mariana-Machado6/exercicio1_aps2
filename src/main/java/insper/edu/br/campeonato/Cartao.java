package insper.edu.br.campeonato;

public class Cartao {
    private Jogador jogador;
    private int minuto;
    private String tipo;
    private Partida partida;

    // CONSTRUTOR

    public Cartao(Jogador jogador, int minuto, String tipo, Partida partida) {
        this.jogador = jogador;
        this.minuto = minuto;
        this.tipo = tipo;
        this.partida = partida;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public String getTipo() {
        return tipo;
    }

    public int getMinuto() {
        return minuto;
    }

    public Partida getPartida() {
        return partida;
    }
}