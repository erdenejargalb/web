package com.example.web.data;

import com.example.web.model.Order;

public interface OrderRepository {
    Order save(Order order);
}
