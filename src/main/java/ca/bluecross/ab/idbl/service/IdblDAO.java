/*
 * $Id: IdblDAO.java,v 1.1.2.6 2018/11/27 20:18:41 myang Exp $
 */
package ca.bluecross.ab.idbl.service;

import ca.bluecross.ab.common.util.model.db.ABCBaseDAO;

import java.sql.SQLException;

import java.util.List;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service 
public class IdblDAO extends ABCBaseDAO {

   /**
    * Executes the given query and returns an object mapped by the provided
    * {@link RowMapper} instance.
    */
   public <T> T queryForObject(final String sql, final RowMapper<T> rowMapper,
                               final Object... args) {   
      return getJdbcTemplate().queryForObject(sql, wrap(rowMapper), args);                               
   }
   
   /**
    * Executes the given query and returns a list of objects mapped by the provided
    * {@link RowMapper} instance.
    */
   public <T> List<T> queryForList(final String sql, final RowMapper<T> rowMapper,
                                   final Object... args) {   
      return getJdbcTemplate().query(sql, wrap(rowMapper), args);                               
   }

   /**
    * Executes a query with the given {@code sql} and {@code args}, then processes
    * the query result with the provided {@link RowCallbackHandler} instance.
    * @param sql the query 
    * @param cbh the instance of {@link RowCallbackHandler} to process the query result
    * @param args the query arguments
    */
   public void query(final String sql, final RowCallbackHandler cbh, final Object... args) {
      getJdbcTemplate().query(sql, wrap(cbh), args);      
   }


   /**
    * Wraps the given instance of {@link RowMapper} so that 
    * the implementation of {@link RowMapper} doesn't need 
    * {@code try-and-catch} the {@link SQLException}.
    * @param rowMapper the instance to be wrapped
    * @return a wrapped instance of {@link RowMapper}
    */
   public static <T> RowMapper<T> wrap(final RowMapper<T> rowMapper) {      
      return (rs, rowNum) -> {
         try {
            return rowMapper.mapRow(rs, rowNum);
         }
         catch (final SQLException ex) {
            throw new RuntimeException(ex);
         }
      };
   }
   
   /**
    * Wraps the given instance of {@link RowCallbackHandler} so that 
    * the implementation of {@link RowCallbackHandler} doesn't need 
    * {@code try-and-catch} the {@link SQLException}.
    * @param cbh the instance to be wrapped
    * @return a wrapped instance of {@link RowCallbackHandler}
    */
   public static RowCallbackHandler wrap(final RowCallbackHandler cbh) {
      return rs -> {
         try {
            cbh.processRow(rs);
         }
         catch (final SQLException ex) {
            throw new RuntimeException(ex);
         }
      };
   }
  
}
