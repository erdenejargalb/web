package com.example.web.data;

import com.example.web.model.Order;
import com.example.web.model.Taco;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcOrderRepository implements OrderRepository{

    private SimpleJdbcInsert orderInserter;
    private SimpleJdbcInsert orderTacoInserter;
    private ObjectMapper objectMapper;

    @Autowired
    public JdbcOrderRepository(JdbcTemplate jdbc){
        this.orderInserter = new SimpleJdbcInsert(jdbc)
                .withTableName("Taco_Order")
                .usingGeneratedKeyColumns("id");

        this.orderTacoInserter = new SimpleJdbcInsert(jdbc)
                .withTableName("Taco_Order_Tacos");

        this.objectMapper = new ObjectMapper();
    }

    @Override
    public Order save(Order order) {
        order.setPlacedAt(new Date());
        long orderId = saveOrderDetails(order);
        order.setId(orderId);
        List<Taco> tacos = order.getTacos();
        for(Taco taco: tacos){
            saveTacoOrder(taco, orderId);
        }
        return order;
    }

    private long saveOrderDetails(Order order){
        @SuppressWarnings("unchecked")
        Map<String, Object> values = new HashMap<>();
        values.put("ID", order.getId());
        values.put("PLACEDAT", order.getPlacedAt());
        values.put("DELIVERYNAME", order.getName());
        values.put("DELIVERYSTREET", order.getStreet());
        values.put("DELIVERYCITY", order.getCity());
        values.put("DELIVERYADDRESS", order.getAddress());
        long orderId =
                orderInserter
                .executeAndReturnKey(values)
                .longValue();
        System.out.println("orderId:" + orderId);
        return orderId;
    }

    private void saveTacoOrder(Taco taco, long orderId){
        System.out.println(orderId);
        System.out.println(taco.getId());
        Map<String, Object> values = new HashMap<>();
        values.put("tacoOrder", orderId);
        values.put("taco", taco.getId());
        orderTacoInserter.execute(values);
    }
}
