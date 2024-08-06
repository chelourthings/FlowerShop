//Jpa repository for performing CRuD operations on the
// 'User' entity
package com.example.ccsd.Repository;

import com.example.ccsd.Dto.UserDto;
import com.example.ccsd.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepo extends JpaRepository<User, Long> {
   User findByUsername(String username);
   User save (UserDto userDto);

   User findByEmail(String email);
}
