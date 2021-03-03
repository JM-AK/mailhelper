package ru.dv.mailhelper.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Embeddable
@Data
@NoArgsConstructor
public class Company {
    private String shortName;

    private String fullName;

}
