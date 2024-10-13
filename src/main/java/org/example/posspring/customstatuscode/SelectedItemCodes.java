package org.example.posspring.customstatuscode;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.posspring.dto.ItemStatus;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SelectedItemCodes implements ItemStatus {
    private int statusCode;
    private String message;
}
