package com.back;

import java.util.Scanner;

public class App {

    private Scanner sc = new Scanner(System.in);
    private int lastId = 0;
    private WiseSaying[] wiseSayings = new WiseSaying[100];
    private int wiseSayingsLastIndex = -1; //가장 마지막에 쓴게 -1 사용자는 0부터 쓸수있다.

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

        WiseSaying[] forListWiseSayings = findForList();

        for (WiseSaying wiseSaying : forListWiseSayings) {
            System.out.printf("%d / %s / %s \n", wiseSaying.id, wiseSaying.author, wiseSaying.content);
        }
    }

    private void actionWrite() {
        System.out.print("명언 : ");
        String wiseSayingContent = sc.nextLine().trim();
        System.out.print("작가 : ");
        String wiseSayingAuthor = sc.nextLine().trim();


        WiseSaying wiseSaying = write(wiseSayingContent, wiseSayingAuthor);
        System.out.println( "%d번 명언이 등록되었습니다.".formatted(wiseSaying.id));

    }

    private WiseSaying write(String content, String author){
        WiseSaying wiseSaying = new WiseSaying(++lastId,content,author);

        wiseSayings[++wiseSayingsLastIndex] = wiseSaying;

        return wiseSaying;
    }

    private int getSize() {
        return wiseSayingsLastIndex + 1;
    }

    private WiseSaying[] findForList() {
        WiseSaying[] forListWiseSayings = new WiseSaying[getSize()];

        int forListWiseSayingsIndex = -1;

        for (int i=wiseSayingsLastIndex; i>=0;i--){
            forListWiseSayings[++forListWiseSayingsIndex] = wiseSayings[i];
        }

        return forListWiseSayings;
    }

    private void actionDelete (String cmd){
        String[] cmdBits = cmd.split("=",2);

        if(cmdBits.length < 2 || cmdBits[1].isEmpty() ){
            System.out.println("id를 입력해주세요.");
            return;
        }

        int id = Integer.parseInt(cmdBits[1]);

        int deletedIndex = delete(id);

        if (deletedIndex == -1 ){
            System.out.println("%d번 명언은 존재하지 않습니다.".formatted(id));
            return;
        }

        System.out.println("%d번 명언이 삭제되었습니다.".formatted(id));
    }

    private int delete(int id) {
        int deletedIndex = findIndexById(id);

        if (deletedIndex == -1 ) return deletedIndex;

        for(int i = deletedIndex+1;i <= wiseSayingsLastIndex; i++){
            wiseSayings[i-1] = wiseSayings[i];
        }

        wiseSayings[wiseSayingsLastIndex] = null;
        wiseSayingsLastIndex--;

        return deletedIndex;

    }

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

        System.out.printf("명언(기존) : %s\n", wiseSaying.content);
        System.out.print("명언 : ");
        String content = sc.nextLine().trim();

        System.out.printf("작가(기존) : %s\n", wiseSaying.author);
        System.out.print("작가 : ");
        String author = sc.nextLine().trim();

        wiseSaying.content = content;
        wiseSaying.author = author;

        System.out.println("%d번 명언은 수정되었습니다.".formatted(id));
    }

    private WiseSaying findById(int id){
       int index = findIndexById(id);

       if (index == -1) return null;

       return wiseSayings[index];
    }

    private int findIndexById(int id){
        for(int i=0; i<=wiseSayingsLastIndex;i++){
            if(wiseSayings[i].id == id) return i;
        }
        return -1;
    }
}

