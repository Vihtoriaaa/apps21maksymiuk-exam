package domain;

import json.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class Student extends BasicStudent {
    List<Tuple> subjects = new ArrayList();
    private static int passedMark = 2;

    public Student(String name, String surname, Integer year, Tuple<String, Integer>... exams) {
        super(name, surname, year);
        for(Tuple tuple : exams){
            this.subjects.add(tuple);
        }
    }

    @Override
    public JsonObject toJsonObject() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add(new JsonPair("name", new JsonString(this.name)));
        jsonObject.add(new JsonPair("surname", new JsonString(this.surname)));
        jsonObject.add(new JsonPair("year", new JsonNumber(this.year)));
        JsonObject[] allExams = new JsonObject[subjects.size()];
        for (int i = 0; i < subjects.size(); i++){
            JsonObject object = new JsonObject();
            object.add(new JsonPair("course", new JsonString(subjects.get(i).key.toString())));
            object.add(new JsonPair("mark", new JsonNumber((int)subjects.get(i).value)));
            object.add(new JsonPair("passed", new JsonBoolean((int)subjects.get(i).value > passedMark)));
            allExams[i] = object;
        }
        JsonArray jasArr = new JsonArray(allExams);
        jsonObject.add(new JsonPair("exams", jasArr));
        return jsonObject;
    }
}
