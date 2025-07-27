import React from "react";
import CohortDetails from "./components/CohortDetails";

function App() {
  const cohorts = [
    {
      name: "INTADMDF10 - .NET FSD",
      startDate: "22-Feb-2022",
      status: "Scheduled",
      coach: "Anshma",
      trainer: "Jojo Jose",
    },
    {
      name: "ADM21JF014 - Java FSD",
      startDate: "10-Sep-2021",
      status: "Ongoing",
      coach: "Apoorv",
      trainer: "Elisa Smith",
    },
    {
      name: "CDBEJ21025 - Java FSD",
      startDate: "24-Dec-2021",
      status: "Ongoing",
      coach: "Asthma",
      trainer: "John Doe",
    },
  ];

  return (
    <div>
      <h2>Cohorts Details</h2>
      {cohorts.map((cohort, index) => (
        <CohortDetails key={index} cohort={cohort} />
      ))}
    </div>
  );
}

export default App;
