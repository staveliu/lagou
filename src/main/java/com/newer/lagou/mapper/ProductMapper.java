package com.newer.lagou.mapper;

import com.newer.lagou.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ProductMapper {
    @Insert("insert into prinformation values(null,null,#{productname},#{pintroduction},#{companyid},#{productaddress})")
    int addProduct(Product product);

    @Update("update prinformation set Posters=#{poster} where Productid=#{id}")
    int updateProductPosters(@Param("poster") String poster, @Param("id")int id);

    @Select("select @@identity as NewID")
    int selectnewid();

    @Select("select * from prinformation where companyid=#{id}")
    List<Product> findProduct(int id);
}
