BEGIN
  EXECUTE IMMEDIATE 'DROP TABLE Transactions';
EXCEPTION WHEN OTHERS THEN NULL; END;
/

BEGIN
  EXECUTE IMMEDIATE 'DROP TABLE Loans';
EXCEPTION WHEN OTHERS THEN NULL; END;
/

BEGIN
  EXECUTE IMMEDIATE 'DROP TABLE Accounts';
EXCEPTION WHEN OTHERS THEN NULL; END;
/

BEGIN
  EXECUTE IMMEDIATE 'DROP TABLE Customers';
EXCEPTION WHEN OTHERS THEN NULL; END;
/

BEGIN
  EXECUTE IMMEDIATE 'DROP TABLE Employees';
EXCEPTION WHEN OTHERS THEN NULL; END;
/

-- Create tables
CREATE TABLE Customers (
    CustomerID NUMBER PRIMARY KEY,
    Name VARCHAR2(100),
    DOB DATE,
    Balance NUMBER,
    LastModified DATE
);

CREATE TABLE Accounts (
    AccountID NUMBER PRIMARY KEY,
    CustomerID NUMBER,
    AccountType VARCHAR2(20),
    Balance NUMBER,
    LastModified DATE,
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);

CREATE TABLE Transactions (
    TransactionID NUMBER PRIMARY KEY,
    AccountID NUMBER,
    TransactionDate DATE,
    Amount NUMBER,
    TransactionType VARCHAR2(10),
    FOREIGN KEY (AccountID) REFERENCES Accounts(AccountID)
);

CREATE TABLE Loans (
    LoanID NUMBER PRIMARY KEY,
    CustomerID NUMBER,
    LoanAmount NUMBER,
    InterestRate NUMBER,
    StartDate DATE,
    EndDate DATE,
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);

CREATE TABLE Employees (
    EmployeeID NUMBER PRIMARY KEY,
    Name VARCHAR2(100),
    Position VARCHAR2(50),
    Salary NUMBER,
    Department VARCHAR2(50),
    HireDate DATE
);

-- Insert sample data
INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified) VALUES (1, 'John Doe', TO_DATE('1985-05-15', 'YYYY-MM-DD'), 1000, SYSDATE);
INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified) VALUES (2, 'Jane Smith', TO_DATE('1990-07-20', 'YYYY-MM-DD'), 15000, SYSDATE);
INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified) VALUES (3, 'Old Customer', TO_DATE('1950-01-01', 'YYYY-MM-DD'), 500, SYSDATE);

INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified) VALUES (1, 1, 'Savings', 1000, SYSDATE);
INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified) VALUES (2, 2, 'Checking', 15000, SYSDATE);
INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified) VALUES (3, 3, 'Savings', 500, SYSDATE);

INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType) VALUES (1, 1, SYSDATE, 200, 'Deposit');
INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType) VALUES (2, 2, SYSDATE, 300, 'Withdrawal');

INSERT INTO Loans (LoanID, CustomerID, LoanAmount, InterestRate, StartDate, EndDate) VALUES (1, 1, 5000, 5, SYSDATE, ADD_MONTHS(SYSDATE, 60));
INSERT INTO Loans (LoanID, CustomerID, LoanAmount, InterestRate, StartDate, EndDate) VALUES (2, 3, 10000, 6, SYSDATE, SYSDATE + 20);

INSERT INTO Employees (EmployeeID, Name, Position, Salary, Department, HireDate) VALUES (1, 'jasna', 'Manager', 70000, 'HR', TO_DATE('2015-06-15', 'YYYY-MM-DD'));
INSERT INTO Employees (EmployeeID, Name, Position, Salary, Department, HireDate) VALUES (2, 'Ahmed', 'Developer', 60000, 'IT', TO_DATE('2017-03-20', 'YYYY-MM-DD'));

COMMIT;

SET SERVEROUTPUT ON SIZE 1000000;

-- Scenario 1: ProcessMonthlyInterest procedure
CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest AS
BEGIN
  UPDATE Accounts
  SET Balance = Balance + (Balance * 0.01),
      LastModified = SYSDATE
  WHERE AccountType = 'Savings';

  COMMIT;
  DBMS_OUTPUT.PUT_LINE('Monthly interest applied to all savings accounts.');
END;
/
-- --------------------------------------------------

