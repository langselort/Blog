package Dal;

import java.sql.*;

public class DB {
	
	public static final String Driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	public static final String Url="jdbc:sqlserver://localhost:1433;DatabaseName=Web";
	public static final String User="sa";
	public static final String Pwd="2005";
	
	///��ҳ
	public static String SetPageSQL(String tablename,int startindex,int pagesize,String key,String col,String order)
	{
		if("".equals(key))
			key=" 1=1 ";
		String sql=String.format("select top %d * from %s where %s not in(select top %d %s from %s where %s order by %s ) and %s order by %s ",
				pagesize,tablename,col,startindex*pagesize,col,tablename,key,order,key,order);
		return sql;
	}
	
	///��ȡ����
	public static String SetCountSQL(String tablename,String key,String type)
	{
		if("".equals(key))
			key=" 1=1 ";
		String sql=String.format("select %s from %s where %s ",type,tablename,key);
		return sql;
	}
	
	
	public Connection getcon() 
					throws ClassNotFoundException, SQLException{
		
		Connection con;
		try{
			Class.forName(Driver);//��������
			}
			catch(ClassNotFoundException event){System.out.print("�޷�����������ʽʵ��!");}
			
			con = DriverManager.getConnection(Url,User,Pwd);
	
		return con;
	}
	
	public void closeAll(Connection con ,PreparedStatement ps,ResultSet rs){
		
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
	}

}
