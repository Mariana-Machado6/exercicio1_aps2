package insper.edu.br.campeonato;

import insper.edu.br.campeonato.time.Time;

public class Classificacao {

    private Time time;
    private int pontos;

    public Classificacao(Time time, int pontos) {
        this.time = time;
        this.pontos = pontos;
    }

    public Time getTime() {
        return time;
    }

    public int getPontos() {
        return pontos;
    }
}