package Dal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Mod.*;

public class TeacherServer extends DB {
	Connection con=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	String tablename="Teacher";
	///������
	public int Add(Teacher t,int zyID)
	{
		int count=0;
		try
		{
			con=this.getcon();
			String sql=String.format("insert %s values('%s',%d,'%s','%s',%d,'%s','%s','%s','%s')",
				tablename,t.getName(),t.getAge(),t.getXueli(),t.getTel(),zyID,t.getContent(),t.getImg(),t.getLogin(),t.getLogin());
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
	
	///�޸���Ϣ
	public int Edit(Teacher t,int zyID)
	{
		int count=0;
		try
		{
			con=this.getcon();
			String sql=String.format("update %s set name='%s',age=%d,tel='%s',xueli='%s',ZhuanYe_ID=%d,Content='%s',Img='%s' where id=%d",
					tablename,t.getName(),t.getAge(),t.getTel(),t.getXueli(),zyID,t.getContent(),t.getImg(),t.getId());
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
	
	
	///��ȡ������Ϣ
	public List<Teacher> GetAll(int startindex,int pagesize)
	{
		List<Teacher> list=new ArrayList<Teacher>();
		try
		{
			con=this.getcon();
			String sql="";
		    sql=SetPageSQL(tablename,startindex,pagesize,"","id","id desc");
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next())
			{
				Teacher w=new Teacher();
				w.setId(rs.getInt("id"));
				w.setName(rs.getString("name"));
				w.setAge(rs.getInt("age"));
				w.setTel(rs.getString("tel"));
				w.setContent(rs.getString("content"));
				w.setImg(rs.getString("img"));
				w.setZhuanye(new ZhuanYeServer().GetByID(rs.getInt("ZhuanYe_ID")));
				w.setXueli(rs.getString("xueli"));
				w.setLogin(rs.getString("login"));
				w.setPass(rs.getString("pass"));
				list.add(w);
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
	
	///��ȡ����
	public int GetCount()
	{
		int count=0;
		try
		{
			con=this.getcon();
			String sql=String.format("select count(*) from %s",tablename);
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
	
	///��ȡԱ����ϸ��Ϣ
	public Teacher Login(String login)
	{
		
		try
		{
			con=this.getcon();
		    String sql=String.format("select * from %s where login='%s'",tablename,login);
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			if(rs.next())
			{
				Teacher w=new Teacher();
				w.setId(rs.getInt("id"));
				w.setName(rs.getString("name"));
				w.setAge(rs.getInt("age"));
				w.setTel(rs.getString("tel"));
				w.setContent(rs.getString("content"));
				w.setImg(rs.getString("img"));
				w.setZhuanye(new ZhuanYeServer().GetByID(rs.getInt("ZhuanYe_ID")));
				w.setXueli(rs.getString("xueli"));
				w.setLogin(rs.getString("login"));
				w.setPass(rs.getString("pass"));
				return w;
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
	
	///��ȡ��ϸ��Ϣ
	public Teacher GetByID(int id)
	{
		
		try
		{
			con=this.getcon();
		    String sql=String.format("select * from [%s] where ID=%d",tablename,id);
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			if(rs.next())
			{
				Teacher w=new Teacher();
				w.setId(rs.getInt("id"));
				w.setName(rs.getString("name"));
				w.setAge(rs.getInt("age"));
				w.setTel(rs.getString("tel"));
				w.setContent(rs.getString("content"));
				w.setImg(rs.getString("img"));
				w.setZhuanye(new ZhuanYeServer().GetByID(rs.getInt("ZhuanYe_ID")));
				w.setXueli(rs.getString("xueli"));
				w.setLogin(rs.getString("login"));
				w.setPass(rs.getString("pass"));
				return w;
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
