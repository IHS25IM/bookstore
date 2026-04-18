package dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomerDTO {
    private Long id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String adress;
    @NotNull
    private Integer zipCode;
    @NotNull
    private String city;
    @NotNull
    private String country;
    @NotNull
    private List<CartLineDTO> cartLines;
}
