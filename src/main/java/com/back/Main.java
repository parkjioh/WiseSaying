package com.back;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        
        System.out.println("==명언 앱 ==");
        
        Scanner sc = new Scanner(System.in);
        int lastId = 0;
        WiseSaying wiseSaying = null;

        while(true){

            System.out.print("명령) ");
            String cmd = sc.nextLine().trim();



            if(cmd.equals("종료")){
                break;
            }else if(cmd.equals("등록")){
                System.out.print("명언 : ");
                String wiseSayingContent = sc.nextLine().trim();
                System.out.print("작가 : ");
                String wiseSayingAuthor = sc.nextLine().trim();

                int id = ++lastId;

                wiseSaying = new WiseSaying(); // while문 안에 안 넣으면 계속 같은 객체만 덮어씀!!! 
                wiseSaying.id = id;
                wiseSaying.content = wiseSayingContent;
                wiseSaying.author = wiseSayingAuthor;

                System.out.println( "%d번 명언이 등록되었습니다.".formatted(wiseSaying.id));
                
            }
            else if(cmd.equals("목록")){

                if(wiseSaying == null) continue;

                System.out.println("번호 / 작가 / 명언 ");
                System.out.println("-------------------------");
                System.out.println("%d / %s / %s".formatted(wiseSaying.id, wiseSaying.author, wiseSaying.content));

            }
        }

        sc.close();


    }
}


