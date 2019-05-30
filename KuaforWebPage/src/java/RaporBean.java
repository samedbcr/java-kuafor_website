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
public class RaporBean {

    CachedRowSet rowSet = null;
    ResultSet result;
    DataSource dataSource;

    public RaporBean() {
        try {
            Context ctx = new InitialContext();
            dataSource = (DataSource) ctx.lookup("jdbc/addressbook");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public ResultSet rapor1() throws SQLException {
        if (dataSource == null) {
            throw new SQLException("Unable to obtain DataSource");
        }

        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("Unable to connect to DataSource");
        }

        try {
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT UYELER.KUL_ADI,RANDEVULAR.RAN_SUBE "
                    + "FROM UYELER "
                    + "RIGHT JOIN RANDEVULAR "
                    + "ON UYELER.KUL_ADI=RANDEVULAR.RAN_KUL_ADI "
                    + "WHERE RAN_HIZMET LIKE '%Makyaj%' "
                    + "ORDER BY RAN_SUBE DESC");
            CachedRowSet resultSet1 = new com.sun.rowset.CachedRowSetImpl();
            resultSet1.populate(stmt.executeQuery());
            return resultSet1;
        } finally {
            connection.close();
        }
    }

    public ResultSet rapor2() throws SQLException {
        if (dataSource == null) {
            throw new SQLException("Unable to obtain DataSource");
        }

        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("Unable to connect to DataSource");
        }

        try {
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT COUNT(RAN_KUL_ADI) as RANDEVU_SAYISI, RAN_HIZMET "
                    + "FROM RANDEVULAR "
                    + "GROUP BY RAN_HIZMET "
                    + "ORDER BY RANDEVU_SAYISI DESC");
            CachedRowSet resultSet1 = new com.sun.rowset.CachedRowSetImpl();
            resultSet1.populate(stmt.executeQuery());
            return resultSet1;
        } finally {
            connection.close();
        }
    }

    public ResultSet rapor3() throws SQLException {
        if (dataSource == null) {
            throw new SQLException("Unable to obtain DataSource");
        }

        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("Unable to connect to DataSource");
        }

        try {
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT COUNT(UYELER.ID) AS UYE_ID "
                    + "FROM UYELER "
                    + "INNER JOIN RANDEVULAR "
                    + "ON UYELER.KUL_ADI=RANDEVULAR.RAN_KUL_ADI");
            CachedRowSet resultSet1 = new com.sun.rowset.CachedRowSetImpl();
            resultSet1.populate(stmt.executeQuery());
            return resultSet1;
        } finally {
            connection.close();
        }
    }

    public ResultSet rapor4() throws SQLException {
        if (dataSource == null) {
            throw new SQLException("Unable to obtain DataSource");
        }

        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("Unable to connect to DataSource");
        }

        try {
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT UYELER.KUL_ADI AS KUL_ADI, RANDEVULAR.RAN_SUBE AS SUBE, "
                    + "RANDEVULAR.RAN_TEL_NO AS TEL_NO, UYELER.EMAIL AS EMAIL "
                    + "FROM UYELER "
                    + "INNER JOIN RANDEVULAR "
                    + "ON UYELER.KUL_ADI=RANDEVULAR.RAN_KUL_ADI");
            CachedRowSet resultSet1 = new com.sun.rowset.CachedRowSetImpl();
            resultSet1.populate(stmt.executeQuery());
            return resultSet1;
        } finally {
            connection.close();
        }
    }
    
    public ResultSet rapor5() throws SQLException {
        if (dataSource == null) {
            throw new SQLException("Unable to obtain DataSource");
        }

        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("Unable to connect to DataSource");
        }

        try {
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT COUNT(ILET_ADI) AS SAYI, ILET_ADI "
                    + "FROM ILETISIM "
                    + "GROUP BY ILET_ADI "
                    + "ORDER BY SAYI DESC");
            CachedRowSet resultSet1 = new com.sun.rowset.CachedRowSetImpl();
            resultSet1.populate(stmt.executeQuery());
            return resultSet1;
        } finally {
            connection.close();
        }
    }

}
