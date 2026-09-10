package insper.edu.br.campeonato;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

@Service
public class JogadorService {

    private HashMap<String, Jogador> jogadores = new HashMap<>();

    public Jogador criar(Jogador jogador) {
        jogador.setId(UUID.randomUUID().toString());
        jogadores.put(jogador.getId(), jogador);
        return jogador;
    }

    public Jogador buscar(String id) {
        return jogadores.get(id);
    }

    public ArrayList<Jogador> listar() {
        return new ArrayList<>(jogadores.values());
    }
}
