package models.user;

import java.util.UUID;

public class User {
    private UUID userId;
    private String userName;
    private String userPassword;
    private Role userRole;

    public UUID getUserId(){
        return this.userId;
    }
    public void setUserId(UUID userId){
        this.userId=userId;
    }
    public String getUserName(){
        return this.userName;
    }
    public void setUserName(String userName){
        this.userName=userName;
    }
    public String getUserPassword(){
        return this.userPassword;
    }
    public void setUserPassword(String userPassword){
        this.userPassword=userPassword;
    }

    public Role getUserRole() {
        return this.userRole;
    }

    public void setUserRole(Role userRole) {
        this.userRole = userRole;
    }
}
