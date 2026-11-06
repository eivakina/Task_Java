package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TasksTest {

    @Test
    public void shouldMatchSimpleTaskWhenQueryInTitle() {
        SimpleTask task = new SimpleTask(5, "Позвонить родителям");
        boolean actual = task.matches("родителям");
        Assertions.assertTrue(actual);
    }

    @Test
    public void shouldNotMatchSimpleTaskWhenQueryNotInTitle() {
        SimpleTask task = new SimpleTask(5, "Позвонить родителям");
        boolean actual = task.matches("бабушке");
        Assertions.assertFalse(actual);
    }

    @Test
    public void shouldMatchEpicWhenQueryInSubtasks() {
        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);
        boolean actual = epic.matches("Яйца");
        Assertions.assertTrue(actual);
    }

    @Test
    public void shouldNotMatchEpicWhenQueryNotInSubtasks() {
        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);
        boolean actual = epic.matches("Сыр");
        Assertions.assertFalse(actual);
    }

    @Test
    public void shouldMatchMeetingWhenQueryInTopic() {
        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );
        boolean actual = meeting.matches("Выкатка");
        Assertions.assertTrue(actual);
    }

    @Test
    public void shouldMatchMeetingWhenQueryInProject() {
        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );
        boolean actual = meeting.matches("НетоБанка");
        Assertions.assertTrue(actual);
    }

    @Test
    public void shouldNotMatchMeetingWhenQueryNotInTopicAndProject() {
        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );
        boolean actual = meeting.matches("Релиз");
        Assertions.assertFalse(actual);
    }

    @Test
    public void shouldNotMatchBaseTask() {
        Task task = new Task(1);
        boolean actual = task.matches("тест");
        Assertions.assertFalse(actual);
    }


    @Test
    public void testTaskEqualsAndHashCode() {
        Task task1 = new Task(1);
        Task task2 = new Task(1);
        Task task3 = new Task(2);

        Assertions.assertEquals(task1, task1);
        Assertions.assertEquals(task1, task2);
        Assertions.assertNotEquals(task1, task3);
        Assertions.assertNotEquals(task1, null);
        Assertions.assertNotEquals(task1, "строка");

        Assertions.assertEquals(task1.hashCode(), task2.hashCode());
    }

    @Test
    public void testGettersAndFields() {
        SimpleTask simpleTask = new SimpleTask(1, "Title");
        Assertions.assertEquals(1, simpleTask.getId());
        Assertions.assertEquals("Title", simpleTask.getTitle());

        String[] subtasks = {"Subtask"};
        Epic epic = new Epic(2, subtasks);
        Assertions.assertArrayEquals(subtasks, epic.getSubtasks());

        Meeting meeting = new Meeting(3, "Topic", "Project", "Start");
        Assertions.assertEquals("Topic", meeting.getTopic());
        Assertions.assertEquals("Project", meeting.getProject());
        Assertions.assertEquals("Start", meeting.getStart());
    }
}
