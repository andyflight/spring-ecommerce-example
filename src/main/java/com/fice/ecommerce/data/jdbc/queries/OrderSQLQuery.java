package com.fice.ecommerce.data.jdbc.queries;

import org.hibernate.annotations.processing.SQL;
import org.springframework.stereotype.Component;

@Component
public class OrderSQLQuery {
    //language=SQL
    private final String findOne = """
        SELECT "order".id AS id,
            "order".order_number AS order_number,
            "order".created_at AS created_at,
            "order".status,
            customer.customer_reference as customer_reference,
            order_item.id AS item_id,
            order_item.product_old_price AS product_old_price,
            order_item.quantity AS item_quantity,
            product.id AS product_id,
            product.code AS product_code
        FROM "order"
        LEFT JOIN "order_item" ON "order".id = order_item.order_id
        LEFT JOIN "customer" ON "order".customer_id = "customer".id
        LEFT JOIN "product" ON "order_item".product_id = "product".id
    """;
    
    public String findOneByNumber() {
        //language=SQL
        return findOne + "WHERE order_number = ?";
    }
    
    public String findManyByCustomerReference() {
        //language=SQL
        return findOne + "WHERE customer_reference = ?";
    }
    
    public String isExistsById() {
        //language=SQL
        return "SELECT EXISTS (SELECT 1 FROM \"order\" WHERE id = ?);";
    }
    
    public String createOrder() {
        //language=SQL
        return "INSERT INTO \"order\"(order_number, status, customer_id) VALUES(?, ?, ?) RETURNING id;";
    }
    
    public String updateOrderById() {
        //language=SQL
        return "UPDATE \"order\" SET status=? WHERE id = ?;";
    }
    
    public String createOrderItem() {
        //language=SQL
        return "INSERT INTO \"order_item\"(order_id, product_id, quantity, product_old_price) VALUES(?, ?, ?, ?);";
    }
    
    public String getIdByOrderNumber() {
        //language=SQL
        return "SELECT id FROM \"order\" WHERE order_number = ?;";
    }
    
    public String deleteOrderById() {
        //language=SQL
        return "DELETE FROM \"order\" WHERE id = ?;";
    }
    
    public String deleteOrderItemById() {
        //language=SQL
        return "DELETE FROM \"order_item\" WHERE order_id = ?;";
    }
}
