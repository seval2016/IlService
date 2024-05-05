package tr.sevalsenturk.ilService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data //otomatik olarak propertileri oluşturur setter getter yazılmaz
@NoArgsConstructor //Constructor oluşturmamak için
@AllArgsConstructor
public class Il {
    @Id
    private String id;
    private Date createDate=new Date();
    private String name;

}
