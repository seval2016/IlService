package tr.sevalsenturk.ilService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //otomatik olarak propertileri oluşturur setter getter yazılmaz
@NoArgsConstructor //Constructor oluşturmamak için
@AllArgsConstructor
public class Il {
    private String id;
    private String name;

}
