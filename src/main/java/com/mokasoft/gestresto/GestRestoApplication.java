package com.mokasoft.gestresto;

import com.mokasoft.gestresto.dtos.*;
import com.mokasoft.gestresto.entities.AppRole;
import com.mokasoft.gestresto.entities.AppTable;
import com.mokasoft.gestresto.entities.AppUser;
import com.mokasoft.gestresto.entities.Category;
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
import java.util.Random;

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

                AppTableDto appTableDto = new AppTableDto();
                appTableDto.setTableNumber("table " + r.getRoomId());
                appTableDto.setAvailable(true);
                appTableDto.setCustomerNumber(0);
                appTableDto.setRoomDto(r);
                roomService.saveTable(appTableDto);

            }

            //creation de quelques catégorie pour le teste
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setName("Sandwich");
            categoryDto.setPicture("https://www.la-viande.fr/sites/default/files/inline-images/club-sandwich.jpg");
            productService.saveCategory(categoryDto);
            CategoryDto categoryDto2 = new CategoryDto();
            categoryDto2.setName("Burgers");
            categoryDto2.setPicture("https://www.foodandwine.com/thmb/iPEKKufR4Ukrbcbjn-5rBrpFNyU=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/hatch-chile-smash-burgers-FT-seo-RECIPE0719_0-183c980af99541528d6cfa7f40ca2c21.jpg");
            productService.saveCategory(categoryDto2);
            CategoryDto categoryDto3 = new CategoryDto();
            categoryDto3.setName("Pizza");
            categoryDto3.setPicture("https://res.cloudinary.com/hv9ssmzrz/image/fetch/c_fill,f_auto,h_387,q_auto,w_650/https://s3-eu-west-1.amazonaws.com/images-ca-1-0-1-eu/tag_photos/original/889/pizza_flickr_4932057475_2a9ce50750_b.jpg");
            productService.saveCategory(categoryDto3);
            CategoryDto categoryDto4 = new CategoryDto();
            categoryDto4.setName("Plat");
            categoryDto4.setPicture("https://gourmandiseassia.fr/wp-content/uploads/2019/02/20200204_230914.jpg");
            productService.saveCategory(categoryDto4);
            CategoryDto categoryDto5 = new CategoryDto();
            categoryDto5.setName("Boissons");
            categoryDto5.setPicture("http://santejeunes.ma/wp-content/uploads/2018/02/Boisson-gazeuse_640.jpg");
            productService.saveCategory(categoryDto5);
            String[] pictures = {"https://img.cuisineaz.com/660x660/2018/04/26/i139890-.webp",
                    "https://img.cuisineaz.com/660x660/2016/04/03/i113488-quiche-lorraine-a-la-vache-qui-rit-et-pointe-d-epinards.webp",
                    "https://img.cuisineaz.com/660x660/2018/12/30/i145407-ramen-au-poulet.webp",
                    "https://img.cuisineaz.com/660x660/2015/04/21/i106372-colombo-de-poulet.webp",
                    "https://img.cuisineaz.com/660x660/2015/07/07/i106502-tempura-de-crevettes.webp"
            };
            List<CategoryDto> categoryDtos = productService.getAllCategories();
            for (CategoryDto c : categoryDtos) {
                for(int i = 0 ; i < 20 ; i++){
                    String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
                    String randomString = "";
                    int lenght = 17;
                    Random rand = new Random();
                    char[] des = new char[lenght];
                    for (int j = 0; j < lenght; j++) {
                        des[j] = characters.charAt(rand.nextInt(characters.length()));
                    }
                    for (int k = 0; k < des.length; k++) {
                        randomString += des[k];
                    }
                    double qte =  Math.random()*100;
                    ProductDto productDto = new ProductDto();
                    productDto.setDesignation(randomString);
                    productDto.setQuantity((int) qte);
                    productDto.setType(ProductType.PLAT);
                    productDto.setCategoryDto(c);
                    productDto.setDescription("");
                    productDto.setCostPrice(BigDecimal.valueOf(Math.random()*100));
                    productDto.setPrice(BigDecimal.valueOf(Math.random()*1000));
                    int index = rand.nextInt(pictures.length);
                    productDto.setPicture(pictures[index]);
                    productService.saveProduct(productDto);
                }

            }


            roomService.affectWaiterToTable("waiter1", "table 1");
            roomService.affectWaiterToTable("waiter1", "table 2");





            List<CategoryDto> categoryDtosd = productService.getAllCategories();
            for (CategoryDto c : categoryDtosd) {
                System.out.println(c.getName() + " " + c.getPicture());
            }

            List<ProductDto> productDtos = productService.getAllProducts();
            for (ProductDto p : productDtos) {
                OptionDto optionDto = new OptionDto();
                optionDto.setName("sans fromage");
                optionDto.setProductDto(p);
                productService.saveOption(optionDto);

                OptionDto optionDto2 = new OptionDto();
                optionDto2.setName("sans sel");
                optionDto2.setProductDto(p);
                productService.saveOption(optionDto2);

                OptionDto optionDto3 = new OptionDto();
                optionDto3.setName("sans salade");
                optionDto3.setProductDto(p);
                productService.saveOption(optionDto3);
            }

            AppUser appUser= new AppUser();
            appUser.setId(1l);
            appUser.setUsername("waiter1");
            List<AppTable> appTables = roomService.getUsersTable(appUser);
            for(AppTable at : appTables){
                System.out.println(at.getTableNumber());
            }

            Category category = new Category();
            category.setCategoryId(1l);
            category.setName("Sandwich");
            List<ProductDto> productDtos1 = productService.getProductByCategory(category);
            for(ProductDto p : productDtos1){
                System.out.println(p.getDesignation());
            }
        };
    }
}
