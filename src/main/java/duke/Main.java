package duke;

import duke.command.Command;
import duke.command.CommandResult;
import duke.command.ExitCommand;
import duke.parser.CommandParser;
import duke.storage.StorageFile;
import duke.tasklist.TaskList;
import duke.ui.TextUi;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    private static final String VERSION = "0.1";
    private TextUi ui;
    private TaskList taskList;
    private StorageFile storageFile;

    public static void main(String... args) {
        new Main().run(args);
    }

    private void run(String... args) {
        init();
        loopUntilExit();
        finish();
    }

    private void init(String... args) {
        try {
            this.ui = new TextUi();
            this.taskList = new TaskList();
            this.storageFile = new StorageFile();
            try {
                int lines = storageFile.loadCsvLoad(taskList);
                ui.printMessage(String.format("Load %d task(es) from file '%s'", lines, storageFile.getPath()));
            } catch (FileNotFoundException e) {
                storageFile.initCsv();
                ui.printMessage(String.format("Create new data file '%s'", storageFile.getPath()));
            }

            ui.printWelcomeMsg(VERSION);
            ui.printDivider();
            ui.printIntroMsg();
            ui.printDivider();
        } catch (Exception e) {
            ui.printMessage("init failed!");
            throw new RuntimeException();
        }
    }

    private void loopUntilExit() {
        Command command;
        do {
            String userInput = ui.getUserCommand();
            try {
                command = new CommandParser().parseCommand(userInput);
                ui.printDivider();
                CommandResult result = executeCommand(command);
                ui.printResult(result);
            } catch (Exception e) {
                command = null;
                ui.printMessage(e.getMessage());
            }
            ui.printDivider();
        } while (!ExitCommand.isExitCommand(command));
    }

    private void finish() {
        try {
            storageFile.writeCsv(taskList);
            ui.printMessage(String.format("Data has been written to '%s'", storageFile.getPath()));
        } catch (IOException e) {
            ui.printMessage("Failed to save task list!");
        }
        ui.printGoodByeMsg();
        System.exit(0);
    }

    private void prepareData(Command command) {
        try {
            command.setTaskList(taskList);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private CommandResult executeCommand(Command command) {
        try {
            prepareData(command);
            return command.execute();
        } catch (Exception e) {
            ui.printMessage(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
