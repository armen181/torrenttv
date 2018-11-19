package net.ddns.armen181.torrenttv.repository;

import net.ddns.armen181.torrenttv.domain.Channel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChannelRepository extends CrudRepository<Channel,Long> {

Optional<Channel> findByChannelNumber (Integer id);

}
