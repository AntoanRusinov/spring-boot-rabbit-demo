package hcl.hackathon.hcl.controller.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditFavouriteBankRequest {

    private Long accountId;

    private String newAccountName;

    private String newAccountNumber;

}
