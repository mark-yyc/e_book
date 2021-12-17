package com.mark.dto;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

@Component
@Scope("session")
@Data
public class ShoppingCart implements Serializable {
    private int userId;
    private Date orderDate;
    private List<ShoppingCartItem> shoppingCartList;
}
