package insper.edu.br.campeonato;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

@Service
public class CampeonatoService {

    private HashMap<String, Campeonato> campeonatos = new HashMap<>();

    public Campeonato criar(Campeonato campeonato) {

        campeonato.setId(UUID.randomUUID().toString());

        campeonatos.put(
                campeonato.getId(),
                campeonato
        );

        return campeonato;
    }

    public Campeonato buscar(String id) {
        return campeonatos.get(id);
    }

    public ArrayList<Classificacao> classificacao(String id) {

        Campeonato campeonato = campeonatos.get(id);

        if (campeonato == null) {
            return null;
        }

        return campeonato.getClassificacao();
    }
}