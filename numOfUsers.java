public class numOfUsers implements DisplayStats {

    private int users;

    public numOfUsers() {
        users = 0;

    }

    @Override
    public void increment() {
        users++;
    }

    @Override
    public String accept(Visitor v) {
        return v.visitor(this) + users;

    }
}
