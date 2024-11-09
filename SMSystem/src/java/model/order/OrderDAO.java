package model.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
                    UserDTO customer = userDao.getUserByName(customerId);

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

    public boolean updateOrderStatus(String status, int orderId) throws SQLException, ClassNotFoundException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(UPDATE_ORDER_STATUS);
                ps.setString(1, status);
                ps.setInt(2, orderId);
                check = ps.executeUpdate() > 0;
            }

        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    private static final String INSERT_ORDER = "INSERT INTO orders (customerId, street, district, city, discountId, paymentMethodId, shippingMethodId) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?)";

    public int createOrder(int userId, String street, String district, String city, Integer discount, int payMethod, int shipMethod) throws SQLException, ClassNotFoundException {
        int orderId = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet generatedKeys = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(INSERT_ORDER, PreparedStatement.RETURN_GENERATED_KEYS);
                ptm.setInt(1, userId);
                ptm.setString(2, street);
                ptm.setString(3, district);
                ptm.setString(4, city);
                if (discount == null) {
                    ptm.setNull(5, java.sql.Types.INTEGER);
                } else {
                    ptm.setInt(5, discount);
                }
                ptm.setInt(6, payMethod);
                ptm.setInt(7, shipMethod);

                int rowsInserted = ptm.executeUpdate();

                if (rowsInserted > 0) {
                    generatedKeys = ptm.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        orderId = generatedKeys.getInt(1);
                    }
                }
            }
        } finally {
            if (generatedKeys != null) {
                generatedKeys.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return orderId;
    }

    public List<OrderDTO> getAllOrders() throws SQLException, ClassNotFoundException {
        List<OrderDTO> orders = new ArrayList<>();
        String sql = "SELECT o.orderId, o.street, o.district, o.city, o.createdAt, o.orderStatus, "
                + "u.userId, u.fullName, d.discountCode, p.paymentName, s.methodName "
                + "FROM orders o "
                + "LEFT JOIN users u ON o.customerId = u.userId "
                + "LEFT JOIN discountCodes d ON o.discountId = d.discountId "
                + "LEFT JOIN paymentMethods p ON o.paymentMethodId = p.paymentMethodId "
                + "LEFT JOIN shippingMethods s ON o.shippingMethodId = s.shippingMethodId";

        try (Connection conn = DBUtils.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                OrderDTO order = new OrderDTO();
                UserDTO customer = new UserDTO();

                customer.setUserId(rs.getInt("userId"));
                customer.setFullName(rs.getString("fullName"));
                order.setCustomer(customer);

                order.setOrderId(rs.getInt("orderId"));
                order.setStreet(rs.getString("street"));
                order.setDistrict(rs.getString("district"));
                order.setCity(rs.getString("city"));
                order.setCreatedAt(rs.getTimestamp("createdAt"));
                order.setOrderStatus(rs.getString("orderStatus"));
                order.setDiscountCode(rs.getString("discountCode"));
                order.setPaymentMethod(rs.getString("paymentName"));
                order.setShippingMethod(rs.getString("methodName"));

                orders.add(order);
            }
        }
        return orders;
    }

    public Map<Integer, Integer> getShipperMap() throws SQLException, ClassNotFoundException {
        Map<Integer, Integer> shipperMap = new HashMap<>();
        String sql = "SELECT orderId, shipperId FROM shipments";

        try (Connection conn = DBUtils.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int orderId = rs.getInt("orderId");
                int shipperId = rs.getInt("shipperId");
                shipperMap.put(orderId, shipperId);
            }
        }
        return shipperMap;
    }

    public boolean assignShipperToOrder(int orderId, int shipperId, String ship) throws SQLException, ClassNotFoundException {
        boolean isAssigned = false;
        String checkSql = "SELECT COUNT(*) FROM shipments WHERE orderId = ?";
        String insertSql = "INSERT INTO shipments (orderId, shipperId, shippingMethodId, shipmentStatus)\n"
                + "SELECT ?, ?, sm.shippingMethodId, 'Order assigned to shipper'\n"
                + "FROM shippingMethods sm\n"
                + "WHERE sm.methodName = ?";
        String updateSql = "UPDATE shipments SET shipperId = ?, shipmentStatus = 'Assigned' WHERE orderId = ?";

        try (Connection conn = DBUtils.getConnection();
                PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {

            checkStmt.setInt(1, orderId);
            ResultSet rs = checkStmt.executeQuery();
            rs.next();
            int count = rs.getInt(1);

            if (count > 0) {
                try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                    updateStmt.setInt(1, shipperId);
                    updateStmt.setInt(2, orderId);
                    isAssigned = updateStmt.executeUpdate() > 0;
                }
            } else {
                try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                    insertStmt.setInt(1, orderId);
                    insertStmt.setInt(2, shipperId);
                    insertStmt.setString(3, ship);
                    isAssigned = insertStmt.executeUpdate() > 0;
                }
            }
        }
        return isAssigned;
    }

    public List<OrderDTO> filterOrder(String dateFilter, String statusFilter) throws ClassNotFoundException, SQLException {
        List<OrderDTO> orders = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        StringBuilder query = new StringBuilder("SELECT o.orderId, o.customerId, o.street, o.district, o.city, dC.discountCode, sM.methodName, pM.paymentName, o.createdAt, o.orderStatus "
                + "FROM orders o "
                + "JOIN paymentMethods pM ON o.paymentMethodId = pM.paymentMethodId "
                + "JOIN shippingMethods sM ON o.shippingMethodId = sM.shippingMethodId "
                + "LEFT JOIN discountCodes dC ON o.discountId = dC.discountId"
                + " WHERE 1=1 ");

        if (dateFilter != null && !dateFilter.isEmpty()) {
            switch (dateFilter) {
                case "Today":
                    query.append("AND o.createdAt >= CAST(GETDATE() AS DATE) ");
                    break;
                case "This Week":
                    query.append("AND o.createdAt >= DATEADD(DAY, -(DATEPART(WEEKDAY, GETDATE()) - 1), CAST(GETDATE() AS DATE)) ");
                    break;
                case "This Month":
                    query.append("AND o.createdAt >= DATEADD(DAY, -(DAY(GETDATE()) - 1), CAST(GETDATE() AS DATE)) ");
                    break;
                default:
                    break;
            }
        }

        if (statusFilter != null && !statusFilter.isEmpty()) {
            query.append("AND orderStatus = ? ");
        }

        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(query.toString());

            int paramIndex = 1;
            if (statusFilter != null && !statusFilter.isEmpty()) {
                ps.setString(paramIndex++, statusFilter);
            }

            rs = ps.executeQuery();
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

                UserDAO userDao = new UserDAO();
                UserDTO customer = userDao.getUserByName(customerId);

                OrderDTO order = new OrderDTO(orderId, customer, street, district, city, discountCode, paymentMethod, shippingMethod, createdAt, orderStatus);
                orders.add(order);
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
        return orders;
    }

    private static final String GET_ORDER_TOTAL = "SELECT \n"
            + "    SUM(p.price * (1 - p.sale) * od.quantity) \n"
            + "    - COALESCE(MAX(d.discountAmount), 0) AS totalAmount\n"
            + "FROM \n"
            + "    orderDetails od\n"
            + "JOIN \n"
            + "    products p ON od.productId = p.productId\n"
            + "JOIN \n"
            + "    orders o ON od.orderId = o.orderId\n"
            + "LEFT JOIN \n"
            + "    discountCodes d ON d.discountId = o.discountId\n"
            + "WHERE \n"
            + "    od.orderId = ?\n"
            + "GROUP BY \n"
            + "    od.orderId;";

    public float getOrderTotal(int orderId) throws ClassNotFoundException, SQLException {
        float total = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ORDER_TOTAL);
                ptm.setInt(1, orderId);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    total = rs.getFloat("totalAmount");
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
        return total;
    }

    public Map<String, Integer> getOrderStatusCount(int customerId) throws ClassNotFoundException, SQLException {
        Map<String, Integer> orderStatusCount = new HashMap<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        String query = "SELECT \n"
                + "    statuses.orderStatus,\n"
                + "    COUNT(o.orderId) AS orderCount\n"
                + "FROM \n"
                + "    (SELECT 'Waiting For Accept' AS orderStatus\n"
                + "	UNION ALL\n"
                + "     SELECT 'Waiting For Pickup'\n"
                + "     UNION ALL\n"
                + "     SELECT 'Delivering'\n"
                + "     UNION ALL\n"
                + "     SELECT 'Completed'\n"
                + "     UNION ALL\n"
                + "     SELECT 'Not Completed') AS statuses\n"
                + "LEFT JOIN \n"
                + "    orders o ON statuses.orderStatus = o.orderStatus AND o.customerId = ?\n"
                + "GROUP BY \n"
                + "    statuses.orderStatus;";

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(query);
                ptm.setInt(1, customerId);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String status = rs.getString("orderStatus");
                    int count = rs.getInt("orderCount");
                    orderStatusCount.put(status, count);
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
        return orderStatusCount;
    }

    public static final String GET_ORDER_BY_STATUS
            = "SELECT o.orderId, o.customerId, o.street, o.district, o.city, \n"
            + "       dC.discountCode, sM.methodName, pM.paymentName, \n"
            + "       o.createdAt, o.orderStatus \n"
            + "FROM orders o \n"
            + "JOIN paymentMethods pM ON o.paymentMethodId = pM.paymentMethodId \n"
            + "JOIN shippingMethods sM ON o.shippingMethodId = sM.shippingMethodId \n"
            + "LEFT JOIN discountCodes dC ON o.discountId = dC.discountId \n"
            + "WHERE o.customerId = ? AND o.orderStatus LIKE ?";

    public List<OrderDTO> getOrderByStatus(int userId, String status) throws ClassNotFoundException, SQLException {
        List<OrderDTO> orderList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ORDER_BY_STATUS);
                ptm.setInt(1, userId);
                ptm.setString(2, status);
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

    public boolean returnItemsToStock(int orderId) throws SQLException, ClassNotFoundException {
        boolean success = false;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            conn.setAutoCommit(false);

            String sql = "SELECT productId, size, quantity FROM orderDetails WHERE orderId = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, orderId);
            rs = ps.executeQuery();

            while (rs.next()) {
                int productId = rs.getInt("productId");
                float size = rs.getFloat("size");
                int quantity = rs.getInt("quantity");

                String updateStock = "UPDATE productVariants SET stock = stock + ? WHERE productId = ? AND size = ?";
                try (PreparedStatement ps2 = conn.prepareStatement(updateStock)) {
                    ps2.setInt(1, quantity);
                    ps2.setInt(2, productId);
                    ps2.setFloat(3, size);
                    int rowsUpdated = ps2.executeUpdate();

                    if (rowsUpdated == 0) {
                        conn.rollback();
                        return false;
                    }
                }
            }

            conn.commit();
            success = true;

        } catch (SQLException e) {
            if (conn != null) {
                conn.rollback();
            }
            throw e;
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
        return success;
    }

}
