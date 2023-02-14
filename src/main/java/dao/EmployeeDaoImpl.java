package dao;

import jdbc.ConnectionManager;
import model.City;
import model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {
    private static String FIND_ALL = "SELECT * FROM employee";
    private static String INSERT = "INSERT INTO employee (id, first_name, last_name, gender, age, city_id) VALUES ((?), (?), (?), (?), (?), (?))";
    private static String INNER = "SELECT * FROM employee INNER JOIN ";

    @Override
    public void add(Employee employee) throws SQLException {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     INSERT)) {

            statement.setLong(1, employee.getId());
            statement.setString(2, employee.getFirstName());
            statement.setString(3, employee.getLastName());
            statement.setString(4, employee.getGender());
            statement.setInt(5, employee.getAge());
            statement.setLong(6, employee.getCityId().getCityId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Employee readById(long id) {
        return null;
    }


//    @Override
//    public Employee readById(long id) {
//        Employee employee=new Employee();
//        try (Connection connection = ConnectionManager.getConnection();
//                PreparedStatement statement = connection.prepareStatement(
//                "SELECT * FROM book INNER JOIN author ON book.author_id=author.author_id AND book_id=(?)")) {
//
//            // Подставляем значение вместо wildcard
//            statement.setInt(1, id);
//
//            // Делаем запрос к базе и результат кладем в ResultSet
//            ResultSet resultSet = statement.executeQuery();
//
//            // Методом next проверяем есть ли следующий элемент в resultSet
//            // и одновременно переходим к нему, если таковой есть
//            while(resultSet.next()) {
//
//                // С помощью методов getInt и getString получаем данные из resultSet
//                // и присваиваем их полим объекта
//                book.setId(Integer.parseInt(resultSet.getString("book_id")));
//                book.setTitle(resultSet.getString("title"));
//                book.setAuthor(new Author(resultSet.getInt("author_id"),
//                        resultSet.getString("name_author")));
//                book.setAmount(Integer.parseInt(resultSet.getString("amount")));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return book;
//    }

    @Override
    public List<Employee> findAll() {
        List<Employee> employees = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Employee employee = new Employee(
                        resultSet.getLong("id"),
                resultSet.getString("firstName"),
                resultSet.getString("lastName"),
                resultSet.getString("gender"),
                resultSet.getInt("age"),
                new City(resultSet.getLong("cityId"))
                );
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    @Override
    public void updateEmployeeById(long id, Employee employee) {

    }

    @Override
    public void deleteById(long id) {

    }

}
