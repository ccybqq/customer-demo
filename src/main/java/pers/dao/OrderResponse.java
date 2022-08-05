package pers.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Convert;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private String customerName;
    private String productName;
}
