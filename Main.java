import java.util.Scanner;
//testing array prints
import java.util.Arrays;
class Main {
  public static void main(String[] args) { 
    Scanner sc = new Scanner(System.in);
    System.out.println("Grade to evaluate (course/category): ");
    String type = sc.nextLine();
    //omly accepts course or category as responses, reinforced using while loop otherwise
    
    while(!type.equals("course") && !type.equals("category")){
      System.out.println("Please choose either course or category");
      type = sc.nextLine();
    }
    
    System.out.println("Desired grade: ");
    double desGrade = sc.nextDouble();

    if (type.equals("course")){
      course(desGrade);
    }
    else{ //if type = category, call category method 
      category(desGrade);
    }
    sc.close();
  }
  
  public static void course(double desGrade){
    Scanner sc = new Scanner(System.in);
    System.out.println("Number of categories in course: ");
    int numCat = Integer.parseInt(sc.nextLine());
    
    //each array = diff category
    String[][] cats = new String[numCat][2];
    //loop to fill in category data 
    for (int i = 0; i < numCat; i++){
      System.out.println("Enter category's grade, weight (ex. 98.3, 65) *NA if category is empty* : ");
      String gradeWeight = sc.nextLine();
      //break string into double grade, double weight
      String[] arr = gradeWeight.split(", ", 2); 
      int col = 0;
      for (String s : arr){
        cats[i][col] = s;
        col++;
      }
    }
    
    int weightSum = 0;
    int empty = 0;
    
    //loop to account for empty categories 
    for (int i = 0; i < numCat; i++){
      if (cats[i][0].equals("NA")){
        empty++;
        weightSum += Integer.parseInt(cats[i][1]);
      }
    }
    
    //if there are any empty categories, implement weight split + change non-empty category weights
    if (empty != 0){
      double weightSplit = (double)weightSum/(numCat-empty);
      for (int i = 0; i < numCat; i++){
        if (!cats[i][0].equals("NA")){
          cats[i][1] = Double.parseDouble(cats[i][1]) + weightSplit + "";
        }
      }
    }
    
    System.out.println("# of upcoming assignments, # of category (w/ respect to the order of earlier input): ");
    String upcomingCat = sc.nextLine();
    
    //splits input into # of upcoming assignments + which category the assignments belong to 
    String[] upCat = upcomingCat.split(", ", 2);
    int upcoming = Integer.parseInt(upCat[0]);
    int category = Integer.parseInt(upCat[1]) - 1; //index of desired category in catWeight
    
    System.out.println("# of current assignments in category: ");
    int current = sc.nextInt();
    double catAvg = Double.parseDouble(cats[category][0]); // current avg of inputted category 
    double catWeight = Double.parseDouble(cats[category][1]); //weight of inputted category
    
    double curGrade = 0;
    //calculates current avg based on rebalanced weight array catWeight
    for (int i = 0; i < numCat; i++){
        if (!cats[i][0].equals("NA")){
        curGrade += Double.parseDouble(cats[i][0])*(Double.parseDouble(cats[i][1])/100);
        }
    }
    
    double gradeDiff = desGrade - curGrade; 
    double neededCatAvg = gradeDiff*0.01*catWeight + catAvg; //needed category avg to have desired avg
    double needed = ((neededCatAvg*(current+upcoming))-(catAvg*current))/upcoming;
    System.out.println("To reach a grade of " + desGrade + " you need an average of " + needed + " over the next " + upcoming + " assignment(s)");
    
    sc.close();
  }

  public static void category(double desGrade){
    Scanner sc = new Scanner(System.in);
    System.out.println("current grade: ");
    int curGrade = sc.nextInt();
    System.out.println("number of current assignmets: ");
    int current = sc.nextInt();
    System.out.println("number of upcoming assignments: ");
    int upcoming = sc.nextInt();
    
    double needed = (double)(desGrade*(current+upcoming) - curGrade*current)/upcoming;
    System.out.println("To reach a grade of " + desGrade + " you need an average of " + needed + " over the next " + upcoming + " assignment(s)");
    
    sc.close();
  }    
}
