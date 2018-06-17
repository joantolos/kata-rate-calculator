# Kata Rate Calculator

This is a technical test from the company Zopa.

## Running the code

To run the build with test coverage:

    ./gradlew clean build jacocoTestReport
    
You will find the coverage report on _kata-rate-calculator/build/jacoco/reports/coverage/html/index.html_

You can run the RateCalculatorApp class on your IDE or by console:

    ./gradlew fatJar
    java -jar build/libs/kata-rate-calculator-all-1.0.jar marketData.csv 1000
    
## Monthly compounding interest formula

I've done some research about the compounding interest formula and I found this:

<p>
The formula for annual compound interest, including principal sum, is:<br><br>
<b>A = P (1 + r/n)<sup> (nt)</sup></b>
</p>

<p>
<b>Where:</b>
</p>
<p>
<b>A</b> = the future value of the investment/loan, including interest<br>
<b>P</b> = the principal investment amount (the initial deposit or loan amount)<br>
<b>r</b> = the annual interest rate (decimal)<br>
<b>n</b> = the number of times that interest is compounded per year<br>
<b>t</b> = the number of years the money is invested or borrowed for
</p>

This formula has been extracted from the following site:

* https://www.thecalculatorsite.com/articles/finance/compound-interest-formula.php

The rate to inject on the formula is a median of the rate of each borrower that leans the money. So, if the client needs 1000 and the two first borrowers offer:

480 at 0.069

and

520 at 0.071

then the rate is (0.069 + 0.071) / 2

If the amount is larger, more borrowers and rates will be involved.

## Test Instructions

There is a need for a rate calculation system allowing prospective borrowers to  obtain a quote from our pool of lenders for 36 month loans. This system will  take the form of a command-line application.  

You will be provided with a file containing a list of all the offers being made  by the lenders within the system in CSV format, see the example market.csv file  provided alongside this specification.

  You should strive to provide as low a rate to the borrower as is possible to  ensure that Zopa's quotes are as competitive as they can be against our  competitors'. 

You should also provide the borrower with the details of the  monthly repayment amount and the total repayment amount. 

 Repayment amounts should be displayed to 2 decimal places and the rate of the  loan should be displayed to one decimal place. 
  
Borrowers should be able to request a loan of any £100 increment between £1000  and £15000 inclusive.

If the market does not have sufficient offers from  lenders to satisfy the loan then the system should inform the borrower that it  is not possible to provide a quote at that time.
 
   The application should take arguments in the form:  
 ```
     cmd> [application] [market_file] [loan_amount]  
 ```    
 Example: 
 ```     
     cmd> quote.exe market.csv 1500  
 ```
 The application should produce output in the form: 
 ```     
     cmd> [application] [market_file] [loan_amount]     
     Requested amount: £XXXX 
     Rate: X.X%     
     Monthly repayment: £XXXX.XX     
     Total repayment: £XXXX.XX  
 ```
 Example:  	
 ```
     cmd> quote.exe market.csv 1000 	
     Requested amount: £1000 	
     Rate: 7.0% 	
     Monthly repayment: £30.78 	
     Total repayment: £1108.10  
 ```
## Remarks    
- We do not mind what language you chose for your implementation  
- The monthly and total repayment should use monthly compounding interest  
- We will review your code and run it against some other test cases to see how  it handles them 
- If you have any questions then don't hesitate to contact us
