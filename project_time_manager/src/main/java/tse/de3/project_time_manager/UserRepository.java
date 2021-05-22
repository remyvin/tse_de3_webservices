package tse.de3.project_time_manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import io.spring.guides.gs_producing_web_service.Role;
import io.spring.guides.gs_producing_web_service.User;

@Component
public class UserRepository {
    private static final Map<Integer, User> users = new HashMap<>();

    @PostConstruct
    public void initData() {
        User thibaud = new User();
        thibaud.setIdUser(0);
        thibaud.setFirstname("Thibaud");
        thibaud.setLastname("Cruzille");
        thibaud.setUsername("tcruzille");
        thibaud.setRole(Role.MANAGER);
        thibaud.setTeam(1);
        users.put(thibaud.getIdUser(), thibaud);

        User remym = new User();
        remym.setIdUser(1);
        remym.setFirstname("Rémy");
        remym.setLastname("Moulaire");
        remym.setUsername("rmoulaire");
        remym.setRole(Role.DEVELOPER);
        remym.setTeam(1);
        users.put(remym.getIdUser(), remym);

        User remyv = new User();
        remyv.setIdUser(2);
        remyv.setFirstname("Rémy");
        remyv.setLastname("Viniacourt");
        remyv.setUsername("rviniacourt");
        remyv.setRole(Role.DEVELOPER);
        remyv.setTeam(2);
        users.put(remyv.getIdUser(), remyv);
    }

    public User findUser(Integer id_user) {
        Assert.notNull(id_user, "The user's id must not be null");
        return users.get(id_user);
    }
    
    public User findUserByUsername(String username) {
        Assert.notNull(username, "The user's username must not be null");
        for(Map.Entry<Integer, User> entry : users.entrySet()) {
        	if(entry.getValue().getUsername().compareTo(username)==0) {
        		return users.get(entry.getKey());
        	}
        }
        return null;
    }
    
    public List<User> findUserByTeam(Integer team) {
        Assert.notNull(team, "The user's team must not be null");
        List<User> teamUsers = new ArrayList<User>(10); //optimize, double size when 100%
        for(Map.Entry<Integer, User> entry : users.entrySet()) {
        	if(entry.getValue().getTeam()==team) {
        		teamUsers.add(entry.getValue()); //met l'objet user qui fait partie de la team dans la liste
        	}
        }
        return teamUsers; //retourne la liste des users
    }
}