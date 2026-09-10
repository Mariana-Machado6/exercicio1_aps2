package insper.edu.br.campeonato;

import java.util.ArrayList;

public class Time {
    private String id;
    private String nome;
    private String cidade;
    private ArrayList<Jogador> jogadores = new ArrayList<>();

    public Time(String nome, String cidade) {
        this.nome = nome;
        this.cidade = cidade;
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

    public String getCidade() { return cidade;}

    public ArrayList<Jogador> getJogadores() {
        return jogadores;
    }

    public void adicionarJogador(Jogador jogador) {
        jogadores.add(jogador);
    }

    public void removerJogador(Jogador jogador) {
        jogadores.remove(jogador);
    }

    public void listarJogadores() {
        for (Jogador jogador : jogadores) {
            System.out.println(jogador.getNome());
        }
    }

    public int mediaIdadeJogadores() {
        int soma = 0;

        for (Jogador jogador : jogadores) {
            soma = soma + jogador.getIdade();
        }

        if (jogadores.isEmpty()) {
            return 0;
        }

        return soma / jogadores.size();
    }
}
