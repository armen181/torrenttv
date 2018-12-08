package net.ddns.armen181.torrenttv.repository;

import net.ddns.armen181.torrenttv.domain.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class ChannelsDao {

    @Autowired
    EntityManager em;

    public ChannelsDao(EntityManager em) {
        this.em = em;
    }

    public List<Channel> findChannelsByCategory(Integer  group) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Channel> cq = cb.createQuery(Channel.class);
        Root<Channel> channelsRoot = cq.from(Channel.class);
        Predicate authorNamePredicate = cb.equal(channelsRoot.get("groupCategory"), group);
        cq.where(authorNamePredicate);
        TypedQuery<Channel> query = em.createQuery(cq);
        return query.getResultList();
    }

//    public List<Channel> findChannelsByName(String  name) {
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Channel> cq = cb.createQuery(Channel.class);
//        Root<Channel> channelsRoot = cq.from(Channel.class);
//        Predicate authorNamePredicate = cb.equal(channelsRoot.get("name"), name);
//        cq.where(authorNamePredicate);
//        TypedQuery<Channel> query = em.createQuery(cq);
//        return query.getResultList();
//    }


}
