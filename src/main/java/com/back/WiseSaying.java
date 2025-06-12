package com.back;

public class WiseSaying {
    private int id;
    private String content;


    private String author;

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
    public String getAuthor() {
        return author;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    //자바에서 내부 변수를 public 하는 경우는 거의 X
    //private가 디폴트
    //외부에서 접근하는 함수를 하나 만듬
    // 직접 접근이막히니 간접 접근을 만들어 주는 것


    public WiseSaying(int id,String author, String content) {
        this.author = author;
        this.id = id;
        this.content = content;
    }
}
