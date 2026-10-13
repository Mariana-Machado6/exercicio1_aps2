package insper.edu.br.campeonato.time;

import insper.edu.br.campeonato.jogador.Jogador;

import java.util.ArrayList;

@jakarta.persistence.Entity
@jakarta.persistence.Table(name = "times")
public class Time {
    @jakarta.persistence.Id
    private String id;
    private String nome;
    private String cidade;
    @jakarta.persistence.OneToMany(mappedBy = "time", fetch = jakarta.persistence.FetchType.EAGER)
    private java.util.List<Jogador> jogadores = new ArrayList<>();

    public Time() {}

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

    public void setNome(String nome) { this.nome = nome; }
    public void setCidade(String cidade) { this.cidade = cidade; }

    public String getNome() {
        return nome;
    }

    public String getCidade() { return cidade;}

    public java.util.List<Jogador> getJogadores() {
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
