import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Question {
    private String questionText;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String correctAnswer;

    public Question(String questionText, String optionA, String optionB, String optionC, String optionD, String correctAnswer) {
        this.questionText = questionText;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getOptionA() {
        return optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public boolean isCorrectAnswer(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }
}

public class QuizApplication {
    private static final String QUESTIONS_FILE = "questions.txt";
    private static final int SCORE_PER_QUESTIONS = 10;

    private List<Question> questions;
    private int score;

    public void loadQuestions() throws FileNotFoundException {
        File file = new File(QUESTIONS_FILE);
        Scanner scanner = new Scanner(file);

        questions = new ArrayList<>();

        int lineCount = 1;

        while(scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();

            if(line.startsWith("Question:")) {
                String questionText = extractContent(line);
                String optionAText = extractContent(scanner.nextLine());
                String optionBText = extractContent(scanner.nextLine());
                String optionCText = extractContent(scanner.nextLine());
                String optionDText = extractContent(scanner.nextLine());
                String correctAnswer = extractContent(scanner.nextLine());

                Question question = new Question(questionText, optionAText, optionBText, optionCText, optionDText, correctAnswer);
                questions.add(question);
            } else {
                System.out.println("Invalid question format at line " + lineCount);
            }

            lineCount ++;
        }
        scanner.close();
    }

    public String extractContent(String line) {
        int colonIndex = line.indexOf(":");
        if (colonIndex != -1) {
            return line.substring(colonIndex + 1).trim();
        } else {
            System.out.println("Invalid format in line:" + line);
            return "";
        }
    }
}