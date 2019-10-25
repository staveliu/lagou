package com.newer.lagou.service;

import com.newer.lagou.domain.Founders;
import com.newer.lagou.mapper.FounderMapper;
import com.newer.lagou.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.BASE64Decoder;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * 创始人事务
 */
@Service
public class FounderService {
    @Autowired
    private FounderMapper founderMapper;

    @Autowired
    private UsersMapper usersMapper;

    //该方法头像暂未处理
    @Transactional(propagation = Propagation.REQUIRED,
            isolation = Isolation.DEFAULT,
            rollbackFor = Exception.class)
    public int addFounder(Founders founder,String username){
//        System.out.println(founder);
        int userid=usersMapper.findUserid(username);
        founder.setCompanyid(userid);
        founderMapper.addFounder(founder);
        int id=founderMapper.selectnewid();//新增的id
        String path="E:/tools/nginx-1.14.0/html/lagou/style/images/founder"+id+".jpg";
        if(!"".equals(founder.getFimage())) {
            generateImage(founder.getFimage().substring(23, founder.getFimage().length()), path);
//        System.out.println(founder.getFimage().substring(23,founder.getFimage().length()));
            founderMapper.updateFounderImage("lagou/style/images/founder" + id + ".jpg", id);
        }
        return 0;
    }

    public List<Founders> findFounderById(int companyid){
        return founderMapper.findFounderById(companyid);
    }




    public static boolean generateImage(String imgStr, String path) {
        if (imgStr == null)
            return false;
        BASE64Decoder decoder = new BASE64Decoder();

        try {
            // 解密
            byte[] b = decoder.decodeBuffer(imgStr);
            // 处理数据
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            OutputStream out = new FileOutputStream(path);
            out.write(b);
            out.flush();
            out.close();
            return true;
        }catch(Exception e) {
            return false;
        }
    }
}
