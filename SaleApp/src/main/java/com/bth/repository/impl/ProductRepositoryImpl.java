/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bth.repository.impl;

import java.util.List;
import com.bth.pojo.Product;
import com.bth.pojo.Category;
import com.bth.pojo.Comments;
import com.bth.pojo.OrderDetail;
import com.bth.pojo.SaleOrder;
import com.bth.pojo.User;
import com.bth.repository.ProductRepository;
import com.bth.repository.UserRepository;
import java.util.ArrayList;
import java.util.Map;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author admin
 */
@Repository
@PropertySource("classpath:databases.properties")
@Transactional
public class ProductRepositoryImpl implements ProductRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    @Autowired
    private Environment env;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Product> getProducts(Map<String, String> params, int page) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Product> q = b.createQuery(Product.class);
        Root root = q.from(Product.class);
        q.select(root);

        if (params != null) {

            List<Predicate> predicates = new ArrayList<>();
            String kw = params.get("kw");
            if (kw != null && !kw.isEmpty()) {
                Predicate p = b.like(root.get("name").as(String.class),
                        String.format("%%%s%%", kw));
                predicates.add(p);
            }

            String fp = params.get("fromPrice");
            if (fp != null) {
                Predicate p = b.greaterThanOrEqualTo(root.get("price").as(Long.class),
                        Long.parseLong(fp));
                predicates.add(p);
            }

            String tp = params.get("toPrice");
            if (tp != null) {
                Predicate p = b.lessThanOrEqualTo(root.get("price").as(Long.class),
                        Long.parseLong(tp));
                predicates.add(p);
            }

            String cateId = params.get("cateId");
            if (cateId != null) {
                Predicate p = b.equal(root.get("categoryId"), Integer.parseInt(cateId));
                predicates.add(p);
            }

            q.where(predicates.toArray(new Predicate[]{}));

            q.orderBy(b.desc(root.get("id")), b.desc(root.get("name")));
        }

        Query query = session.createQuery(q);

        if (page > 0) {
            int size = Integer.parseInt(env.getProperty("page.size"));
            int start = (page - 1) * size;
            query.setFirstResult(start);
            query.setMaxResults(size);
        }

        return query.getResultList();
    }

    @Override
    public int countProuct() {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        Query q = session.createQuery("SELECT COUNT(*) FROM Product");

        return Integer.parseInt(q.getSingleResult().toString());
    }

    @Override
    public boolean addProduct(Product p) {
        Session session = this.sessionFactory.getObject().getCurrentSession();

        try {
            session.save(p);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteProduct(int id) {
        Session session = this.sessionFactory.getObject().getCurrentSession();

        try {
            Product p = session.get(Product.class, id);
            session.delete(p);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Object[]> cateStats() {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);

        Root rP = q.from(Product.class);
        Root rC = q.from(Category.class);
        q.where(b.equal(rP.get("categoryId"), rC.get("id")));

        q.multiselect(rC.get("id"), rC.get("name"), b.count(rP.get("id")));
        q.groupBy(rC.get("id"));

        Query query = session.createQuery(q);
        return query.getResultList();
    }

    @Override
    public List<Object[]> revenueStats() {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);

        Root rP = q.from(Product.class);
        Root rD = q.from(OrderDetail.class);
        Root rO = q.from(SaleOrder.class);

        q.multiselect(rP.get("id"), rP.get("name"), b.sum(b.prod(rD.get("unitPrice"), rD.get("num"))));

        Predicate p = b.equal(b.function("QUARTER", Integer.class, rO.get("createdDate")), 4);

        q.where(b.equal(rP.get("id"), rD.get("productId")),
                b.equal(rD.get("orderId"), rO.get("id")), p);

        q.groupBy(rP.get("id"));

        Query query = session.createQuery(q);
        return query.getResultList();
    }

    @Override
    public List<Comments> getComments(int productId) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        Query q = session.createQuery("FROM Comments WHERE productId.id=:productId");
        q.setParameter("productId", productId);

        return q.getResultList();
    }

    @Override
    public Product getProductById(int id) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        return session.get(Product.class, id);
    }

    @Override
    public Comments addComment(String content, int productId) {
        Session session = this.sessionFactory.getObject().getCurrentSession();

        Comments c = new Comments();
        c.setContent(content);
        c.setProductId(this.getProductById(productId));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        c.setUserId( this.userRepository.getUserByUsername(currentPrincipalName) );

        session.save(c);

        return c;
    }
}
