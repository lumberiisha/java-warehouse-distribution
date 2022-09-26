package com.frakton.javawarehousedistribution.models.warehouse;

import com.frakton.javawarehousedistribution.models.employee.Employee;
import com.frakton.javawarehousedistribution.models.location.Address;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;
@Entity
public class Warehouse {
    @Id
    private UUID id;
    private String name;
    @OneToMany
    private List<Product> products;
    @OneToMany
    private List<Employee> employees;
    @OneToOne
    private Address address;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
