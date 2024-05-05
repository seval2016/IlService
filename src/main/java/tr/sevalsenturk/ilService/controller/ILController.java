package tr.sevalsenturk.ilService.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.sevalsenturk.ilService.exception.IlAlreadyExistsException;
import tr.sevalsenturk.ilService.exception.IlNotFoundException;
import tr.sevalsenturk.ilService.model.Il;
import tr.sevalsenturk.ilService.service.ILService;


import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController //bu classın bir controller class'ı oldugunu bildiriyoruz
@RequestMapping("/iller") //Controller hangi URL de çalışacak
@AllArgsConstructor
public class ILController {

    private final ILService ilService;


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
    /*
     http://localhost:8080/iller?name=Bursa dediğimizde sadece Bursa il verisini
     http://localhost:8080/iller dediğimizde tüm illeri getiren controller methodu
     */
    @GetMapping //http://localhost:8080/iller?name=Bursa
    public ResponseEntity<List<Il>> getIller(@RequestParam(required = false) String name) {
        //ResponseEntity ->rest ortamında kullanılan classlardır
        return new ResponseEntity<>(ilService.getIller(name), OK);
    }

    /* tek bir il  getirmek için
    @PathVariable  -> @GetMapping("/{id}") bu kısımdaki verilen id'yi kodumuzun içerisine alabilmek için kullanılır.  @PathVariable yanına tanımladığımız parametre ile @GetMapping de tanımlanan değişken parametre aynı olursa güzel olur. Eğer farklı bir isim yazacaksak @PathVariable yanına parantez içerisinde getmapping'deki variable tanımlanmalı. Aynı isimleri yazarsak buna gerek kalmaz.*/
    /*//1.yöntem
    for (Il il: iller) {

        if(il.getId().equals(id)){
            return new ResponseEntity<>(il, OK);
        }
        return ResponseEntity.notFound().build(); // If no matching id is found
    }*/
    /* Il result=null;
    //2.yöntem
     for (int i=0; i<iller.size();i++) {
       Il il=iller.get(i);
         if(il.getId().equals(id)){
             result=il;
         }
         if(result==null){
            throw new RuntimeException("IL not found");
         }
     }*/
    /* //1.yöntem
        try{
            return new ResponseEntity<>(getIlById(id), OK);
        }catch (IlNotFoundException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }*/
    @GetMapping("/{id}") //http://localhost:8080/iller/34
    public ResponseEntity<Il> getIl(@PathVariable String id) {
    return new ResponseEntity<>(getIlById(id),OK);
    }

    @PostMapping //http://localhost:8080/iller -> yeni il eklemek için kullanılır
    public ResponseEntity<Il> createIl(@RequestBody Il newIl){
        return new ResponseEntity<>(ilService.createIl(newIl),CREATED);
    }

    @PutMapping("/{id}") //http://localhost:8080/iller/16 -> update için kullanılır
    public ResponseEntity<Void> getIl (@PathVariable String id, @RequestBody Il newIl){

        ilService.updateIl(id,newIl);
        return new ResponseEntity<>(HttpStatus.OK);
        
    }

    @DeleteMapping("/{id}") //http://localhost:8080/iller/40
    public ResponseEntity<Void> deleteIl (@PathVariable String id){
        ilService.deleteIl(id);
        return new ResponseEntity<>(OK);
    }

    private Il getIlById(String id) {
        return ilService.getIlById(id);
    }

    @ExceptionHandler(IlNotFoundException.class)
    public ResponseEntity<String> handleIlNotFoundException(IlNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(IlAlreadyExistsException.class)
    public ResponseEntity<String> handleIlAlreadyExistsException(IlAlreadyExistsException ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.CONFLICT);

    }
}
