import React from 'react';

const courses = [
  { id: 1, name: 'Angular', date: '4/5/2021' },
  { id: 2, name: 'React', date: '6/3/2021' },
];

function CourseDetails() {
  return (
    <div style={{ flex: 1, padding: '10px' }}>
      <h3>Course Details</h3>
      {courses.map(course => (
        <div key={course.id}>
          <h4>{course.name}</h4>
          <p>{course.date}</p>
        </div>
      ))}
    </div>
  );
}

export default CourseDetails;
