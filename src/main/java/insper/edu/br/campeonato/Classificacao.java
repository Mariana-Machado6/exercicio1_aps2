package insper.edu.br.campeonato;

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