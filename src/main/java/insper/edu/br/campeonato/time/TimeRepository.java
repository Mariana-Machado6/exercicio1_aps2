package insper.edu.br.campeonato.time;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface TimeRepository extends JpaRepository<Time, String> {
    
}
