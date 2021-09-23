package hcl.hackathon.hcl.controller;

import hcl.hackathon.hcl.controller.requests.AddFavouriteBankRequest;
import hcl.hackathon.hcl.controller.requests.DeleteFavouriteBankRequest;
import hcl.hackathon.hcl.controller.requests.EditFavouriteBankRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    @PostMapping("/api/customers/login")
    public ResponseEntity<?> login() {
        return ResponseEntity.ok("null");
    }

    @PostMapping("/api/customers/favouriteaccounts")
    public ResponseEntity<?> addFavourites(AddFavouriteBankRequest request) {
        return ResponseEntity.ok("null");
    }

    @PutMapping("/api/customers/favouriteaccounts")
    public ResponseEntity<?> addFavourites(EditFavouriteBankRequest request) {
        return ResponseEntity.ok("null");
    }

    @DeleteMapping("/api/customers/favouriteaccounts")
    public ResponseEntity<?> addFavourites(DeleteFavouriteBankRequest request) {
        return ResponseEntity.ok("null");
    }

}
