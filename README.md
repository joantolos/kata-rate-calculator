# Kata Rate Calculator

This is a technical test from the company Zopa.

## Running the code

You will need Gradle install on your machine. Then create the gradle wrapper, on the project folder:

    gradle wrapper

To run the build with test coverage:

    ./gradlew clean build jacocoTestReport
    
You will find the coverage report on _kata-rate-calculator/build/jacoco/reports/coverage/html/index.html_

You can run the RateCalculator class on your IDE or by console:

Creating the fatJar:
    
    ./gradlew fatJar
    
Running the jar:

    java -jar build/libs/kata-rate-calculator-all-1.0.jar marketData.csv 1000
    
## How I've calculated the rate

I've investigated a little bit about this _monthly compounding interest_ way of rate calculation and I tried a couple of approaches. But, since I don't have many expertise on the field, I wasn't completely sure what I was doing.

So, I've decided to make a different approach applying my logical way of understanding the problem. An example:

If I want to provide 1000 pounds, and I have a list of lenders that provide different rates for their available amounts, what I can do is order them by rate ascending. Then grab the available amount for the first one, and if is not the total amount desired, just consider that amount plus it's specific interest. Then I proceed for the next one until the total amount is reached. That way I have the total repayments calculated. 

Then from the total repayment amount and the original amount requested, I can infer the rate and the monthly repayment.

This is a "naive" approach to the problem, but I have preferred that to apply some magic formula that I don't really understand. 
 
## Test Instructions

There is a need for a rate calculation system allowing prospective lenders to  obtain a quote from our pool of lenders for 36 month loans. This system will  take the form of a command-line application.  

You will be provided with a file containing a list of all the offers being made  by the lenders within the system in CSV format, see the example market.csv file  provided alongside this specification.

  You should strive to provide as low a rate to the borrower as is possible to  ensure that Zopa's quotes are as competitive as they can be against our  competitors'. 

You should also provide the borrower with the details of the  monthly repayment amount and the total repayment amount. 

 Repayment amounts should be displayed to 2 decimal places and the rate of the  loan should be displayed to one decimal place. 
  
Borrowers should be able to request a loan of any £100 increment between £1000  and £15000 inclusive.

If the market does not have sufficient offers from  lenders to satisfy the loan then the system should inform the borrower that it  is not possible to provide a quote at that time.
 
  The application should take arguments in the form:  

    cmd> [application] [market_file] [loan_amount]    

Example: 
    
    cmd> quote.exe market.csv 1500  
    
The application should produce output in the form:     
 
    cmd> [application] [market_file] [loan_amount]     
    Requested amount: £XXXX 
    Rate: X.X%     
    Monthly repayment: £XXXX.XX     
    Total repayment: £XXXX.XX  
     
Example:  	
 
    cmd> quote.exe market.csv 1000 	
    Requested amount: £1000 	
    Rate: 7.0% 	
    Monthly repayment: £30.78 	
    Total repayment: £1108.10  

## Remarks    
- We do not mind what language you chose for your implementation  
- The monthly and total repayment should use monthly compounding interest  
- We will review your code and run it against some other test cases to see how  it handles them 
- If you have any questions then don't hesitate to contact us
