package tr.sevalsenturk.ilService.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tr.sevalsenturk.ilService.exception.IlAlreadyExistsException;
import tr.sevalsenturk.ilService.exception.IlNotFoundException;
import tr.sevalsenturk.ilService.model.Il;
import tr.sevalsenturk.ilService.repository.IlRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ILService {
    private final IlRepository ilRepository;
    public List<Il> getIller(String name) {
        if(name == null){
            return ilRepository.findAll();
        }else{
            return ilRepository.findAllByName(name);
        }

    }

    public Il createIl(Il newIl) {
       Optional <Il> ilByName= ilRepository.findByName(newIl.getName()); //varsa hata verıyoruz yoksa save ediyoruz
       if(ilByName.isPresent()){
           throw new IlAlreadyExistsException("IL already exist with name :" +newIl.getName());
       }

        return ilRepository.save(newIl);
    }

    public void deleteIl(String id) {
       ilRepository.deleteById(id);
    }

    public Il getIlById(String id) {
        return ilRepository.findById(id)
                .orElseThrow(() -> new IlNotFoundException("IL Not Found with id :" +id));
    }

    public void updateIl(String id, Il newIl) {
       Il oldIl= getIlById(id);
       oldIl.setName(newIl.getName());
       ilRepository.save(oldIl);
    }
}
