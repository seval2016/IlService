package tr.sevalsenturk.ilService.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.sevalsenturk.ilService.model.IL;

import java.util.List;

@RestController //bu classın bir controller class'ı oldugunu bildiriyoruz
@RequestMapping("/il") //Controller hangi URL de çalışacak
public class ILController {

    public ResponseEntity<List<IL>> getIller(){

    }

}
