
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;


// this class implements Singleton Pattern
public class Highlighter implements TreeSelectionListener {
private String highlighted;
private static Highlighter h = null;

    private Highlighter() {
        highlighted = "";


    }



    public String getHighlighted() {
        return highlighted;
    }

    public static Highlighter getInstance() {
        if(h == null) {
            h = new Highlighter();
        }
        return h;

    }

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        if(e.getNewLeadSelectionPath() != null) {
            highlighted = e.getNewLeadSelectionPath().getLastPathComponent().toString();
        }
    }


}
