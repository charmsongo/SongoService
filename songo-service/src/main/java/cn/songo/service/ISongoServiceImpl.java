package cn.songo.service;

import cn.songo.bean.Songo;

/**
 * @Author SonGo
 * @Create 2020/9/30 16:25
 * @Description
 */
public class ISongoServiceImpl implements ISongoService {
    public Songo queryById(int id) {
        Songo songo = new Songo();
        songo.setId(id);
        songo.setName("songo");
        songo.setAddress("www.baidu.com");
        return songo;
    }
}
