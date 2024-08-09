package Matdol.SmartGazalBee.User.Controller;

import Matdol.SmartGazalBee.Common.BeeResponse;
import Matdol.SmartGazalBee.Common.ResponseBody;
import Matdol.SmartGazalBee.Common.Status;
import Matdol.SmartGazalBee.User.Domain.User;
import Matdol.SmartGazalBee.User.Domain.UserType;
import Matdol.SmartGazalBee.User.Repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @Transactional
    @GetMapping("/join/{testName}")
    public ResponseEntity<ResponseBody<User>> postJoin(@PathVariable String testName) {
        User user = new User();
        user.setNickname(testName);
        user.setName(testName);
        user.setLoginEmail(testName+"@naver.com");
        user.setLoginPw("12345678");
        user.setDeclaration(0);
        user.setUserType(UserType.purchaser);
        user.setFcmToken("eVKh4w_pSJOczv1JpKOFFt:APA91bHjggpcmeAVU0LXWV5rY7eRiPkUaMqHDDl4JrOoHtfE7H34HBfvRCZq93Rd3MYVYE_KZE5RoBLBDZR6V5wWFeR1BxCvon5bhHyJrMqC_714nNX9UL2dbcj9Nia2UrS9CPJiNsb0"); //임의로 생성

        userRepository.save(user);

        return BeeResponse.toResponse(Status.RUN, user);
    }

    @GetMapping("/login/{testId}")
    public ResponseEntity<ResponseBody<User>> getLogin(@PathVariable Long testId){
        User user = userRepository.findById(testId).orElse(null);

        return BeeResponse.toResponse(Status.RUN, user);
    }
}
