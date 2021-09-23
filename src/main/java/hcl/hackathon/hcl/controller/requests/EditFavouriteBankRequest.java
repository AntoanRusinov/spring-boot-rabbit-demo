package hcl.hackathon.hcl.controller.requests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class EditFavouriteBankRequest {

    @NotBlank(message = "'AccountId' is mandatory")
    private Long id;
    @NotBlank(message = "'BankName' is mandatory")
    private String name;

    @Size(min = 10, max = 19)
    @NotBlank(message = "'Iban' is mandatory")
    private String iban;

}
