package hcl.hackathon.hcl.controller;

import hcl.hackathon.hcl.controller.requests.AddFavouriteBankRequest;
import hcl.hackathon.hcl.controller.requests.DeleteFavouriteBankRequest;
import hcl.hackathon.hcl.controller.requests.EditFavouriteBankRequest;
import hcl.hackathon.hcl.service.FavouriteBankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {

    @Autowired
    private FavouriteBankAccountService favouriteBankAccountService;

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
    public ResponseEntity<?> deleteAccount(@RequestBody DeleteFavouriteBankRequest request) {
        if (request.getAccountId() == null || favouriteBankAccountService.findById(request.getAccountId()).isEmpty()) {
            return new ResponseEntity<>("Account not found", HttpStatus.NOT_FOUND);
        } else {
            favouriteBankAccountService.deleteById(request.getAccountId());
            return new ResponseEntity<>("Favorite Bank Account deleted", HttpStatus.ACCEPTED);
        }
    }

}
