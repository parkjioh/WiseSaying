package com.back;

import java.util.Scanner;

public class App {

    Scanner sc = new Scanner(System.in);
    int lastId = 0;
    WiseSaying[] wiseSayings = new WiseSaying[100];
    int wiseSayingsLastIndex = -1; //가장 마지막에 쓴게 -1 사용자는 0부터 쓸수있다.

    void run() {
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
            }
        }

        sc.close();

    }

    void actionList() {
        System.out.println("번호 / 작가 / 명언 ");
        System.out.println("-------------------------");

        WiseSaying[] forListWiseSayings = findForList();

        for (WiseSaying wiseSaying : forListWiseSayings) {
            System.out.printf("%d / %s / %s \n", wiseSaying.id, wiseSaying.author, wiseSaying.content);
        }
    }

    void actionWrite() {
        System.out.print("명언 : ");
        String wiseSayingContent = sc.nextLine().trim();
        System.out.print("작가 : ");
        String wiseSayingAuthor = sc.nextLine().trim();


        WiseSaying wiseSaying = write(wiseSayingContent, wiseSayingAuthor);
        System.out.println( "%d번 명언이 등록되었습니다.".formatted(wiseSaying.id));

    }

    WiseSaying write(String content, String author){
        WiseSaying wiseSaying = new WiseSaying(); // while문 안에 안 넣으면 계속 같은 객체만 덮어씀!!!
        wiseSaying.id = ++lastId;
        wiseSaying.content = content;
        wiseSaying.author = author;

        wiseSayings[++wiseSayingsLastIndex] = wiseSaying;

        return wiseSaying;
    }

    int getSize() {
        return wiseSayingsLastIndex + 1;
    }

    WiseSaying[] findForList() {
        WiseSaying[] forListWiseSayings = new WiseSaying[getSize()];

        int forListWiseSayingsIndex = -1;

        for (int i=wiseSayingsLastIndex; i>=0;i--){
            forListWiseSayings[++forListWiseSayingsIndex] = wiseSayings[i];
        }

        return forListWiseSayings;
    }

    void actionDelete (String cmd){
        String[] cmdBits = cmd.split("=",2);

        if(cmdBits.length < 2 || cmdBits[1].isEmpty() ){
            System.out.println("id를 입력해주세요.");
            return;
        }

        int id = Integer.parseInt(cmdBits[1]);

        delete(id);

        System.out.println("%d번 명언이 삭제되었습니다.".formatted(id));
    }


    void delete(int id) {
        int deleteIndex = -1;
        for(int i=0; i<=wiseSayingsLastIndex; i++) {
            if (wiseSayings[i].id == id) {
                deleteIndex = i;
                break;
            }
        }

        if (deleteIndex == -1 ) return;

        for(int i = deleteIndex+1;i <= wiseSayingsLastIndex; i++){
            wiseSayings[i-1] = wiseSayings[i];
        }

        wiseSayings[wiseSayingsLastIndex] = null;
        wiseSayingsLastIndex--;

    }
}

