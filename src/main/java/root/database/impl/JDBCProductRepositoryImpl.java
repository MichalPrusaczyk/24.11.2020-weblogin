package root.database.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import root.database.IProductRepository;
import root.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCProductRepositoryImpl implements IProductRepository {

    @Autowired
    Connection connection;

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        try {
            String SQL = "SELECT * FROM tproduct";
            PreparedStatement preparedStatement = this.connection.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                products.add(this.mapResultSetToProduct(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    @Override
    public List<Product> getProductsByCategory(Product.Category category) {
        List<Product> products = new ArrayList<>();
        try {
            String SQL = "SELECT * FROM tproduct WHERE category=?";
            PreparedStatement preparedStatement = this.connection.prepareStatement(SQL);
            preparedStatement.setString(1, category.toString());

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                products.add(this.mapResultSetToProduct(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public Product getProductByEAN(String ean) {
        try {
            String SQL = "SELECT * FROM tproduct WHERE ean=?";
            PreparedStatement preparedStatement = this.connection.prepareStatement(SQL);
            preparedStatement.setString(1, ean);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                return this.mapResultSetToProduct(resultSet);
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void addProduct(Product product) {
        try {
            String SQL = "INSERT INTO tproduct (category, name, shape, length, ean, price) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = this.connection.prepareStatement(SQL);
            preparedStatement.setString(1, product.getCategory().toString());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setString(3, product.getShape());
            preparedStatement.setDouble(4, product.getLength());
            preparedStatement.setString(5, product.getEan());
            preparedStatement.setDouble(6, product.getPrice());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateProduct(Product product) {
        try {
            String SQL = "UPDATE tproduct SET name=?, length=?, price=?, category=? WHERE isbn=?";
            PreparedStatement preparedStatement = this.connection.prepareStatement(SQL);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getLength());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setString(4, product.getCategory().toString());
            preparedStatement.setString(5, product.getEan());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Product mapResultSetToProduct(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getInt("id"));
        product.setName(resultSet.getString("name"));
        product.setShape(resultSet.getString("shape"));
        product.setLength(resultSet.getInt("length"));
        product.setEan(resultSet.getString("ean"));
        product.setPrice(resultSet.getDouble("price"));
        product.setCategory(Product.Category.valueOf(resultSet.getString("category")));

        return product;
    }
}