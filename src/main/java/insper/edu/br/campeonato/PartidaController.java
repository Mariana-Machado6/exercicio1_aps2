package insper.edu.br.campeonato;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class PartidaController {

    @Autowired
    private PartidaService partidaService;

    @PostMapping("/partidas")
    @ResponseStatus(HttpStatus.CREATED)
    public Partida criarPartida(Partida partida) {

        return partidaService.criar(partida);
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
