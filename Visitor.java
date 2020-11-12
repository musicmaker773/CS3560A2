public interface Visitor {

    // Visitor for visitor pattern
    public String visitor(numOfUsers u);
    public String visitor(numOfGroups g);
    public String visitor(numOfMessages m);
    public String visitor(positiveMessages pm);
}
