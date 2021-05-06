package kr.mjc.junghoon.web.mvc;


import kr.mjc.junghoon.web.dao.Article;
import kr.mjc.junghoon.web.dao.ArticleDao;
import kr.mjc.junghoon.web.dao.User;
import kr.mjc.junghoon.web.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class ArticleController {

    private final ArticleDao articleDao;
    private final UserDao userDao;

    @Autowired
    public ArticleController(ArticleDao articleDao, UserDao userDao) {
        this.articleDao = articleDao;
        this.userDao = userDao;
    }

    public void loginForm(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/jsp/mvc/article/loginForm.jsp")
                .forward(request, response);

    }

    /**
     * 로그인 액션
     */
    public void login(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            User user = userDao.login(email, password);
            HttpSession session = request.getSession();
            session.setAttribute("USER", user);
            response.sendRedirect(request.getContextPath() + "/mvc/article/articleForm");
        } catch (EmptyResultDataAccessException e) {
            response.sendRedirect(request.getContextPath() +
                    "/mvc/article/loginForm?msg=Wrong email or password");
        }
    }

    public void articleForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/jsp/mvc/article/articleForm.jsp")
                .forward(request, response);
    }

    /**
     * 글 목록 화면
     */
    public void articleList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("articleList", articleDao.listArticles(0, 100));

        request.getRequestDispatcher("/WEB-INF/jsp/mvc/article/articleList.jsp")
                .forward(request, response);
    }

    public void getArticle(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("getArticle", articleDao.getArticle(Integer.parseInt(request.getParameter("articleId"))));

        request.getRequestDispatcher("/WEB-INF/jsp/mvc/article/getArticle.jsp")
                .forward(request, response);
    }
    /**
     * 글 상세보기 화면
     */
    public void getForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/jsp/mvc/article/getArticleForm.jsp")
                .forward(request, response);
    }

    public void addArticle(HttpServletRequest request, HttpServletResponse response)
            throws  IOException {
        Article article = new Article();
        article.setTitle(request.getParameter("title"));
        article.setContent(request.getParameter("content"));
        article.setUserId(Integer.parseInt(request.getParameter("userId")));
        article.setName(request.getParameter("name"));
        articleDao.addArticle(article);
        response.sendRedirect(request.getContextPath() + "/mvc/article/articleList");
    }

    /**
     * 글 등록 화면
     */
    public void addForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/jsp/mvc/article/addArticleForm.jsp")
                .forward(request, response);
    }

    public void updateArticle(HttpServletRequest request, HttpServletResponse response)
            throws  IOException {
        Article article = new Article();
        article.setArticleId(Integer.parseInt(request.getParameter("articleId")));
        article.setTitle(request.getParameter("title"));
        article.setContent(request.getParameter("content"));
        article.setUserId(Integer.parseInt(request.getParameter("userId")));
        articleDao.updateArticle(article);
        response.sendRedirect(request.getContextPath() + "/mvc/article/articleList");
    }

    /**
     * 업데이트 화면
     */
    public void updateForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/jsp/mvc/article/updateArticleForm.jsp")
                .forward(request, response);
    }

    public void deleteArticle(HttpServletRequest request, HttpServletResponse response)
            throws  IOException {
        articleDao.deleteArticle(Integer.parseInt(request.getParameter("articleId")),
                                 Integer.parseInt(request.getParameter("userId")));
        response.sendRedirect(request.getContextPath() + "/mvc/article/articleList");
    }

    /**
     * 글 삭제 화면
     */
    public void deleteForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/jsp/mvc/article/deleteArticleForm.jsp")
                .forward(request, response);
    }
}