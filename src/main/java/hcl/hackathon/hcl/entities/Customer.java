package hcl.hackathon.hcl.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
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

    private String customerId;

    private String password;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "customer_id")
    private List<FavouriteBankAccount> favouriteBankAccounts;

    public Customer(String customerId, String password, List<FavouriteBankAccount> favouriteBankAccounts) {
        this.customerId = customerId;
        this.password = password;
        this.favouriteBankAccounts = favouriteBankAccounts;
    }

}
