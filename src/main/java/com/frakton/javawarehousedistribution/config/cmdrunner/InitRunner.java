package com.frakton.javawarehousedistribution.config.cmdrunner;

import com.frakton.javawarehousedistribution.models.user.Role;
import com.frakton.javawarehousedistribution.models.user.User;
import com.frakton.javawarehousedistribution.models.warehouse.Product;
import com.frakton.javawarehousedistribution.repository.user.UserRepository;
import com.frakton.javawarehousedistribution.repository.warehouse.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;

@Component
public class InitRunner implements CommandLineRunner {
    private static final Logger LOG = Logger.getLogger(InitRunner.class.getName());
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ProductRepository productRepository;

    public InitRunner(UserRepository userRepository, PasswordEncoder passwordEncoder, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.productRepository = productRepository;
    }
    @Override
    public void run(String... args){
        if (userRepository.count()==0){
            User user=new User();
            user.setRole(Role.ADMIN);
            user.setUserName("admin");
            user.setPassword(passwordEncoder.encode("admin"));
            userRepository.save(user);
            LOG.info("Admin user added");
        }
        if (productRepository.count()==0){
            for (int i = 1; i <= 10; i++) {
                Product product=new Product();
                product.setName("Product "+i);
                product.setDescription("Description "+i);
                product.setManufacturingDate(new Date());
                product.setExpirationDate(new Date());
                product.setPrice((double) i);
                productRepository.save(product);
            }
            LOG.info("Product list added");
        }
    }
}
