package tr.sevalsenturk.ilService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data //otomatik olarak propertileri oluşturur setter getter yazılmaz
@NoArgsConstructor //Constructor oluşturmamak için
@AllArgsConstructor
public class Il {
    private Date createDate;
    private String id;
    private String name;

}
