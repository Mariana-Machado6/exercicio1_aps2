package insper.edu.br.campeonato.campeonato;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface CampeonatoRepository extends JpaRepository<Campeonato, String> {
    
}
