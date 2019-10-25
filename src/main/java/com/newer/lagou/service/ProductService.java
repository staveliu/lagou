package com.newer.lagou.service;

import com.newer.lagou.domain.Product;
import com.newer.lagou.mapper.ProductMapper;
import com.newer.lagou.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.BASE64Decoder;

import java.io.FileOutputStream;
import java.io.OutputStream;

@Service
public class ProductService {
    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private UsersMapper usersMapper;

    @Transactional(propagation = Propagation.REQUIRED,
            isolation = Isolation.DEFAULT,
            rollbackFor = Exception.class)
    public int addProduct(Product product,String username){
        int userid=usersMapper.findUserid(username);
        product.setCompanyid(userid);
        productMapper.addProduct(product);
        int id=productMapper.selectnewid();//新增的id
        String path="E:/tools/nginx-1.14.0/html/lagou/style/images/product"+id+".jpg";
        if(!"".equals(product.getPosters())) {
            generateImage(product.getPosters().substring(23, product.getPosters().length()), path);
//        System.out.println(founder.getFimage().substring(23,founder.getFimage().length()));
            productMapper.updateProductPosters("lagou/style/images/founder" + id + ".jpg", id);
        }
        return 0;
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
