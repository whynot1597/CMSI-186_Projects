/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name  :  DuplicateDenominationException.java
 * Purpose    :  @see Use with http://bjohnson.lmu.build/cmsi186web/homework07.html
 * @author    :  Jeremy Goldberg
 * Date       :  2018-05-03
 * Description:  Handles error case when coin values are repeated
 * Notes      :  Specific type of IllegalArgumentException
 * Warnings   :  None
 * Exceptions :  None
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
public class DuplicateDenominationException extends IllegalArgumentException {
  DuplicateDenominationException() {
    super("Coin values cannot be repeated");
  }
}
