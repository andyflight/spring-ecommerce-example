package com.fice.ecommerce.data.jdbc.impl;

import com.fice.ecommerce.data.CustomerRepository;
import com.fice.ecommerce.domain.Customer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

@Repository
public class JdbcCustomerRepository implements CustomerRepository {

  private static final String SQL_FIND_BY_REFERENCE = "SELECT id, customer_reference, fullname, username, password, email FROM customer WHERE customer_reference = CAST(? AS UUID)";

  private static final String SQL_DELETE_BY_REFERENCE = "DELETE FROM customer WHERE customer_reference = CAST(? AS UUID)";

  private static final String sqlInsert = "INSERT INTO customer (customer_reference, fullname, username, password, email) " +
          "VALUES (CAST(? AS UUID), ?, ?, ?, ?) RETURNING id";

  private static final String sqlUpdate = "UPDATE customer SET fullname = ?, username = ?, password = ?, email = ? " +
          "WHERE customer_reference = CAST(? AS UUID)";

  private final JdbcTemplate jdbcTemplate;
  private final RowMapper<Customer> customerRowMapper;

  public JdbcCustomerRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
    this.customerRowMapper = (rs, rowNum) -> mapToCustomer(rs);
  }

  @Override
  public Optional<Customer> findByReference(UUID reference) {
    return jdbcTemplate.query(SQL_FIND_BY_REFERENCE, customerRowMapper, reference.toString())
            .stream()
            .findFirst();
  }

  @Override
  public Customer save(Customer customer) {
    if (customer.getId() == null || !existsByReference(customer.getCustomerReference())) {
      // create
      Long generatedId = jdbcTemplate.queryForObject(sqlInsert, Long.class,
              customer.getCustomerReference().toString(),
              customer.getFullName(),
              customer.getUsername(),
              customer.getPassword(),
              customer.getEmail()
      );
      return customer.toBuilder().id(generatedId).build();
    } else {
      // update
      jdbcTemplate.update(sqlUpdate,
              customer.getFullName(),
              customer.getUsername(),
              customer.getPassword(),
              customer.getEmail(),
              customer.getCustomerReference().toString()
      );
      return customer;
    }
  }

  @Override
  public void deleteByReference(UUID reference) {
    jdbcTemplate.update(SQL_DELETE_BY_REFERENCE, reference.toString());
  }

  private static Customer mapToCustomer(ResultSet rs) throws SQLException {
    return Customer.builder()
            .id(rs.getLong("id"))
            .customerReference(UUID.fromString(rs.getString("customer_reference")))
            .fullName(rs.getString("fullname"))
            .username(rs.getString("username"))
            .password(rs.getString("password"))
            .email(rs.getString("email"))
            .build();
  }

  private boolean existsByReference(UUID reference) {
    String sqlExists = "SELECT EXISTS (SELECT 1 FROM customer WHERE customer_reference = CAST(? AS UUID))";
    return Boolean.TRUE.equals(jdbcTemplate.queryForObject(sqlExists, Boolean.class, reference.toString()));
  }
}

