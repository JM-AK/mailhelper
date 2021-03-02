package ru.dv.mailhelper.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Embeddable
@Data
@NoArgsConstructor
public class Company {
    @Column(name = "short_name")
    private String shortName;

    @Column(name = "full_name")
    private String fullName;

}
