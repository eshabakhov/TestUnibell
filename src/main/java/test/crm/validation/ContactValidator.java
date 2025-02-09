package test.crm.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import test.crm.dto.Contact;

public class ContactValidator implements ConstraintValidator<ValidContact, Contact> {
    private static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
    private static final String PHONE_REGEX = "^\\+?[0-9]{10,15}$";

    @Override
    public boolean isValid(Contact contact, ConstraintValidatorContext context) {
        if (contact.getTypeContact() == null || contact.getValueContact() == null) {
            return false;
        }

        return switch (contact.getTypeContact()) {
            case EMAIL -> contact.getValueContact().matches(EMAIL_REGEX);
            case PHONE -> contact.getValueContact().matches(PHONE_REGEX);
        };
    }
}
