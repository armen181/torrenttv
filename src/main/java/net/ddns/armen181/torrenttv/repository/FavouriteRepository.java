package net.ddns.armen181.torrenttv.repository;

import net.ddns.armen181.torrenttv.domain.Category;
import net.ddns.armen181.torrenttv.domain.Favourite;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FavouriteRepository extends CrudRepository<Favourite, Long> {

    Optional<Favourite> findByName(String name);

}
