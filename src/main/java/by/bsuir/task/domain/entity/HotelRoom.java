package by.bsuir.task.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class HotelRoom implements BaseEntity {

    private Integer id;
    private String roomNumber;
    private Boolean occupied;
}
