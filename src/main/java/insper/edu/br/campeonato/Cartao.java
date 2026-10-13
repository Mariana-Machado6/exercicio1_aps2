package insper.edu.br.campeonato;

import insper.edu.br.campeonato.jogador.Jogador;
import insper.edu.br.campeonato.partida.Partida;

@jakarta.persistence.Entity
public class Cartao {
    @jakarta.persistence.Id
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    @jakarta.persistence.ManyToOne
    @com.fasterxml.jackson.annotation.JsonIgnoreProperties("time")
    private Jogador jogador;
    private int minuto;
    private String tipo;
    @jakarta.persistence.ManyToOne
    @com.fasterxml.jackson.annotation.JsonIgnore
    private Partida partida;

    // CONSTRUTOR

    public Cartao() {}
    public Long getId() { return id; }

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