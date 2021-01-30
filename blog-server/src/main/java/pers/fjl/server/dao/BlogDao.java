package pers.fjl.server.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import pers.fjl.common.po.Blog;
import pers.fjl.common.vo.BlogVo;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author fangjiale
 * @since 2021-01-28
 */
@Repository
public interface BlogDao extends BaseMapper<Blog> {

    /**
     * 获取对应用户的博客列表(多表查询)
     *
     * @param
     * @return List
     */
    @Select("SELECT b.blog_id, t.type_name, b.recommend, b.flag, b.update_time, b.title, b.flag " +
            "FROM blog b,type t " +
            "WHERE b.type_id = t.type_id AND b.uid = #{uid} LIMIT #{start},#{pageSize} ")
    List<BlogVo> getAllBlogs(Long uid, Integer start, Integer pageSize);

    /**
     * 获取首页博客列表(多表查询)
     *
     * @param
     * @return List
     */
    @Select("SELECT b.blog_id, u.nickname, t.type_name, b.views, b.description, b.create_time ,b.recommend, b.flag, b.update_time, b.title, b.flag, b.first_picture " +
            "FROM blog b, user u, type t  " +
            "WHERE b.uid = u.uid AND b.type_id = t.type_id ORDER BY b.create_time DESC " +
            "LIMIT #{start},#{pageSize} ")
    List<BlogVo> findHomePage(Integer start, Integer pageSize);
}
