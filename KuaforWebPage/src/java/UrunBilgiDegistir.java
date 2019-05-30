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
import javax.faces.bean.SessionScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
    
/**
 *
 * @author Sammy
 */
@ManagedBean
@SessionScoped
public class UrunBilgiDegistir {

    DataSource dataSource;
    private String id;
    private String ad;
    private String aciklama;
    private String miktar;
    private String fiyat;
    private String yeni_ad;
    private String yeni_aciklama;
    private String yeni_miktar;
    private String yeni_fiyat;
    private int num;
    private String hata;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
    
    

    public String getHata() {
        return hata;
    }

    public void setHata(String hata) {
        this.hata = hata;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
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

    public String getYeni_ad() {
        return yeni_ad;
    }

    public void setYeni_ad(String yeni_ad) {
        this.yeni_ad = yeni_ad;
    }

    public String getYeni_aciklama() {
        return yeni_aciklama;
    }

    public void setYeni_aciklama(String yeni_aciklama) {
        this.yeni_aciklama = yeni_aciklama;
    }

    public String getYeni_miktar() {
        return yeni_miktar;
    }

    public void setYeni_miktar(String yeni_miktar) {
        this.yeni_miktar = yeni_miktar;
    }

    public String getYeni_fiyat() {
        return yeni_fiyat;
    }

    public void setYeni_fiyat(String yeni_fiyat) {
        this.yeni_fiyat = yeni_fiyat;
    }

    public UrunBilgiDegistir() {
        try {
            Context ctx = new InitialContext();
            dataSource = (DataSource) ctx.lookup("jdbc/addressbook");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public String urunBilgileri(String id) throws SQLException {

        if (dataSource == null) {
            throw new SQLException("Unable to obtain DataSource");
        }

        // obtain a connection from the connection pool
        Connection connection = dataSource.getConnection();

        // check whether connection was successful
        if (connection == null) {
            throw new SQLException("Unable to connect to DataSource");
        }
        this.num = Integer.valueOf(id);

        try {
            String sql = "Select * from URUNLER where ID= ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, num);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                setId(rs.getString("ID"));
                setAd(rs.getString("URUN_ADI"));
                setAciklama(rs.getString("ACIKLAMA"));
                setMiktar(rs.getString("MIKTAR"));
                setFiyat(rs.getString("FIYAT"));
            }

        } // end try
        catch (Exception e) {
            return "/faces/index";

        } finally {
            connection.close();
        }

        return "/urunduzenle_2.xhtml?faces-redirect=true";

    }

    public String adGuncelle() throws SQLException {

        if (dataSource == null) {
            throw new SQLException("Unable to obtain DataSource");
        }

        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("Unable to conntect to DataSource");
        }

        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE URUNLER "
                    + "SET urun_adi=? WHERE ID=?");
            statement.setString(1, getYeni_ad());
            statement.setInt(2, getNum());

            statement.executeUpdate();
            return "/urunduzenle.xhtml?faces-redirect=true";

        } catch (Exception e) {
            hata = e.getMessage();
            return "/index";
        } finally {
            connection.close();
        }

    }

    public String aciklamaGuncelle() throws SQLException {

        if (dataSource == null) {
            throw new SQLException("Unable to obtain DataSource");
        }

        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("Unable to conntect to DataSource");
        }

        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE URUNLER "
                    + "SET aciklama=? WHERE ID=?");
            num = Integer.valueOf(getId());

            statement.setString(1, getYeni_aciklama());
            statement.setInt(2, num);

            statement.executeUpdate();
            return "/urunduzenle.xhtml?faces-redirect=true";

        } catch (Exception e) {
            return "/index";
        } finally {
            connection.close();
        }

    }

    public String miktarGuncelle() throws SQLException {

        if (dataSource == null) {
            throw new SQLException("Unable to obtain DataSource");
        }

        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("Unable to conntect to DataSource");
        }

        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE URUNLER "
                    + "SET miktar=? WHERE ID=?");
            num = Integer.valueOf(getId());

            statement.setString(1, getYeni_miktar());
            statement.setInt(2, num);

            statement.executeUpdate();
            return "/urunduzenle.xhtml?faces-redirect=true";

        } catch (Exception e) {
            return "/index";
        } finally {
            connection.close();
        }

    }

    public String fiyatGuncelle() throws SQLException {

        if (dataSource == null) {
            throw new SQLException("Unable to obtain DataSource");
        }

        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("Unable to conntect to DataSource");
        }

        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE URUNLER "
                    + "SET fiyat=? WHERE ID=?");

            statement.setString(1, getYeni_fiyat());
            statement.setInt(2, num);

            statement.executeUpdate();
            return "/urunduzenle.xhtml?faces-redirect=true";

        } catch (Exception e) {
            return "/index";
        } finally {
            connection.close();
        }

    }

}
