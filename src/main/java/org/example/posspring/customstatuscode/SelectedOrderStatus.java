package org.example.posspring.customstatuscode;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.posspring.dto.OrderStatus;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SelectedOrderStatus implements OrderStatus {
    private int statusCode;
    private String message;
}
