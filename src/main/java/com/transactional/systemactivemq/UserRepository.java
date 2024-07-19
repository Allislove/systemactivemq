package com.transactional.systemactivemq;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository  extends MongoRepository<User, String>{
    // Creamos un metodo findByEmail para buscar usuarios con el mismo email para evitarl la duplicidad de registros
    Optional<User> findByEmail(String email);
}
