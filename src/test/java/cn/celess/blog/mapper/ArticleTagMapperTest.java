package cn.celess.blog.mapper;

import cn.celess.blog.BaseTest;
import cn.celess.blog.entity.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

public class ArticleTagMapperTest extends BaseTest {

    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    ArticleTagMapper articleTagMapper;
    @Autowired
    TagMapper tagMapper;


    @Test
    public void insert() {
        ArticleTag articleTag = generateArticle();
        assertNotNull(articleTag);
        assertNotNull(articleTag.getArticle());
        assertNotNull(articleTag.getTag());
    }

    @Test
    public void update() {
        Tag tag = tagMapper.findTagByName("电影");
        ArticleTag articleTag = generateArticle();
        articleTag.setTag(tag);

        articleTagMapper.update(articleTag);

        ArticleTag oneById = articleTagMapper.findOneById(articleTag.getId());
        assertEquals(articleTag.getArticle().getId(), oneById.getArticle().getId());
        assertEquals(articleTag.getArticle().getTitle(), oneById.getArticle().getTitle());
        assertEquals(articleTag.getTag().getName(), oneById.getTag().getName());
    }

    @Test
    public void deleteById() {
        ArticleTag articleTag = generateArticle();

        articleTagMapper.deleteById(articleTag.getId());

        ArticleTag oneById = articleTagMapper.findOneById(articleTag.getId());
        assertNull(oneById);

        articleTagMapper.insert(articleTag);
    }

    @Test
    public void deleteByArticleId() {
        ArticleTag articleTag = generateArticle();
        int i = articleTagMapper.deleteByArticleId(articleTag.getArticle().getId());
        assertNotEquals(0, i);
    }

    @Test
    public void findAllByArticleId() {
        ArticleTag articleTag = generateArticle();
        List<ArticleTag> allByArticleId = articleTagMapper.findAllByArticleId(articleTag.getArticle().getId());
        assertEquals(1, allByArticleId.size());

        List<ArticleTag> list = articleTagMapper.findAllByArticleId(5L);
        assertEquals(2, list.size());
    }

    @Test
    public void deleteMultiById() {
        ArticleTag articleTag = new ArticleTag();
        Article article = new Article();
        article.setId(-1L);
        Tag tag = new Tag();
        tag.setId(1L);
        articleTag.setArticle(article);
        articleTag.setTag(tag);

        articleTagMapper.insert(articleTag);
        articleTagMapper.insert(articleTag);
        articleTagMapper.insert(articleTag);
        articleTagMapper.insert(articleTag);
        articleTagMapper.insert(articleTag);
        articleTagMapper.insert(articleTag);

        List<ArticleTag> allByArticleId = articleTagMapper.findAllByArticleId(-1L);
        assertEquals(6, allByArticleId.size());
        int lines = articleTagMapper.deleteMultiById(allByArticleId);
        assertEquals(6, lines);
    }


    private ArticleTag generateArticle() {
        String randomText = UUID.randomUUID().toString();

        Article article = new Article();
        Category category = new Category();
        category.setId(1L);
        article.setCategory(category);
        article.setTitle(" unity test for  article " + randomText);
        article.setMdContent("# unity test for  article");
        article.setSummary("unity test for  article");

        User user = new User();
        user.setId(1L);
        article.setUser(user);
        article.setType(true);

        articleMapper.insert(article);

        ArticleTag articleTag = new ArticleTag();
        Tag tag = tagMapper.findTagByName("随笔");
        articleTag.setArticle(article);
        articleTag.setTag(tag);
        articleTagMapper.insert(articleTag);

        return articleTag;
    }

}