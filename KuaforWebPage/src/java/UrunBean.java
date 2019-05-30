/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;

/**
 *
 * @author Sammy
 */
@ManagedBean
@RequestScoped
public class UrunBean {

    DataSource dataSource;
    public String urunAdi;
    public String aciklama;
    public String miktar;
    public String fiyat;

    public String getUrunAdi() {
        return urunAdi;
    }

    public void setUrunAdi(String urunAdi) {
        this.urunAdi = urunAdi;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public String getMiktar() {
        return miktar;
    }

    public void setMiktar(String miktar) {
        this.miktar = miktar;
    }

    public String getFiyat() {
        return fiyat;
    }

    public void setFiyat(String fiyat) {
        this.fiyat = fiyat;
    }
    

    public UrunBean() {
        try {
            Context ctx = new InitialContext();
            dataSource = (DataSource) ctx.lookup("jdbc/addressbook");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public ResultSet urunGetir() throws SQLException {
        if (dataSource == null) {
            throw new SQLException("Unable to obtain DataSource");
        }

        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("Unable to connect to DataSource");
        }

        try {
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT * FROM URUNLER");
            CachedRowSet resultSet1 = new com.sun.rowset.CachedRowSetImpl();
            resultSet1.populate(stmt.executeQuery());
            return resultSet1;
        } finally {
            connection.close();
        }
    }
    
    public String urunEkle() throws SQLException {

        if (dataSource == null) {
            throw new SQLException("Unable to obtain DataSource");
        }

        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("Unable to conntect to DataSource");
        }

        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO URUNLER "
                    + "(urun_adi,aciklama,miktar,fiyat)"
                    + "VALUES ( ?, ?, ?, ? )");
            statement.setString(1, getUrunAdi());
            statement.setString(2, getAciklama());
            statement.setString(3, getMiktar());
            statement.setString(4, getFiyat());

            statement.executeUpdate();
            return "/urunduzenle.xhtml?faces-redirect=true";

        } catch (Exception e) {
            return "/urunekle.xhtml?faces-redirect=true";
        } finally {
            connection.close();
        }

    }

}
