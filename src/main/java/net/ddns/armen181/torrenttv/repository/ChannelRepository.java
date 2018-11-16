package net.ddns.armen181.torrenttv.repository;

import net.ddns.armen181.torrenttv.domain.Channels;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChannelRepository extends CrudRepository<Channels,Long> {

Optional<Channels> findByChannelId (Integer id);

}
