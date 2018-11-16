package net.ddns.armen181.torrenttv.repository;

import net.ddns.armen181.torrenttv.domain.Channels;
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

    public List<Channels> findChannelsByCategory(Integer  group) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Channels> cq = cb.createQuery(Channels.class);
        Root<Channels> channelsRoot = cq.from(Channels.class);
        Predicate authorNamePredicate = cb.equal(channelsRoot.get("group"), group);
        cq.where(authorNamePredicate);
        TypedQuery<Channels> query = em.createQuery(cq);
        return query.getResultList();
    }

    public List<Channels> findChannelsByName(String  name) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Channels> cq = cb.createQuery(Channels.class);
        Root<Channels> channelsRoot = cq.from(Channels.class);
        Predicate authorNamePredicate = cb.equal(channelsRoot.get("name"), name);
        cq.where(authorNamePredicate);
        TypedQuery<Channels> query = em.createQuery(cq);
        return query.getResultList();
    }


}
