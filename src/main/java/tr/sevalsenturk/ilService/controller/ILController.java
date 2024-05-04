package tr.sevalsenturk.ilService.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.sevalsenturk.ilService.model.Il;

import java.util.Arrays;
import java.util.List;

@RestController //bu classın bir controller class'ı oldugunu bildiriyoruz
@RequestMapping("/il") //Controller hangi URL de çalışacak
public class ILController {


    /*
     @GetMapping
     @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Il> getIller(){
        Il il1=new Il("75","Ardahan");
        Il il2=new Il("34","İstanbul");
        Il il3=new Il("16","Bursa");

       return Arrays.asList(il1,il2,il3);

        Aşağıdaki  getIller methodu bu şekilde de yazılır fakat burada default olarak spring boot 200 döndürür. Aşağıdaki kodda döndürülen değere mesaj da ekleyebiliriz fakar burada müdahale edemeyiz. Sadece @ResponseStatus ile yine springboot'un kendi mesajını verdirebiliriz.

    }
     */

    @GetMapping
    public ResponseEntity<List<Il>> getIller(){
        Il il1=new Il("75","Ardahan");
        Il il2=new Il("34","İstanbul");
        Il il3=new Il("16","Bursa");

        List<Il> iller = Arrays.asList(il1,il2,il3);
        //ResponseEntity ->rest ortamında kullanılan classlardır
        return new ResponseEntity<>(iller, HttpStatus.OK);
    }

}
