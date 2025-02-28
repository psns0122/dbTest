package boards.controller;

import jdbc.advanced.boards.service.IJdbcBoardService;
import jdbc.advanced.boards.service.JdbcBoardServiceImple;
import jdbc.advanced.boards.vo.JdbcBoardVO;

import java.util.List;
import java.util.Scanner;

public class JdbcBoardController {
    private IJdbcBoardService service;
    private Scanner scan;

    public JdbcBoardController() {
        service = JdbcBoardServiceImple.getInstance();
        scan = new Scanner(System.in);
    }

    public static void main(String[] args) {
        new JdbcBoardController().boardStart();
    }

    public void boardStart(){
        String title = null;
        while(true){
            int choice = displayMenu(title);
            title = null;
            switch(choice){
                case 1 :	// 새글 작성
                    insertBoard(); break;
                case 2 : 	// 게시글 보기
                    viewBoard(); break;
                case 3 : 	// 검색
                    title = searchBoard(); break;
                case 0 :  	// 작업 끝...
                    System.out.println();
                    System.out.println("게시판 프로그램을 종료합니다.");
                    return;
                default :
                    System.out.println("작업 번호를 잘못 입력했습니다. 다시 입력하세요.");
            }
        }

    }

    // 게시글의 검색 작업  ==> 검색할 단어를 입력 받아서 반환하는 메서드
    private String searchBoard(){
        scan.nextLine();  // 입력 버퍼 비우기
        System.out.println();
        System.out.println("검색 작업");
        System.out.println("--------------------------------------------");
        System.out.print("- 검색할 제목 입력 : ");
        String title = scan.nextLine();
        return title;
    }


    // 게시글의 내용을 보여주는 메서드
    private void viewBoard(){
        System.out.println();
        System.out.print("보기를 원하는 게시물 번호 입력 >> ");
        int boardNo = scan.nextInt();

        JdbcBoardVO boardVo = service.getBoard(boardNo);

        if(boardVo == null){
            System.out.println(boardNo + "번의 게시글이 존재하지 않습니다.");
            return;
        }

        int num;
        do{
            System.out.println();
            System.out.println("------------------------------------------------------------");
            System.out.println("- 제  목 : " + boardVo.getBoard_title());
            System.out.println("- 작성자 : " + boardVo.getBoard_writer());
            System.out.println("- 내  용 : " + boardVo.getBoard_content());
            System.out.println("- 작성일 : " + boardVo.getBoard_date());
            System.out.println("- 조회수 : " + boardVo.getBoard_cnt());
            System.out.println("-------------------------------------------------------------");
            System.out.println("메뉴 : 1. 수정    2. 삭제    3. 리스트로 가기");
            System.out.print("작업선택 >> ");
            num = scan.nextInt();

            switch(num){
                case 1 : // 수정
                    updateBoard(boardNo); break;
                case 2 : // 삭제
                    deleteBoard(boardNo); break;
                case 3 : // 리스트로 가기
                    return;
                default :
                    System.out.println("작업 번호는 1번~3번 사이만 입력하세요.");
                    System.out.println("다시 입력하세요.");
            }

        }while(num<1 || num>3);
    }

    // 게시글을 삭제하는 메서드
    private void deleteBoard(int boardNo){
        int cnt = service.deleteBoard(boardNo);
        if(cnt>0){
            System.out.println(boardNo + "번글이 삭제되었습니다.");
        }else{
            System.out.println(boardNo + "번글 삭제 작업 실패!!!");
        }
    }


    // 게시글의 제목과 내용을 수정하는 메서드
    private void updateBoard(int boardNo){
        scan.nextLine(); // 입력 버퍼 비우기
        System.out.println();
        System.out.println("수정 작업하기");
        System.out.println("-----------------------------------");
        System.out.print("- 제 목 : ");
        String title = scan.nextLine();

        System.out.print("- 내 용 : ");
        String content = scan.nextLine();

        JdbcBoardVO boardVo = new JdbcBoardVO();
        boardVo.setBoard_no(boardNo);
        boardVo.setBoard_title(title);
        boardVo.setBoard_content(content);

        int cnt = service.updateBoard(boardVo);

        if(cnt>0){
            System.out.println(boardNo + "번글이 수정되었습니다. ");
        }else{
            System.out.println(boardNo + "번글 수정 작업 실패!!!");
        }

    }



    // 새글을 작성하는 메서드
    private void insertBoard(){
        scan.nextLine();  // 입력 버퍼 비우기
        System.out.println();
        System.out.println("새글 작성하기");
        System.out.println("--------------------------------------------");
        System.out.print("- 제    목 : ");
        String title = scan.nextLine();
        System.out.print("- 작 성 자 : ");
        String writer = scan.nextLine();
        System.out.print("- 내    용 : ");
        String content = scan.nextLine();

        JdbcBoardVO boardVo = new JdbcBoardVO();
        boardVo.setBoard_title(title);
        boardVo.setBoard_writer(writer);
        boardVo.setBoard_content(content);

        int cnt = service.insertBoard(boardVo);

        if(cnt>0){
            System.out.println("새글이 추가되었습니다...");
        }else{
            System.out.println("새글 추가 실패!!!");
        }


    }



    // 게시판 목록을 보여주고 메뉴를 나타내며
    // 사용자가 입력한 작업번호를 반환하는 메서드
    private int displayMenu(String title){

        List<JdbcBoardVO> boardList = null;
        if(title==null){
            boardList = service.getAllBoardList();
        }else{
            boardList = service.getSearchBoardList(title);
        }


        System.out.println();
        System.out.println("-------------------------------------------------------------");
        System.out.println(" No         제 목            작성자          조회수");
        System.out.println("-------------------------------------------------------------");

        if(boardList==null || boardList.size()==0){
            System.out.println("     출력할 게시글이 하나도 없습니다...");
        }else{
            for(JdbcBoardVO boardVo : boardList){
                System.out.println(
                        boardVo.getBoard_no() + "\t" +
                                boardVo.getBoard_title() + "\t" +
                                boardVo.getBoard_writer() + "\t" +
                                boardVo.getBoard_cnt()
                );
            }
        }
        System.out.println("-------------------------------------------------------------");
        System.out.println("메뉴 : 1. 새글작성     2. 게시글보기    3. 검색    0. 작업끝");
        System.out.print("작업 선택 >> ");
        int num = scan.nextInt();

        return num;
    }

}
