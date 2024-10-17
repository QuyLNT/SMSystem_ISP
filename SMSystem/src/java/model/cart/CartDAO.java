/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.cart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import model.product.ProductDAO;
import model.product.ProductDTO;
import utils.DBUtils;

/**
 *
 * @author LENOVO
 */
public class CartDAO {

    private static final String DELETE_ITEM = "DELETE FROM cart_items WHERE cartItemId = ?";
    private static final String INSERT_CART = "INSERT INTO carts (customerId) VALUES (?)";
    private static final String INSERT_CART_ITEM = "INSERT INTO cart_items (cartId, productId, quantity, size) VALUES (?, ?, ?, ?)";
    private static final String GET_CART_ID = "SELECT cartId FROM carts WHERE customerId = ?";
    private static final String GET_CART_BY_USER_ID = "SELECT cartItemId, cartId, productId, quantity FROM cart_items WHERE cartId = ?";
    private static final String GET_CART_ITEMS = "SELECT cartItemId, productId, quantity, size FROM cart_items WHERE cartId = ?";
    private static final String UPDATE_CART_ITEM_QUANTITY = "UPDATE cart_items SET quantity = ? WHERE cartId = ? AND productId = ? AND size = ?";
    private static final String EXISTS_CART_ITEMS = "SELECT cartItemId FROM cart_items WHERE cartId = ? AND productId = ? AND size = ?";
    private static final String UPDATE_QUANTITY = "UPDATE cart_items SET quantity = ? WHERE cartItemId = ? ";
    private static final String UPDATE_SIZE = "UPDATE cart_items SET size = ? WHERE cartItemId = ?";

    public boolean deleteCartItem(int cartItemId) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        boolean result = false;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE_ITEM);
                ptm.setInt(1, cartItemId);
                result = ptm.executeUpdate() > 0;

            }
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
            return result;
        }
    }

    public boolean createCart(CartDTO cart) throws SQLException, NamingException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean isCreated = false;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(INSERT_CART);
                ps.setInt(1, cart.getCustomerId());

                int rows = ps.executeUpdate();
                if (rows > 0) {
                    isCreated = true;
                }
            }
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return isCreated;
    }

    public boolean addCartItem(int cartId, int productId, int quantity, Float size) throws ClassNotFoundException, SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(INSERT_CART_ITEM);
                ptm.setInt(1, cartId);
                ptm.setInt(2, productId);
                ptm.setInt(3, quantity);
                ptm.setFloat(4, size);
                int rowsAffected = ptm.executeUpdate();
                check = rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public CartDTO getCartByUserId(int userId) throws SQLException, NamingException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        CartDTO cart = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                // Lấy cartId từ bảng carts
                ps = conn.prepareStatement(GET_CART_ID);
                ps.setInt(1, userId);
                rs = ps.executeQuery();
                if (rs.next()) {
                    int cartId = rs.getInt("cartId");

                    // Lấy các sản phẩm từ bảng cart_items
                    ps = conn.prepareStatement(GET_CART_ITEMS);
                    ps.setInt(1, cartId);
                    rs = ps.executeQuery();

                    List<CartItems> cartItemsList = new ArrayList<>();
                    while (rs.next()) {
                        int productId = rs.getInt("productId");
                        int cartItemId = rs.getInt("cartItemId");
                        int quantity = rs.getInt("quantity");
                        float size = rs.getFloat("size");

                        // Khởi tạo ProductDTO theo productId nếu cần
                        ProductDTO product = new ProductDAO().getProductById(productId);

                        CartItems item = new CartItems();
                        item.setCartItemId(cartItemId);
                        item.setProduct(product);
                        item.setQuantity(quantity);
                        item.setSize(size);
                        cartItemsList.add(item);
                    }

                    // Khởi tạo CartDTO và set thông tin
                    cart = new CartDTO();
                    cart.setCartId(cartId);
                    cart.setCustomerId(userId);
                    cart.setCartItemsList((ArrayList<CartItems>) cartItemsList);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return cart;
    }

    public int createCartIfNotExists(int customerId) throws SQLException, ClassNotFoundException {
        int cartId = -1;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_CART_ID);
                ptm.setInt(1, customerId);
                rs = ptm.executeQuery();

                if (rs.next()) {
                    cartId = rs.getInt("cartId");
                } else {
                    ptm = conn.prepareStatement(INSERT_CART, PreparedStatement.RETURN_GENERATED_KEYS);
                    ptm.setInt(1, customerId);
                    ptm.executeUpdate();
                    rs = ptm.getGeneratedKeys();
                    if (rs.next()) {
                        cartId = rs.getInt(1);
                    }
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return cartId;
    }

    public List<CartItems> getCartItemsByCartId(int cartId) throws SQLException, ClassNotFoundException {
        List<CartItems> cartItemsList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_CART_ITEMS);
                ptm.setInt(1, cartId);
                rs = ptm.executeQuery();

                while (rs.next()) {
                    int productId = rs.getInt("productId");
                    int quantity = rs.getInt("quantity");
                    float size = rs.getFloat("size");
                    ProductDTO product = getProductById(productId);
                    CartItems cartItem = new CartItems(product, rs.getInt("cartItemId"), cartId, productId, quantity, size);
                    cartItemsList.add(cartItem);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return cartItemsList;
    }

    private ProductDTO getProductById(int productId) throws SQLException, ClassNotFoundException {
        ProductDAO productDAO = new ProductDAO();
        return productDAO.getProductById(productId);
    }

    public boolean updateCartItemQuantity(int cartId, int productId, float size, int quantity) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ptm = null;
        boolean check = false;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_CART_ITEM_QUANTITY);
                ptm.setInt(1, quantity);
                ptm.setInt(2, cartId);
                ptm.setInt(3, productId);
                ptm.setFloat(4, size);
                check = ptm.executeUpdate() > 0;
            }
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean isCartItemExists(int cartId, int productId, float size) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(EXISTS_CART_ITEMS);
                ps.setInt(1, cartId);
                ps.setInt(2, productId);
                ps.setFloat(3, size);
                rs = ps.executeQuery();
                return rs.next();
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return false;
    }

    public boolean editQuantity(int cartItemId, int newQuantity) throws SQLException, ClassNotFoundException {
        boolean rowUpdated = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBUtils.getConnection();
            if (connection != null) {
                preparedStatement = connection.prepareStatement(UPDATE_QUANTITY);
                preparedStatement.setInt(1, newQuantity);
                preparedStatement.setInt(2, cartItemId);

                rowUpdated = preparedStatement.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return rowUpdated;
    }

    public boolean updateSize(int cartItemId, String newSize) throws SQLException, ClassNotFoundException {
        boolean rowUpdated = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBUtils.getConnection();
            if (connection != null) {
                preparedStatement = connection.prepareStatement(UPDATE_SIZE);
                preparedStatement.setString(1, newSize);
                preparedStatement.setInt(2, cartItemId);

                rowUpdated = preparedStatement.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return rowUpdated;
    }
}
