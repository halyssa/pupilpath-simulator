import java.util.Scanner;
//testing array prints
import java.util.Arrays;
class Main {
  public static void main(String[] args) { 
    Scanner sc = new Scanner(System.in);
    System.out.println("Grade to evaluate (course/category): ");
    String type = sc.nextLine();
    //omly accepts course or category as responses, reinforced using while loop otherwise
    while(!type.equals("course") && !type.equals("caregory")){
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
      System.out.println("Enter category's grade, weight (ex. 98.3, 65) *N/A if category is empty* : ");
      String gradeWeight = sc.nextLine();
      //break string into double grade, double weight, boolean empty
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
      if (catWeight[i][0].equals("N/A")){
        empty++;
        weightSum += Integer.parseInt(catWeight[i][1]);
      }
    }
    //if there are any empty categories, implement weight split + change non-empty category weights
    if (empty != 0){
      double weightSplit = (double)weightSum/(numCat-empty);
      for (int i = 0; i < numCat; i++){
        if (!catWeight[i][0].equals("N/A")){
          catWeight[i][1] = Double.parseDouble(catWeight[i][1]) + weightSplit + "";
      }
    }
      }
    System.out.println(Arrays.deepToString(catWeight));
    System.out.println(weightSum);
    System.out.println(Arrays.deepToString(catWeight));
    sc.close();
  }




/*
      System.out.println("# of assignments are coming up, category of assignments: ");
      String numAssign = sc.nextLine();
*/
  
    //break string into int assignments, string category 
  public static void category(double desGrade){
    
  }    
}