package insper.edu.br.campeonato.partida;

import insper.edu.br.campeonato.Cartao;
import insper.edu.br.campeonato.campeonato.Campeonato;
import insper.edu.br.campeonato.time.Time;

import java.util.ArrayList;

@jakarta.persistence.Entity
public class Partida {
    @jakarta.persistence.Id
    private String id;
    private String data;
    @jakarta.persistence.ManyToOne
    @com.fasterxml.jackson.annotation.JsonIgnoreProperties("jogadores")
    private Time timeMandante;
    @jakarta.persistence.ManyToOne
    @com.fasterxml.jackson.annotation.JsonIgnoreProperties("jogadores")
    private Time timeVisitante;
    private int golMandante;
    private int golVisitante;
    @jakarta.persistence.OneToMany(mappedBy = "partida", cascade = jakarta.persistence.CascadeType.ALL)
    private java.util.List<Cartao> cartoes = new ArrayList<>();
    @jakarta.persistence.ManyToOne
    @com.fasterxml.jackson.annotation.JsonIgnoreProperties({"times", "partidas"})
    private Campeonato campeonato;

    public Partida() {}
    public void setData(String data) { this.data = data; }
    public void setTimeMandante(Time time) { this.timeMandante = time; }
    public void setTimeVisitante(Time time) { this.timeVisitante = time; }
    public Campeonato getCampeonato() { return campeonato; }
    public void setCampeonato(Campeonato campeonato) { this.campeonato = campeonato; }
    public java.util.List<Cartao> getCartoes() { return cartoes; }
    //GETTERS

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public Time getTimeMandante() {
        return timeMandante;
    }

    public Time getTimeVisitante() {
        return timeVisitante;
    }

    public int getGolMandante() {
        return golMandante;
    }

    public int getGolVisitante() {
        return golVisitante;
    }

    //MÉTODOS

    public void registrarTimes(Time timeMandante, Time timeVisitante) {
        this.timeMandante = timeMandante;
        this.timeVisitante = timeVisitante;
    }

    public void registrarGol(int golMandante, int golVisitante) {
        this.golMandante = golMandante;
        this.golVisitante = golVisitante;
    }

    public void registrarCartao(Cartao cartao) {
        this.cartoes.add(cartao);
    }

    public Time buscarVencedor() {
        if (golMandante > golVisitante) {
            return timeMandante;
        } else if (golMandante < golVisitante) {
            return timeVisitante;
        } else {
            return null;
        }
    }

    public void exibirPlacar() {
        System.out.println(
                getTimeMandante().getNome() + " " + golMandante + " x " + golVisitante + " " + getTimeVisitante().getNome()
        );
    }

}
