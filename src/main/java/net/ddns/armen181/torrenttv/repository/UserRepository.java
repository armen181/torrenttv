package net.ddns.armen181.torrenttv.repository;

import net.ddns.armen181.torrenttv.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
Optional<User> findByName(String userName);

}
