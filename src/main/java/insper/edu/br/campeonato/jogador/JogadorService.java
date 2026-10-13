package insper.edu.br.campeonato.jogador;
import insper.edu.br.campeonato.time.TimeRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.util.*;
@Service
public class JogadorService {
    private final JogadorRepository repository;
    private final TimeRepository times;
    public JogadorService(JogadorRepository repository, TimeRepository times) { this.repository = repository; this.times = times; }
    public Jogador criar(Jogador jogador) {
        jogador.setId(UUID.randomUUID().toString());
        if (jogador.getTime() != null) jogador.setTime(times.findById(jogador.getTime().getId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Time não encontrado")));
        return repository.save(jogador);
    }
    public Jogador buscar(String id) { return repository.findById(id).orElse(null); }
    public ArrayList<Jogador> listar() { return new ArrayList<>(repository.findAll()); }
}
