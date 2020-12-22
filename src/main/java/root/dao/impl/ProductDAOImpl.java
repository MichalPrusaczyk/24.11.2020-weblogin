package root.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import root.dao.IProductDAO;
import root.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements IProductDAO {

    @Autowired
    Connection connection;

    @Override
    public Product getProductByEAN(String ean) {
        try {
            String sql = "SELECT * FROM tproduct WHERE ean=?";
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, ean);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                return this.mapResultSetToProduct(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void updateProduct(Product product) {
        try {
            String sql = "UPDATE tproduct SET category=?, name=?, shape=?, length=?, ean=?, price=? WHERE id=?";
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, product.getCategory().toString());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setString(3, product.getShape());
            preparedStatement.setDouble(4, product.getLength());
            preparedStatement.setString(5, product.getEan());
            preparedStatement.setDouble(6, product.getPrice());
            preparedStatement.setInt(7, product.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void persistProduct(Product product) {
        try {
            String SQL = "INSERT INTO tproduct (category, name, shape, length, ean, price) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = this.connection.prepareStatement(SQL);
            preparedStatement.setString(1, product.getCategory().toString());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setString(3, product.getShape());
            preparedStatement.setDouble(4, product.getLength());
            preparedStatement.setString(5, product.getEan());
            preparedStatement.setDouble(6, product.getPrice());
            preparedStatement.setInt(7, product.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product getProductById(int id) {
        try {
            String sql = "SELECT * FROM tproduct WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                return this.mapResultSetToProduct(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Product> getProductsByCategory(Product.Category category) {
        List<Product> product = new ArrayList<>();
        try {
            String SQL = "SELECT * FROM tproduct WHERE category=?";
            PreparedStatement preparedStatement = this.connection.prepareStatement(SQL);
            preparedStatement.setString(1, category.toString());

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                product.add(this.mapResultSetToProduct(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

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