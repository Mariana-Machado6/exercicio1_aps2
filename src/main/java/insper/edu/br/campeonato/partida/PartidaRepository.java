package insper.edu.br.campeonato.partida;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface PartidaRepository extends JpaRepository<Partida, String> {
    List<Partida> findByCampeonatoId(String campeonatoId);
}
