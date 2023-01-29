/**
 * Represents tasks that need to be done before a specific date/time.
 * e.g., submit report by 11/10/2019 5pm.
 */
public class Deadline extends Task {
    protected String by;

    public Deadline(String content, String by) {
        super(content);
        this.by = by;
    }

    public Deadline(String content, boolean isDone, String by) {
        super(content, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by + ")";
    }
}
