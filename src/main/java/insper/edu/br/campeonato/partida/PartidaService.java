package insper.edu.br.campeonato.partida;
import insper.edu.br.campeonato.Cartao;
import insper.edu.br.campeonato.campeonato.Campeonato;
import insper.edu.br.campeonato.campeonato.CampeonatoRepository;
import insper.edu.br.campeonato.jogador.Jogador;
import insper.edu.br.campeonato.jogador.JogadorRepository;
import insper.edu.br.campeonato.time.Time;
import insper.edu.br.campeonato.time.TimeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.util.UUID;
@Service
public class PartidaService {
    private final PartidaRepository repository;
    private final TimeRepository times;
    private final CampeonatoRepository campeonatos;
    private final JogadorRepository jogadores;
    public PartidaService(PartidaRepository repository, TimeRepository times, CampeonatoRepository campeonatos, JogadorRepository jogadores) {
        this.repository = repository; this.times = times; this.campeonatos = campeonatos; this.jogadores = jogadores;
    }
    @Transactional
    public Partida criar(Partida partida) {
        if (partida.getTimeMandante() == null || partida.getTimeVisitante() == null || partida.getCampeonato() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Informe campeonato, timeMandante e timeVisitante com seus IDs");
        Time mandante = times.findById(partida.getTimeMandante().getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Mandante não encontrado"));
        Time visitante = times.findById(partida.getTimeVisitante().getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Visitante não encontrado"));
        Campeonato campeonato = campeonatos.findById(partida.getCampeonato().getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Campeonato não encontrado"));
        if (mandante.getId().equals(visitante.getId())) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Os times devem ser diferentes");
        if (campeonato.getTimes().stream().noneMatch(t -> t.getId().equals(mandante.getId())) ||
            campeonato.getTimes().stream().noneMatch(t -> t.getId().equals(visitante.getId())))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Os dois times devem participar do campeonato");
        partida.registrarTimes(mandante, visitante);
        partida.setCampeonato(campeonato);
        partida.setId(UUID.randomUUID().toString());
        return repository.save(partida);
    }
    public Partida atualizaPlacar(String id, int golMandante, int golVisitante) {
        Partida partida = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Partida não encontrada"));
        if (golMandante < 0 || golVisitante < 0) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Gols não podem ser negativos");
        partida.registrarGol(golMandante, golVisitante);
        return repository.save(partida);
    }
    @Transactional
    public Cartao registrarCartao(String idPartida, String idJogador, int minuto, String tipo) {
        Partida partida = repository.findById(idPartida).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Partida não encontrada"));
        Jogador jogador = jogadores.findById(idJogador).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Jogador não encontrado"));
        if (!"AMARELO".equals(tipo) && !"VERMELHO".equals(tipo)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tipo inválido");
        if (jogador.getTime() == null || (!jogador.getTime().getId().equals(partida.getTimeMandante().getId()) &&
            !jogador.getTime().getId().equals(partida.getTimeVisitante().getId())))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Jogador não participa desta partida");
        Cartao cartao = new Cartao(jogador, minuto, tipo, partida);
        partida.registrarCartao(cartao);
        repository.save(partida);
        return cartao;
    }
}
