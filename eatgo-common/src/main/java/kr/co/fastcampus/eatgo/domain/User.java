package kr.co.fastcampus.eatgo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    @Setter
    private String name;

    @NotEmpty
    @Setter
    private String email;

    @NotEmpty
    @Setter
    private String password;

    @NotNull
    @Setter
    private Long level;

    public boolean isAdmin() {
        return level >= 100;
    }

    public boolean isActive() {
        return level > 0;
    }

    public void deactive() {
        level = 0L;
    }

    @JsonIgnore
    public String getAccessToken() {
        if (password == null)
            return "";

        return password.substring(0,11);
    }
}
