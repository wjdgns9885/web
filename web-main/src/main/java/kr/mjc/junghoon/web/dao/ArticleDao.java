package kr.mjc.junghoon.web.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ArticleDao {

  private static final String LIST_ARTICLES = """
      select articleId, title, userId, name, left(cdate,16) cdate
      from article order by articleId desc limit ?,?""";

  private static final String GET_ARTICLE = """
      select articleId, title, content, userId, name,
        left(cdate,16) cdate, left(udate,16) udate
      from article where articleId=?""";

  private static final String ADD_ARTICLE = """
      insert article(title, content, userId, name)
      values(:title, :content, :userId, :name)""";

  private static final String UPDATE_ARTICLE = """
      update article set title=:title, content=:content
      where articleId=:articleId and userId=:userId""";

  private static final String DELETE_ARTICLE =
      "delete from article where articleId=:articleId and userId=:userId";

  private JdbcTemplate jdbcTemplate;

  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  private RowMapper<Article> rowMapper =
      new BeanPropertyRowMapper<>(Article.class);

  public ArticleDao(JdbcTemplate jdbcTemplate,
                    NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
    this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
  }

  public List<Article> listArticles(int offset, int count) {
    return jdbcTemplate.query(LIST_ARTICLES, rowMapper, offset, count);
  }

  public Article getArticle(int articleId) {
    return jdbcTemplate.queryForObject(GET_ARTICLE, rowMapper, articleId);
  }

  public void addArticle(Article article) {
    namedParameterJdbcTemplate
        .update(ADD_ARTICLE, new BeanPropertySqlParameterSource(article));
  }

  public int updateArticle(Article article) {
    return namedParameterJdbcTemplate
        .update(UPDATE_ARTICLE, new BeanPropertySqlParameterSource(article));
  }

  public int deleteArticle(int articleId, int userId) {
    Map<String, Object> params = new HashMap<>();
    params.put("articleId", articleId);
    params.put("userId", userId);
    return namedParameterJdbcTemplate.update(DELETE_ARTICLE, params);
  }
}
