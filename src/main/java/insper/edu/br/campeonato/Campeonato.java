package insper.edu.br.campeonato;

import java.util.ArrayList;

public class Campeonato {

    private String id;
    private String nome;
    private Integer ano;
    private ArrayList<Time> times = new ArrayList<>();
    private ArrayList<Partida> partidas = new ArrayList<>();

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

    public String getNome() {
        return nome;
    }

    public Integer getAno() {
        return ano;
    }

    public ArrayList<Time> getTimes() {
        return times;
    }

    public ArrayList<Partida> getPartidas() {
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