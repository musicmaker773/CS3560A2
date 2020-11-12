import java.text.DecimalFormat;

public class positiveMessages implements DisplayStats{
    private int pMessages;
    private double percent;
    DecimalFormat df = new DecimalFormat("#.0");
    public positiveMessages() {
        pMessages = 0;
        percent= 0.0;
    }

    public void calculatePercent(numOfMessages m) {
        if (m.getMessages() == 0) {
            if(pMessages > 0) {
                percent = 100.0;
                return;
            }
            percent = 0.0;
            return;
        }
        percent =  ((double) pMessages / m.getMessages()) * 100;

    }

    @Override
    public void increment() {
        pMessages++;
    }

    @Override
    public String accept(Visitor v) {

        return v.visitor(this) + df.format(percent) + " %";
    }
}
