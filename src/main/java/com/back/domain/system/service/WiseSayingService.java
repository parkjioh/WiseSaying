package com.back.domain.system.service;

import com.back.WiseSaying;
import com.back.domain.AppContext;
import com.back.domain.system.repository.WiseSayingRepository;

import java.util.List;

public class WiseSayingService {
    private final WiseSayingRepository wiseSayingRepository = AppContext.wiseSayingRepository;

    public List<WiseSaying> findForList() {
        return wiseSayingRepository.findForList();
    }

    public WiseSaying write(String content, String author){
        WiseSaying wiseSaying = new WiseSaying(content,author);

        wiseSayingRepository.save(wiseSaying);

        return wiseSaying;
    }

    public WiseSaying findById(int id){
        return  wiseSayingRepository.findById(id);
    }

    public boolean delete(int id) {
        return wiseSayingRepository.deleteById(id);

    }

    public void modify(WiseSaying wiseSaying, String content, String author) {
        wiseSaying.setContent(content);
        wiseSaying.setAuthor(author);

        wiseSayingRepository.save(wiseSaying);
    }

}
