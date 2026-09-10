package insper.edu.br.campeonato;

import java.util.ArrayList;

public class Partida {
    private String id;
    private String data;
    private Time timeMandante;
    private Time timeVisitante;
    private int golMandante;
    private int golVisitante;
    private ArrayList<Cartao> cartoes = new ArrayList<>();
    private Campeonato campeonato;

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
