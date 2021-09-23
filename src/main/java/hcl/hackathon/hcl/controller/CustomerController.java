package hcl.hackathon.hcl.controller;

import hcl.hackathon.hcl.controller.requests.AddFavouriteBankRequest;
import hcl.hackathon.hcl.controller.requests.DeleteFavouriteBankRequest;
import hcl.hackathon.hcl.controller.requests.EditFavouriteBankRequest;
import hcl.hackathon.hcl.entities.Customer;
import hcl.hackathon.hcl.entities.InternalUserDetails;
import hcl.hackathon.hcl.service.BankService;
import hcl.hackathon.hcl.service.CustomerService;
import hcl.hackathon.hcl.service.FavouriteBankAccountService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import hcl.hackathon.hcl.controller.response.ErrorResponse;
import hcl.hackathon.hcl.controller.response.SuccessResponse;
import javax.validation.Valid;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private BankService bankService;

    @Autowired
    private FavouriteBankAccountService favouriteBankAccountService;

    @PostMapping("/api/customers/login")
    @ApiOperation(value = "User login",
            notes = "Service that allows the User to login.")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "The service was not found."),
            @ApiResponse(code = 500, message = "An unexpected error has occurred.")})
    public ResponseEntity<?> login() {
        InternalUserDetails principal = (InternalUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Customer customer = customerService.getCustomerByCustomerId(principal.getUsername());
        return ResponseEntity.ok(customer.getFavouriteBankAccounts());
    }

    @PostMapping("/api/customers/favouriteaccounts")
    @ApiOperation(value = "User add favourites",
            notes = "Service that allows the User add favourite account.")
    public ResponseEntity<?> addFavourites(@Valid @ApiParam(value = "Add favourite information.") @RequestBody AddFavouriteBankRequest request) {
        bankService.addToFavourites(request);
        return ResponseEntity.ok("Account added as a favourite");
    }

    @PutMapping("/api/customers/favouriteaccounts")
    @ApiOperation(value = "User edit favourites",
            notes = "Service that allows the User to edit the favourite account.")
    public ResponseEntity<?> editFavourites(@Valid @ApiParam(value = "Edit favourite information") @RequestBody EditFavouriteBankRequest request) {
        return ResponseEntity.ok("null");
    }

    @DeleteMapping("/api/customers/favouriteaccounts")
    @ApiOperation(value = "User delete favourites",
            notes = "Service that allows the User to delete the favourite account.")
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
