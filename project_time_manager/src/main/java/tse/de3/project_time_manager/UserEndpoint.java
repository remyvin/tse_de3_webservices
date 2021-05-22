package tse.de3.project_time_manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import io.spring.guides.gs_producing_web_service.GetUserByTeamRequest;
import io.spring.guides.gs_producing_web_service.GetUserByTeamResponse;
import io.spring.guides.gs_producing_web_service.GetUserByMailRequest;
import io.spring.guides.gs_producing_web_service.GetUserByMailResponse;
import io.spring.guides.gs_producing_web_service.GetUserConnectionRequest;
import io.spring.guides.gs_producing_web_service.GetUserConnectionResponse;
import io.spring.guides.gs_producing_web_service.GetUserRequest;
import io.spring.guides.gs_producing_web_service.GetUserResponse;
import io.spring.guides.gs_producing_web_service.User;

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
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserByMailRequest")
    @ResponsePayload
    public GetUserByMailResponse getUser(@RequestPayload GetUserByMailRequest request) {
        GetUserByMailResponse response = new GetUserByMailResponse();
        response.setUser(userRepository.findUserByMail(request.getMail()));

        return response;
    }
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserByTeamRequest")
    @ResponsePayload
    public GetUserByTeamResponse getUser(@RequestPayload GetUserByTeamRequest request) {
        GetUserByTeamResponse response = new GetUserByTeamResponse();
        response.getUser().addAll(userRepository.findUserByTeam(request.getTeam()));  
        
        return response;
    }
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserConnectionRequest")
    @ResponsePayload
    public GetUserConnectionResponse getUser(@RequestPayload GetUserConnectionRequest request) {
        GetUserConnectionResponse response = new GetUserConnectionResponse();
        User user = userRepository.findUserConnection(request.getMail(), request.getPassword()); //var temporaire
        if(user != null){
        	response.setConnected(true);
        }
        else {
        	response.setConnected(false);
        }
        response.setUser(user); //retourne le user ou null si non connecté
        
        return response;
    }
}