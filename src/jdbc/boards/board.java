package jdbc.boards;

import lombok.Data;

@Data
public class board {
    private int bno;
    private String btitle;
    private String bcontent;
    private String bwriter;
    private String bdate;
    private String bfilename;
}
