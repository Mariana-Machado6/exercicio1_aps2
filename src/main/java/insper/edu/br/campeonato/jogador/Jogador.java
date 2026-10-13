package insper.edu.br.campeonato.jogador;

import insper.edu.br.campeonato.time.Time;

@jakarta.persistence.Entity
public class Jogador {

    @jakarta.persistence.Id
    private String id;
    private String nome;
    private int numero;
    private String posicao;
    @jakarta.persistence.ManyToOne
    @com.fasterxml.jackson.annotation.JsonIgnoreProperties("jogadores")
    private Time time;
    private int idade;

    // CONSTRUTOR
    public Jogador() {}

    public Jogador(String nome, int numero, String posicao, Time time, int idade) {
        this.nome = nome;
        this.numero = numero;
        this.posicao = posicao;
        this.time = time;
        this.idade = idade;
    }

    // GETTERS
    public String getId() { return id; }

    public void setId(String id) {
        this.id = id;
    }

    public void setNome(String nome) { this.nome = nome; }
    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }
    public String getPosicao() { return posicao; }
    public void setPosicao(String posicao) { this.posicao = posicao; }
    public void setIdade(int idade) { this.idade = idade; }
    public void setTime(Time time) { this.time = time; }
    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public Time getTime() {
        return time;
    }
}
