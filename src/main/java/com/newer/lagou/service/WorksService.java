package com.newer.lagou.service;

import com.newer.lagou.domain.Works;
import com.newer.lagou.mapper.WorksMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorksService {

    @Autowired
    private WorksMapper worksMapper;

    public List<Works> findWorks(int resumeid){
        return worksMapper.findWorks(resumeid);
    }

    public int addWorks(int resummeid,String link,String describe){
        return worksMapper.addWorks(resummeid,link,describe);
    }

    public int delWorks(int id){
        return worksMapper.delWorks(id);
    }

}
