package duke.task;

/**
 * The Task class represents real-world tasks with content and marking status.
 */
public abstract class Task {

    protected String content;
    protected boolean isMarked;

    /**
     * Default constructor for the Task class.
     * Initialize task content to be empty and set isMarked to be false.
     */
    public Task() {
        this("", false);
    }

    /**
     * Another constructor for the Task class.
     * Initialize task content to be the parameter and set isMarked to be false.
     * @param content content of the task
     */
    public Task(String content) {
        this(content, false);
    }

    /**
     * Another constructor for the Task class.
     * @param content content of the task.
     * @param isMarked marking status of the task.
     */
    public Task(String content, boolean isMarked) {
        this.content = content;
        this.isMarked = isMarked;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean getMarked() {
        return isMarked;
    }

    /**
     * Prints the marking status of the task following by the content.
     */
    public void printTask() {
        System.out.println(this);
    }

    /**
     * Marks the marking status of the task, i.e. set isMarked to be true.
     * Prints a reply message after successfully mark the task.
     * Prints an error message if the task is already marked.
     */
    public void mark() {
        if (!isMarked) {
            isMarked = true;
            System.out.println("\t Nice! I've marked this task as done:");
            System.out.println("\t " + this);
        } else {
            System.err.println("\t The task is already marked!");
        }
    }

    /**
     * Unmarks the marking status of the task, i.e. set isMarked to be false.
     * Prints a reply message after successfully unmark the task.
     * Prints an error message if the task is already unchecked.
     */
    public void unmark() {
        if (isMarked) {
            isMarked = false;
            System.out.println("\t OK, I've marked this task as not done yet:");
            System.out.println("\t " + this);
        } else {
            System.err.println("\t This task is already not marked!");
        }
    }

    public String toCSV() {
        return (isMarked ? "1" : "0") + "," +
                "\"" + content + "\"";
    }

    /**
     * Converts the task to a string containing marking status and content.
     * e.g. For a marked task: [X] a marked task.
     * e.g. For an unmarked task: [ ] an unmarked task.
     * @return a string containing marking status and task content
     */
    @Override
    public String toString() {
        return "[" + (isMarked ? "X" : " ") + "]" + " " + content;
    }
}
