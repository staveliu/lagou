package com.newer.lagou.mapper;

import com.newer.lagou.domain.Company;
import com.newer.lagou.domain.Stage;
import org.apache.ibatis.annotations.*;

public interface CompanyMapper {
    @Update("update cinformation set label=#{label} where companyid=#{companyid}")
    int changeLabel(@Param("companyid") int companyid, @Param("label") String label);

    @Insert("insert into cinformation (userid,email,phone,companyname) values(#{userid},#{email},#{phone},#{companyname})")
    int addNewCompany(@Param("userid")int userid,@Param("email")String email,@Param("phone")String phone,@Param("companyname")String companyname);

    @Update("update cinformation set Detailed=#{detailed} where companyid=#{companyid}")
    int changeDetailed(Company company);

    @Update("update cinformation set cAbbreviations=#{cAbbreviations},logo=#{logo},web=#{web},city=#{city},field=#{field}," +
            "csize=#{csize},stage=#{stage},shortDetailed=#{shortDetailed} where companyid=#{companyid}")
    int changeCinformation(Company company);

    @Select("select companyid from cinformation where userid=#{id}")
    int findCompnyid(int userid);

    @Insert("insert into iinstitution values(null,#{id},#{org},#{stage})")
    int addIinstitution(Stage stage);

    @Select("select email from cinformation where userid=#{userid}")
    String findEmailByUserid(int userid);

    @Delete("delete from cinformation where userid=#{userid}")
    int delCompny(int userid);

    @Select("select * from cinformation where userid=#{userid}")
    Company findByUserid(int userid);
}
