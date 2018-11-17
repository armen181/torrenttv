package net.ddns.armen181.torrenttv.repository;

import net.ddns.armen181.torrenttv.domain.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

    Optional<Category> findByCategoryIdOnApi(Integer id);

}
