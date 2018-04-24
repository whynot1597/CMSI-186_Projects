public class NegativeTargetValueException extends IllegalArgumentException {
  NegativeTargetValueException() {
    super("Target amount must be at least 0");
  }
}
