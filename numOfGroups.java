public class numOfGroups implements DisplayStats {

    private int groups;

    public numOfGroups() {
        groups = 1;
    }

    @Override
    public void increment() {
        groups++;
    }

    @Override
    public String accept(Visitor v) {

       return v.visitor(this) + groups;
    }
}
