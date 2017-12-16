package domain;

import json.*;

public class Exam {

        public static JsonObject formExamData(String course, int mark, boolean passed) {
            JsonPair courseName = new JsonPair("course", new JsonString(course));
            JsonPair markValue = new JsonPair("mark", new JsonNumber(mark));
            JsonPair isPassed = new JsonPair("passed", new JsonBoolean(passed));

            return new JsonObject(courseName, markValue, isPassed);
    }
}
