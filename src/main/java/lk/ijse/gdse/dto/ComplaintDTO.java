package lk.ijse.gdse.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ComplaintDTO {
    private String id;
    private String userName;
    private String title;
    private String Complaint;
    private Date date;
}
