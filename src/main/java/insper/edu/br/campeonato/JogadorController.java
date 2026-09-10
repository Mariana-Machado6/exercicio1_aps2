package insper.edu.br.campeonato;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class JogadorController {

    @Autowired
    private JogadorService jogadorService;

    @PostMapping("/jogadores")
    @ResponseStatus(HttpStatus.CREATED)
    public Jogador criarJogador(@RequestBody Jogador jogador) {
        return jogadorService.criar(jogador);
    }

    @GetMapping("/jogadores")
    public ArrayList<Jogador> getJogadores() {
        return jogadorService.listar();
    }

    @GetMapping("/jogadores/{id}")
    public Jogador getJogador(@PathVariable String id) {
        return jogadorService.buscar(id);
    }
}
