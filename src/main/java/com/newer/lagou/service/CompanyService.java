package com.newer.lagou.service;

import com.newer.lagou.domain.Company;
import com.newer.lagou.domain.Stage;
import com.newer.lagou.mapper.CompanyMapper;
import com.newer.lagou.mapper.UsersMapper;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.*;

@Service
public class CompanyService {
    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private UsersMapper usersMapper;

    public int addNewCompany(int userid,String email,String phone,String companyname){
        return companyMapper.addNewCompany(userid,email,phone,companyname );
    }

    public int changeLabel(int compnyid,String label){
        return companyMapper.changeLabel(compnyid,label);
    }

    public int changeDetailed(Company company){
        System.out.println(company);
        return companyMapper.changeDetailed(company);
    }

    public int changeCinformation(Company company,String stagelist,String username){

        if(!"".equals(company.getLogo())){
            String strImg=company.getLogo().substring(23,company.getLogo().length());
            String path="E:/tools/nginx-1.14.0/html/lagou/style/images/company"+company.getCompanyid()+".jpg";
            System.out.println(path);
            company.setLogo("lagou/style/company"+company.getCompanyid()+".jpg");
            generateImage(strImg,path);
        }


        JSONArray objar = new JSONArray(stagelist);
        List<Object> list = objar.toList();
        List<Stage> stages=new ArrayList<>();
        for(Object obj :list){
            Map<String,Object> map = (HashMap<String,Object>)obj;
            Set<String> sets = map.keySet();
            boolean i=true;
            Stage stage=new Stage();
            for(String key:sets){
                if("".equals(map.get(key))==false){
                    if(i){
                        stage.setStage((String) map.get(key));
                        i=false;
                    }else {
                        stage.setOrg((String) map.get(key));
                    }
                    System.out.println(key+": "+map.get(key));
                }

            }
            if(stage.getOrg()!=null&&stage.getStage()!=null){
                stage.setId(company.getCompanyid());
                stages.add(stage);//添加该公司的投资机构
                companyMapper.addIinstitution(stage);
            }
            System.out.println(stage);
        }
        return companyMapper.changeCinformation(company);
    }

    public int findCompanyid(int userid){
        return companyMapper.findCompnyid(userid);
    }



    public String findEmailByUserid(int userid){
        String email=companyMapper.findEmailByUserid(userid);
        return email;
    }

    /**
     * 通过userid查询Company
     * @param userid
     * @return
     */
    public Company findByUserid(int userid){
        return companyMapper.findByUserid(userid);
    }



    /**
     * 通过用户id删除用户信息
     * @param userid
     * @return
     */
    public int delCompany(int userid){
        return companyMapper.delCompny(userid);
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
