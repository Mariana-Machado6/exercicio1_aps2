package insper.edu.br.campeonato;

public class Jogador {

    private String id;
    private String nome;
    private int numero;
    private String posicao;
    private Time time;
    private int idade;

    // CONSTRUTOR
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
