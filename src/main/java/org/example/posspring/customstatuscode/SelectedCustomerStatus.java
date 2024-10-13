package org.example.posspring.customstatuscode;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.posspring.dto.CustomerStatus;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SelectedCustomerStatus implements CustomerStatus {
    private int statusCode;
    private String message;
}
