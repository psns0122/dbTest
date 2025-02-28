package boards.vo;


import lombok.Data;

@Data
public class JdbcBoardVO {
    private int board_no;
    private String board_title;
    private String board_writer;
    private String board_date;
    private int board_cnt;
    private String board_content;

}