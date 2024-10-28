package model.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.user.UserDTO;
import model.user.UserDAO;
import utils.DBUtils;

public class OrderDAO {

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
