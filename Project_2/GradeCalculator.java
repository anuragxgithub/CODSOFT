/*
 * Grading Rules: 
 * (PERCENTAGE)      |      (GRADES)
 * 96-100            |      A+
 * 90-95             |      A
 * 86-89             |      B+
 * 80-85             |      B
 * 76-79             |      C+
 * 70-75             |      C
 * 66-69             |      D+
 * 60-65             |      D
 * <59               |      F
 */
package Project_2;

import java.util.Scanner;
import java.util.InputMismatchException;

public class GradeCalculator {
    // ________FIRST METHOD (EFFICIENT)__________  //

    public static void main(String[] args) {
        final int TOTAL_SUBJECTS = 5;
        final int PASSING_MARK = 33;
        final int MAX_MARKS = 100;
        int[] marks = new int[TOTAL_SUBJECTS];
        Scanner sc = new Scanner(System.in);

        for(int i = 0; i < 5; i++) {
            String subject = getSubject(i);

            marks[i] = getValidMarks(subject, sc, 0, MAX_MARKS);  //adding all marks to an array
        }

        int totalMarks = calculateTotalMarks(marks);
        float percentage = (float)totalMarks/5;
        String grade = calculateGrade(marks, percentage, PASSING_MARK);
        displayResult(totalMarks, percentage, grade);
    }

    private static String getSubject(int index) {
        switch(index) {
            case 0 : return "MATHS";
            case 1 : return "PHYSICS";
            case 2 : return "CHEMISTRY";
            case 3 : return "HINDI";
            case 4 : return "ENGLISH";
            default : return "UNKNOWN";
        }
    }

    private static int getValidMarks(String subject, Scanner sc, int min, int max) {
        boolean validMark = false;
        int mark = 0;
        do {
            try {
                System.out.print(subject +": ");
                mark = sc.nextInt();
                if(mark >= min && mark <= max) {
                    validMark = true;
                } else {
                    System.out.println("Invalid input!! Marks must be between "+ min +" and " + max);
                }
            } catch(InputMismatchException e) {
                System.out.println("Invalid input!! Please enter a valid number.");
                sc.nextLine(); // Consume the invalid input
            }
            
        } while(!validMark);
        return mark; 
    }

    private static int calculateTotalMarks(int[] marks) {
        int totalMarks = 0;
        for(int m : marks) {
            totalMarks += m; 
        }
        return totalMarks;
    }

    private static String calculateGrade(int[] marks, float percentage, int passingMark) {
        // Checking if any subject has marks less than 33
        String grade = "-"; 
        for(int i = 0; i < 5; i++) {
            if(marks[i] < 33) {
                grade = "F";
                return grade;
            }
        }
        if(percentage > 95 && percentage <= 100) {
            grade = "A+";
        } else if(percentage >= 90 && percentage < 96) {
            grade = "A";
        } else if(percentage > 85 && percentage < 90) {
            grade = "B+";
        } else if(percentage >= 80 && percentage < 86) {
            grade = "B";
        } else if(percentage > 75 && percentage < 80) {
            grade = "C+";
        } else if(percentage >= 70 && percentage < 76) {
            grade = "C";
        } else if(percentage > 65 && percentage < 70) {
            grade = "D+";
        } else if(percentage >= 60 && percentage < 66) {
            grade = "D";
        } else {
            grade = "F";
        }
        return grade;
    }

    private static void displayResult(int totalMarks, float percentage, String grade) {
        System.out.println(">>>RESULTS ARE GIVEN BELOW<<<");
        System.out.println("Total Marks is : "+ totalMarks);
        // System.out.println("Percentage is : " + percentage +"%");
        System.out.printf("Percentage: %.2f%%%n", percentage);
        System.out.println("Grade is : "+ "'"+ grade +"'");
    }

    // ________SECOND METHOD (NOT MUCH EFFICIENT)__________  //


    // int marks[] = new int[5];
        // Scanner sc = new Scanner(System.in);
        // System.out.println("ENTER YOU MARKS OUT OF 100 BELOW IN EACH (5) SUBJECTS");
        // System.out.print("MATHS: ");
        // marks[0] = sc.nextInt();
        // System.out.print("PHYSICS: ");
        // marks[1] = sc.nextInt();
        // System.out.print("CHEMISTRY: ");
        // marks[2] = sc.nextInt();
        // System.out.print("HINDI: ");
        // marks[3] = sc.nextInt();
        // System.out.print("ENGLISH: ");
        // marks[4] = sc.nextInt();
        // float percentage = 0, totalMarks = 0;
        // String grade = null;

        // for(int m : marks) {
        //     totalMarks += m; 
        // }

        // percentage = totalMarks/5;
        
        // // Checking if any subject has marks less than 33
        // boolean fail = false;
        // for(int i = 0; i < 5; i++) {
        //     if(marks[i] < 33) {
        //         fail = true;
        //         break;
        //     }
        // }
        // if(fail) {
        //     grade = "F";
        // } else if(percentage > 95 && percentage <= 100) {
        //     grade = "A+";
        // } else if(percentage >= 90 && percentage < 96) {
        //     grade = "A";
        // } else if(percentage > 85 && percentage < 90) {
        //     grade = "B+";
        // } else if(percentage >= 80 && percentage < 86) {
        //     grade = "B";
        // } else if(percentage > 75 && percentage < 80) {
        //     grade = "C+";
        // } else if(percentage >= 70 && percentage < 76) {
        //     grade = "C";
        // } else if(percentage > 65 && percentage < 70) {
        //     grade = "D+";
        // } else if(percentage >= 60 && percentage < 66) {
        //     grade = "D";
        // } else {
        //     grade = "F";
        // }
        
        // System.out.println(">>>RESULTS ARE GIVEN BELOW<<<");
        // System.out.println("Total Marks is : "+ totalMarks);
        // System.out.println("Percentage is : " + percentage);
        // System.out.println("Grade is : "+"'"+grade+"'");
        // sc.close();
}

