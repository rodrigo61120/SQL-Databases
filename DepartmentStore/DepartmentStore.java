//Rodrigo Sejas Jaldin CS450 HW6

import java.sql.*;  //Import the java SQL library
import java.util.Scanner;

class DepartmentStore     //Create a new class to encapsulate the program
{
 
 public static void SQLError (Exception e)   //Our function for handling SQL errors
 {
	System.out.println("ORACLE error detected:");
	e.printStackTrace();	
 }

public static void main (String args[])  //The main function

{

 try {                                        //Keep an eye open for errors
       
       String driverName = "oracle.jdbc.driver.OracleDriver";
       Class.forName(driverName);

       System.out.println("Connecting   to Oracle...");  

       String url = "........."; //replace with url to oracle server
       Connection conn = DriverManager.getConnection(url,"....","...."); //replace strings with proper credentials

       System.out.println("Connected!");
       
		
		Scanner input = new Scanner(System.in);
		int control = 0;
		while(control!=3){
		
		System.out.println("\n\n\t~~~~~~~~SUMMARY REPORS MENU~~~~~~~~");
		System.out.println("Please chose one of the options by entering the number that corresponds to your choice:");
		System.out.println("1 - Customer Report");
		System.out.println("2 - Supplier Report");
		System.out.println("3 - QUIT");
		int choice = input.nextInt();
		
		if(choice==1){
		//I decided to make a different stmt for each query. reusing stmts deleted queries i needed.
       Statement stmt = conn.createStatement();   //Create a new statement
       Statement stmt2 = conn.createStatement();   //Create a new statement
		Statement stmt3 = conn.createStatement();   //Create a new statement
		Statement stmt4 = conn.createStatement();   //Create a new statement
		
		//-------------------------------Customer Summary----------------------------------------------
 		
		int quarterCharges=0; //number of charges in quarter.
		int quarterTotal=0; //total amount spent this quarter.
		
		//header for customer activity 
				System.out.println("\n\n-------------------------------CUSTOMER SUMMARY----------------------------------------------");
		System.out.println("'\tCustomer Activity for the Quarter April 1 - June 30, 2016\t\n");
		
		//Fetching the customers who bought items during the quarter
		ResultSet CustomerInfo = stmt.executeQuery("SELECT DISTINCT C.CUSTOMERID, C.CNAME, C.Address FROM Purchases P, Customer C Where P.CustomerID = C.CUSTOMERID AND P.PurchaseDate>=160401 AND P.PurchaseDate<=160630 Order by C.CUSTOMERID ASC");
		ResultSet ItemsInfo;
		while(CustomerInfo.next()){
		String Cnum = CustomerInfo.getString("CUSTOMERID");
		System.out.println("Customer Number: "+Cnum + "\t" + "Name: "+CustomerInfo.getString("CNAME") + "\t" +
		"Address: "+CustomerInfo.getString("Address")+"\n");
		System.out.println("Date\tItem#\tDesc\t\tPrice\tShip\tQty\tT Price\tT Ship\tTotal\tCard#");
		System.out.println("------\t----\t-------\t\t----\t------\t-----\t-----\t-----\t----\t-----"); //Print a header
		//fetching the items the customer bought
		ItemsInfo = stmt2.executeQuery("SELECT P.PurchaseDate, I.ITEMNUM, I.DESCRIPTION, I.PRICE, I.SHIPPINGCOST, P.QUANTITY, SUM(P.QUANTITY*I.PRICE) AS TOTALPRICE, SUM(P.QUANTITY*I.SHIPPINGCOST) AS TOTALSHIP, SUM(P.QUANTITY*(I.SHIPPINGCOST+I.PRICE)) AS TPRICE, P.CardNum FROM Purchases P, Items I WHERE P.CustomerID="+Cnum+" AND P.Item=I.ITEMNUM AND P.PurchaseDate>=160401 AND P.PurchaseDate<=160630 group by P.PurchaseDate, I.ITEMNUM, I.DESCRIPTION, I.PRICE, I.SHIPPINGCOST, P.QUANTITY, P.CardNum Order by PurchaseDate ASC");
			while(ItemsInfo.next()){
			//prints out the info of the items 
			System.out.println(ItemsInfo.getString("PurchaseDate")+ "\t" +ItemsInfo.getString("ITEMNUM")+ "\t" +ItemsInfo.getString("DESCRIPTION")+ 			"\t\t"+ItemsInfo.getString("PRICE")+ "\t" +ItemsInfo.getString("SHIPPINGCOST")+ "\t" +ItemsInfo.getString("QUANTITY")+ "\t" 			+ItemsInfo.getString("TOTALPRICE")+ "\t" +ItemsInfo.getString("TOTALSHIP")+ "\t" +ItemsInfo.getString("TPRICE")+ "\t" 			+ItemsInfo.getString("CardNum")+"\n");
			}
			//gets the summary info of the customer this quarter
		ResultSet Summary= stmt3.executeQuery("SELECT COUNT(*) AS NoP, SUM(P.QUANTITY*(I.SHIPPINGCOST+I.PRICE)) AS TOTALPRICE, AVG(P.QUANTITY*(I.SHIPPINGCOST+I.PRICE)) AS AVGPRICE FROM Purchases P, Items I WHERE P.CustomerID="+Cnum+" AND P.Item=I.ITEMNUM AND P.PurchaseDate>=160401 AND P.PurchaseDate<=160630");
			while(Summary.next()){
			//stores and increments customer info to variables that track number of purchases nd total $$ spent this quarter
			String NoP = Summary.getString("NoP");
			String TotalSpent = Summary.getString("TOTALPRICE");
			//prints out quarter report of each customer that made purchases
			System.out.println("# of Customer Purchases: "+NoP+ "\t" +"Overall Charge: "+TotalSpent+ "\t" +"Average Charge:"+Summary.getString("AVGPRICE")+"\n");
			int tempCharges = Integer.parseInt(NoP);
			int tempTotal = Integer.parseInt(TotalSpent);
			quarterCharges+=tempCharges;
			quarterTotal+=tempTotal;
			}
		}
		//Summary of quarter - number of purchases and total ammount spent
		System.out.println("# of Quarter Purchases: "+quarterCharges+"\tOverall sum of Charges: "+quarterTotal+ "\tAverage Charge: "+(quarterTotal/quarterCharges));
		
		//Customers who did not purchase anything during the quarter
		System.out.println("\n--------------NOTE--------------");
		System.out.println("Following Customers have bought Items but did not make a purchase during this quarter period");
		ResultSet inactive = stmt4.executeQuery("(SELECT C.CUSTOMERID, C.CNAME FROM Customer C, Purchases P WHERE C.CUSTOMERID = P.CustomerID) minus (Select C.CUSTOMERID, C.CNAME FROM Purchases P, Customer C, Items I WHERE P.CustomerID=C.CUSTOMERID AND P.Item=I.ITEMNUM AND P.PurchaseDate>=160401 AND P.PurchaseDate <=160630) Order by CUSTOMERID ASC");
		while(inactive.next()){
			//prints out customers who have made purchases but were not active this quarter
			System.out.println("Customer Number: "+inactive.getString("CUSTOMERID") + "\t" + "Name: "+inactive.getString("CNAME"));
		}
  
		} //end of customer option 
		
		else if(choice==2){
		Statement stmt11 = conn.createStatement();   //Create a new statement
		Statement stmt22 = conn.createStatement();   //Create a new statement
		Statement stmt33 = conn.createStatement();   //Create a new statement
		Statement stmt44 = conn.createStatement();   //Create a new statement
		
		//-------------------------------SUPPLIER SUMMARY----------------------------------------------
		
		int quarterCharges2=0;
		int quarterTotal2=0;
		System.out.println("\n\n-------------------------------SUPPLIER SUMMARY----------------------------------------------");
		System.out.println("\n\tSupplier Activity for the Quarter April 1 - June 30, 2016\t\n");
		//gets supplier and their procurements info
		ResultSet Sinfo = stmt11.executeQuery("SELECT DISTINCT S.SUPPLIERID, S.SNAME, S.LOCATION FROM Procurements P, Supplier S Where P.SCODE = S.SUPPLIERID AND P.DDATE>=150401 AND P.DDATE<=150630 Order by SUPPLIERID ASC");
		while(Sinfo.next()){
		String scode=Sinfo.getString("SUPPLIERID");
		System.out.println("Supplier Number: "+scode + "\t" + "Name: "+Sinfo.getString("SNAME") + "\t" +"Location "+Sinfo.getString("LOCATION")+"\n");
		System.out.println("Transition Number\tDate\tItem Number\tDescription\tQuantity\tTotal Cost");
		System.out.println("-----------------\t----\t-----------\t-----------\t--------\t----------"); //Print a header
		//gets information of each specific procurement of each supplier 
		ResultSet Procure = stmt22.executeQuery("SELECT P.TNUM, P.DDATE, P.ITEMNUM, I.Description, P.QUANTITY, SUM(P.QUANTITY*A.COST) AS TPRICE FROM Procurements P, Items I, Available A WHERE P.SCODE = "+scode+" AND P.SCODE = A.SCODE AND P.ITEMNUM=A.ITEMNUM AND P.ITEMNUM=I.ITEMNUM AND P.DDATE >=150401 AND P.DDATE <=150630 GROUP BY P.TNUM, P.DDATE, P.ITEMNUM, I.Description, P.QUANTITY ORDER BY TNUM ASC");
			while(Procure.next()){
				//prints out the info of each procurement 
				System.out.println(Procure.getString("TNUM")+"\t\t\t"+Procure.getString("DDATE")+"\t"+Procure.getString("ITEMNUM")+"\t\t"+Procure.getString("Description")+"\t\t"+Procure.getString("QUANTITY")+"\t\t"+Procure.getString("TPRICE")+"\n");
			}
			//gets the summary reports of each supplier and their total procurements this quarter
		ResultSet Summary= stmt33.executeQuery("Select COUNT(*) AS NoP, SUM(TPRICE) AS TOTALPRICE, AVG(TPRICE) AS AVGCOST FROM (SELECT P.TNUM, P.DDATE, P.ITEMNUM, I.Description, P.QUANTITY, SUM(P.QUANTITY*A.COST) AS TPRICE FROM Procurements P, Items I, Available A WHERE P.SCODE = '409' AND P.SCODE = A.SCODE AND P.ITEMNUM=A.ITEMNUM AND P.ITEMNUM=I.ITEMNUM AND P.DDATE >=150401 AND P.DDATE <=150630 GROUP BY P.TNUM, P.DDATE, P.ITEMNUM, I.Description, P.QUANTITY ORDER BY TNUM ASC)");
			while(Summary.next()){
			//prints out supplier procurement summary
			String NoP = Summary.getString("NoP");
			String TotalSpent = Summary.getString("TOTALPRICE");
			System.out.println("Supplier Procurements: "+NoP+ "\t" +"Overall Charge: "+TotalSpent+ "\t" +"Average Charge:"+Summary.getString("AVGCOST")+"\n");
			int tempCharges = Integer.parseInt(NoP);
			int tempTotal = Integer.parseInt(TotalSpent);
			quarterCharges2+=tempCharges;
			quarterTotal2+=tempTotal;
			}
		}
		//summary of all procurements this quarter 
	System.out.println("Quarter Procurements: "+quarterCharges2+"\tOverall sum of Charges: "+quarterTotal2+ "\tAverage Charge: "+(quarterTotal2/quarterCharges2));
	System.out.println("\n--------------NOTE--------------");
	System.out.println("Following Suppliers have procured Items but have not procured during quarter period");
	//gets the supplier info of suppliers who have procurements but not during this quarter.
	ResultSet inactiveSupplier = stmt44.executeQuery("(SELECT S.SUPPLIERID, S.SNAME FROM Supplier S, Procurements P WHERE S.SUPPLIERID = P.SCODE) minus (Select S.SUPPLIERID, S.SNAME FROM Supplier S, Procurements P, Items I WHERE S.SUPPLIERID = P.SCODE AND P.ITEMNUM=I.ITEMNUM AND P.DDATE>=150401 AND P.DDATE<=150630) Order by SUPPLIERID ASC");
	while(inactiveSupplier.next()){
		//prints out the info of suppliers who were not active this quarter
		System.out.println("Supplier Number: "+inactiveSupplier.getString("SUPPLIERID") + "\t" + "Name: "+inactiveSupplier.getString("SNAME"));
	}
	}//ends if statement of option 3
	else if(choice==3){control=3;}
	} //ends loop menu 
	
        conn.close();  // Close our connection.
	System.out.println("Thank You for using the software. Goodbye!");

      }
       catch (Exception e) {SQLError(e);} //if any error occurred in the try..catch block, call the SQLError function

}
}
