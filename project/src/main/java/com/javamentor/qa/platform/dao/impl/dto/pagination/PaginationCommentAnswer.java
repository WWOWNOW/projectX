package com.javamentor.qa.platform.dao.impl.dto.pagination;

import com.javamentor.qa.platform.dao.abstracts.dto.PageDtoDao;
import com.javamentor.qa.platform.models.dto.CommentDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

@Repository
public class PaginationCommentAnswer implements PageDtoDao<CommentDto> {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<CommentDto> getItems(Map<String, Object> params) {
        int itemsOnPage = (int) params.get("itemsOnPage");
        return em.createQuery(
                        "SELECT new com.javamentor.qa.platform.models.dto.CommentDto" +
                                "(c.id, c.text, c.user.id, c.user.fullName, SUM(r.count), c.persistDateTime)" +
                                "FROM CommentAnswer ca " +
                                "JOIN Comment c ON ca.comment.id = c.id " +
                                "JOIN Reputation r ON c.user.id = r.author.id " +
                                "WHERE ca.answer.id = :answerId " +
                                "GROUP BY c.id, c.user.fullName " +
                                "ORDER BY c.persistDateTime DESC ", CommentDto.class)
                .setParameter("answerId", params.get("answerId"))
                .setFirstResult(((int) params.get("currentPageNumber") - 1) * itemsOnPage)
                .setMaxResults(itemsOnPage)
                .getResultList();
    }

    @Override
    public int getTotalResultCount(Map<String, Object> params) {
        Query queryTotal = em.createQuery("SELECT COUNT(ca) FROM CommentAnswer ca WHERE ca.answer.id = :answerId ")
                .setParameter("answerId", params.get("answerId"));
        return ((Long) queryTotal.getSingleResult()).intValue();
    }
}
