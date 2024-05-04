package tr.sevalsenturk.ilService.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.sevalsenturk.ilService.model.Il;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController //bu classın bir controller class'ı oldugunu bildiriyoruz
@RequestMapping("/iller") //Controller hangi URL de çalışacak
public class ILController {

    private static final List<Il> iller=new ArrayList<>();

    public ILController() {
        if(iller.isEmpty()){
            Il il1 = new Il(new Date(), "75", "Ardahan");
            Il il2 = new Il(new Date(), "34", "İstanbul");
            Il il3 = new Il(new Date(), "16", "Bursa");

            iller.add(il1);
            iller.add(il2);
            iller.add(il3);
        }


    }

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

    //Tüm illeri getirmek için
    @GetMapping
    public ResponseEntity<List<Il>> getIller() {
        //ResponseEntity ->rest ortamında kullanılan classlardır
        return new ResponseEntity<>(iller, OK);
    }

    /* tek bir il  getirmek için
    @PathVariable  -> @GetMapping("/{id}") bu kısımdaki verilen id'yi kodumuzun içerisine alabilmek için kullanılır.  @PathVariable yanına tanımladığımız parametre ile @GetMapping de tanımlanan değişken parametre aynı olursa güzel olur. Eğer farklı bir isim yazacaksak @PathVariable yanına parantez içerisinde getmapping'deki variable tanımlanmalı. Aynı isimleri yazarsak buna gerek kalmaz.*/
    @GetMapping("/{id}")
    public ResponseEntity<Il> getIl(@PathVariable String id) {

      /* //1.yöntem
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

        Il result = getIlById(id);
        return new ResponseEntity<>(result, OK);

    }

    @PostMapping
    public ResponseEntity<Il> createIl(@RequestBody Il newIl){
        newIl.setCreateDate(new Date());
        iller.add(newIl);
        return new ResponseEntity<>(newIl,CREATED);
    }

    @PutMapping("/{id}") //update
    public ResponseEntity<Void> getIl (@PathVariable String id, @RequestBody Il newIl){

        Il oldIl=getIlById(id);
        oldIl.setName(newIl.getName());
        oldIl.setCreateDate(new Date());

        return new ResponseEntity<>(OK);
        
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIl (@PathVariable String id){
        Il il=getIlById(id);
        iller.remove(il);
        return new ResponseEntity<>(OK);
    }

    private Il getIlById(String id) {
        return iller.stream()
                .filter(il -> il.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("IL Not Found"));
    }


}
