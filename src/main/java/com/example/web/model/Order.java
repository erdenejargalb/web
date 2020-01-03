package com.example.web.model;

import lombok.Data;
import java.util.Date;

@Data
public class Order {
    private Long id;
    private String name;
    private String street;
    private String city;
    private String address;
    private Date placedAt;
}
