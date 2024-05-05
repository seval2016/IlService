package tr.sevalsenturk.ilService.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tr.sevalsenturk.ilService.model.Il;

import java.util.List;
import java.util.Optional;


public interface IlRepository  extends MongoRepository<Il,String> {
    List<Il> findAllByName(String name);
    Optional<Il> findByName(String name);

}
