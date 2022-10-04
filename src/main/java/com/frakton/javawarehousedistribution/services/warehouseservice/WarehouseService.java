package com.frakton.javawarehousedistribution.services.warehouseservice;

import com.frakton.javawarehousedistribution.models.location.Address;
import com.frakton.javawarehousedistribution.models.warehouse.Warehouse;
import com.frakton.javawarehousedistribution.repository.location.AddressRepository;
import com.frakton.javawarehousedistribution.repository.warehouse.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
@Service
public class WarehouseService {

    @Autowired
    private WarehouseRepository warehouseRepository;
    @Autowired
    private AddressRepository addressRepository;

    public Optional<Warehouse> addWarehouse(Warehouse warehouse){
        Warehouse warehouse1=new Warehouse();

        Address address1=new Address();
       // address1.setId(UUID.randomUUID());
//        address1.setCity(address.getCity());
//        address1.setRegion(address.getRegion());
//        address1.setPostalCode(address.getPostalCode());
        warehouse1.setId(UUID.randomUUID());
        warehouse1.setName(warehouse.getName());
        address1.setPostalCode(warehouse.getAddress().getPostalCode());

        warehouse1.setAddress(address1);
        //addressRepository.save(address1);
        warehouseRepository.save(warehouse1);
        return warehouseRepository.findById(warehouse1.getId());
    }

}
