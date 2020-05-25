package kr.co.fastcampus.eatgo.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {

    @Id
    @GeneratedValue
    @Setter
    private Long id;

    private String name;
    private String address;

    @Transient
    private List<MenuItem> menuItems;

    public void updateInformation(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public void setMenuItem(List<MenuItem> menuItems) {
        this.menuItems = new ArrayList<>(menuItems);

    }

    public String getInformation() {
        return name + " in "+ address;
    }

}
