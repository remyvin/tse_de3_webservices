package tse.de3.project_webservice;

import io.spring.guides.gs_producing_web_service.User;
import io.spring.guides.gs_producing_web_service.Role;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class UserRepository {
    private static final Map<Integer, User> users = new HashMap<>();

    @PostConstruct
    public void initData() {
        User thibaud = new User();
        thibaud.setFirstname("Thibaud");
        thibaud.setLastname("Cruzille");
        thibaud.setRole(Role.MANAGER);
        thibaud.setTeam(1);
        users.put(thibaud.getIdUser(), thibaud);

        User remym = new User();
        remym.setFirstname("Rémy");
        remym.setLastname("Moulaire");
        remym.setRole(Role.DEVELOPER);
        remym.setTeam(1);
        users.put(remym.getIdUser(), remym);

        User remyv = new User();
        remyv.setFirstname("Rémy");
        remyv.setLastname("Viniacourt");
        remyv.setRole(Role.DEVELOPER);
        remyv.setTeam(1);
        users.put(remyv.getIdUser(), remyv);
    }

    public User findUser(Integer id_user) {
        Assert.notNull(id_user, "The user's id must not be null");
        return users.get(id_user);
    }
}