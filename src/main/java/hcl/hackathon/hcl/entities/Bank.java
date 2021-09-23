package hcl.hackathon.hcl.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bank")
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String code;

    private String bank_name;

    public Bank(String code, String bank_name) {
        this.code = code;
        this.bank_name = bank_name;
    }
}
