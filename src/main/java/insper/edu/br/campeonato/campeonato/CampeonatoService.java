package insper.edu.br.campeonato.campeonato;
import insper.edu.br.campeonato.Classificacao;
import insper.edu.br.campeonato.partida.Partida;
import insper.edu.br.campeonato.partida.PartidaRepository;
import insper.edu.br.campeonato.time.Time;
import insper.edu.br.campeonato.time.TimeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.util.*;
@Service
public class CampeonatoService {
    private final CampeonatoRepository repository;
    private final TimeRepository times;
    private final PartidaRepository partidas;
    public CampeonatoService(CampeonatoRepository repository, TimeRepository times, PartidaRepository partidas) {
        this.repository = repository; this.times = times; this.partidas = partidas;
    }
    @Transactional
    public Campeonato criar(Campeonato campeonato) {
        campeonato.setId(UUID.randomUUID().toString());
        List<Time> participantes = new ArrayList<>();
        if (campeonato.getTimes() != null) {
            for (Time time : campeonato.getTimes()) participantes.add(times.findById(time.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Time não encontrado")));
        }
        campeonato.setTimes(participantes);
        return repository.save(campeonato);
    }
    public Campeonato buscar(String id) { return repository.findById(id).orElse(null); }
    @Transactional
    public Campeonato adicionarTime(String id, String idTime) {
        Campeonato campeonato = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Campeonato não encontrado"));
        Time time = times.findById(idTime).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Time não encontrado"));
        if (campeonato.getTimes().stream().noneMatch(t -> t.getId().equals(idTime))) campeonato.adicionarTime(time);
        return repository.save(campeonato);
    }
    @Transactional(readOnly = true)
    public ArrayList<Classificacao> classificacao(String id) {
        Campeonato campeonato = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Campeonato não encontrado"));
        List<Partida> jogos = partidas.findByCampeonatoId(id);
        ArrayList<Classificacao> resultado = new ArrayList<>();
        for (Time time : campeonato.getTimes()) {
            int pontos = 0;
            for (Partida partida : jogos) {
                if (partida.getTimeMandante().getId().equals(time.getId())) {
                    if (partida.getGolMandante() > partida.getGolVisitante()) pontos += 3;
                    else if (partida.getGolMandante() == partida.getGolVisitante()) pontos++;
                } else if (partida.getTimeVisitante().getId().equals(time.getId())) {
                    if (partida.getGolVisitante() > partida.getGolMandante()) pontos += 3;
                    else if (partida.getGolVisitante() == partida.getGolMandante()) pontos++;
                }
            }
            resultado.add(new Classificacao(time, pontos));
        }
        resultado.sort(Comparator.comparingInt(Classificacao::getPontos).reversed());
        return resultado;
    }
}
