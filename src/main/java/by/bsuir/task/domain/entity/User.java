package by.bsuir.task.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class User implements BaseEntity, Serializable {

    private static final long serialVersionUID = 4958483859493859385L;

    private Integer id;
    private String username;
    private String password;
    private Role role;

    public User(Integer id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
}
