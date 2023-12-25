public class CaesarsDepositBonusStrategy extends BasicStrategy {
    public CaesarsDepositBonusStrategy() {
        setTakesInsurance(false);
        setStrategyName("CAESARS DEPOSIT BONUS STRATEGY");
        for (int i = 0; i < 10; i++) setCountValue(i, 0);
    }
}
