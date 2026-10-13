package insper.edu.br.campeonato.partida;

import insper.edu.br.campeonato.PlacarRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class PartidaController {

    @Autowired
    private PartidaService partidaService;

    @PostMapping("/partidas")
    @ResponseStatus(HttpStatus.CREATED)
    public Partida criarPartida(@RequestBody Partida partida) {

        return partidaService.criar(partida);
    }

    @PostMapping("/partidas/{id}/cartoes")
    @ResponseStatus(HttpStatus.CREATED)
    public insper.edu.br.campeonato.Cartao registrarCartao(@PathVariable String id,
            @RequestBody CartaoRequest request) {
        return partidaService.registrarCartao(id, request.idJogador(), request.minuto(), request.tipo());
    }

    @PatchMapping("/partidas/{id}/placar")
    public Partida atualizarPlacar(@PathVariable String id, @RequestBody PlacarRequest placar) {

        return partidaService.atualizaPlacar(
                id,
                placar.getGolMandante(),
                placar.getGolVisitante()
        );
    }
}
