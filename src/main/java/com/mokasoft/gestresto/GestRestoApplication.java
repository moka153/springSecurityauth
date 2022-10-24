package com.mokasoft.gestresto;

import com.mokasoft.gestresto.dtos.AppTableDto;
import com.mokasoft.gestresto.dtos.CategoryDto;
import com.mokasoft.gestresto.dtos.ProductDto;
import com.mokasoft.gestresto.dtos.RoomDto;
import com.mokasoft.gestresto.entities.AppRole;
import com.mokasoft.gestresto.entities.AppUser;
import com.mokasoft.gestresto.enums.ProductType;
import com.mokasoft.gestresto.mappers.ProductMapper;
import com.mokasoft.gestresto.mappers.RoomTableMapper;
import com.mokasoft.gestresto.services.AccountService;
import com.mokasoft.gestresto.services.ProductService;
import com.mokasoft.gestresto.services.RoomService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class GestRestoApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestRestoApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner start(AccountService accountService, RoomService roomService,
                            RoomTableMapper roomMapper, ProductService productService,
                            ProductMapper productMapper) {
        return args -> {

            accountService.addNewRole(new AppRole(null, "ADMIN"));
            accountService.addNewRole(new AppRole(null, "WAITER"));
            accountService.addNewRole(new AppRole(null, "CASHIER"));


            accountService.addNewUser(new AppUser(null, "waiter1", "1234", new ArrayList<>(), new ArrayList<>()));
            accountService.addNewUser(new AppUser(null, "admin", "1234", new ArrayList<>(), new ArrayList<>()));
            accountService.addNewUser(new AppUser(null, "waiter2", "1234", new ArrayList<>(), new ArrayList<>()));
            accountService.addNewUser(new AppUser(null, "cashier1", "1234", new ArrayList<>(), new ArrayList<>()));
            accountService.addNewUser(new AppUser(null, "cashier2", "1234", new ArrayList<>(), new ArrayList<>()));


            accountService.addRoleToUser("waiter1", "WAITER");
            accountService.addRoleToUser("admin", "ADMIN");
            accountService.addRoleToUser("admin", "WAITER");
            accountService.addRoleToUser("admin", "CASHIER");
            accountService.addRoleToUser("waiter2", "WAITER");
            accountService.addRoleToUser("cashier1", "CASHIER");
            accountService.addRoleToUser("cashier2", "CASHIER");

            //ajout de deux salles pour les testes
            RoomDto roomDto = new RoomDto();
            roomDto.setName("Salle une");
            RoomDto roomDto2 = new RoomDto();
            roomDto2.setName("Salle deux");
            roomService.saveRoom(roomDto);
            roomService.saveRoom(roomDto2);

            List<RoomDto> roomDtos = roomService.getRooms();

            for (RoomDto r : roomDtos) {
                for (int i = 0; i < 20; i++) {
                    AppTableDto appTableDto = new AppTableDto();
                    appTableDto.setTableNumber("table " + i);
                    appTableDto.setAvailable(true);
                    appTableDto.setCustomerNumber(0);
                    appTableDto.setRoomDto(r);
                    roomService.saveTable(appTableDto);
                }
            }

            //creation de quelques catégorie pour le teste
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setName("Sandwich");
            categoryDto.setPicture("https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.istockphoto.com%2Ffr%2Fphotos%2Fsandwich&psig=AOvVaw2LbUQsicbX6nbb8cYhJ4rv&ust=1666705977420000&source=images&cd=vfe&ved=0CAwQjRxqFwoTCMD5196C-foCFQAAAAAdAAAAABAE");
            productService.saveCategory(categoryDto);
            CategoryDto categoryDto2 = new CategoryDto();
            categoryDto2.setName("Burgers");
            categoryDto2.setPicture("https://www.google.com/url?sa=i&url=https%3A%2F%2Ffr.depositphotos.com%2Fstock-photos%2Fburger.html&psig=AOvVaw0lm8BscHqbkWSARCJAinCD&ust=1666706045445000&source=images&cd=vfe&ved=0CAwQjRxqFwoTCMCWsPaC-foCFQAAAAAdAAAAABAE");
            productService.saveCategory(categoryDto2);
            CategoryDto categoryDto3 = new CategoryDto();
            categoryDto3.setName("Pizza");
            categoryDto3.setPicture("https://www.google.com/url?sa=i&url=https%3A%2F%2Ffr.freepik.com%2Fphotos-vecteurs-libre%2Fpizza&psig=AOvVaw0SNiKwLWe6umfjQK1S3v54&ust=1666706326904000&source=images&cd=vfe&ved=0CAwQjRxqFwoTCODjx4WD-foCFQAAAAAdAAAAABAE");
            productService.saveCategory(categoryDto3);
            CategoryDto categoryDto4 = new CategoryDto();
            categoryDto4.setName("Plat");
            categoryDto4.setPicture("https://www.google.com/url?sa=i&url=https%3A%2F%2Frecettescookeo.com%2Frecipe-type%2Fplats&psig=AOvVaw2YaEwXcBcxa6Cj_3-GLjF5&ust=1666706058928000&source=images&cd=vfe&ved=0CAwQjRxqFwoTCKjFxpSD-foCFQAAAAAdAAAAABAE");
            productService.saveCategory(categoryDto4);
            CategoryDto categoryDto5 = new CategoryDto();
            categoryDto5.setName("Boissons");
            categoryDto5.setPicture("https://www.google.com/url?sa=i&url=https%3A%2F%2Ffr.freepik.com%2Fphotos%2Fboissons&psig=AOvVaw1VRlr3wvJ5LUSVqmzSEfQZ&ust=1666706394618000&source=images&cd=vfe&ved=0CAwQjRxqFwoTCMCp7aWD-foCFQAAAAAdAAAAABAE");
            productService.saveCategory(categoryDto5);

            List<CategoryDto> categoryDtos = productService.getAllCategories();
            for(CategoryDto c : categoryDtos){
                ProductDto productDto = new ProductDto();
                productDto.setDesignation("product "+c.getCategoryId());
                productDto.setQuantity(10);
                productDto.setType(ProductType.PLAT);
                productDto.setCategoryDto(c);
                productDto.setDescription("");
                productDto.setCostPrice(BigDecimal.valueOf(250));
                productDto.setPrice(BigDecimal.valueOf(500));
                productDto.setPicture("");
                productService.saveProduct(productDto);
            }



            List<CategoryDto> categoryDtosd = productService.getAllCategories();
            for(CategoryDto c : categoryDtosd){
                System.out.println(c.getName() + " " + c.getPicture());
            }
        };
    }
}
