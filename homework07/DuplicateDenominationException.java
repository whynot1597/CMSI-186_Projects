public class DuplicateDenominationException extends IllegalArgumentException {
  DuplicateDenominationException() {
    super("Coin values cannot be repeated");
  }
}
