//package boards.service;
//
//import jdbc.advanced.boards.dao.IJdbcBoardDao;
//import jdbc.advanced.boards.dao.JdbcBoardDaoImpl;
//import jdbc.advanced.boards.vo.JdbcBoardVO;
//
//import java.util.List;
//
//public class JdbcBoardServiceImple implements IJdbcBoardService {
//    private IJdbcBoardDao dao;
//
//    private static JdbcBoardServiceImple service;
//
//    private JdbcBoardServiceImple(){
//        dao = JdbcBoardDaoImpl.getInstance();
//    }
//
//    public static JdbcBoardServiceImple getInstance(){
//        if(service==null) service = new JdbcBoardServiceImple();
//
//        return service;
//    }
//
//
//
//    @Override
//    public int insertBoard(JdbcBoardVO boardVo) {
//        return dao.insertBoard(boardVo);
//    }
//
//    @Override
//    public int deleteBoard(int boardNo) {
//        return dao.deleteBoard(boardNo);
//    }
//
//    @Override
//    public int updateBoard(JdbcBoardVO boardVo) {
//        return dao.updateBoard(boardVo);
//    }
//
//    @Override
//    public List<JdbcBoardVO> getAllBoardList() {
//        return dao.getAllBoardList();
//    }
//
//    @Override
//    public JdbcBoardVO getBoard(int boardNo) {
//
//        int cnt = dao.setCountIncrement(boardNo);  // 조회수 증가하기
//
//        if(cnt==0){  // 조회수 증가하기 실패
//            return null;
//        }
//
//        return dao.getBoard(boardNo);
//    }
//
//    @Override
//    public List<JdbcBoardVO> getSearchBoardList(String title) {
//        return dao.getSearchBoardList(title);
//    }
//
//	@Override
//	public int setCountIncrement(int boardNo) {
//		return dao.setCountIncrement(boardNo);
//	}
//
//}