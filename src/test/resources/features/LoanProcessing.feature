Feature: Loan Application Processing
  As a ParaBank customer
  I want to apply for a personal loan
  So that I can borrow money based on my current account standing

  @UI @Regression
  Scenario Outline: System dynamically approves or denies loan applications
    Given the user is logged in with username "john" and password "demo"
    And the user navigates to the Request Loan page
    When the user requests a loan amount of "<loan_amount>" with a "<down_payment>" down payment
    Then the loan provider status should be "<status>"

    Examples:
      | loan_amount | down_payment | status   |
      | 1000        | 100          | Approved |
      | 5000000     | 1            | Denied   |