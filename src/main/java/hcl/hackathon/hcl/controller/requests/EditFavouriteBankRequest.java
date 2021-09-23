package hcl.hackathon.hcl.controller.requests;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Data
public class EditFavouriteBankRequest {
	
	
	 @NotBlank(message = "'AccountId' is mandatory")
	 private Long id;
    @NotBlank(message = "'BankName' is mandatory")
    private String name;
    @NotBlank(message = "'Iban' is mandatory")
    private String iban;
}
