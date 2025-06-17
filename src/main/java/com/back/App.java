package com.back;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    private Scanner sc = new Scanner(System.in);
    private int lastId = 0;
    private List<WiseSaying> wiseSayings = new ArrayList<>();

    public void run() {
        System.out.println("==명언 앱 ==");

        while(true){
            System.out.print("명령) ");
            String cmd = sc.nextLine().trim();

            if(cmd.equals("종료")){
                break;
            }else if(cmd.equals("등록")){
                actionWrite();
            }
            else if(cmd.equals("목록")){
                actionList();
            }else if(cmd.startsWith("삭제")){
                actionDelete(cmd);
            }else if(cmd.startsWith("수정")){
                actionModify(cmd);
            }
        }

        sc.close();

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

    private void actionDelete (String cmd){
        String[] cmdBits = cmd.split("=",2);

        if(cmdBits.length < 2 || cmdBits[1].isEmpty() ){
            System.out.println("id를 입력해주세요.");
            return;
        }

        int id = Integer.parseInt(cmdBits[1]);

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

    private void actionModify (String cmd){
        String[] cmdBit = cmd.split("=",2);

        if (cmdBit.length < 2 || cmdBit[1].isEmpty() ){
            System.out.println("id를 입력해주세요.");
            return;
        }

        int id = Integer.parseInt(cmdBit[1]);

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
