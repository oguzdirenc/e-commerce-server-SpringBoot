package ecommerce.ecommerceserver.validator;

import ecommerce.ecommerceserver.domain.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors){
        User user = (User) o;

        if(user.getPassword().length() < 6){
            errors.rejectValue("password","Length","Şifre uzunluğu en az 6 karakter olmalıdır");
        }

        if(!user.getPassword().equals(user.getConfirmPassword())){
            errors.rejectValue("confirmPassword","Match","Şifreler aynı değil   ");
        }

    }
}
