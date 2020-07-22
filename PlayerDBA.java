package guiTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PlayerDBA {
	String url, user, pwd;

	public PlayerDBA() { //DB연결
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			url = "jdbc:oracle:thin:@localhost:1521:xe";
			user="scott";
			pwd = "1234";
	   	} catch (ClassNotFoundException e) {
				e.printStackTrace();
		} 
	}
	// 추가
	public void playerInsert(Player p) {
		Connection con = null;
		PreparedStatement ps = null;
	
		try {
			con = DriverManager.getConnection(url, user, pwd);
			String sql ="insert into player "
					+ " values(player_seq.nextval,?,?,?,?,?)";
			ps= con.prepareStatement(sql);
			ps.setString(1, p.getName());
			ps.setString(2, p.getBirth());
			ps.setDouble(3, p.getHeight());
			ps.setDouble(4, p.getWeight());
			ps.setString(5, p.getKind());
			ps.executeUpdate();
		} catch (SQLException e) {
				e.printStackTrace();
		}finally {
			closeConnection(con,ps);
		}
		
		
	}
	//상세보기
	public Player playerDetail(int num) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		Player p =null;
		try {
			con = DriverManager.getConnection(url, user, pwd);
			String sql = "select * from player where num="+num;
			st = con.createStatement();
			rs= st.executeQuery(sql);
			if(rs.next()) {
				 p = new Player();
				p.setBirth(rs.getString("birth"));
				p.setHeight(rs.getDouble("height"));
				p.setKind(rs.getString("kind"));
				p.setName(rs.getString("name"));
				p.setNum(rs.getInt("num"));
				p.setWeight(rs.getDouble("weight"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, st, rs);
		}
		return p;
		
	}
	
	//전체보기
    public ArrayList<Player> playerView() {
    	Connection con = null;
    	Statement st = null;
    	ResultSet rs = null;
    	ArrayList<Player> arr = new ArrayList<Player>();
    	
    	try {
			con  = DriverManager.getConnection(url, user, pwd);
			String sql="select * from player order by num desc";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				Player p = new Player();
				p.setBirth(rs.getString("birth"));
				p.setHeight(rs.getDouble("height"));
				p.setKind(rs.getString("kind"));
				p.setName(rs.getString("name"));
				p.setNum(rs.getInt("num"));
				p.setWeight(rs.getDouble("weight"));
				arr.add(p);
    		}
    	} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, st, rs);
		}
    	 return arr;
    	
    	
	}
	//수정
	public void playerUpdate(Player p) {
		Connection con = null;
		PreparedStatement ps =null;
		 try {
				  String sql ="update player set name=?, birth=?,"
					  		+ "height=?, weight=?, kind=? where num=?";
				  con = DriverManager.getConnection(url, user, pwd);
				ps =  con.prepareStatement(sql)  ;
				ps.setString(1, p.getName());
				ps.setString(2, p.getBirth());
				ps.setDouble(3,p.getHeight());
				ps.setDouble(4,p.getWeight());
				ps.setString(5, p.getKind());
				ps.setInt(6,p.getNum());
				ps.executeUpdate();
		 }catch (SQLException e) {
			e.printStackTrace();
		}	
		 finally {
			 closeConnection(con, ps);
		 }
	}
		//삭제
	public void playerDelete(int num) {
		Connection con = null;
		Statement st = null;
		
		try {
			con = DriverManager.getConnection(url, user, pwd);
		    String sql = "delete from player where num="+num;
		    st = con.createStatement();
		    st.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, st, null);
		}
	}
		//검색
	public ArrayList<Player> playerSearch(String key, String word) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<Player> arr= new ArrayList<Player>();
		
		try {
			con = DriverManager.getConnection(url, user, pwd);
			st = con.createStatement();
			String sql="select * from player "
					+ " where "+key +" like '%"+word+"%'";
			rs = st.executeQuery(sql);
			while(rs.next()) {
				Player p = new Player();
				p.setBirth(rs.getString("birth"));
				p.setHeight(rs.getDouble("height"));
				p.setKind(rs.getString("kind"));
				p.setName(rs.getString("name"));
				p.setNum(rs.getInt("num"));
				p.setWeight(rs.getDouble("weight"));
				arr.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, st, rs);
		}
		return arr;
		
	}
	//닫기(종료)메소드
	public void closeConnection(Connection con, 
			 Statement st, ResultSet rs) {
		try {
			if(rs!=null) rs.close();
			if(st!=null) st.close();
			if(con!=null) con.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void closeConnection(Connection con, 
			 PreparedStatement ps) {
		try {
			if(ps!=null) ps.close();
			if(con!=null) con.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
}
