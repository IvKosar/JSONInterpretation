package domain;

import json.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class Student extends BasicStudent {
    private List<Tuple> exams;

    public Student(String name, String surname, Integer year, Tuple<String, Integer>... exams) {
        super(name, surname, year);
        this.exams = new ArrayList<>(Arrays.asList(exams));
    }

    public JsonObject toJsonObject() {
        JsonObject studentObject = new JsonObject();
        studentObject.add(new JsonPair("name", new JsonString(this.name)));
        studentObject.add(new JsonPair("surname", new JsonString(this.surname)));
        studentObject.add(new JsonPair("year", new JsonNumber(this.year)));

        List<JsonObject> examsObject = new ArrayList<>();
        for (Tuple examTuple:this.exams){
            JsonObject exam = Exam.formExamData((String)examTuple.key, (Integer)examTuple.value, (Integer)examTuple.value > 2);
            examsObject.add(exam);
        }

        studentObject.add(new JsonPair("exams", new JsonArray(examsObject.toArray(new Json[exams.size()]))));
        return studentObject;
    }
}
