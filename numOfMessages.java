public class numOfMessages implements DisplayStats {

    private int messages;

    public numOfMessages() {
        messages = 0;
    }

    public int getMessages() {
        return messages;
    }

    @Override
    public void increment() {
        messages++;
    }

    @Override
    public String accept(Visitor v) {

        return v.visitor(this) + messages;

    }
}
