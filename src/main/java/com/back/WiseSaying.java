package com.back;

import com.back.domain.AppContext;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WiseSaying {
    private int id;
    private String content;
    private String author;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private static DateTimeFormatter forPrintDateTimeFormatter = DateTimeFormatter.ofPattern("yy-MM-dd HH:mm:ss");

    //자바에서 내부 변수를 public 하는 경우는 거의 X
    //private가 디폴트
    //외부에서 접근하는 함수를 하나 만듬
    // 직접 접근이막히니 간접 접근을 만들어 주는 것

    public WiseSaying(String author, String content) {
        this.author = author;

        this.content = content;
    }

    public boolean isNew() {
        return getId() == 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(LocalDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getForPrintCreateDate() {
        return createDate.format(AppContext.forPrintDateTimeFormatter);
    }

    public String getForPrintModifyDate() {
        return modifyDate.format(AppContext.forPrintDateTimeFormatter);
    }
}