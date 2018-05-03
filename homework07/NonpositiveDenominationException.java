/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name  :  NonpositiveDenominationException.java
 * Purpose    :  @see Use with http://bjohnson.lmu.build/cmsi186web/homework07.html
 * @author    :  Jeremy Goldberg
 * Date       :  2018-05-03
 * Description:  Handles error case when coin values are 0 or negative
 * Notes      :  Specific type of IllegalArgumentException
 * Warnings   :  None
 * Exceptions :  None
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
public class NonpositiveDenominationException extends IllegalArgumentException {
  NonpositiveDenominationException() {
    super("Coin values must be greater than 0");
  }
}
