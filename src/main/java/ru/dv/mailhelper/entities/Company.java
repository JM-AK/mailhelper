package ru.dv.mailhelper.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company {
    private String shortName;

    private String fullName;

}
