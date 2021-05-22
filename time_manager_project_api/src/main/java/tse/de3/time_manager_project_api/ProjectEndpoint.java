package tse.de3.time_manager_project_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import io.spring.guides.gs_producing_web_service.GetProjectRequest;
import io.spring.guides.gs_producing_web_service.GetProjectResponse;

@Endpoint
public class ProjectEndpoint {
    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

    private ProjectRepository projectRepository;

    @Autowired
    public ProjectEndpoint(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProjectRequest")
    @ResponsePayload
    public GetProjectResponse getProject(@RequestPayload GetProjectRequest request) {
        GetProjectResponse response = new GetProjectResponse();
        response.setProject(projectRepository.findProject(request.getIdProject()));

        return response;
    }
}