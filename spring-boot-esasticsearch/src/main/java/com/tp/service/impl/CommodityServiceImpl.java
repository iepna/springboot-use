package com.tp.service.impl;

import com.tp.dao.CommodityRepository;
import com.tp.entity.Commodity;
import com.tp.service.CommodityService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.search.MatchQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@Service
public class CommodityServiceImpl implements CommodityService {
    @Autowired
    private CommodityRepository commodityRepository;

    @Override
    public long count() {
        return commodityRepository.count();
    }

    @Override
    public Commodity save(Commodity commodity) {
        try {
            return commodityRepository.save(commodity);
        }catch (Exception e){
            log.error("保存出错",e);
        }
        return null;
    }

    @Override
    public void delete(Commodity commodity) {
        try {
            commodityRepository.delete(commodity);
        }catch (Exception e){
            log.error("删除出错",e);
        }
    }

    @Override
    public Iterable<Commodity> getAll() {
        try {
            return commodityRepository.findAll();
        }catch (Exception e){
            log.error("查询出错",e);
        }
        return null;
    }

    @Override
    public List<Commodity> getByName(String name) {
        List<Commodity> list = new ArrayList<>();
        MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder("name",name);
        Iterable<Commodity> iterable = commodityRepository.search(matchQueryBuilder);
        iterable.forEach(e->list.add(e));
        return list;
    }

    @Override
    public Page<Commodity> pageQuery(Integer pageNo, Integer pageSize, String kw) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(QueryBuilders.matchPhraseQuery("name",kw))
                .withPageable(PageRequest.of(pageNo,pageSize))
                .build();
        return commodityRepository.search(searchQuery);
    }
}
