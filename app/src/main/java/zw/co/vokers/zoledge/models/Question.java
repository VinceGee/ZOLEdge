package zw.co.vokers.zoledge.models;

public class Question {
    public String questions[] = {
            "The story of the customer's experience from initial contact through engagement to a long term relationship with the organisation is called:",
            "As a principle, how many positive experiences does it take to make up for one negative experience?",
            "Which of the following apps is not included in Office 365?",
            "The Ops Manager wants to allow his team access to a report only to read it and give verbal feedback. He should:"
    };

    public String choices[][] = {
            {"Customer Experience", "Customer Lifecycle", "Customer Lifetime Value","Customer Journey Mapping"},
            {"0","1","5","12"},
            {"Word", "Publisher", "Excel Online", "Powerpoint"},
            {"Select Share and then View", "Select Share and then Edit", "Select the file then Revoke", "Click Advanced Settings"}
    };

    public String correctAnswer[] = {
            "Customer Journey Mapping",
            "12",
            "Publisher",
            "Select Share and then Edit"
    };

    public String getQuestion(int a){
        String question = questions[a];
        return question;
    }

    public String getchoice1(int a){
        String choice = choices[a][0];
        return choice;
    }

    public String getchoice2(int a){
        String choice = choices[a][1];
        return choice;
    }

    public String getchoice3(int a){
        String choice = choices[a][2];
        return choice;
    }

    public String getchoice4(int a){
        String choice = choices[a][3];
        return choice;
    }

    public String getCorrectAnswer(int a){
        String answer = correctAnswer[a];
        return answer;
    }
}
