package insper.edu.br.campeonato;

import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

@Service
public class TimeService {

    private HashMap<String, Time> times = new HashMap<>();

    @Autowired
    private JogadorService jogadorService;

    public Time criar(@NonNull Time time) {
        time.setId(UUID.randomUUID().toString());
        times.put(time.getId(), time);
        return time;
    }

    public ArrayList<Time> listar() {
        return new ArrayList<>(times.values());
    }

    public Time buscar(String id) {
        return times.get(id);
    }

    public Time adicionarJogador(String idTime, String idJogador) {
        Time time = times.get(idTime);
        Jogador jogador = jogadorService.buscar(idJogador);

        time.adicionarJogador(jogador);

        return time;
    }

}
