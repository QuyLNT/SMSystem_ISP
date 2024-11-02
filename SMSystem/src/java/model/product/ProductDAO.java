/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

/**
 *
 * @author LENOVO
 */
public class ProductDAO {

    private static final String GET_PRODUCT = "SELECT productId,brandId,userObjectId,detail,hot,"
            + "                                      name,color,price,sale,warrantPeriod,productStatus \n"
            + "FROM products\n"
            + "WHERE productId=?";

    private static final String UPDATE_PRODUCT = "UPDATE products\n"
            + "SET brandId=?, userObjectId=?, detail=?, name=?, price=?, color=?, sale=?, warrantPeriod =?\n"
            + "WHERE ProductID=?";

    private static final String GET_ALL_PRODUCT = "SELECT productId,brandId,userObjectId,detail,hot,"
            + "                                      name,color,price,sale,warrantPeriod,productStatus \n"
            + "FROM products";

    private static final String ADD_PRODUCT = "INSERT INTO products (brandId, userObjectId, detail, hot, name, color, price, sale, warrantPeriod, productStatus) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_HOT = "UPDATE products\n"
            + "SET hot=?\n"
            + "WHERE productId=?";
    private static final String UPDATE_PRODUCT_STATUS = "UPDATE products\n"
            + "SET productStatus=?\n"
            + "WHERE productId=?";

    private static final String SEARCH_PRODUCT_BY_NAME = "SELECT productId,brandId,userObjectId,detail,hot,"
            + "                                      name,color,price,sale,warrantPeriod,productStatus \n"
            + "FROM products\n"
            + "WHERE name LIKE ?";

    private static final String GET_TOP_WOMEN_LIST = "SELECT TOP 5 * FROM products WHERE userObjectId = 2 and hot = 1 and productStatus = 1";
    private static final String GET_TOP_MEN_LIST = "SELECT TOP 5 * FROM products WHERE userObjectId = 1 and hot = 1 and productStatus = 1";
    private static final String GET_TOP_KID_LIST = "SELECT TOP 5 * FROM products WHERE userObjectId = 3 and hot = 1 and productStatus = 1";
    private static final String GET_RELATED_LIST = "SELECT TOP 4 *\n"
            + "FROM products\n"
            + "WHERE userObjectId = (SELECT userObjectId FROM products WHERE productId =?)\n"
            + "AND productStatus =1"
            + "ORDER BY sale";

    private static final String GET_SALE_PRODUCT = "SELECT *\n"
            + "FROM products\n"
            + "Where sale>0\n"
            + "ORder by sale";

    private static final String GET_PRODUCT_BY_CATE = "SELECT productId,brandId,userObjectId,detail,hot,name,color,price,sale,warrantPeriod,productStatus \n"
            + "FROM products\n"
            + "WHERE userObjectId = ? AND productStatus = 1";
    private static final String SEARCH_PRODUCT = "SELECT productId,brandId,userObjectId,detail,hot,"
            + "                                      name,color,price,sale,warrantPeriod,productStatus \n"
            + "FROM products\n"
            + "WHERE name LIKE ? AND productStatus = 1";

    public List<ProductDTO> getAllProduct() throws ClassNotFoundException, SQLException {
        List<ProductDTO> listProduct = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ALL_PRODUCT);
                rs = ptm.executeQuery();

                while (rs.next()) {
                    int productId = rs.getInt("productId");
                    int brandId = rs.getInt("brandId");
                    int userObjectId = rs.getInt("userObjectId");
                    String detail = rs.getString("detail");
                    boolean hot = rs.getBoolean("hot");
                    String name = rs.getString("name");
                    float price = rs.getFloat("price");
                    String color = rs.getString("color");
                    float sale = rs.getFloat("sale");
                    int warrantPeriod = rs.getInt("warrantPeriod");
                    boolean productStatus = rs.getBoolean("productStatus");

                    ProductDTO product = new ProductDTO(productId, brandId, userObjectId, detail, hot, name, color, price, sale, warrantPeriod, productStatus);
                    listProduct.add(product);

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

        return listProduct;
    }

    public ProductDTO getProductById(int productId) throws ClassNotFoundException, SQLException {
        ProductDTO product = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_PRODUCT);
                ptm.setInt(1, productId);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    int brandId = rs.getInt("brandId");
                    int userObjectId = rs.getInt("userObjectId");
                    String detail = rs.getString("detail");
                    boolean hot = rs.getBoolean("hot");
                    String name = rs.getString("name");
                    String color = rs.getString("color");
                    float price = rs.getFloat("price");
                    float sale = rs.getFloat("sale");
                    int warrantPeriod = rs.getInt("warrantPeriod");
                    boolean productStatus = rs.getBoolean("productStatus");

