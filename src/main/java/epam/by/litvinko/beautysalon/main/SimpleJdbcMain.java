package epam.by.litvinko.beautysalon.main;

import epam.by.litvinko.beautysalon.connection.ConnectionCreator;
import epam.by.litvinko.beautysalon.entity.*;
import epam.by.litvinko.beautysalon.exception.DatabaseConnectionException;

import java.sql.*;
import java.time.LocalDate;
import java.util.Locale;


public class SimpleJdbcMain {
    public static void main(String[] args) {

        try (Connection connection = ConnectionCreator.createConnection();
             Statement statement = connection.createStatement()){
            //String sql = "SELECT client.id, client.user_id, role.role, users.username, users.password, users.email, users.first_name, users.last_name, " +
              //      "users.is_active, users.data_joined, users.photo, client.phone, client.date_of_birthday, client.is_regular FROM client JOIN users ON client.user_id = users.id JOIN role ON users.role_id = role.id" ;
            //String sql = "SELECT master.id, master.user_id, role.role, users.username, users.password, users.email, users.first_name, users.last_name, " +
            //         "users.is_active, users.data_joined, users.photo, master.description, position.position FROM master JOIN position ON master.position_id = position.id JOIN users ON master.user_id = users.id JOIN role ON users.role_id = role.id" ;
            //String sql = "SELECT salon_service.id, salon_service.category_id, salon_service.name, salon_service.description, " +
                   // "salon_service.price, salon_service.service_time, salon_service.available, salon_service.created, salon_service.updated, salon_service.image FROM salon_service WHERE salon_service.category_id = 1" ;

            String sql = "SELECT users.id, role.role, users.username, users.password, users.email, users.first_name, users.last_name, users.is_active, users.data_joined, users.photo FROM users JOIN role ON users.role_id = role.id WHERE users.email = 'client1@mail.ru'" ;

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                /*User.Builder builder = User.newBuilder();
                builder.setID(resultSet.getLong("id"))
                        .setRole(Role.valueOf(resultSet.getString("role").toUpperCase(Locale.ROOT)))
                        .setUserName(resultSet.getString("username"))
                        .setPassword(resultSet.getString("password"))
                        .setEmail(resultSet.getString("email"))
                        .setFirstName(resultSet.getString("first_name"))
                        .setLastName(resultSet.getString("last_name"))
                        .setIsActive(resultSet.getBoolean("is_active"))
                        .setDateJoined(LocalDate.parse(resultSet.getString("data_joined")))
                        .setPhoto(resultSet.getBytes("photo"));
                User user = builder.build();
                System.out.println(user);*/
                /*Client.Builder builder = Client.newBuilder();
                builder.setUserId(resultSet.getLong("user_id"))
                        .setPhone(resultSet.getString("phone"))
                        .setDateOfBirthday(LocalDate.parse(resultSet.getString("date_of_birthday")))
                        .setIsRegular(resultSet.getBoolean("is_regular"))
                        .setID(resultSet.getLong("id"))
                        .setRole(Role.valueOf(resultSet.getString("role").toUpperCase(Locale.ROOT)))
                        .setUserName(resultSet.getString("username"))
                        .setPassword(resultSet.getString("password"))
                        .setEmail(resultSet.getString("email"))
                        .setFirstName(resultSet.getString("first_name"))
                        .setLastName(resultSet.getString("last_name"))
                        .setIsActive(resultSet.getBoolean("is_active"))
                        .setDateJoined(LocalDate.parse(resultSet.getString("data_joined")))
                        .setPhoto(resultSet.getBytes("photo"));
                Client client = builder.build();
                System.out.println(client);*/
                /*Master.Builder builder = Master.newBuilder();
                builder.setUserId(resultSet.getLong("user_id"))
                        .setPosition(Position.valueOf(resultSet.getString("position").toUpperCase(Locale.ROOT)))
                        .setDescription(resultSet.getString("description"))
                        .setID(resultSet.getLong("id"))
                        .setRole(Role.valueOf(resultSet.getString("role").toUpperCase(Locale.ROOT)))
                        .setUserName(resultSet.getString("username"))
                        .setPassword(resultSet.getString("password"))
                        .setEmail(resultSet.getString("email"))
                        .setFirstName(resultSet.getString("first_name"))
                        .setLastName(resultSet.getString("last_name"))
                        .setIsActive(resultSet.getBoolean("is_active"))
                        .setDateJoined(LocalDate.parse(resultSet.getString("data_joined")))
                        .setPhoto(resultSet.getBytes("photo"));
                Master master = builder.build();
                System.out.println(master);*/
                /*ProvideService.Builder builder = ProvideService.newBuilder();
                builder.setId(resultSet.getLong("id"))
                        .setCategoryId(resultSet.getLong("category_id"))
                        .setName(resultSet.getString("name"))
                        .setDescription(resultSet.getString("description"))
                        .setPrice(resultSet.getBigDecimal("price"))
                        .setServiceTime(resultSet.getInt("service_time"))
                        .setAvailable(resultSet.getBoolean("available"))
                        .setCreated(LocalDate.parse(resultSet.getString("created")))
                        .setUpdated(LocalDate.parse(resultSet.getString("updated")))
                        .setImage(resultSet.getBytes("image"));
                ProvideService provideService = builder.build();
                System.out.println(provideService);*/
                User.Builder builder = User.newBuilder();
                builder.setID(resultSet.getLong("id"))
                        .setRole(Role.valueOf(resultSet.getString("role").toUpperCase(Locale.ROOT)))
                        .setUserName(resultSet.getString("username"))
                        .setPassword(resultSet.getString("password"))
                        .setEmail(resultSet.getString("email"))
                        .setFirstName(resultSet.getString("first_name"))
                        .setLastName(resultSet.getString("last_name"))
                        .setIsActive(resultSet.getBoolean("is_active"))
                        .setDateJoined(LocalDate.parse(resultSet.getString("data_joined")))
                        .setPhoto(resultSet.getBytes("photo"));
                User user = builder.build();
                System.out.println(user);

            }

        } catch (DatabaseConnectionException | SQLException e) {
            e.printStackTrace();
        }



    }
}
