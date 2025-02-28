package boards.service;

import jdbc.advanced.boards.vo.JdbcBoardVO;

import java.util.List;

public interface IJdbcBoardService {
    /**
     * JdbcBoardVO에 담겨진 자료를 DB에 insert하는 메서드
     *
     * @param boardVo DB에 insert할 자료가 저장된 JdbcBoardVO객체
     * @return 작업성공 : 1, 작업실패 : 0
     */
    public int insertBoard(JdbcBoardVO boardVo);

    /**
     * 게시글 번호를 인수값으로 받아서 해당 게시글을 삭제하는 메서드
     *
     * @param boardNo 삭제할 게시글 번호
     * @return	작업성공 : 1, 작업실패 : 0
     */
    public int deleteBoard(int boardNo);

    /**
     * 하나의 JdbcBoardVO자료를 이용하여 DB에 update하는 메서드
     *
     * @param boardVo update할 게시글 정보가 저장된 JdbcBoardVO객체
     * @return 작업성공 : 1, 작업실패 : 0
     */
    public int updateBoard(JdbcBoardVO boardVo);

    /**
     * DB에서 전체 게시글 목록을 가져오는 메서드
     * @return JdbcBoardVO객체를 담고 있는 List객체
     */
    public List<JdbcBoardVO> getAllBoardList();

    /**
     * 게시글 번호를 인수값으로 받아서 해당 게시글 정보를 가져오는 메서드
     *
     * @param boardNo 가져올 게시글의 게시글 번호
     * @return	게시글 번호에 맞는 자료가 있으면 해당 게시글 정보를
     * 			담고 있는 JdbcBoardVO객체, 자료가 없으면 null 반환
     */
    public JdbcBoardVO getBoard(int boardNo);

    /**
     * 게시글의 제목을 인수값으로 받아서 게시글을 검색하는 메서드
     *
     * @param title 검색할 게시글의 제목
     * @return 검색한 결과가 저장된 List객체
     */
    public List<JdbcBoardVO> getSearchBoardList(String title);

    /**
     * 게시글 번호를 인수값으로 받아서 해당 게시글의 조회수를 증가시키는 메서드
     *
     * @param boardNo 조회수를 증가할 게시글 번호
     * @return 작업성공 : 1, 작업실패 : 0
     */
    public int setCountIncrement(int boardNo);
}
