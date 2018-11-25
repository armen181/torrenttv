package net.ddns.armen181.torrenttv.repository;

import net.ddns.armen181.torrenttv.domain.Channel;
import net.ddns.armen181.torrenttv.util.AccessTranslation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

@Repository
public interface ChannelRepository extends CrudRepository<Channel,Long> {

Optional<Channel> findByChannelNumber (Integer id);
Optional<List<Channel>> findAllByAccessTranslationAndGroupCategory(AccessTranslation accessTranslation, Integer group);
Optional<List<Channel>> findByGroupCategory(Integer group);
Optional<List<Channel>> findByEpgNumber(Integer epgNumber);
Optional<Channel> findByName(String name);
Optional<List<Channel>> findByLogo(String logo);
Optional<List<Channel>> findByAccessTranslation(AccessTranslation accessTranslation);



}
