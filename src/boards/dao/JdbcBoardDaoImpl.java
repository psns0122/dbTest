package boards.dao;


import jdbc.advanced.boards.config.DBUtil;
import jdbc.advanced.boards.vo.JdbcBoardVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcBoardDaoImpl implements IJdbcBoardDao {
    private static JdbcBoardDaoImpl dao;

    private JdbcBoardDaoImpl(){ }

    public static JdbcBoardDaoImpl getInstance(){
        if(dao==null) dao = new JdbcBoardDaoImpl();

        return dao;
    }

    private Connection conn;
    private PreparedStatement pstmt;
    private Statement stmt;
    private ResultSet rs;

    // 사용한 자원을 반납하는 메서드
    private void disConnect(){
        if(rs!=null) try{ rs.close(); }catch(SQLException e){}
        if(stmt!=null) try{ stmt.close(); }catch(SQLException e){}
        if(pstmt!=null) try{ pstmt.close(); }catch(SQLException e){}
        if(conn!=null) try{ conn.close(); }catch(SQLException e){}
    }


    @Override
    public int insertBoard(JdbcBoardVO boardVo) {
        int cnt = 0;

        try {
            conn = DBUtil.getConnection();

            String sql = "insert into jdbc_board "
                    + "(board_title, board_writer,"
                    + " board_date, board_cnt, board_content) "
                    + " values(?, ? , now(), 0, ? ) ";
            pstmt = conn.prepareStatement(sql , Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, boardVo.getBoard_title());
            pstmt.setString(2, boardVo.getBoard_writer());
            pstmt.setString(3, boardVo.getBoard_content());

            cnt = pstmt.executeUpdate();

        } catch (SQLException e) {
            cnt = 0;
            e.printStackTrace();
        } finally{
            disConnect();
        }

        return cnt;
    }

    @Override
    public int deleteBoard(int boardNo) {
        int cnt = 0;

        try {
            conn = DBUtil.getConnection();

            String sql = "delete from jdbc_board where board_no = ? ";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, boardNo);

            cnt = pstmt.executeUpdate();

        } catch (SQLException e) {
            cnt = 0;
            e.printStackTrace();
        } finally{
            disConnect();
        }

        return cnt;
    }

    @Override
    public int updateBoard(JdbcBoardVO boardVo) {
        int cnt = 0;

        try {
            conn = DBUtil.getConnection();

            String sql = "update jdbc_board set "
                    + " board_title = ? ,"
                    + " board_content = ? ,"
                    + " board_date = now() "
                    + " where board_no = ? ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, boardVo.getBoard_title());
            pstmt.setString(2, boardVo.getBoard_content());
            pstmt.setInt(3, boardVo.getBoard_no());

            cnt = pstmt.executeUpdate();

        } catch (SQLException e) {
            cnt = 0;
            e.printStackTrace();
        } finally {
            disConnect();
        }

        return cnt;
    }

    @Override
    public List<JdbcBoardVO> getAllBoardList() {
        List<JdbcBoardVO> boardList = null;

        try {
            conn = DBUtil.getConnection();

            String sql = "select board_no, board_title, board_writer,"
                    + " to_char(board_date, 'YYYY-MM-DD') board_date , board_cnt, board_content "
                    + " from jdbc_board "
                    + " order by board_no desc";
            stmt = conn.createStatement();

            rs = stmt.executeQuery(sql);

            boardList = new ArrayList<>();

            while(rs.next()){
                JdbcBoardVO boardVo = new JdbcBoardVO();
                boardVo.setBoard_no(rs.getInt("board_no"));
                boardVo.setBoard_title(rs.getString("board_title"));
                boardVo.setBoard_writer(rs.getString("board_writer"));
                boardVo.setBoard_date(rs.getString("board_date"));
                boardVo.setBoard_cnt(rs.getInt("board_cnt"));
                boardVo.setBoard_content(rs.getString("board_content"));

                boardList.add(boardVo);
            }


        } catch (SQLException e) {
            boardList = null;
            e.printStackTrace();
        } finally {
            disConnect();
        }
        return boardList;
    }

    @Override
    public JdbcBoardVO getBoard(int boardNo) {
        JdbcBoardVO boardVo = null;

        try {
            conn = DBUtil.getConnection();

            String sql = "select board_no, board_title, board_writer,"
                    + " to_char(board_date, 'YYYY-MM-DD') board_date , board_cnt, board_content "
                    + " from jdbc_board "
                    + " where board_no = ? ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, boardNo);

            rs = pstmt.executeQuery();

            if(rs.next()){
                boardVo = new JdbcBoardVO();
                boardVo.setBoard_no(rs.getInt("board_no"));
                boardVo.setBoard_title(rs.getString("board_title"));
                boardVo.setBoard_writer(rs.getString("board_writer"));
                boardVo.setBoard_date(rs.getString("board_date"));
                boardVo.setBoard_cnt(rs.getInt("board_cnt"));
                boardVo.setBoard_content(rs.getString("board_content"));
            }

        } catch (SQLException e) {
            boardVo = null;
            e.printStackTrace();
        } finally{
            disConnect();
        }

        return boardVo;
    }

    @Override
    public List<JdbcBoardVO> getSearchBoardList(String title) {
        List<JdbcBoardVO> boardList = null;

        try {
            conn = DBUtil.getConnection();

            String sql = "select board_no, board_title, board_writer,"
                    + " to_char(board_date, 'YYYY-MM-DD') board_date , board_cnt, board_content "
                    + " from jdbc_board "
                    + " where board_title like '%' || ? || '%'"
                    + " order by board_no desc";
            // where board_title like '%' || ? || '%'
            // where board_title like '%' || '가' || '%'

            // where board_title like '%?%'  ==> 사용 불가
            // where board_title like '%'가'%'
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, title);

            rs = pstmt.executeQuery();

            boardList = new ArrayList<>();

            while(rs.next()){
                JdbcBoardVO boardVo = new JdbcBoardVO();
                boardVo.setBoard_no(rs.getInt("board_no"));
                boardVo.setBoard_title(rs.getString("board_title"));
                boardVo.setBoard_writer(rs.getString("board_writer"));
                boardVo.setBoard_date(rs.getString("board_date"));
                boardVo.setBoard_cnt(rs.getInt("board_cnt"));
                boardVo.setBoard_content(rs.getString("board_content"));

                boardList.add(boardVo);
            }


        } catch (SQLException e) {
            boardList = null;
            e.printStackTrace();
        } finally {
            disConnect();
        }
        return boardList;
    }

    @Override
    public int setCountIncrement(int boardNo) {
        int cnt = 0;

        try {
            conn = DBUtil.getConnection();

            String sql = "update jdbc_board "
                    + " set board_cnt = board_cnt + 1 "
                    + "	where board_no = ? ";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, boardNo);

            cnt = pstmt.executeUpdate();

        } catch (SQLException e) {
            cnt = 0;
            e.printStackTrace();
        } finally {
            disConnect();
        }


        return cnt;
    }

}
