package hcl.hackathon.hcl.controller;

import hcl.hackathon.hcl.controller.requests.AddFavouriteBankRequest;
import hcl.hackathon.hcl.controller.requests.DeleteFavouriteBankRequest;
import hcl.hackathon.hcl.controller.requests.EditFavouriteBankRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/api/customers")
public class CustomerController {


    @PostMapping("/login")
    public ResponseEntity<?> login() {
        return ResponseEntity.ok("null");
    }

    @PostMapping("/favouriteaccounts")
    public ResponseEntity<?> addFavourites(AddFavouriteBankRequest request) {
        return ResponseEntity.ok("null");
    }

    @PutMapping("/favouriteaccounts")
    public ResponseEntity<?> addFavourites(EditFavouriteBankRequest request) {
        return ResponseEntity.ok("null");
    }
    @DeleteMapping("/favouriteaccounts")
    public ResponseEntity<?> addFavourites(DeleteFavouriteBankRequest request) {
        return ResponseEntity.ok("null");
    }

}
