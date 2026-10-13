package insper.edu.br.campeonato.jogador;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface JogadorRepository extends JpaRepository<Jogador, String> {
    
}
