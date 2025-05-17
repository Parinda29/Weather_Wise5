<%@ page import="java.util.List" %>
<%@ page import="com.weatherwise.model.Users_LocationModel" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Locations</title>
    <link rel="stylesheet" href="css/user_location.css">
</head>
<body>
    <div class="container">
        <h2>User - Location Mapping</h2>

        <%
            List<Users_LocationModel> userLocations = (List<Users_LocationModel>) request.getAttribute("userLocations");
            if (userLocations != null && !userLocations.isEmpty()) {
        %>
            <table>
                <tr>
                    <th>User ID</th>
                    <th>Location ID</th>
                </tr>
                <%
                    for (Users_LocationModel ul : userLocations) {
                %>
                    <tr>
                        <td><%= ul.getUserId() %></td>
                        <td><%= ul.getLocationId() %></td>
                    </tr>
                <%
                    }
                %>
            </table>
        <%
            } else {
        %>
            <p>No user-location records found.</p>
        <%
            }
        %>
    </div>
</body>
</html>
