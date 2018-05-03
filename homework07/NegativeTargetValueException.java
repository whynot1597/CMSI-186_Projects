/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name  :  NegativeTargetValueException.java
 * Purpose    :  @see Use with http://bjohnson.lmu.build/cmsi186web/homework07.html
 * @author    :  Jeremy Goldberg
 * Date       :  2018-05-03
 * Description:  Handles error case when target value is negative
 * Notes      :  Specific type of IllegalArgumentException
 * Warnings   :  None
 * Exceptions :  None
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
public class NegativeTargetValueException extends IllegalArgumentException {
  NegativeTargetValueException() {
    super("Target amount must be at least 0");
  }
}
