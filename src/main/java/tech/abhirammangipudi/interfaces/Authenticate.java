package tech.abhirammangipudi.interfaces;

import tech.abhirammangipudi.errors.AuthenticationException;
import tech.abhirammangipudi.models.User;

public interface Authenticate {
    boolean authenticate(User user, String password) throws AuthenticationException;
}
