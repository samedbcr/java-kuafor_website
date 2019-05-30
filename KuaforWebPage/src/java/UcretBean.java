

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;

/**
 *
 * @author ss
 */
@ManagedBean
@SessionScoped
public class UcretBean {

    private String ucretadi;
    private String ucretfiyati;

    public String getUcretfiyati() {
        return ucretfiyati;
    }

    public void setUcretfiyati(String ucretfiyati) {
        this.ucretfiyati = ucretfiyati;
    }
    
    public String getUcretadi() {
        return ucretadi;
    }

    public void setUcretadi(String ucretadi) {
        this.ucretadi = ucretadi;
    }
    
    
    private String hata;

    DataSource dataSource;

    public UcretBean() {
        try {
            Context ctx = new InitialContext();
            dataSource = (DataSource) ctx.lookup("jdbc/addressbook");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    

    public String getHata() {
        return hata;
    }

    public void setHata(String hata) {
        this.hata = hata;
    }

    public ResultSet ucretGetir() throws SQLException {
        if (dataSource == null) {
            throw new SQLException("Unable to obtain DataSource");
        }

        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("Unable to connect to DataSource");
        }

        try {
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT * FROM UCRETLER");
            CachedRowSet resultSet1 = new com.sun.rowset.CachedRowSetImpl();
            resultSet1.populate(stmt.executeQuery());
            return resultSet1;
        } finally {
            connection.close();
        }
    }

    public String ucretEkle() throws SQLException {

        if (dataSource == null) {
            throw new SQLException("Unable to obtain DataSource");
        }

        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("Unable to conntect to DataSource");
        }

        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO UCRETLER "
                    + "(UCRET_ADI,UCRET_FIYATI) "
                    + "VALUES ( ?, ?)");
            statement.setString(1, getUcretadi());
            statement.setString(2, getUcretfiyati());
           
            statement.executeUpdate();
            return "/hakkimizda";

        } catch (Exception e) {
            hata = e.getMessage();
            System.out.println("ee:" + hata);
            return "/secured/randevu.xhtml?faces-redirect=true";
        } finally {
            connection.close();
        }

    }

}
