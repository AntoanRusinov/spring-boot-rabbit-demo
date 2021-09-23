package hcl.hackathon.hcl.controller;

import hcl.hackathon.hcl.controller.requests.AddFavouriteBankRequest;
import hcl.hackathon.hcl.controller.requests.DeleteFavouriteBankRequest;
import hcl.hackathon.hcl.controller.requests.EditFavouriteBankRequest;
import hcl.hackathon.hcl.controller.response.ErrorResponse;
import hcl.hackathon.hcl.controller.response.SuccessResponse;
import hcl.hackathon.hcl.service.FavouriteBankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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
            ErrorResponse errorResponse = new ErrorResponse("Account not found", null,  HttpStatus.NOT_FOUND.value());
            EntityModel<ErrorResponse> resource = EntityModel.of(errorResponse);
            resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).deleteAccount(request)).withSelfRel());
            return new ResponseEntity<>(resource, HttpStatus.NOT_FOUND);
        } else {
            favouriteBankAccountService.deleteById(request.getAccountId());
            SuccessResponse successResponse = new SuccessResponse("Favorite Bank Account deleted", null, HttpStatus.ACCEPTED.value());
            EntityModel<SuccessResponse> resource = EntityModel.of(successResponse);
            resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).deleteAccount(request)).withSelfRel());
            return new ResponseEntity<>(resource, HttpStatus.ACCEPTED);
        }
    }

}
