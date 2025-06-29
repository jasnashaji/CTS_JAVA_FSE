-- Drop and recreate all tables
BEGIN
  EXECUTE IMMEDIATE 'DROP TABLE Transactions';
  EXECUTE IMMEDIATE 'DROP TABLE Accounts';
  EXECUTE IMMEDIATE 'DROP TABLE Loans';
  EXECUTE IMMEDIATE 'DROP TABLE Employees';
  EXECUTE IMMEDIATE 'DROP TABLE Customers';
EXCEPTION
  WHEN OTHERS THEN NULL;
END;
/

-- Create tables
CREATE TABLE Customers (
    CustomerID NUMBER PRIMARY KEY,
    Name VARCHAR2(100),
    DOB DATE,
    Balance NUMBER,
    LastModified DATE,
    IsVIP CHAR(1)
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
INSERT INTO Customers VALUES (1, 'JASNA', TO_DATE('1950-05-15', 'YYYY-MM-DD'), 1000, SYSDATE, NULL);
INSERT INTO Customers VALUES (2, 'AHMED', TO_DATE('1990-07-20', 'YYYY-MM-DD'), 15000, SYSDATE, NULL);
INSERT INTO Customers VALUES (3, 'JES', TO_DATE('1940-01-01', 'YYYY-MM-DD'), 500, SYSDATE, NULL);

INSERT INTO Loans VALUES (1, 1, 5000, 5, SYSDATE, SYSDATE + 60);
INSERT INTO Loans VALUES (2, 3, 2000, 4, SYSDATE, SYSDATE + 20);

-- Logic and Formatted Output
SET SERVEROUTPUT ON;
BEGIN
  -- Update VIP Status
  FOR c IN (SELECT CustomerID, Balance FROM Customers) LOOP
    UPDATE Customers
    SET IsVIP = CASE WHEN c.Balance > 10000 THEN 'Y' ELSE 'N' END
    WHERE CustomerID = c.CustomerID;
  END LOOP;

  -- Box-style Output
  DBMS_OUTPUT.PUT_LINE('Output:');
  DBMS_OUTPUT.PUT_LINE('');
  
  -- Loan Reminders
  DBMS_OUTPUT.PUT_LINE('Loan Reminders Due in Next 30 Days:');
  DBMS_OUTPUT.PUT_LINE(RPAD('LoanID', 10) || RPAD('Customer Name', 20) || 'Due Date');
  DBMS_OUTPUT.PUT_LINE(RPAD('-', 40, '-'));

  FOR r IN (
    SELECT l.LoanID, c.Name, TO_CHAR(l.EndDate, 'DD-MON-YYYY') AS DueDate
    FROM Loans l
    JOIN Customers c ON l.CustomerID = c.CustomerID
    WHERE l.EndDate BETWEEN SYSDATE AND SYSDATE + 30
  ) LOOP
    DBMS_OUTPUT.PUT_LINE(RPAD(r.LoanID, 10) || RPAD(r.Name, 20) || r.DueDate);
  END LOOP;

  DBMS_OUTPUT.PUT_LINE('');
  
  -- VIP Customers
  DBMS_OUTPUT.PUT_LINE('Customer VIP Status:');
  DBMS_OUTPUT.PUT_LINE(RPAD('CustomerID', 12) || RPAD('Name', 20) || RPAD('Balance', 10) || 'IsVIP');
  DBMS_OUTPUT.PUT_LINE(RPAD('-', 50, '-'));

  FOR c IN (SELECT CustomerID, Name, Balance, IsVIP FROM Customers) LOOP
    DBMS_OUTPUT.PUT_LINE(
      RPAD(c.CustomerID, 12) || RPAD(c.Name, 20) || RPAD(c.Balance, 10) || c.IsVIP
    );
  END LOOP;

  COMMIT;
END;
/
