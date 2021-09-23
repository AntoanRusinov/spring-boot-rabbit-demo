package hcl.hackathon.hcl.service;

import com.github.javafaker.Faker;
import hcl.hackathon.hcl.entities.Customer;
import hcl.hackathon.hcl.entities.InternalUserDetails;
import hcl.hackathon.hcl.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;

    public long count() {
        return customerRepository.count();
    }

    public void insertCustomers(List<Customer> customers) {
        customerRepository.saveAll(customers);
    }

    public String generateCustomerId() {
        return new Faker().code().gtin13();
    }

    public Customer getCustomerByCustomerId(String customer_id){
        return customerRepository.findByCustomerId(customer_id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer user = customerRepository.findByCustomerId(username);
        return new InternalUserDetails(user);
    }
}