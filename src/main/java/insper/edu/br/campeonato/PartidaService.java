package insper.edu.br.campeonato;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.UUID;

@Service
public class PartidaService {

    private HashMap<String, Partida> partidas = new HashMap<>();

    @Autowired
    private JogadorService jogadorService;

    public Partida criar(Partida partida) {

        if (partida.getTimeMandante().getId()
                .equals(partida.getTimeVisitante().getId())) {
            return null;
        }

        partida.setId(UUID.randomUUID().toString());
        partidas.put(partida.getId(), partida);

        return partida;
    }

    public Partida atualizaPlacar(String id, int golMandante, int golVisitante) {
        Partida partida = partidas.get(id);

        if (partida == null) {
            return null;
        }

        partida.registrarGol(golMandante, golVisitante);

        return partida;
    }

    public Cartao registrarCartao(
            String idPartida,
            String idJogador,
            int minuto,
            String tipo) {

        Partida partida = partidas.get(idPartida);
        Jogador jogador = jogadorService.buscar(idJogador);

        if (partida == null || jogador == null) {
            return null;
        }

        if (!tipo.equals("AMARELO") && !tipo.equals("VERMELHO")) {
            return null;
        }

        String idTimeJogador = jogador.getTime().getId();
        String idMandante = partida.getTimeMandante().getId();
        String idVisitante = partida.getTimeVisitante().getId();

        if (!idTimeJogador.equals(idMandante)
                && !idTimeJogador.equals(idVisitante)) {
            return null;
        }

        Cartao cartao = new Cartao(jogador, minuto, tipo, partida);
        partida.registrarCartao(cartao);

        return cartao;
    }
}