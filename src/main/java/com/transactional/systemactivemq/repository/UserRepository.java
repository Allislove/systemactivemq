package com.transactional.systemactivemq.repository;
import com.transactional.systemactivemq.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository  extends MongoRepository<User, String>{
    // Creamos un metodo findByEmail para buscar usuarios con el mismo email para evitarl la duplicidad de registros
    Optional<User> findByEmail(String email);
}
