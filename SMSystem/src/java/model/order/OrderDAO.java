package model.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.user.UserDAO;
import model.user.UserDTO;
import utils.DBUtils;

/**
 *
 * @author LENOVO
 */
public class OrderDAO {

    private static final String GET_ALL = "SELECT o.orderId, u.fullName, u.email, o.street, o.district, o.city, "
            + "d.discountCode, p.paymentName, s.methodName AS shippingMethodName, o.createdAt, o.orderStatus "
            + "FROM orders o "
            + "LEFT JOIN discountCodes d ON o.discountId = d.discountId "
            + "LEFT JOIN paymentMethods p ON o.paymentMethodId = p.paymentMethodId "
            + "LEFT JOIN shippingMethods s ON o.shippingMethodId = s.shippingMethodId "
            + "LEFT JOIN users u ON o.customerId = u.userId "
            + "WHERE o.customerId = ?;";

    private static final String GET_ORDER_BY_ID = "SELECT o.orderId, u.fullName, u.email, o.street, o.district, o.city, "
            + "d.discountCode, p.paymentName, s.methodName AS shippingMethodName, o.createdAt, o.orderStatus "
            + "FROM orders o "
            + "LEFT JOIN discountCodes d ON o.discountId = d.discountId "
            + "LEFT JOIN paymentMethods p ON o.paymentMethodId = p.paymentMethodId "
            + "LEFT JOIN shippingMethods s ON o.shippingMethodId = s.shippingMethodId "
            + "LEFT JOIN users u ON o.customerId = u.userId "
            + "WHERE o.customerId = ? AND o.orderId = ?;";

    public List<OrderDTO> getAllOrder(UserDTO user) throws ClassNotFoundException, SQLException {
        List<OrderDTO> orderList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ALL);
                ptm.setInt(1, user.getUserId());
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int orderID = rs.getInt("orderId");
                    String customerName = rs.getString("fullName");
                    String customerEmail = rs.getString("email");
                    String street = rs.getString("street");
                    String district = rs.getString("district");
                    String city = rs.getString("city");
                    String discountCode = rs.getString("discountCode");
                    String paymentName = rs.getString("paymentName");
                    String shippingMethodName = rs.getString("shippingMethodName");
                    Date createdAt = rs.getDate("createdAt");
                    String orderStatus = rs.getString("orderStatus");
                    
                    UserDTO customer = new UserDTO();
                    customer.setFullName(customerName);
                    customer.setEmail(customerEmail);

