public class NonpositiveDenominationException extends IllegalArgumentException {
  NonpositiveDenominationException() {
    super("Coin values must be greater than 0");
  }
}
