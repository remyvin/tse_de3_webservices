package tse.de3.project_time_manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import io.spring.guides.gs_producing_web_service.GetUserByTeamRequest;
import io.spring.guides.gs_producing_web_service.GetUserByTeamResponse;
import io.spring.guides.gs_producing_web_service.GetUserByUsernameRequest;
import io.spring.guides.gs_producing_web_service.GetUserByUsernameResponse;
import io.spring.guides.gs_producing_web_service.GetUserRequest;
import io.spring.guides.gs_producing_web_service.GetUserResponse;

@Endpoint
public class UserEndpoint {
    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

    private UserRepository userRepository;

    @Autowired
    public UserEndpoint(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserRequest")
    @ResponsePayload
    public GetUserResponse getUser(@RequestPayload GetUserRequest request) {
        GetUserResponse response = new GetUserResponse();
        response.setUser(userRepository.findUser(request.getIdUser()));

        return response;
    }
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserByUsernameRequest")
    @ResponsePayload
    public GetUserByUsernameResponse getUser(@RequestPayload GetUserByUsernameRequest request) {
        GetUserByUsernameResponse response = new GetUserByUsernameResponse();
        response.setUser(userRepository.findUserByUsername(request.getUsername()));

        return response;
    }
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserByTeamRequest")
    @ResponsePayload
    public GetUserByTeamResponse getUser(@RequestPayload GetUserByTeamRequest request) {
        GetUserByTeamResponse response = new GetUserByTeamResponse();
        response.getUser().addAll(userRepository.findUserByTeam(request.getTeam()));  
        
        return response;
    }
}