                    OrderDTO order = new OrderDTO(orderID, customer, street, district, city, discountCode, paymentName, shippingMethodName, createdAt, orderStatus);
                    orderList.add(order);
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
        return orderList;
    }

    public OrderDTO getOrderById(UserDTO user, int orderId) throws ClassNotFoundException, SQLException {
        OrderDTO order = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ORDER_BY_ID);
                ptm.setInt(1, user.getUserId());
                ptm.setInt(2, orderId);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    int orderID = rs.getInt("orderId");
                    String customerName = rs.getString("fullName");
                    String customerEmail = rs.getString("email");
                    String street = rs.getString("street");
                    String district = rs.getString("district");
                    String city = rs.getString("city");
                    String discountCode = rs.getString("discountCode");
                    String paymentName = rs.getString("paymentName");
                    String shippingMethodName = rs.getString("shippingMethodName");
                    Date createdAt = rs.getDate("createdAt");
                    String orderStatus = rs.getString("orderStatus");

                    UserDTO customer = new UserDTO();
                    customer.setFullName(customerName);
                    customer.setEmail(customerEmail);

                    order = new OrderDTO(orderID, customer, street, district, city, discountCode, paymentName, shippingMethodName, createdAt, orderStatus);
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
        return order;
    }

    public List<OrderDTO> getAllOrdersByCustomerId(int customerId) throws ClassNotFoundException, SQLException {
        List<OrderDTO> orderList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ALL);
                ptm.setInt(1, customerId);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int orderID = rs.getInt("orderId");
                    String customerName = rs.getString("fullName");
                    String customerEmail = rs.getString("email");
                    String street = rs.getString("street");
                    String district = rs.getString("district");
                    String city = rs.getString("city");
                    String discountCode = rs.getString("discountCode");
                    String paymentName = rs.getString("paymentName");
                    String shippingMethodName = rs.getString("shippingMethodName");
                    Date createdAt = rs.getDate("createdAt");
                    String orderStatus = rs.getString("orderStatus");

                    UserDTO customer = new UserDTO();
                    customer.setFullName(customerName);
                    customer.setEmail(customerEmail);

                    OrderDTO order = new OrderDTO(orderID, customer, street, district, city, discountCode, paymentName, shippingMethodName, createdAt, orderStatus);
                    orderList.add(order);
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
        return orderList;
    }

    // Câu truy vấn đã được cập nhật để lấy thêm thông tin từ các bảng liên quan
    public static final String UPDATE_ORDER_STATUS = "UPDATE orders SET orderStatus = ? WHERE orderId = ?";

    public static final String SHOW_ORDER_LIST = "SELECT o.orderId, o.customerId, o.street, o.district, o.city, dC.discountCode, sM.methodName, pM.paymentName, o.createdAt, o.orderStatus "
            + "FROM orders o "
            + "JOIN paymentMethods pM ON o.paymentMethodId = pM.paymentMethodId "
            + "JOIN shippingMethods sM ON o.shippingMethodId = sM.shippingMethodId "
            + "LEFT JOIN discountCodes dC ON o.discountId = dC.discountId";
    public static final String SHOW_ORDER
            = "SELECT o.orderId, o.customerId, o.street, o.district, o.city, dC.discountCode, sM.methodName, pM.paymentName, o.createdAt, o.orderStatus "
            + "FROM orders o "
            + "JOIN paymentMethods pM ON o.paymentMethodId = pM.paymentMethodId "
            + "JOIN shippingMethods sM ON o.shippingMethodId = sM.shippingMethodId "
            + "LEFT JOIN discountCodes dC ON o.discountId = dC.discountId "
            + "WHERE o.orderId = ?";
    public List<OrderDTO> getAllOrder() throws ClassNotFoundException, SQLException {
        List<OrderDTO> listOrder = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SHOW_ORDER_LIST);
                rs = ptm.executeQuery();

                while (rs.next()) {
                    int orderId = rs.getInt("orderId");
                    int customerId = rs.getInt("customerId");
                    String street = rs.getString("street");
                    String district = rs.getString("district");
                    String city = rs.getString("city");
                    String discountCode = rs.getString("discountCode");
                    String shippingMethod = rs.getString("methodName");
                    String paymentMethod = rs.getString("paymentName");
                    Date createdAt = rs.getDate("createdAt");
                    String orderStatus = rs.getString("orderStatus");


                    // Lấy thông tin khách hàng từ UserDAO
                    UserDAO userDao = new UserDAO();
                    UserDTO customer = userDao.getUserById(customerId);

                    // Tạo đối tượng OrderDTO với thông tin khách hàng là UserDTO
                    OrderDTO order = new OrderDTO(orderId, customer, street, district, city, discountCode, paymentMethod, shippingMethod, createdAt, orderStatus);
                    listOrder.add(order);
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
        return listOrder;
    }

    
    public OrderDTO getOrderById(int orderId) throws ClassNotFoundException, SQLException {
    OrderDTO order = new OrderDTO();
    Connection conn = null;
    PreparedStatement ptm = null;
    ResultSet rs = null;
    try {
        conn = DBUtils.getConnection();
        if (conn != null) {
            ptm = conn.prepareStatement(SHOW_ORDER);
            ptm.setInt(1, orderId); 
            rs = ptm.executeQuery();

            if (rs.next()) {
                    int customerId = rs.getInt("customerId");
                    String street = rs.getString("street");
                    String district = rs.getString("district");
                    String city = rs.getString("city");
                    String discountCode = rs.getString("discountCode");
                    String shippingMethod = rs.getString("methodName");
                    String paymentMethod = rs.getString("paymentName");
                    Date createdAt = rs.getDate("createdAt");
                    String orderStatus = rs.getString("orderStatus");

                    // Lấy thông tin khách hàng từ UserDAO
                    UserDAO userDao = new UserDAO();
                    UserDTO customer = userDao.getUserById(customerId);

                    // Tạo đối tượng OrderDTO với thông tin khách hàng là UserDTO
                    order = new OrderDTO(orderId, customer, street, district, city, discountCode, paymentMethod, shippingMethod, createdAt, orderStatus);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }finally {
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
    return order;
}
  
}
