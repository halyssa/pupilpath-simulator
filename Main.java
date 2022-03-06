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
    String[][] catWeight = new String[numCat][2];
    //loop to fill in category data 
    for (int i = 0; i < numCat; i++){
      System.out.println("Enter category's grade, weight (ex. 98.3, 65) *NA if category is empty* : ");
      String gradeWeight = sc.nextLine();
      //break string into double grade, double weight
      String[] arr = gradeWeight.split(", ", 2); 
      int col = 0;
      for (String s : arr){
        catWeight[i][col] = s;
        col++;
      }
      }
    int weightSum = 0;
    int empty = 0;
    //loop to account for empty categories 
    for (int i = 0; i < numCat; i++){
      if (catWeight[i][0].equals("NA")){
        empty++;
        weightSum += Integer.parseInt(catWeight[i][1]);
      }
    }
    //if there are any empty categories, implement weight split + change non-empty category weights
    if (empty != 0){
      double weightSplit = (double)weightSum/(numCat-empty);
      for (int i = 0; i < numCat; i++){
        if (!catWeight[i][0].equals("NA")){
          catWeight[i][1] = Double.parseDouble(catWeight[i][1]) + weightSplit + "";
        }
      }
    }
    System.out.println(Arrays.deepToString(catWeight));

    
    /*
    System.out.println("# of upcoming assignments, category of assignments: ");
    String upcomingCat = sc.nextLine();
    //splits input into # of upcoming assignments + which category the assignments belong to 
    String[] upCat = upcomingCat.split(", ", 2);
    int upcoming = Integer.parseInt(upCat[0]);
    String category = upCat[1];
    */

    
    double curGrade = 0;
    //calculates current avg based on rebalanced weight array catWeight
    for (int i = 0; i < numCat; i++){
      if (!catWeight[i][0].equals("NA")){
        curGrade += Double.parseDouble(catWeight[i][0])*(Double.parseDouble(catWeight[i][1])/100);
      }
    }
    System.out.println("cur: "+ curGrade);
    //System.out.println(weightSum);
    //System.out.println(Arrays.deepToString(catWeight));
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
    System.out.println("To reach a grade of " + desGrade + " you need an average of " + needed + " over your next " + upcoming + " assignments");
    sc.close();
  }    
}
