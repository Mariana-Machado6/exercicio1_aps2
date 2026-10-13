package insper.edu.br.campeonato.campeonato;

import insper.edu.br.campeonato.Classificacao;
import insper.edu.br.campeonato.partida.Partida;
import insper.edu.br.campeonato.time.Time;

import java.util.ArrayList;

@jakarta.persistence.Entity
public class Campeonato {

    @jakarta.persistence.Id
    private String id;
    private String nome;
    private Integer ano;
    @jakarta.persistence.ManyToMany
    private java.util.List<Time> times = new ArrayList<>();
    @com.fasterxml.jackson.annotation.JsonIgnore
    @jakarta.persistence.OneToMany(mappedBy = "campeonato")
    private java.util.List<Partida> partidas = new ArrayList<>();

    public Campeonato() {
    }

    public Campeonato(String nome, Integer ano) {
        this.nome = nome;
        this.ano = ano;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTimes(java.util.List<Time> times) { this.times = times; }
    public void setNome(String nome) { this.nome = nome; }
    public void setAno(Integer ano) { this.ano = ano; }

    public String getNome() {
        return nome;
    }

    public Integer getAno() {
        return ano;
    }

    public java.util.List<Time> getTimes() {
        return times;
    }

    public java.util.List<Partida> getPartidas() {
        return partidas;
    }

    public void adicionarTime(Time time) {
        times.add(time);
    }

    public void adicionarPartida(Partida partida) {
        partidas.add(partida);
    }

    public Time buscarTime(String nome) {
        for (Time time : times) {
            if (time.getNome().equals(nome)) {
                return time;
            }
        }

        return null;
    }

    @jakarta.persistence.Transient
    @com.fasterxml.jackson.annotation.JsonIgnore
    private int calcularPontos(Time time) {
        int pontos = 0;

        for (Partida partida : partidas) {

            if (partida.getTimeMandante().equals(time)) {

                if (partida.getGolMandante() > partida.getGolVisitante()) {
                    pontos = pontos + 3;

                } else if (partida.getGolMandante() == partida.getGolVisitante()) {
                    pontos = pontos + 1;
                }

            } else if (partida.getTimeVisitante().equals(time)) {

                if (partida.getGolVisitante() > partida.getGolMandante()) {
                    pontos = pontos + 3;

                } else if (partida.getGolVisitante() == partida.getGolMandante()) {
                    pontos = pontos + 1;
                }
            }
        }

        return pontos;
    }

    @jakarta.persistence.Transient
    @com.fasterxml.jackson.annotation.JsonIgnore
    public ArrayList<Classificacao> getClassificacao() {

        ArrayList<Classificacao> classificacao = new ArrayList<>();

        for (Time time : times) {

            Classificacao item =
                    new Classificacao(time, calcularPontos(time));

            classificacao.add(item);
        }

        classificacao.sort(
                (item1, item2) ->
                        item2.getPontos() - item1.getPontos()
        );

        return classificacao;
    }
}