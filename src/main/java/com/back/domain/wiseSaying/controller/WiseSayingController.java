package com.back.domain.wiseSaying.controller;

import com.back.Rq;
import com.back.WiseSaying;
import com.back.domain.system.service.WiseSayingService;

import java.util.List;
import java.util.Scanner;

public class WiseSayingController {
    private final Scanner sc;
    private final WiseSayingService wiseSayingservice;

    public WiseSayingController(Scanner sc){
        this.sc = sc;
        this.wiseSayingservice = new WiseSayingService();
    }

    public void actionList() {
        System.out.println("번호 / 작가 / 명언 ");
        System.out.println("-------------------------");

        List<WiseSaying> forListWiseSayings = wiseSayingservice.findForList();

        for (WiseSaying wiseSaying : forListWiseSayings) {
            System.out.printf("%d / %s / %s \n", wiseSaying.getId(), wiseSaying.getAuthor(), wiseSaying.getContent());
        }
    }

    public void actionWrite() {
        System.out.print("명언 : ");
        String wiseSayingContent = sc.nextLine().trim();
        System.out.print("작가 : ");
        String wiseSayingAuthor = sc.nextLine().trim();

        WiseSaying wiseSaying = wiseSayingservice.write(wiseSayingContent, wiseSayingAuthor);
        System.out.println( "%d번 명언이 등록되었습니다.".formatted(wiseSaying.getId()));

    }

    public void actionDelete (Rq rq){
        int id = rq.getParamAsInt("id",-1);

        if(id == -1){
            System.out.println("id를 숫자로 입력해주세요");
            return;
        }

        boolean deleted = wiseSayingservice.delete(id);

        if (!deleted){
            System.out.println("%d번 명언은 존재하지 않습니다.".formatted(id));
            return;
        }

        System.out.println("%d번 명언이 삭제되었습니다.".formatted(id));
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

    public void actionModify (Rq rq){
        int id = rq.getParamAsInt("id",-1);

        if(id == -1 ){
            System.out.println("id를 숫자로 입력해주세요");
            return;
        }
        WiseSaying wiseSaying = wiseSayingservice.findById(id);

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



    /*
    private int findIndexById(int id){
        return        IntStream.range(0, wiseSayings.size())0
                .filter(index -> wiseSayings.get(index).getId() == id)
                .findFirst()
                .orElse(-1);

    }*/
}
