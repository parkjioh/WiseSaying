package com.back;

import com.back.domain.system.controller.SystemController;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    private final Scanner sc = new Scanner(System.in);
    private int lastId = 0;
    private final List<WiseSaying> wiseSayings = new ArrayList<>();

    public void run() {
        System.out.println("==명언 앱 ==");

        SystemController systemController = new SystemController();

        while(true){
            System.out.print("명령) ");
            String cmd = sc.nextLine().trim();
            Rq rq = new Rq(cmd);

            switch (rq.getactionName()){
                case "종료":
                    systemController.actionExit();

                    return;
                case "목록":
                    actionList();
                    break;
                case "등록":
                    actionWrite();
                    break;
                case "삭제":
                    actionDelete(rq);
                    break;
                case "수정":
                    actionModify(rq);
                    break;
            }


        }

    }

    private void actionList() {
        System.out.println("번호 / 작가 / 명언 ");
        System.out.println("-------------------------");

        List<WiseSaying> forListWiseSayings = findForList();

        for (WiseSaying wiseSaying : forListWiseSayings) {
            System.out.printf("%d / %s / %s \n", wiseSaying.getId(), wiseSaying.getAuthor(), wiseSaying.getContent());
        }
    }

    private void actionWrite() {
        System.out.print("명언 : ");
        String wiseSayingContent = sc.nextLine().trim();
        System.out.print("작가 : ");
        String wiseSayingAuthor = sc.nextLine().trim();


        WiseSaying wiseSaying = write(wiseSayingContent, wiseSayingAuthor);
        System.out.println( "%d번 명언이 등록되었습니다.".formatted(wiseSaying.getId()));

    }

    private WiseSaying write(String content, String author){
        WiseSaying wiseSaying = new WiseSaying(++lastId,content,author);

        wiseSayings.add(wiseSaying);

        return wiseSaying;
    }


    private List<WiseSaying> findForList() {
      return wiseSayings.reversed();
    }

    private void actionDelete (Rq rq){


        int id = rq.getParamAsInt("id",-1);

        if(id == -1){
            System.out.println("id를 숫자로 입력해주세요");
            return;
        }

        boolean deleted = delete(id);

        if (!deleted ){
            System.out.println("%d번 명언은 존재하지 않습니다.".formatted(id));
            return;
        }

        System.out.println("%d번 명언이 삭제되었습니다.".formatted(id));
    }

    private boolean delete(int id) {
         return wiseSayings.removeIf(wiseSaying -> wiseSaying.getId() == id);

    }

    /*
        int deletedIndex = findIndexById(id);
        if (deletedIndex == -1 ) return deletedIndex;
        wiseSayings.remove(deletedIndex);
        return deletedIndex;

         기존 로직보다 성능은 안 좋지만 가독성이 좋음
        실무에서는 이런 선택을 하면 안된다 ...
        removeif는 boolean을 리턴함
        wiseSayings.removeIf(wiseSaying -> wiseSaying.getId() == id);
          private boolean delete(int id) {
             return wiseSayings.removeIf(wiseSaying -> wiseSaying.getId() == id);
        }
       */

    private void actionModify (Rq rq){
        int id = rq.getParamAsInt("id",-1);

       WiseSaying wiseSaying = findById(id);

        if(wiseSaying == null ){
            System.out.println("%d번 명언은 존재하지 않습니다.".formatted(id));
            return;
        }

        System.out.printf("명언(기존) : %s\n", wiseSaying.getContent());
        System.out.print("명언 : ");
        String content = sc.nextLine().trim();

        System.out.printf("작가(기존) : %s\n", wiseSaying.getAuthor());
        System.out.print("작가 : ");
        String author = sc.nextLine().trim();

        wiseSaying.setContent(content);
        wiseSaying.setAuthor(author);

        //수정할 때 수정이 불가능함 ->  setter
        System.out.println("%d번 명언은 수정되었습니다.".formatted(id));
    }

    private WiseSaying findById(int id){
        return wiseSayings
                .stream()
                .filter(wiseSaying -> wiseSaying.getId() == id)
                .findFirst()
                .orElse(null);
    }

    /*
    private int findIndexById(int id){
        return        IntStream.range(0, wiseSayings.size())0
                .filter(index -> wiseSayings.get(index).getId() == id)
                .findFirst()
                .orElse(-1);

    }*/
} 
