package module8.batch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Savepoint;

import org.apache.log4j.Logger;

import module8.proc.OutParamDemo;

public class FixRecords {

	private static final Logger logger = Logger.getLogger(OutParamDemo.class.getClass());
	private final static String db_url = "jdbc:mysql://localhost:3306/test?autoReconnect=true&useSSL=false";	
	private final static String user = "root";
	private final static String psw = "root";
	
	public static void main(String[] args) {
		try(Connection conn = DriverManager.getConnection(db_url,user,psw);){
			Savepoint marker = null;
			conn.setAutoCommit(false);
			//two strings for insert and update respectively
			String sql_insert = "insert into test.student (name) values (?)";
			String sql_update = "update test.student set name = ? where id = ?";
			try {
				//two prepared statements for batch processing
				PreparedStatement pstmt_insert = conn.prepareStatement(sql_insert);
				PreparedStatement pstmt_update = conn.prepareStatement(sql_update);
				marker = conn.setSavepoint("start_trans");
				
				pstmt_insert.setString(1, "Sam");
				pstmt_insert.addBatch();
				pstmt_insert.setString(1, "Duke");
				pstmt_insert.addBatch();
				logger.debug("Executing batch inserting...");
				int insert[] = pstmt_insert.executeBatch();
				logger.debug("Inserted " + insert.length + " records.");
				pstmt_update.setInt(2, 1);
				pstmt_update.setString(1, "Andyy");
				pstmt_update.addBatch();
				pstmt_update.setInt(2, 2);
				pstmt_update.setString(1, "Jasonn");
				pstmt_update.addBatch();
				logger.debug("Executing batch updating...");
				int update[] = pstmt_update.executeBatch();
				logger.debug("Updated " + update.length + " records.");
				conn.commit();
				logger.debug("Committed transaction.");
				conn.releaseSavepoint(marker);
				conn.setAutoCommit(true);
			}catch (SQLException e) {
				e.printStackTrace();
				if(marker != null) {
					conn.rollback(marker);
				}
				logger.debug("Rolled back transaction.");
				conn.setAutoCommit(true);
				}
		
		}catch (SQLException e) {
			e.printStackTrace();
			}
		}

	}
