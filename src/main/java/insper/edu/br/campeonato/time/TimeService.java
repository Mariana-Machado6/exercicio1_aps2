package insper.edu.br.campeonato.time;
import insper.edu.br.campeonato.jogador.Jogador;
import insper.edu.br.campeonato.jogador.JogadorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.util.*;
@Service
public class TimeService {
    private final TimeRepository repository;
    private final JogadorRepository jogadores;
    public TimeService(TimeRepository repository, JogadorRepository jogadores) { this.repository = repository; this.jogadores = jogadores; }
    public Time criar(Time time) { time.setId(UUID.randomUUID().toString()); return repository.save(time); }
    public ArrayList<Time> listar() { return new ArrayList<>(repository.findAll()); }
    public Time buscar(String id) { return repository.findById(id).orElse(null); }
    @Transactional
    public Time adicionarJogador(String idTime, String idJogador) {
        Time time = repository.findById(idTime).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Time não encontrado"));
        Jogador jogador = jogadores.findById(idJogador).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Jogador não encontrado"));
        jogador.setTime(time);
        jogadores.save(jogador);
        if (time.getJogadores().stream().noneMatch(j -> j.getId().equals(idJogador))) time.adicionarJogador(jogador);
        return time;
    }
}