                    product = new ProductDTO(productId, brandId, userObjectId, detail, hot, name, color, price, sale, warrantPeriod, productStatus);
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

        return product;
    }

    public boolean updateProduct(ProductDTO product) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        boolean result = false;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_PRODUCT);
                ptm.setInt(1, product.getBrandId());
                ptm.setInt(2, product.getUserOjectId());
                ptm.setString(3, product.getDetail());
                ptm.setString(4, product.getName());
                ptm.setFloat(5, product.getPrice());
                ptm.setString(6, product.getColor());
                ptm.setFloat(7, product.getSale());
                ptm.setInt(8, product.getWarrantyPeriod());
                ptm.setInt(9, product.getProductId());
                result = ptm.executeUpdate() > 0;

            }
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return result;
    }

    public ProductDTO addProduct(ProductDTO product) throws SQLException, ClassNotFoundException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet generatedKeys = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(ADD_PRODUCT, PreparedStatement.RETURN_GENERATED_KEYS);
                ptm.setInt(1, product.getBrandId());
                ptm.setInt(2, product.getUserOjectId());
                ptm.setString(3, product.getDetail());
                ptm.setBoolean(4, product.isHot());
                ptm.setString(5, product.getName());
                ptm.setString(6, product.getColor());
                ptm.setFloat(7, product.getPrice());
                ptm.setFloat(8, product.getSale());
                ptm.setInt(9, product.getWarrantyPeriod());
                ptm.setBoolean(10, product.isProductStatus());

                int rowsInserted = ptm.executeUpdate();

                if (rowsInserted > 0) {
                    generatedKeys = ptm.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        int productId = generatedKeys.getInt(1);
                        product.setProductId(productId);
                        check = true;
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
        return product;
    }

    public boolean updateHot(int productId, boolean hot) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ptm = null;
        boolean result = false;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_HOT);
                ptm.setBoolean(1, hot);
                ptm.setInt(2, productId);
                result = ptm.executeUpdate() > 0;
            }
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return result;
    }

    public boolean updateProductStatus(int productId, boolean productStatus) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        boolean result = false;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_PRODUCT_STATUS);
                ptm.setBoolean(1, productStatus);
                ptm.setInt(2, productId);
                result = ptm.executeUpdate() > 0;
            }
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return result;
    }

    public List<ProductDTO> searchProductsByName(String searchTerm) throws ClassNotFoundException, SQLException {
        List<ProductDTO> products = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ps = con.prepareStatement(SEARCH_PRODUCT_BY_NAME);
                ps.setString(1, "%" + searchTerm + "%");
                rs = ps.executeQuery();

                while (rs.next()) {
                    int productId = rs.getInt("productId");
                    int brandId = rs.getInt("brandId");
                    int userObjectId = rs.getInt("userObjectId");
                    String detail = rs.getString("detail");
                    boolean hot = rs.getBoolean("hot");
                    String productName = rs.getString("name");
                    float price = rs.getFloat("price");
                    String color = rs.getString("color");
                    float sale = rs.getFloat("sale");
                    int warrantPeriod = rs.getInt("warrantPeriod");
                    boolean productStatus = rs.getBoolean("productStatus");

                    ProductDTO product = new ProductDTO(productId, brandId, userObjectId, detail, hot, productName, color, price, sale, warrantPeriod, productStatus);
                    products.add(product);
                }

            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return products;
    }

    private static final String DELETE_PRODUCT = "DELETE FROM products WHERE productId = ?";

    public boolean deleteProduct(int productId) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ptm = null;
        boolean result = false;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE_PRODUCT);
                ptm.setInt(1, productId);
                result = ptm.executeUpdate() > 0 ? true : false;
            }
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return result;
    }

    public List<ProductDTO> getTopMenList() throws ClassNotFoundException, SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        List<ProductDTO> list = new ArrayList<>();
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_TOP_MEN_LIST);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int productId = rs.getInt("productId");
                    int brandId = rs.getInt("brandId");
                    int userObjectID = rs.getInt("userObjectId");
                    String detail = rs.getString("detail");
                    boolean hot = rs.getBoolean("hot");
                    String name = rs.getString("name");
                    String color = rs.getString("color");
                    float price = rs.getFloat("price");
                    float sale = rs.getFloat("sale");
                    int warrantPeriod = rs.getInt("warrantPeriod");
                    boolean productStatus = rs.getBoolean("productStatus");
                    ProductDTO p = new ProductDTO(productId, brandId, userObjectID, detail, hot, name, color, price, sale, warrantPeriod, productStatus);
                    list.add(p);
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
        return list;
    }

    public List<ProductDTO> getTopWomenList() throws ClassNotFoundException, SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        List<ProductDTO> list = new ArrayList<>();
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_TOP_WOMEN_LIST);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int productId = rs.getInt("productId");
                    int brandId = rs.getInt("brandId");
                    int userObjectID = rs.getInt("userObjectId");
                    String detail = rs.getString("detail");
                    boolean hot = rs.getBoolean("hot");
                    String name = rs.getString("name");
                    String color = rs.getString("color");
                    float price = rs.getFloat("price");
                    float sale = rs.getFloat("sale");
                    int warrantPeriod = rs.getInt("warrantPeriod");
                    boolean productStatus = rs.getBoolean("productStatus");
                    ProductDTO pr = new ProductDTO(productId, brandId, userObjectID, detail, hot, name, color, price, sale, warrantPeriod, productStatus);
                    list.add(pr);
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
        return list;
    }

    public List<ProductDTO> getTopKidList() throws ClassNotFoundException, SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        List<ProductDTO> list = new ArrayList<>();
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_TOP_KID_LIST);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int productId = rs.getInt("productId");
                    int brandId = rs.getInt("brandId");
                    int userObjectID = rs.getInt("userObjectId");
                    String detail = rs.getString("detail");
                    boolean hot = rs.getBoolean("hot");
                    String name = rs.getString("name");
                    String color = rs.getString("color");
                    float price = rs.getFloat("price");
                    float sale = rs.getFloat("sale");
                    int warrantPeriod = rs.getInt("warrantPeriod");
                    boolean productStatus = rs.getBoolean("productStatus");
                    ProductDTO pro = new ProductDTO(productId, brandId, userObjectID, detail, hot, name, color, price, sale, warrantPeriod, productStatus);
                    list.add(pro);
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
        return list;
    }

    public List<ProductDTO> sortSaleProduct() throws ClassNotFoundException, SQLException {
        List<ProductDTO> listProduct = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_SALE_PRODUCT);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int productId = rs.getInt("productId");
                    int brandId = rs.getInt("brandId");
                    int userObjectId = rs.getInt("userObjectId");
                    String detail = rs.getString("detail");
                    boolean hot = rs.getBoolean("hot");
                    String name = rs.getString("name");
                    float price = rs.getFloat("price");
                    String color = rs.getString("color");
                    float sale = rs.getFloat("sale");
                    int warrantPeriod = rs.getInt("warrantPeriod");
                    boolean productStatus = rs.getBoolean("productStatus");

                    ProductDTO product = new ProductDTO(productId, brandId, userObjectId, detail, hot, name, color, price, sale, warrantPeriod, productStatus);
                    listProduct.add(product);
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

        return listProduct;
    }

    public List<ProductDTO> getRelatedList(int id) throws ClassNotFoundException, SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        List<ProductDTO> list = new ArrayList<>();
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_RELATED_LIST);
                ptm.setInt(1, id);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int productId = rs.getInt("productId");
                    int brandId = rs.getInt("brandId");
                    int userObjectID = rs.getInt("userObjectId");
                    String detail = rs.getString("detail");
                    boolean hot = rs.getBoolean("hot");
                    String name = rs.getString("name");
                    String color = rs.getString("color");
                    float price = rs.getFloat("price");
                    float sale = rs.getFloat("sale");
                    int warrantPeriod = rs.getInt("warrantPeriod");
                    boolean productStatus = rs.getBoolean("productStatus");
                    ProductDTO pro = new ProductDTO(productId, brandId, userObjectID, detail, hot, name, color, price, sale, warrantPeriod, productStatus);
                    list.add(pro);
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
        return list;
    }

    public List<ProductDTO> getProductByCate(int type) throws ClassNotFoundException, SQLException {
        List<ProductDTO> listProduct = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_PRODUCT_BY_CATE);
                ptm.setInt(1, type);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int productId = rs.getInt("productId");
                    int brandId = rs.getInt("brandId");
                    int userObjectId = rs.getInt("userObjectId");
                    String detail = rs.getString("detail");
                    boolean hot = rs.getBoolean("hot");
                    String name = rs.getString("name");
                    float price = rs.getFloat("price");
                    String color = rs.getString("color");
                    float sale = rs.getFloat("sale");
                    int warrantPeriod = rs.getInt("warrantPeriod");
                    boolean productStatus = rs.getBoolean("productStatus");

                    ProductDTO product = new ProductDTO(productId, brandId, userObjectId, detail, hot, name, color, price, sale, warrantPeriod, productStatus);
                    listProduct.add(product);

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

        return listProduct;
    }

    public List<ProductDTO> search(String search) throws SQLException, ClassNotFoundException {
        List<ProductDTO> products = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ps = con.prepareStatement(SEARCH_PRODUCT);
                ps.setString(1, "%" + search + "%");
                rs = ps.executeQuery();

                while (rs.next()) {
                    int productId = rs.getInt("productId");
                    int brandId = rs.getInt("brandId");
                    int userObjectId = rs.getInt("userObjectId");
                    String detail = rs.getString("detail");
                    boolean hot = rs.getBoolean("hot");
                    String productName = rs.getString("name");
                    float price = rs.getFloat("price");
                    String color = rs.getString("color");
                    float sale = rs.getFloat("sale");
                    int warrantPeriod = rs.getInt("warrantPeriod");
                    boolean productStatus = rs.getBoolean("productStatus");

                    ProductDTO product = new ProductDTO(productId, brandId, userObjectId, detail, hot, productName, color, price, sale, warrantPeriod, productStatus);
                    products.add(product);
                }

            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return products;
    }

    public List<ProductDTO> filter(int[] brands, String minPrice, String maxPrice, String[] colors, int[] uOb) throws SQLException, SQLException, ClassNotFoundException {
        StringBuilder sql = new StringBuilder("SELECT * FROM products WHERE 1 = 1 AND productStatus = 1");
        List<ProductDTO> ls = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                // Thêm điều kiện BrandID
                if (brands != null && brands.length > 0) {
                    sql.append(" AND brandId IN (");
                    for (int i = 0; i < brands.length; i++) {
                        sql.append("?");
                        if (i < brands.length - 1) {
                            sql.append(",");
                        }
                    }
                    sql.append(")");
                }

                // Thêm điều kiện Price
                if (minPrice != null && !minPrice.isEmpty()) {
                    sql.append(" AND price >= ?");
                }
                if (maxPrice != null && !maxPrice.isEmpty()) {
                    sql.append(" AND price <= ?");
                }

                // Thêm điều kiện Color
                if (colors != null && colors.length > 0) {
                    StringBuilder colorCondition = new StringBuilder(" AND (");
                    for (int i = 0; i < colors.length; i++) {
                        colorCondition.append("color LIKE ?");
                        if (i < colors.length - 1) {
                            colorCondition.append(" OR ");
                        }
                    }
                    colorCondition.append(")");
                    sql.append(colorCondition);
                }

                // Thêm điều kiện userObjectID
                if (uOb != null && uOb.length > 0) {
                    sql.append(" AND userObjectId = ?");
                }

                // Chuẩn bị câu lệnh SQL
                ps = con.prepareStatement(sql.toString());
                int paramIndex = 1;

                // Thiết lập giá trị cho BrandID
                if (brands != null && brands.length > 0) {
                    for (int brand : brands) {
                        ps.setInt(paramIndex++, brand);
                    }
                }

                // Thiết lập giá trị cho minPrice và maxPrice
                if (minPrice != null && !minPrice.isEmpty()) {
                    ps.setFloat(paramIndex++, Float.parseFloat(minPrice));
                }
                if (maxPrice != null && !maxPrice.isEmpty()) {
                    ps.setFloat(paramIndex++, Float.parseFloat(maxPrice));
                }

                // Thiết lập giá trị cho Color
                if (colors != null && colors.length > 0) {
                    for (String color : colors) {
                        ps.setString(paramIndex++, "%"+color+"%");
                    }
                }

                // Thiết lập giá trị cho userObjectID
                if (uOb != null && uOb.length > 0) {
                    for (int u : uOb) {
                        ps.setInt(paramIndex++, u);
                    }
                }

                // Thực thi truy vấn
                rs = ps.executeQuery();
                while (rs.next()) {
                    int productId = rs.getInt("productId");
                    int brandId = rs.getInt("brandId");
                    int userObjectId = rs.getInt("userObjectId");
                    String detail = rs.getString("detail");
                    boolean hot = rs.getBoolean("hot");
                    String productName = rs.getString("name");
                    float price = rs.getFloat("price");
                    String color = rs.getString("color");
                    float sale = rs.getFloat("sale");
                    int warrantPeriod = rs.getInt("warrantPeriod");
                    boolean productStatus = rs.getBoolean("productStatus");

                    ProductDTO product = new ProductDTO(productId, brandId, userObjectId, detail, hot, productName, color, price, sale, warrantPeriod, productStatus);
                    ls.add(product);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return ls;
    }

}
