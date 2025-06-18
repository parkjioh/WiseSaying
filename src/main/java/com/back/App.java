package com.back;

import com.back.domain.AppContext;
import com.back.domain.system.controller.SystemController;
import com.back.domain.wiseSaying.controller.WiseSayingController;

import java.util.Scanner;

public class App {


    public void run() {
        System.out.println("==명언 앱 ==");
        Scanner sc = AppContext.sc;
        SystemController systemController = AppContext.systemController;
        WiseSayingController wiseSayingController = AppContext.wiseSayingController;

        while(true){
            System.out.print("명령) ");
            String cmd = sc.nextLine().trim();
            Rq rq = new Rq(cmd);

            switch (rq.getactionName()){
                case "종료":
                    systemController.actionExit();

                    return;
                case "목록":
                    wiseSayingController.actionList();
                    break;
                case "등록":
                    wiseSayingController.actionWrite();
                    break;
                case "삭제":
                    wiseSayingController.actionDelete(rq);
                    break;
                case "수정":
                    wiseSayingController.actionModify(rq);
                    break;
            }


        }

    }


} 
