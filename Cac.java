package com.da2;

import com.da2.entities.Company;
import com.da2.entities.Students;

import java.time.LocalDateTime;
import java.util.*;

public class Cac {

    private final ArrayList<String> questions = new ArrayList<>();

    public void recommendCompanies(ArrayList<Students> students, ArrayList<Company> companies) {

        int index = 0;
        for(Students s : students) {
            System.out.println(questions.get(index));
            index +=1;
            System.out.println("Profile overview of "+ s.studentName);
            System.out.println("\tSkillSet(s): "+ s.skillSet);
            System.out.println("\tInterest(s): "+ s.interest);
            System.out.print("\tLooking for: "+ s.futurePlan);
            if(s.aiming_for != null) {
                System.out.println(" in "+ s.aiming_for);
            } else {
                System.out.print("\n");
            }
            System.out.println();



            for(Company c :companies) {
                boolean isFuturePlansForMatches = false;
//                Checks if the future plans of the student matches the company's offer
//                (say student is looking for placement offer and the company is offering internships, we will not recommend that company)
                for(Object x : s.futurePlan) {
                    if(Objects.equals(x, c.offers)) {
                        isFuturePlansForMatches = true;
                        break;
                    }
                }

                if(isFuturePlansForMatches) {

                    boolean isDomainMatch = false;

//                    Finds if the company offers work in the field in which the student is skillful
                    for(Object y : s.skillSet) {
                        if(Objects.equals(c.domain, y)) {
                            isDomainMatch = true;
                            break;
                        }
                    }

//                    Finds is the company offers work in the field of interest of the student
                    for(Object y : s.interest) {
                        if(Objects.equals(c.domain, y)) {
                            isDomainMatch = true;
                            break;
                        }
                    }

//                    Finding if the company is a startup or a big company using its founding year
                    boolean isStartup = LocalDateTime.now().getYear() - c.founding_year <=5;

                    if(isDomainMatch ) {
                        if((Objects.equals(s.aiming_for, "startups") && isStartup))
                            s.recommended_companies.add(c);
                        else if(!((Objects.equals(s.aiming_for, "startups") && !isStartup)))
                            s.recommended_companies.add(c);
                    }
                }
            }

            if (s.recommended_companies.size() ==0 ) {
                // If the person is aiming for higher studies or civil services or start up only
                // and not internship or placement, they will not get any recommendation
                // so, they will be notified with this console message.
                if(s.futurePlan.contains("higherStudies")) {
                    System.out.println("There is no recommendation as you are aiming for higher studies.");
                } else if(s.futurePlan.contains("civilService")) {
                    System.out.println("There is no recommendation as you are aiming for civil services.");
                } else if(s.futurePlan.contains("startUp")) {
                    System.out.println("There is no recommendation as you are aiming for start up.");
                } else {
                    System.out.println("No recommendations found.");
                }
            } else {
                s.recommended_companies.sort((o1, o2) -> Long.compare(o2.pay + o2.annual_increment * 3 - o2.probation_period * 5, o1.pay + o1.annual_increment * 3 - o1.probation_period * 5));
            }


            int i=1;
            for(Company c : s.recommended_companies) {
                System.out.println("Recommendation #"+ i);
                System.out.println("\t" + c.companyName + ", founded in "+c.founding_year +", provides "+ c.offers + " in " + c.domain + " at Rs." + c.pay + " LPA ");
                if(!Objects.equals(c.offers, "internship")) {
                    System.out.println("\tHas a probation period of " + c.probation_period + " months and salary increase of Rs."+ c.annual_increment + " every year.");
                }
                i = i+1;
            }

            System.out.println("\n");
        }


        System.out.println("6. Some students look for specific domain based company");
        System.out.println("All the recommendations in this system happens based on the domains.");

    }

    public Cac() {
        questions.add("1. Some students  may be in need internship and placement");
        questions.add("2. Some students look for  the  growing company");
        questions.add("3. Some students look for industry experience for minimum period and the go for higher studies");
        questions.add("4. Some students look for the minimum probation period");
        questions.add("5. Some students look for higher studies/civil service exams/startups");
    }
}
