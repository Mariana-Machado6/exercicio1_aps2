package insper.edu.br.campeonato.campeonato;

import insper.edu.br.campeonato.Classificacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class CampeonatoController {

    @Autowired
    private CampeonatoService campeonatoService;

    @PostMapping("/campeonatos")
    @ResponseStatus(HttpStatus.CREATED)
    public Campeonato criarCampeonato(
            @RequestBody Campeonato campeonato) {

        return campeonatoService.criar(campeonato);
    }

    @PostMapping("/campeonatos/{id}/times/{idTime}")
    public Campeonato adicionarTime(@PathVariable String id, @PathVariable String idTime) {
        return campeonatoService.adicionarTime(id, idTime);
    }

    @GetMapping("/campeonatos/{id}/classificacao")
    public ArrayList<Classificacao> getClassificacao(
            @PathVariable String id) {

        return campeonatoService.classificacao(id);
    }
}