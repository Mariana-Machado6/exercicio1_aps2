package insper.edu.br.campeonato;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class TimeController {

    @Autowired
    private TimeService timeService;

    @PostMapping("/times")
    @ResponseStatus(HttpStatus.CREATED)
    public Time criarTime(@RequestBody Time time) {
        return timeService.criar(time);
    }

    @GetMapping("/times")
    public ArrayList<Time> getTimes() {
        return timeService.listar();
    }

    @GetMapping("/times/{id}")
    public Time getTime(@PathVariable String id) {
        return timeService.buscar(id);
    }

    @PostMapping("/times/{idTime}/jogadores/{idJogador}")
    @ResponseStatus(HttpStatus.CREATED)
    public Time postJogador(
            @PathVariable String idTime,
            @PathVariable String idJogador) {

        return timeService.adicionarJogador(idTime, idJogador);
    }
}