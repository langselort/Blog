package Dal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import Mod.*;

public class DownServer extends DB {
	Connection con=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	String tablename="Down";
	
	///������Ϣ
	public List<Down> GetAll(int startindex,int pagesize,String key)
	{
		List<Down> list=new ArrayList<Down>();
		try
		{
			con=this.getcon();
			String sql=DB.SetPageSQL(tablename, startindex, pagesize, key, "id", " id desc");
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next())
			{
				Down b=new Down();
				b.setId(rs.getInt("id"));
				b.setTitle(rs.getString("title"));
				b.setInTime(rs.getDate("intime"));
				b.setContent(rs.getString("content"));
				b.setTeacher(new TeacherServer().GetByID(rs.getInt("Teacher_ID")));
				b.setUrl(rs.getString("url"));
				list.add(b);
			}
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			this.closeAll(con, ps, rs);
		}
		return list;
	}
	
	///��ȡ������
	public int GetCount(String key)
	{
		int count=0;
		try
		{
			con=this.getcon();
			String sql=DB.SetCountSQL(tablename, key,"count(*)");
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			rs.next();
            count = rs.getInt(1);
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			this.closeAll(con, ps, rs);
		}
		return count;
	}
	
	///�ϴ���Դ
	public int Add(Down b)
	{
		int count=0;
		try
		{
			con=this.getcon();
			String sql=String.format("insert %s values(%d,'%s','%s',getdate(),'%s')",
					tablename,b.getTeacher().getId(),b.getTitle(),b.getContent(),b.getUrl());
			ps=con.prepareStatement(sql);
			count=ps.executeUpdate();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			this.closeAll(con, ps, rs);
		}
		return count;
	}
	
	///�޸�
	public int Edit(Down b)
	{
		int count=0;
		try
		{
			con=this.getcon();
			String sql=String.format("update %s set title='%s',content='%s',url='%s' where id=%d",tablename,b.getTitle(),b.getContent(),b.getUrl(),b.getId());
			ps=con.prepareStatement(sql);
			count=ps.executeUpdate();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			this.closeAll(con, ps, rs);
		}
		return count;
	}

	
	///ɾ����Ϣ
	public int Del(int id)
	{
		int count=0;
		try
		{
			con=this.getcon();
			String sql=String.format("delete from %s where id=%d",tablename,id);
			ps=con.prepareStatement(sql);
			count=ps.executeUpdate();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			this.closeAll(con, ps, rs);
		}
		return count;
	}
	
	///��ȡ��Ϣ
	public Down GetByID(int id)
	{
		
		try
		{
			con=this.getcon();
		    String sql=String.format("select * from %s where ID=%d",tablename,id);
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			if(rs.next())
			{
				Down b=new Down();
				b.setId(rs.getInt("id"));
				b.setTitle(rs.getString("title"));
				b.setInTime(rs.getDate("intime"));
				b.setContent(rs.getString("content"));
				b.setTeacher(new TeacherServer().GetByID(rs.getInt("Teacher_ID")));
				b.setUrl(rs.getString("url"));
				return b;
			}
			
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			this.closeAll(con, ps, rs);
		}
		return null;
	}
}
