package hcl.hackathon.hcl.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String customer_id;

    private String password;

    @OneToMany(targetEntity = FavouriteBankAccount.class, fetch = FetchType.EAGER)
    private List<FavouriteBankAccount> favouriteBankAccounts;

}
