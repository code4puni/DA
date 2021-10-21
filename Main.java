package com.da2;

import com.da2.entities.Company;
import com.da2.entities.Students;
import com.da2.helpers.Setup;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException, ParseException {

        FileReader companiesReader =  new FileReader(".\\src\\main\\java\\com\\da2\\jsonFiles\\companies.json");

        JSONParser jsonParser = new JSONParser();
        JSONObject companiesObj =(JSONObject) jsonParser.parse(companiesReader);

        FileReader studentsReader =  new FileReader(".\\src\\main\\java\\com\\da2\\jsonFiles\\students.json");

        JSONObject studentsObj =(JSONObject) jsonParser.parse(studentsReader);

        ArrayList<Students> students = new Setup().setupStudents(studentsObj);
        ArrayList<Company> companies = new Setup().setUpCompanies(companiesObj);

        Cac cac = new Cac();
        cac.recommendCompanies(students, companies);

    }
}
