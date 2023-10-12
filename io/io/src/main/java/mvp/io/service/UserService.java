package mvp.io.service;

import lombok.RequiredArgsConstructor;
import mvp.io.domain.User.UserSaveDto;
import mvp.io.domain.User.Users;
import mvp.io.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public Long save(UserSaveDto user){
        return userRepository.save(user.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, UserSaveDto user){
        Users findUser = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        System.out.println("findUser.getPoint() = " + findUser.getPoint());
        findUser.update(user.getName(), user.getAddress(), user.getPicture());
        return id;
    }

    @Transactional
    public void delete(Long id){
        Users findUser = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다 id = " + id));
        userRepository.delete(findUser);
    }

    public Users findById(Long id){
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(("해당 게시글이 없습니다. id=" + id)));
        return user;
    }

    public Users findByEmail(String  email){
        Users user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException(("해당 게시글이 없습니다. email=" + email)));
        return user;
    }

    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

}
