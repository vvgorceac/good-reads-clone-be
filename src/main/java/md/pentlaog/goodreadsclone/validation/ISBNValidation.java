package md.pentlaog.goodreadsclone.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ISBNValidation implements ConstraintValidator<ISBN, String> {
  @Override
  public void initialize(ISBN constraintAnnotation) {}

  @Override
  public boolean isValid(String isbn, ConstraintValidatorContext constraintValidatorContext) {
    return validateISBN(isbn);
  }

  private boolean validateISBN(String isbn) {
    String regex =
        "^(?:ISBN(?:-1[03])?:? )?(?=[0-9X]{10}$|(?=(?:[0-9]+[- ]){3})[- 0-9X]{13}$|97[89][0-9]{10}$|(?=(?:[0-9]+[- ]){4})[- 0-9]{17}$)(?:97[89][- ]?)?[0-9]{1,5}[- ]?[0-9]+[- ]?[0-9]+[- ]?[0-9X]$";

    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(isbn);
    return matcher.matches();
  }
}
