package tse.de3.time_manager_project_api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import io.spring.guides.gs_producing_web_service.Project;

@Component
public class ProjectRepository {
    private static final Map<Integer, Project> projects = new HashMap<>();

    @PostConstruct
    public void initData() {
    	Project zero = new Project();
    	zero.setIdProject(0);
    	zero.setName("Zéro");
        projects.put(zero.getIdProject(), zero);
        
        Project cerberus = new Project();
        cerberus.setIdProject(1);
        cerberus.setName("Cerberus");
        projects.put(cerberus.getIdProject(), cerberus);
    }

    public Project findProject(Integer id_project) {
        Assert.notNull(id_project, "The project's id must not be null");
        return projects.get(id_project);
    }
    
    public List<Project> findAllProject() {
        return new ArrayList<Project>(projects.values());   
    }
}