-- Scenario 2: UpdateEmployeeBonus procedure
CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus (
  p_department IN VARCHAR2,
  p_bonus_percent IN NUMBER
) AS
BEGIN
  UPDATE Employees
  SET Salary = Salary + (Salary * p_bonus_percent / 100)
  WHERE Department = p_department;

  COMMIT;
  DBMS_OUTPUT.PUT_LINE('Salaries updated for department: ' || p_department);
END;
/
-- --------------------------------------------------

-- Scenario 3: TransferFunds procedure
CREATE OR REPLACE PROCEDURE TransferFunds (
  p_from_account IN NUMBER,
  p_to_account IN NUMBER,
  p_amount IN NUMBER
) AS
  v_from_balance NUMBER;
BEGIN
  -- Check if source account has sufficient balance
  SELECT Balance INTO v_from_balance FROM Accounts WHERE AccountID = p_from_account;

  IF v_from_balance < p_amount THEN
    RAISE_APPLICATION_ERROR(-20001, 'Insufficient funds in source account.');
  END IF;

  -- Debit source account
  UPDATE Accounts
  SET Balance = Balance - p_amount,
      LastModified = SYSDATE
  WHERE AccountID = p_from_account;

  -- Credit destination account
  UPDATE Accounts
  SET Balance = Balance + p_amount,
      LastModified = SYSDATE
  WHERE AccountID = p_to_account;

  COMMIT;

  DBMS_OUTPUT.PUT_LINE('Transferred ' || p_amount || ' from account ' || p_from_account || ' to account ' || p_to_account);
EXCEPTION
  WHEN NO_DATA_FOUND THEN
    DBMS_OUTPUT.PUT_LINE('Account not found.');
  WHEN OTHERS THEN
    DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
    ROLLBACK;
END;
/
-- --------------------------------------------------

-- Test calls to procedures
BEGIN
  -- Process monthly interest for savings accounts
  ProcessMonthlyInterest;

  -- Update employee bonus by 5% for IT department
  UpdateEmployeeBonus('IT', 5);

  -- Transfer 200 from account 1 to account 2
  TransferFunds(1, 2, 200);
END;
/

-- Print accounts balances (after updates)
BEGIN
  DBMS_OUTPUT.PUT_LINE(CHR(10) || '--- Accounts Balances After Updates ---');
  DBMS_OUTPUT.PUT_LINE(RPAD('AccountID',10) || RPAD('CustomerID',12) || RPAD('Type',10) || RPAD('Balance',15) || 'LastModified');
  DBMS_OUTPUT.PUT_LINE(RPAD('-', 10, '-') || RPAD('-', 12, '-') || RPAD('-', 10, '-') || RPAD('-', 15, '-') || RPAD('-', 20, '-'));

  FOR rec IN (
    SELECT AccountID, CustomerID, AccountType, Balance, TO_CHAR(LastModified, 'DD-MON-YYYY HH24:MI:SS') AS LastModified
    FROM Accounts ORDER BY AccountID
  ) LOOP
    DBMS_OUTPUT.PUT_LINE(
      RPAD(rec.AccountID,10) ||
      RPAD(rec.CustomerID,12) ||
      RPAD(rec.AccountType,10) ||
      RPAD(TO_CHAR(rec.Balance, '9999990.00'),15) ||
      rec.LastModified
    );
  END LOOP;
END;
/

-- Print employees salaries (after bonus)
BEGIN
  DBMS_OUTPUT.PUT_LINE(CHR(10) || '--- Employees Salaries After Bonus ---');
  DBMS_OUTPUT.PUT_LINE(RPAD('EmployeeID',12) || RPAD('Name',20) || RPAD('Department',15) || 'Salary');
  DBMS_OUTPUT.PUT_LINE(RPAD('-', 12, '-') || RPAD('-', 20, '-') || RPAD('-', 15, '-') || RPAD('-', 10, '-'));

  FOR rec IN (
    SELECT EmployeeID, Name, Department, Salary FROM Employees ORDER BY EmployeeID
  ) LOOP
    DBMS_OUTPUT.PUT_LINE(
      RPAD(rec.EmployeeID,12) ||
      RPAD(rec.Name,20) ||
      RPAD(rec.Department,15) ||
      TO_CHAR(rec.Salary, '9999990.00')
    );
  END LOOP;
END;