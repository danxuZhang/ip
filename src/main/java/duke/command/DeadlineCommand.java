package duke.command;

import duke.common.AddTaskCommandReply;
import duke.tasklist.exception.DuplicateTaskException;
import duke.tasklist.task.Task;
import duke.tasklist.task.Deadline;

public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    public static final String COMMAND_USAGE = COMMAND_WORD
            + ": add a deadline task with by time";

    private final String content;
    private final String byTime;

    public DeadlineCommand(String content, String byTime) {
        this.content = content;
        this.byTime = byTime;
    }

    @Override
    public CommandResult execute() {
        Task task = new Deadline(content, byTime);
        try {
            taskList.addTask(task);
        } catch (DuplicateTaskException e) {
            return new CommandResult(AddTaskCommandReply.REPLY_DUPLICATE);
        }
        return new CommandResult(
                AddTaskCommandReply.REPLY_HEADER,
                task + "\n",
                String.format(AddTaskCommandReply.REPLY_TAIL_FORMAT, taskList.size())
        );
    }
}
