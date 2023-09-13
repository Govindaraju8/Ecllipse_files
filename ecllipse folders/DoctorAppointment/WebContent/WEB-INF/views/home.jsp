<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h2>Doctor Information Form</h2>
   
 <h1>Admin Page</h1>
<form action="raju" method="POST">
    <label for="doctorSelect">Select Doctor:</label>
    <select id="doctorSelect">
        <option value="101" name="doc1">Doctor 1</option>
        <option value="102" name="doc2">Doctor 2</option>
    </select>

    <h2>Set Availability</h2>
    <div id="availabilityForm">

        <table>
            <tr>
                <td><input type="checkbox" name="days" id="sun" value="1" class="wkdys">Sunday</td>
            </tr>
            <tr>
                <td><input type="checkbox" name="days" id="mon" value="2" class="wkdys">Monday</td>
                <td><input type="checkbox" name="days" id="tue" value="3" class="wkdys">Tuesday</td>
            </tr>
            <tr>
                <td><input type="checkbox" name="days" id="wed" value="4" class="wkdys">Wednesday</td>
                <td><input type="checkbox" name="days" id="thu" value="5" class="wkdys">Thursday</td>
            </tr>
            <tr>
                <td><input type="checkbox" name="days" id="fri" value="6" class="wkdys">Friday</td>
                <td><input type="checkbox" name="days" id="sat" value="7 " class="wkdys">Saturday</td>
            </tr>
        </table><br><br>

        <label>From: <input type="time" name="fromTime" value="09:00"></label>
        <label>To: <input type="time" name="toTime" value="12:00"></label><br><br>

        <button onclick="addAvailability()">Add Availability</button>
    </div>

    <h2>Selected Availability</h2>
    <table border="1">
        <thead>
            <tr>
                <th>Doctor</th>
                <th>Day</th>
                <th>Time Slotfrom</th>
                <th>Time Slotto</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody id="selectedAvailability"></tbody>
    </table><br>
    <button>Save</button>

    <script>
        const availabilityData = {}; 

        function isTimeSlotOverlapping(doctor, day, fromTime, toTime) {
            if (!availabilityData[doctor] || !availabilityData[doctor][day]) return false;

            for (const slot of availabilityData[doctor][day]) {
                const [existingFrom, existingTo] = slot.split('-').map(time => time.trim());
                if ((fromTime >= existingFrom && fromTime < existingTo) || (toTime > existingFrom && toTime <= existingTo)) {
                    return true;
                }
            }
            return false;
        }

        function deleteRow(row) {
            const doctorCell = row.cells[0].textContent;
            const dayCell = row.cells[1].textContent;
            const timeSlotCell = row.cells[2].textContent;

            if (availabilityData[doctorCell] && availabilityData[doctorCell][dayCell]) {
                availabilityData[doctorCell][dayCell] = availabilityData[doctorCell][dayCell].filter(
                    slot => slot !== timeSlotCell
                );
            }

            row.parentNode.removeChild(row);
        }


        function addAvailability() {
            const doctorSelect = document.getElementById('doctorSelect');
            const selectedDoctor = doctorSelect.value;

            const daysCheckboxes = document.querySelectorAll('input[name="days"]:checked');
            const fromTime = document.querySelector('input[name="fromTime"]').value;
            const toTime = document.querySelector('input[name="toTime"]').value;

            const selectedAvailabilityTable = document.getElementById('selectedAvailability');

            daysCheckboxes.forEach(dayCheckbox => {
                const day = dayCheckbox.value;

                if (!isTimeSlotOverlapping(selectedDoctor, day, fromTime, toTime)) {
                    const row = selectedAvailabilityTable.insertRow();
                    const doctorCell = row.insertCell(0);
                    const dayCell = row.insertCell(1);
                    const timeSlotCell1 = row.insertCell(2);
                    const timeSlotCell2= row.insertCell(3);
                    const actionCell = row.insertCell(4);

                    doctorCell.textContent = selectedDoctor;
                    dayCell.textContent = day;
                    timeSlotCell1.textContent = fromTime;
                    timeSlotCell2.textContent = toTime;

                    const deleteButton = document.createElement('button');
                    deleteButton.textContent = 'Delete';
                    deleteButton.onclick = function () {
                        deleteRow(row);
                    };

                    actionCell.appendChild(deleteButton);

                    if (!availabilityData[selectedDoctor]) {
                        availabilityData[selectedDoctor] = {};
                    }

                    if (!availabilityData[selectedDoctor][day]) {
                        availabilityData[selectedDoctor][day] = [];
                    }

                    availabilityData[selectedDoctor][day].push(`${fromTime} - ${toTime}`);
                } else {
                    window.alert("Time slot overlaps with existing availability.");
                }
            });
        }
        console.log(availabilityData);
    </script>
        
   
    </form>
</body>
</html>