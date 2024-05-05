package tr.sevalsenturk.ilService.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tr.sevalsenturk.ilService.model.Il;

import java.util.List;


public interface IlRepository  extends MongoRepository<Il,String> {
    List<Il> findByName(String name);
}
