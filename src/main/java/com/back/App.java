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
            }
        }

        sc.close();

    }

    void actionList() {
        System.out.println("번호 / 작가 / 명언 ");
        System.out.println("-------------------------");

        for(int i=wiseSayingsLastIndex; i >= 0 ; i--){
            WiseSaying wiseSaying = wiseSayings[i];


            System.out.printf("%d / %s / %s \n", wiseSaying.id, wiseSaying.author, wiseSaying.content);
        }
    }

    void actionWrite() {
        System.out.print("명언 : ");
        String wiseSayingContent = sc.nextLine().trim();
        System.out.print("작가 : ");
        String wiseSayingAuthor = sc.nextLine().trim();

        int id = ++lastId;

        WiseSaying wiseSaying = new WiseSaying(); // while문 안에 안 넣으면 계속 같은 객체만 덮어씀!!!
        wiseSaying.id = id;
        wiseSaying.content = wiseSayingContent;
        wiseSaying.author = wiseSayingAuthor;
        wiseSayings[++wiseSayingsLastIndex] = wiseSaying;
        System.out.println( "%d번 명언이 등록되었습니다.".formatted(wiseSaying.id));

    }
}
